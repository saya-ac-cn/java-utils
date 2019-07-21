package ac.cn.saya.builder;

/**
 * @Title: HouseBuilder
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-17 22:36
 * @Description:
 * 抽象的建造者
 */

public abstract class HouseBuilder {

    protected House house = new House();

    //将建造的流程写好, 抽象的方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    //建造房子好， 将产品(房子) 返回
    public House buildHouse() {
        return house;
    }

}
