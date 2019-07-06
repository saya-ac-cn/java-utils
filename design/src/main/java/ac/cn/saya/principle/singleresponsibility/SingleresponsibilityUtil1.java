package ac.cn.saya.principle.singleresponsibility;

/**
 * @Title: SingleresponsibilityUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-06 21:56
 * @Description:
 * 单一职责方案1
 */

public class SingleresponsibilityUtil1 {

    public static void main(String [] args){
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("高铁");
    }

}

// 交通工具类
// 违反单一职责原则
// 解决的方案非常简单。根据交通运行方法不同，分解成不同类即可
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle + "在公路上行驶");
    }
}
