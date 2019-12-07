package ac.cn.saya.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @Title: ObjectStructure
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-06 21:57
 * @Description: 管理多个评委
 */

public class ObjectStructure {

    //维护了一个集合
    private List<Person> persons = new LinkedList<>();

    //增加到 list
    public void attach(Person p) {
        persons.add(p);
    }

    //移除
    public void detach(Person p) {
        persons.remove(p);
    }

    //显示测评情况
    public void display(Action action) {
        for (Person p : persons) {
            System.out.println("-");
            p.accept(action);
        }
    }

}
