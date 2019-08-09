package ac.cn.saya.iterator;

/**
 * @Title: ArrMenuIterator
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-07 21:54
 * @Description:
 * 数组迭代器
 */

public class ArrMenuIterator implements Iterator {

    private MenuItem[] items;
    private int position=0;

    public ArrMenuIterator(MenuItem[] items){
        this.items=items;
    }

    @Override
    public boolean hasNext() {
        if(position>=items.length || items[position]==null){
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        MenuItem item = items[position];
        position+=1;
        return item;
    }

}
