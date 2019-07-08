package ac.cn.saya.principle.segregation;

/**
 * @Title: SegregationUtil1
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-07 22:51
 * @Description:
 *接口隔离原则（基础版）
 */

public class SegregationUtil1 {
}

interface ISegregationUtil1{
    public void operation1();
    public void operation2();
    public void operation3();
    public void operation4();
    public void operation5();
}

class SegregationBUtil1 implements ISegregationUtil1{
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

    @Override
    public void operation4(){
        System.out.println("B 实现了 operation4");
    }

    @Override
    public void operation5(){
        System.out.println("B 实现了 operation5");
    }
}

class SegregationDUtil1 implements ISegregationUtil1{
    @Override
    public void operation1(){
        System.out.println("D 实现了 operation1");
    }

    @Override
    public void operation2(){
        System.out.println("D 实现了 operation2");
    }

    @Override
    public void operation3(){
        System.out.println("D 实现了 operation3");
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

class SegregationAUtil1{
    public void depend1(SegregationBUtil1 i){
        i.operation1();
    }

    public void depend2(SegregationBUtil1 i){
        i.operation2();
    }

    public void depend3(SegregationBUtil1 i){
        i.operation3();
    }
}

class SegregationCUtil1{
    public void depend1(SegregationBUtil1 i){
        i.operation1();
    }

    public void depend4(SegregationBUtil1 i){
        i.operation4();
    }

    public void depend5(SegregationBUtil1 i){
        i.operation5();
    }
}
