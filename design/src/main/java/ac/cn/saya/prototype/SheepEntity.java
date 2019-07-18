package ac.cn.saya.prototype;

/**
 * @Title: SheepEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-15 21:21
 * @Description:
 * 原型模式
 */

public class SheepEntity implements Cloneable {

    private String name;

    private Integer age;

    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SheepEntity() {

    }

    @Override
    public String toString() {
        return "SheepEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    protected Object clone() {
        SheepEntity sheepEntity = null;
        try {
            sheepEntity = (SheepEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheepEntity;
    }
}
