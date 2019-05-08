package ac.cn.saya.juc.list;

/**
 * @Title: TransFerTestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-07 21:15
 * @Description:
 * 传值 传地
 */

public class TransFerTestUtil {

    public void changeValue1(int id){
        id = 20;
    }

    public void changeValue2(User user){
        user.setName("xxx");
    }

    public void changeValue3(String name){
        name = "xxx";
        System.out.println(System.identityHashCode(name));
    }

    public static void main(String[] args) {
        TransFerTestUtil test = new TransFerTestUtil();
        int id = 20;
        test.changeValue1(id);
        System.out.println("id-----"+id);

        User user = new User("abc");
        test.changeValue2(user);
        System.out.println("userName----"+user.getName());

        String name = "abc";
        System.out.println(System.identityHashCode(name));
        test.changeValue3(name);
        System.out.println("String--"+name);
    }

}


class User{
    private Integer id;
    private String name;

    public User( ) {
    }

    public User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}