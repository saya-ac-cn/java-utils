package ac.cn.saya.java16;

/**
 * instanceof 模式匹配（Pattern Matching of instanceof）
 * @author saya
 * @title: Instanceof
 * @projectName java-utils
 * @description: TODO
 * @date: 2023/5/20 11:08
 * @description:
 * instanceof模式匹配允许我们以内联形式转换变量，并在所需的if-else块中使用它而无需进行强转。
 */

public class InstanceofTest {

    class Car{
      protected double price;
    }

    class Nio extends Car{

    }

    class Tesla extends Car{

    }

    /**
     * 老的方法
     * @param car
     * @return
     */
    public static double getPriceOld(Car car) {
        if (car instanceof Nio) {
            Nio nio = (Nio) car;
            return nio.price;
        } else if (car instanceof Tesla) {
            Tesla tesla = (Tesla) car;
            return tesla.price;
        } else throw new IllegalArgumentException();
    }

    /**
     * 新的方法
     * @param car
     * @return
     */
    public static double getPriceNew(Car car) {
        if (car instanceof Nio nio) {
            return nio.price;
        } else if (car instanceof Tesla tesla) {
            return tesla.price;
        } else throw new IllegalArgumentException();
    }


}
