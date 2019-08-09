package ac.cn.saya.iterator;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 22:01
 * @Description:
 * 测试单元：
 * 迭代器模式
 * 提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示
 * 情节
 * 有两家餐厅，共有两个菜单，两家准备合并，每个餐厅都有一个销售系统，但是内部菜单的组成结构稍有不同，一个是用数组存储菜单对象，一个是用集合存储菜单对象
 * 需求
 * 将两个菜单合并显示
 */

public class TestUtil {

    public static void main(String[] args) {
        ArrMenu arrMenu = new ArrMenu();
        ListMenu listMenu = new ListMenu();
        System.out.println("---------------数组 菜类---------------");
        printMenu(arrMenu.createIterator());
        System.out.println("---------------集合 汤类---------------");
        printMenu(listMenu.createIterator());
    }

    private static void printMenu(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem next = (MenuItem) iterator.next();
            System.out.println("menu name is :"+next.getName());
            System.out.println("menu description is :"+next.getDescription());
            System.out.println("menu price is :"+next.getPrice());
        }
    }


}
