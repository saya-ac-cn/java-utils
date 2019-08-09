package ac.cn.saya.iterator;

import java.util.List;

/**
 * @Title: ListMenuIterator
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 21:57
 * @Description:
 * list迭代器
 */

public class ListMenuIterator implements Iterator{

    private List listMenus;
    private int position=0;

    public ListMenuIterator(List listMenus){
        this.listMenus=listMenus;
    }

    @Override
    public boolean hasNext() {
        if(position>=listMenus.size() || listMenus.get(position)==null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        MenuItem menuItem = (MenuItem) listMenus.get(position);
        position+=1;
        return menuItem;
    }
}
