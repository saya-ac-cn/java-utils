package ac.cn.saya.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ListMenu
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 21:51
 * @Description:
 * B店集合菜单
 */

public class ListMenu {

    private List<MenuItem> menuItems;
    public ListMenu(){
        menuItems = new ArrayList<MenuItem>();
        addItem("紫菜鸡蛋汤","有营养的汤",6.00F);
        addItem("豆腐脑","早餐必备",7.00F);
        addItem("鱿鱼汤","补充大脑",8.00F);
    }


    private void addItem(String name,String description,Float price){
        MenuItem menuItem = new MenuItem(name, description, price);
        menuItems.add(menuItem);
    }

    public Iterator createIterator(){
        return new ListMenuIterator(menuItems);
    }

}
