package ac.cn.saya.composite;

/**
 * @Title: Department
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-24 21:31
 * @Description:
 * 二级学院
 */

public class Department extends OrganizationComponent  {

    public Department(String name, String des) {
        super(name, des);
    }

    /**
     * 方法 print, 做成抽象的, 子类都需要实现
     */
    @Override
    protected void print() {
        System.out.println(getName());
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }
}
