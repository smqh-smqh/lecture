package pku;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Collection;
import java.util.List;

/**
 * 这是一个简单的基于内存的实现，以方便选手理解题意； 实际提交时，请维持包名和类名不变，把方法实现修改为自己的内容；
 */
public class DefaultQueueStoreImpl extends QueueStore {

    public static Collection<byte[]> EMPTY = new ArrayList<byte[]>();
    public Map<String, Queue>[] queueMaps; // 记录队列在文件中的位置的详细信息的列表

    public AtomicLong L2BufferWritten; // 当前已经写入了多少块
    private static ByteBuffer flushBuffer = ByteBuffer.allocate(512 * 1024 * 1024); // 64K,二级缓存

    private static final int FILE_SIZE = 1; // 几个文件
    private static final int FILE_INDEX = 1; // 表示第几个文件

    private FileChannel[] channels;
    private AtomicLong[] wrotePositions; // 每个文件当前的写位置, 指针数组
    public static final String dir = "data/";

    public DefaultQueueStoreImpl() {
        channels = new FileChannel[FILE_SIZE];
        wrotePositions = new AtomicLong[FILE_SIZE];
        queueMaps = new ConcurrentHashMap[FILE_INDEX];
        for (int i = 0; i < FILE_SIZE; i++) {
            RandomAccessFile memoryMappedFile = null;
            try {
                memoryMappedFile = new RandomAccessFile(dir + "all_" + i + ".data", "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            queueMaps[i] = new ConcurrentHashMap<>();
            this.channels[i] = memoryMappedFile.getChannel();
            this.wrotePositions[i] = new AtomicLong(0L);
        }
    }

    /**
     * 这是一个同步方法 根据队列名把要写入的字节数组写入一个文件，后面会改进的
     */
    public void put(String queueName, byte[] message) {
        int index = Math.abs(queueName.hashCode()) % FILE_SIZE; // 由hashCode决定翻到哪一个文件里里面去
        Queue queue;
        queue = queueMaps[index].get(queueName);
        AtomicInteger absBlock = new AtomicInteger(0);
        if (queue == null) {
            synchronized (this) { // This 指代的是 QueueStore 实例
                queue = queueMaps[index].get(queueName);
                if (queue == null) {
                    queue = new Queue(queueName, channels[index], wrotePositions[index], flushBuffer, absBlock);
                    queueMaps[index].put(queueName, queue);
                }
            }
        }
        queue.put(message);
    }

    /**
     *
     */
    public Collection<byte[]> get(String queueName, long offset, long num) {

        int index = Math.abs(queueName.hashCode()) % FILE_SIZE;
        Queue queue = queueMaps[index].get(queueName);
        return queue.get(offset, num);
    }

}

class Queue {
    public final static int bufferBytes = 1024*2;
    private ByteBuffer buffer = ByteBuffer.allocateDirect(bufferBytes);
    private static ByteBuffer flushBuffer;

    private volatile boolean firstGet = true; // 扫尾工作使用的变量，因为无法判断是否是最后一次put
    private int msgSize = 0; //

    final static int MSG_CAPACITY = 1000; // 假设一个队列内最大的消息数量默认值，如果超过这个值，代码中有动态扩容
    private static final int BLOCK_NUM = 10;
    private long[] blocksOffset = new long[BLOCK_NUM]; // 每一个块的在文件中的起始位置
    private int[] queueIndexes = new int[BLOCK_NUM];
    private int currentBlock = 0; // 当前块号

//	private AtomicInteger  currentBlockAbs; // 当前块号
//	private int[] blocksAbs = new int[BLOCK_NUM]; //绝对块号

    private FileChannel channel;
    private AtomicLong wrotePosition; // 当前文件FileChannel的写位置，在put阶段
    private int firstIndex = 0;
    String name;

    public Queue(String name, FileChannel channel, AtomicLong wrotePosition, ByteBuffer flushBuffer, AtomicInteger currentBlockAbs ) {
        this.name = name;
        this.channel = channel;
        this.wrotePosition = wrotePosition;
        this.flushBuffer = flushBuffer;
//		this.currentBlockAbs = currentBlockAbs;
    }


    public void put(byte[] message) {
        // 写入磁盘
        if (buffer.remaining() < message.length + 2 + 1) { //tricky put TODO
            flush();
        }
        buffer.putShort((short) message.length);
        buffer.put(message);
        buffer.put((byte)-1); // 留作校验
        msgSize++;
    }

    private void flush() {
        buffer.flip(); // 操作这些满了的区域了
        long writePosition=0;
        // 全局文件写入的位置而且是定长写入
//		synchronized(wrotePosition) {
        writePosition = wrotePosition.getAndAdd(bufferBytes);
//		blocksAbs[currentBlock] = currentBlockAbs.getAndIncrement();
//		}

        if(writePosition%bufferBytes != 0) {
            throw new RuntimeException("writePosition 有问题！！");
        }
        try {
            // 指定位置写入，中间有空隙,就算有争用也不会覆盖
            channel.write(buffer, writePosition);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.clear();

/////////////////顺序写，但是要锁住， 不会不影响并发呢？////////////////////////////////
        // 必须争用 wrotePosition， 保证 writePosition 顺序和
        // 实际落盘顺序一致
//		synchronized(wrotePosition) {
//			writePosition = wrotePosition.getAndAdd(buffer.remaining()); // 全局文件写入的位置
//			try {
//				channel.write(flushBuffer, writePosition); //绝对位置写
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
/////////////////////////////////////////////////

        blocksOffset[currentBlock] = writePosition;
        queueIndexes[currentBlock] = this.firstIndex;

/////////////////////////////////////////////////// 检查索引块
//		if(blocksAbs[currentBlock] * bufferBytes != writePosition) {
//			System.out.println(name + "  currentBlock是：" +  currentBlock);
//			System.out.println(name + "  currentBlockAbs是：" +  blocksAbs[currentBlock]);
//			System.out.println(name + "  writePosition是：" +  writePosition);
//			throw new RuntimeException("writePosition 有问题！XXX！");
//
//		}
/////////////////////////////////////////////////

        this.firstIndex = this.msgSize;
        currentBlock++;
        if (currentBlock > blocksOffset.length * 0.7) {
            blocksOffset = copyOf(blocksOffset, blocksOffset.length * 2);
            queueIndexes = copyOf(queueIndexes, queueIndexes.length * 2);
        }
    }

    public long[] copyOf(long[] original, int newLength) {
        long[] copy = new long[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public synchronized Collection<byte[]> get(long offset, long num) {

        synchronized(this) {
            if (firstGet) {
                flushForGet();
                firstGet = false;
            }
        }

        if (offset > msgSize - 1) {
            return DefaultQueueStoreImpl.EMPTY;
        }

        int startIndex = (int) offset;
        int endIndex = Math.min(startIndex + (int) num - 1, msgSize - 1);
        List<byte[]> result = new ArrayList<>();

        int startBlock = -1;
        int endBlock = -1;

        int left = 0;
        int right = currentBlock - 1;
        startBlock = indexSearch(queueIndexes, startIndex);
        endBlock = indexSearch(queueIndexes, endIndex);

        if (startBlock == -1 || endBlock == -1) {
            throw new RuntimeException("未找到对应的数据块");
        }
        for (int j = startBlock; j <= endBlock; j++) {
            int blockStartIndex= queueIndexes[j];
            buffer.clear();
            try {
                // 从绝对位置开始读, buffer是 特定大小的，所以写入的时候也要按定长写入，否则就把后面的侵占了，就完蛋了！
                channel.read(buffer, this.blocksOffset[j]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.flip();

            int nMsg = queueIndexes[j + 1] - queueIndexes[j];

            for (int i = 0; i < nMsg; i++) {
                if (startIndex <= blockStartIndex + i && blockStartIndex + i <= endIndex) {
                    Short len = buffer.getShort();
                    byte[] bytes = new byte[len];
                    buffer.get(bytes);
                    if(buffer.get() != -1) { // 如果取出来的消息后面不是 -1， 说明取出来的消息有问题！
                        throw new RuntimeException("验证失败！！");
                    }
                    result.add(bytes);
                } else if (blockStartIndex + i > endIndex) {
                    break;
                } else {
                    Short len = buffer.getShort();
                    byte[] bytes = new byte[len+1];
                    buffer.get(bytes);
                }
            }

        }
        return result;
    }

    private int indexSearch(int[] queueIndexes, int index) {
        for(int i=0; i<queueIndexes.length; i++) {
            if(index >= queueIndexes[i] && index < queueIndexes[i+1]) {
                return i;
            }
        }
        return -1;
    }

    private void flushForGet() {
        flush();
        //补足最后一个元素的信息
        queueIndexes[currentBlock] = this.firstIndex;
    }

    /**
     * 等到所有的 put 线程执行完，这个线程仅执行一次
     */
    private void lastFlush() {

        if (flushBuffer.position() > 0) {  // flushBuffer里还有没写的字节
            flushBuffer.flip();
            try {
                channel.write(flushBuffer);
                flushBuffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
