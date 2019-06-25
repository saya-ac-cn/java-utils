package tree;

import java.io.*;
import java.time.Instant;
import java.util.*;

/**
 * @Title: HuffmanCodeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-24 20:13
 * @Description:
 * 哈夫曼编码
 */

public class HuffmanCodeUtil {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);


        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("加密后的密文="+Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes = decode(huffmanCodes,huffmanCodeBytes);
        System.out.println("解密后的明文="+new String(sourceBytes));
    }

    private static List<HuffmanCodeNode> getNodes(byte[] bytes){
        // 1 创建一个ArrayList
        ArrayList<HuffmanCodeNode> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数-map【key，value】
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b:bytes){
            Integer count = counts.get(b);
            if(count == null){
                // 第一次统计，值为1
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }

        // 把每一个键值对转成一个Node对象，并加入nodes集合
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new HuffmanCodeNode(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //通过list创建对应的哈夫曼树
    private static HuffmanCodeNode createHuffmanTree(List<HuffmanCodeNode> nodes){
        while (nodes.size() > 1){
            //排序，升序
            Collections.sort(nodes);
            //取出最小的节点和次小的节点
            HuffmanCodeNode left = nodes.get(0);
            HuffmanCodeNode right = nodes.get(1);
            //创建一个新的二叉树，它的根节点没有data，只有权值
            HuffmanCodeNode parent = new HuffmanCodeNode(null,left.getWeight()+right.getWeight());
            parent.left = left;
            parent.right = right;
            //删除取出的节点，防止重复处理
            nodes.subList(0,2).clear();
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //编写一个前序遍历的方法
    public static void preOrder(HuffmanCodeNode root) {
        if(root != null) { root.preOrder();
        }else{
            System.out.println("是空树，不能遍历~~");
        }
    }

    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    // 1、将哈夫曼编码存放在Map<Byte,String>形式
    static Map<Byte,String > huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(HuffmanCodeNode root) {
        if(root == null) {
            return null;
        }
        //处理 root 的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理 root 的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }


    /**
     * 功能:将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
     * @param node 传入结点
     * @param code 路径: 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(HuffmanCodeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将 code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if(node != null) {
            //如果 node == null 不处理
            // 判断当前 node 是叶子结点还是非叶子结点
            if(node.data == null) {
                //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //说明是一个叶子结点 //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * @描述
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-24
     * @修改人和其它信息
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<HuffmanCodeNode> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树
        //对应的赫夫曼编码(根据 赫夫曼树)
        HuffmanCodeNode huffmanTreeRoot = createHuffmanTree(nodes);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    /** *
     * @param bytes 这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码 map
     * @return 返回赫夫曼编码处理后的 byte[]
     * */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历 bytes 数组
        for(byte b: bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());
        // 将 "1010100010111111110..." 转成 byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if(stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        //记录是第几个 byte
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8){
            //因为是每 8 位对应一个 byte,所以步长 +8
            String strByte;
            if(i+8 > stringBuilder.length()) {
                //不够 8 位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将 strByte 转成一个 byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组 * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuffer stringBuffer = new StringBuffer();
        //将 byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length;i++){
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length -1);
            stringBuilder.append(byteToBitString(!flag,b));
        }

        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建要给集合，存放 byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length();){
            // 计数器
            int count = 0;
            boolean flag = true;
            Byte b = null;
            while (flag){
                //1010100010111...
                //递增的取出 key 1
                //i 不动，让 count 移动，指定匹配到一个字符
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){
                    //说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag =false;

                }
            }
            list.add(b);
            //i 直接移动到 count
            i += count;
        }
        //当 for 循环结束后，我们 list 中就存放了所有的字符 //把 list 中的数据放入到 byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i); }
        return b;
    }

    /**
     * 将一个 byte 转成一个二进制的字符串
     * @param b 传入的 byte
     * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补, 如果是最后一 个字节，无需补高位
     * @return 是该 b 对应的二进制的字符串，(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b
        // 将b转成int
        int temp = b;
        if (flag){
            //按位与 256 1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        // 返回的是temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }

    /**
     * @描述 解压文件
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     * @返回值
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-25
     * @修改人和其它信息
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;

        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取 byte 数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将 bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }


    }

    /**
     * @描述 文件压缩
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     * @返回值  void
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2019-06-25
     * @修改人和其它信息
     */
    public static void zipFile(String srcFile, String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;

        try {
            // 创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { is.close();
                oos.close();
                os.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

class HuffmanCodeNode implements Comparable<HuffmanCodeNode>{
    //存放字符的ASSIC编码
    public Byte data;

    //权值
    public int weight;
    public HuffmanCodeNode left;
    public HuffmanCodeNode right;

    public HuffmanCodeNode() {
    }

    public HuffmanCodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HuffmanCodeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanCodeNode left) {
        this.left = left;
    }

    public HuffmanCodeNode getRight() {
        return right;
    }

    public void setRight(HuffmanCodeNode right) {
        this.right = right;
    }

    //前序遍历
    public void preOrder(){
        // 先输出父节点信息
        System.out.println(this);
        // 前序递归遍历左子树
        if(this.left != null){
            this.left.preOrder();
        }
        // 前序递归遍历右子树
        if (this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Node [data= "+this.data+" weight="+this.weight+"]";
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(HuffmanCodeNode o) {
        return this.weight - o.getWeight();
    }
}
