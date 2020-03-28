package ac.cn.saya.juc.interview;

/**
 * @Title: FactoryUnit
 * @ProjectName spring-boot-webflux
 * @Description: TODO
 * @Author Administrator
 * @Date: 2020/3/10 0010 11:38
 * @Description:抽象工厂模式
 */

public class FactoryUnit {
    public static void main(String[] args) {
        FactoryLine line = new BusLine();
        Transport bus = line.choise();
        bus.create();
    }
}

// 制造出的产品
interface Transport{
    public void create();
}

class Car implements Transport{
    @Override
    public void create() {
        System.out.println("制造轿车");
    }
}

class Bus implements Transport{
    @Override
    public void create() {
        System.out.println("制造大巴");
    }
}

// 流水线
interface FactoryLine{
    public Transport choise();
}

//轿车生产线
class CarLine implements FactoryLine{
    @Override
    public Transport choise() {
        System.out.println("启动轿车生产线");
        return new Car();
    }
}

class BusLine implements FactoryLine{
    @Override
    public Transport choise() {
        System.out.println("启动大巴生产线");
        return new Bus();
    }
}