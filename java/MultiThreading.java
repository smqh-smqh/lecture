import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/***
 * ��ʵ��һ������IO�Ķ��̶߳�������
 * ��������߹�ͬ����messageSendNum����Ϣ����������ط�������queue��
 * ��������߸��������У��10%��queue��ȷ����Ϣ����������û��ȱʧ
 * ���ͽ׶����֮���ٿ�ʼУ��׶�
 * 5���ط����
 */
public class MultiThreading {

	public static Collection<byte[]> EMPTY = new ArrayList<byte[]>();

	public static void main(String[] args) throws Exception {
		// ���е�����
		int queueNum = 100;
		// �������ܵ���Ϣ������
		int messageSendNum = 20 * 10000;
		// ������У��Ĵ���
		int checkQueueNum = queueNum/10;

		long startTime = System.currentTimeMillis();

		ConcurrentMap<String, AtomicInteger> queueNumMap = new ConcurrentHashMap<>();
		for (int i = 0; i < queueNum; i++) {
			queueNumMap.put("Queue-" + i, new AtomicInteger(0));
		}

		// ������
		int sendTsNum = 4;
		// ��ǰ������Ϣ������
		AtomicLong sendCounter = new AtomicLong(0);
		QueueStore queueStore = new QueueStore();
		Thread[] sends = new Thread[sendTsNum];
		for (int i = 0; i < sendTsNum; i++) {
			sends[i] = new Thread(new Producer(queueStore, messageSendNum, sendCounter, queueNumMap));
		}
		for (int i = 0; i < sendTsNum; i++) {
			// �����߳�
			//**************************
			sends[i].start();
			//**************************
		}
		for (int i = 0; i < sendTsNum; i++) {
			//��֤�������߿�ʼ֮ǰ����������������
			//**************************
			sends[i].join();
			//**************************
		}
		System.out.println("End of production process");
		// ������
		int checkTsNum = 4;
		Thread[] checks = new Thread[checkTsNum];
		for (int i = 0; i < checkTsNum; i++) {
			checks[i] = new Thread(new Consumer(queueStore,  checkQueueNum, queueNumMap));
		}
		for (int i = 0; i < checkTsNum; i++) {
			checks[i].start();
		}
		for (int i = 0; i < checkTsNum; i++) {
			//��֤��ͳ������ʱ�俪ʼ֮ǰ����������������
			//**************************
			checks[i].join();
			//**************************
		}
		System.out.println("End of consumption process");
		long endTime = System.currentTimeMillis();
		// ͳ������ʱ��
		System.out.printf("cost time : [%.2f] s", (endTime - startTime + 0.1) / 1000);
	}
}

class QueueStore {

	public void put(String queueName, byte[] message) throws IOException {
		File dir = new File("data");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File("data/" + queueName);
		if (!file.exists()) {
			file.createNewFile();
		}
		// ����Ϣд�뵽ָ��queue�ļ�
		FileOutputStream out = new FileOutputStream(file,true);
		out.write(message.length);
		out.write(message);
		out.close();
	}

	public Collection<byte[]> get(String queueName, long offset, long num) throws NumberFormatException, IOException {
		File file = new File("data/" + queueName);
		if (!file.exists()) {
			return MultiThreading.EMPTY;
		}
		Collection<byte[]> result = new ArrayList<>();
		// ��ָ��queue�ļ��ж�ȡһϵ�е���Ϣ
		FileInputStream in = new FileInputStream(file);
		int msgLen = 0;
		while ((msgLen = in.read()) > 0 && num > 0) {
			byte[] byteArr = new byte[msgLen];
			in.read(byteArr);
			String msg = new String(byteArr);
			if (Long.valueOf(msg.split(" ")[1]) == offset) {
				result.add(byteArr);
				num--;
				offset++;
			}
		}
		in.close();
		return result;
	}
}

class Producer implements Runnable {

	QueueStore queueStore;
	int messageSendNum;
	AtomicLong sendCounter;
	ConcurrentMap<String, AtomicInteger> queueNumMap;

	public Producer(QueueStore queueStore, int messageSendNum, AtomicLong sendCounter,
			ConcurrentMap<String, AtomicInteger> queueNumMap) {
		//��ʼ����Ա����
		//**************************
		this.queueStore=queueStore;
		this.messageSendNum=messageSendNum;
		this.sendCounter=sendCounter;
		this.queueNumMap=queueNumMap;
		//**************************
	}

	@Override
	public void run() {
		long count;
		while ((count = sendCounter.getAndIncrement()) < messageSendNum) {
			try {
				String queueName = "Queue-" + count % queueNumMap.size();
				// ͬ������飬����ÿ��queueNumMap�е�AtomicInteger������
				//**************************
				synchronized(queueNumMap.get(queueName)) {
					queueStore.put(queueName,
							(queueName + " " + queueNumMap.get(queueName).getAndIncrement()).getBytes());
				}
				//**************************
			} catch (Throwable t) {
				t.printStackTrace();
				System.exit(-1);
			}
		}
	}
}

class Consumer implements Runnable {
	QueueStore queueStore;
	int checkQueueNum;
	AtomicLong checkTimeCounter;
	ConcurrentMap<String, AtomicInteger> queueNumMap;
	public Consumer(QueueStore queueStore, int checkQueueNum,  ConcurrentMap<String, AtomicInteger> queueNumMap) {
		this.queueStore = queueStore;
		this.checkQueueNum = checkQueueNum;
		this.queueNumMap = queueNumMap;
	}

	@Override
	public void run() {
		  Random random = new Random();
		  int queueNum = queueNumMap.size();
		  ConcurrentMap<String, AtomicInteger> offsets = new ConcurrentHashMap<>();
		  //��queueNumMap�������ȡ���ظ���10%queue����offsets��
          for (int j = 0; j < checkQueueNum; j++) {
              String queueName = "Queue-" + random.nextInt(queueNum);
              while (offsets.containsKey(queueName)) {
                  queueName = "Queue-" + random.nextInt(queueNum);
              }
              offsets.put(queueName, queueNumMap.get(queueName));
          }
		  ConcurrentMap<String, AtomicInteger> pullOffsets = new ConcurrentHashMap<>();
          for (String queueName: offsets.keySet()) {
              pullOffsets.put(queueName, new AtomicInteger(0));
          }
          while (pullOffsets.size() > 0) {
              try {
                  for (String queueName : pullOffsets.keySet()) {
                      int index = pullOffsets.get(queueName).get();
                      Collection<byte[]> msgs = queueStore.get(queueName, index, 10);
                      if (msgs != null && msgs.size() > 0) {
                          pullOffsets.get(queueName).getAndAdd(msgs.size());
                          for (byte[] msg : msgs) {
                        	  // У����Ϣ˳���Ƿ�����
                          	  String id = new String(msg).split(" ")[1];
                              if (!id.equals(String.valueOf(index++))) {
                              	  System.out.printf("Consume Check error:get %s should be %d",id,index-1);
                                  System.exit(-1);
                              }
                          }
                      }
					  //�������ͳ�����������Ƿ���ȷ
                      if (msgs == null || msgs.size() < 10) {
                          if (pullOffsets.get(queueName).get() != offsets.get(queueName).get()) {
                          	  System.out.printf(pullOffsets.get(queueName).get()+":"+offsets.get(queueName).get());
                              System.out.printf("Queue Number Error");
                              System.exit(-1);
                          }
                          pullOffsets.remove(queueName);
                      }
                  }
              } catch (Throwable t) {
                  t.printStackTrace();
                  System.exit(-1);
              }
          }

	}

}
