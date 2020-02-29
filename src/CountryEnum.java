public enum CountryEnum {

        ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"韩"),FIVE(5,"赵"),SIX(6,"魏");

    public Integer getRetCode() {
        return retCode;
    }

    private Integer retCode;

    public String getRetMessage() {
        return retMessage;
    }

    private String retMessage;
        CountryEnum(Integer retCode, String retMessage){
            this.retCode=retCode;
            this.retMessage=retMessage;
        }

        public static String ret(int index){
            CountryEnum[] myData=CountryEnum.values();
            for(CountryEnum ele:myData){
                if(index==ele.getRetCode()){
                    return ele.getRetMessage();
                }
            }
            return null;
        }

    public static void main(String[] args) {
        System.out.println(CountryEnum.ret(5));
    }
}
