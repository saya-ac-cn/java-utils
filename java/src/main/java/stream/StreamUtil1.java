package stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Title: StreamUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-14 22:02
 * @Description: 题目：请按照给出的数据，找出同时满足以下条件的用户，
 * 偶数ID且年龄大于24且用户名字母大写倒叙
 * 只输出1个用户名字
 */

class User {
    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ']';
    }
}

public class StreamUtil1 {

    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5);
        userList.stream().filter(user -> user.getId() % 2 == 0 && user.getAge() > 24
        ).map(u -> (u.getName()).toUpperCase())
                .sorted((o1, o2) -> o2.compareTo(o1)).limit(1).forEach(System.out::println);
    }

}
