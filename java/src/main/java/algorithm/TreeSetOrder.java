package algorithm;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Title: TreeSetOrder
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-12-24 22:02
 * @Description:题目：一个已经构建好的 TreeSet，怎么完成倒排序
 */

public class TreeSetOrder {

    public static void main(String[] args) {
        // 利用内部提供的方法进行排序
        //TreeSet<Integer> treeSet = new TreeSet<>();
        //TreeSet<Integer> treeReverse = new TreeSet<>();
        //treeSet.add(2);
        //treeSet.add(7);
        //treeSet.add(3);
        //treeSet.add(19);
        //treeReverse = (TreeSet)treeSet.descendingSet();
        //Arrays.asList(treeSet).stream().forEach(s -> System.out.print(s));
        //Arrays.asList(treeReverse).stream().forEach(s -> System.out.print(s));

        Set<PeopleEntity> ts = new TreeSet<PeopleEntity>();
        ts.add(new PeopleEntity("zhangsan", 23));
        ts.add(new PeopleEntity("lisi", 18));
        ts.add(new PeopleEntity("wangmazi", 22));
        ts.add(new PeopleEntity("wangwu",12));
        ts.add(new PeopleEntity("mazi", 16));
        Iterator<PeopleEntity> it = ts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}

class PeopleEntity implements Comparable{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public PeopleEntity() {
    }

    public PeopleEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PeopleEntity{姓名=" + name +", 年龄=" + age +"}";
    }

    @Override
    public int compareTo(Object o) {
        // 强转
        PeopleEntity temp = (PeopleEntity) o;
        //降序
        int result = getAge() < temp.getAge() ? 1 : (getAge() == temp.getAge() ? 0 : -1);
        //升序
        //int result = getAge() > temp.getAge() ? 1 : (getAge() == temp.getAge() ? 0 : -1);
        if (result == 0) {
            result = name.compareTo(temp.name);
        }
        return result;
    }
}
