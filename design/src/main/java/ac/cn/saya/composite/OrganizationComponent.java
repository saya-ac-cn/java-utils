package ac.cn.saya.composite;

/**
 * @Title: OrganizationComponent
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-24 21:19
 * @Description:
 * 组织结构
 */

public abstract class OrganizationComponent {

    /**
     * 名字
     */
    private String name;

    /**
     * 描述
     */
    private String des;

    protected void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }


    protected void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    /**
     * 方法 print, 做成抽象的, 子类都需要实现
     */
    protected abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
