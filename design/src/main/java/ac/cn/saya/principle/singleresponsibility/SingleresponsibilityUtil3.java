package ac.cn.saya.principle.singleresponsibility;

/**
 * @Title: SingleresponsibilityUtil3
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-06 22:10
 * @Description:
 * 单一职责方案3
 */

public class SingleresponsibilityUtil3 {

    public static void main(String [] args){
        VehicleUtils vehicleUtils = new VehicleUtils();
        vehicleUtils.roadRun("汽车");
        vehicleUtils.airRun("飞机");
        vehicleUtils.waterRun("货轮");
    }
}

class VehicleUtils{
    public void roadRun(String vehicle){
        System.out.println(vehicle + "在公路上行驶");
    }

    public void airRun(String vehicle){
        System.out.println(vehicle + "在天空中飞行");
    }

    public void waterRun(String vehicle){
        System.out.println(vehicle + "在水上行驶");
    }
}
