package ac.cn.saya.iterator;

/**
 * @Title: MenuItem
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 21:47
 * @Description:
 * 菜单实体对象
 */

public class MenuItem {

    private String name;
    private String description;
    private Float price;

    public MenuItem() {
    }

    public MenuItem(String name, String description, Float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "菜品{" +
                "菜名='" + name + '\'' +
                ", 描述='" + description + '\'' +
                ", 单价=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
