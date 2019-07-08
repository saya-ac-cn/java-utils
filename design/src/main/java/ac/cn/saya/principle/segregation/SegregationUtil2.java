package ac.cn.saya.principle.segregation;

/**
 * @Title: SegregationUtil2
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-07 23:02
 * @Description:
 * 改进的接口隔离原则
 */

public class SegregationUtil2 {
}


interface ISegregationUtil2_1{
    public void operation1();
}

interface ISegregationUtil2_2{
    public void operation2();
    public void operation3();
}

interface ISegregationUtil2_3{
    public void operation4();
    public void operation5();
}

class SegregationBUtil2 implements ISegregationUtil2_1,ISegregationUtil2_2{
    @Override
    public void operation1(){
        System.out.println("B 实现了 operation1");
    }

    @Override
    public void operation2(){
        System.out.println("B 实现了 operation2");
    }

    @Override
    public void operation3(){
        System.out.println("B 实现了 operation3");
    }
}

class SegregationDUtil2 implements ISegregationUtil2_1,ISegregationUtil2_3{
    @Override
    public void operation1(){
        System.out.println("D 实现了 operation1");
    }

    @Override
    public void operation4(){
        System.out.println("D 实现了 operation4");
    }

    @Override
    public void operation5(){
        System.out.println("D 实现了 operation5");
    }
}

class SegregationAUtil2{
    public void depend1(SegregationBUtil2 i){
        i.operation1();
    }

    public void depend2(SegregationBUtil2 i){
        i.operation2();
    }

    public void depend3(SegregationBUtil2 i){
        i.operation3();
    }
}

class SegregationCUtil2{
    public void depend1(SegregationDUtil2 i){
        i.operation1();
    }

    public void depend4(SegregationDUtil2 i){
        i.operation4();
    }

    public void depend5(SegregationDUtil2 i){
        i.operation5();
    }
}