package ac.cn.saya.juc.unsafe;

/**
 * @Title: User
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-20 23:44
 * @Description:
 */

public class User {

    private Integer id;
    private String name;
    private String property;

    /**
     * 构造方法私有化后，UnSafe依然能初始化
     */
    private User() {
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

    @Override
    public String toString() {
        return "User【" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", property='" + property + '\'' +
                ']';
    }
}
