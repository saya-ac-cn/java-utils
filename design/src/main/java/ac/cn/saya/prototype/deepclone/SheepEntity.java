package ac.cn.saya.prototype.deepclone;

import java.io.*;

/**
 * @Title: SheepEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-15 21:21
 * @Description: 深拷贝
 */

public class SheepEntity implements Serializable, Cloneable {

    private String name;

    private TypeEntity type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }

    public SheepEntity() {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        //这里完成对基本数据类型(属性)和 String 的克隆
        deep = super.clone();
        //对引用类型的属性，进行单独处理
        SheepEntity deepProtoType = (SheepEntity) deep;
        deepProtoType.setType((TypeEntity) type.clone());
        return deepProtoType;
    }

    //深拷贝 - 方式 2 通过对象的序列化实现 (推荐)
    public Object deepClone() {
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前这个对象以对象流的方式输出

            //序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            SheepEntity copyObj = (SheepEntity)ois.readObject();

            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭流
            try { bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
            }
        }
    }

}
