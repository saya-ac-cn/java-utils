package ac.cn.saya.prototype.deepclone;

import java.io.Serializable;

/**
 * @Title: TypeEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-16 23:07
 * @Description:
 * 羊 的类别
 */

public class TypeEntity implements Serializable, Cloneable{

    private static final long serialVersionUID = 3448081119870110450L;

    private int weight;

    public TypeEntity() {
    }

    public TypeEntity(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //因为该类的属性，都是 String , 因此我们这里使用默认的 clone 完成即可
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
