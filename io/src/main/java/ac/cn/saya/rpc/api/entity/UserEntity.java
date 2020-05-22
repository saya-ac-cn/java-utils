package ac.cn.saya.rpc.api.entity;

import java.io.Serializable;

/**
 * @Title: UserEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/23 0023 10:00
 * @Description:
 */

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5434338503762305843L;

    private String name;

    private Integer age;

    public UserEntity() {
    }

    public UserEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", age=" + age +']';
    }

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
}
