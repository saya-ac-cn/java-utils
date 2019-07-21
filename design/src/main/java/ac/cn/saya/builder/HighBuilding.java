package ac.cn.saya.builder;

/**
 * @Title: HighBuilding
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-17 22:38
 * @Description:
 * 高楼建造者
 */

public class HighBuilding extends HouseBuilder {

    @Override
    public void buildBasic() {
        System.out.println(" 高楼的打地基 100 米 ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 高楼的砌墙 20cm ");
    }

    @Override
    public void roofed() {
        System.out.println(" 高楼的透明屋顶 ");
    }
}
