package algorithm;


/**
 * @Title: BitMap
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-16 18:57
 * @Description:
 *  实现BitMap
 *  注：这个bitMap的index是从1开始的
 *  参见：https://my.oschina.net/freelili/blog/2885263
 *  https://www.jianshu.com/p/9e7f8f33a61a?from=singlemessage
 */

public class BitMap {

    private long length;
    private static int[] bitsMap;

    //构造函数中传入数据中的最大值
    public BitMap(long length) {
        this.length = length;
        /**
         * 根据长度算出，所需数组大小
         * 当 length%32=0 时大小等于
         * = length/32
         * 当 length%32>0 时大小等于
         * = length/32+l
         */
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }

    public int getBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value "+index+" is  illegal!");
        }
        int intData = bitsMap[(int) ((index - 1) >> 5)];
        //System.out.println("intData:"+Integer.toBinaryString(intData));
        int offset = (int) ((index - 1) & 31);
        //System.out.println("offset:"+Integer.toBinaryString(offset));
        //System.out.println("intData >> offset:"+Integer.toBinaryString(intData >> offset));
        //System.out.println("intData >> offset & 0x01:"+Integer.toBinaryString(intData >> offset & 0x01));
        return intData >> offset & 0x01;
    }


    public void setBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value "+index+" is  illegal!");
        }
        // 求出该index - 1所在bitMap的下标,等价于"(index - 1)/5"
        int belowIndex = (int) ((index - 1) >> 5);
        // 求出该值的偏移量(求余),等价于"(index - 1)%31"
        int offset = (int) ((index - 1) & 31);
        int inData = bitsMap[belowIndex];
        /**
         * 等价于
         * int bits = bitsMap[index];
         * bitsMap[index]=bits| BIT_VALUE[offset];
         * 例如,n=4时,设置byte第4个位置为1 （从1开始计数，bitsMap[0]可代表的数为：0~31，从左到右每一个bit位表示一位数）
         * bitsMap[0]=00000000 00000000 00000000 00000000  |  00000000 00000000 00000000 00010000=00000000 00000000 00000000 00000000 00010000
         */
        bitsMap[belowIndex] = inData | (0x01 << offset);
    }

    /**
     * 判断该数对应位是否为11
     * @param index
     * @return
     */
    public int getBit_(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value "+index+" is  illegal!");
        }
        int intData = bitsMap[(int) ((index - 1) >> 5)];
        int offset = (int) ((index - 1) & 31);
        return intData >> offset & 0x11;
    }

    /**
     * 将该数对应位置为11
     * @param index
     */
    public void setBit_(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value "+index+" is  illegal!");
        }
        // 求出该index - 1所在bitMap的下标
        int belowIndex = (int) ((index - 1) >> 5);
        // 求出该值的偏移量(求余)
        int offset = (int) ((index - 1) & 31);
        int inData = bitsMap[belowIndex];
        bitsMap[belowIndex] = inData | (0x11 << offset);
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(32);
        bitMap.setBit(1);
        System.out.println(bitMap.getBit(1));
        System.out.println(bitMap.getBit(89));
    }
}
