package ac.cn.saya.entity;

import java.util.Comparator;

/**
 * @Title: CatEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-21 22:06
 * @Description:
 */

public class CatEntity implements Comparator<CatEntity> {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CatEntity() {
    }

    public CatEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compare(CatEntity o1, CatEntity o2) {
        if (o1.getAge() - o2.getAge() > 0)
            return 1;
        else if (o1.getAge() - o2.getAge() == 0)
            return 0;
        else
            return -1;
    }
}

