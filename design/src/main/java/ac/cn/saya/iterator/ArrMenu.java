package ac.cn.saya.iterator;

/**
 * @Title: ArrMenu
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 21:45
 * @Description:
 * A店数组菜单
 */

public class ArrMenu {

    static final int MAX_ITEMS=3;
    private int items=0;
    private MenuItem[] menuItems;

    public ArrMenu(){
        menuItems=new MenuItem[MAX_ITEMS];
        addItem("西红柿鸡蛋","有营养",10.00F);
        addItem("酸辣白菜","好吃",11.00F);
        addItem("酸辣土豆丝","特色菜",12.00F);
    }

    private void addItem(String name,String description,Float price){
        MenuItem menuItem = new MenuItem(name, description, price);
        if(items>=MAX_ITEMS){
            System.err.println("数组菜单已满，不能再加入");
        }else{
            menuItems[items]=menuItem;
            items+=1;
        }
    }

    public Iterator createIterator(){
        return new ArrMenuIterator(menuItems);
    }


}
