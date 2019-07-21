package ac.cn.saya.builder;

/**
 * @Title: CommonHouse
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-17 22:40
 * @Description:
 * 普通房子建造
 */

public class CommonHouse extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基 5 米 ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子砌墙 10cm ");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子屋顶 ");
    }
}
