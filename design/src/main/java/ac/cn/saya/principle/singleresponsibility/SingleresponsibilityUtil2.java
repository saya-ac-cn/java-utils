package ac.cn.saya.principle.singleresponsibility;

/**
 * @Title: SingleresponsibilityUtil2
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-06 22:04
 * @Description:
 * 单一职责方案2
 */

public class SingleresponsibilityUtil2 {

    public static void main(String [] args){
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("游轮");
    }
}

/**
 * 方案2的分析
 * 1、遵守单一职责原则
 * 2、但是这样的改动很大，即将类分解，同时修改客户端
 * 3、改进：直接修改Vehicle类，改动的代码会比较少
 */
class RoadVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + "在公路上行驶");
    }
}

class AirVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + "在天空中飞行");
    }
}

class WaterVehicle{
    public void run(String vehicle){
        System.out.println(vehicle + "在水上行驶");
    }
}