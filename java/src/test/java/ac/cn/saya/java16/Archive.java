package ac.cn.saya.java16;

/**
 * 档案类是用来表示不可变数据的透明载体，用来简化不可变数据的表达，提高编码效率，降低编码错误。
 *
 * 档案类是个终极（final）类，其父类是父类是java.lang.Record。不支持继承，不能用任何其他类来继承它。没有子类，也就意味着我们不能通过修改子类来改变档案类；
 * 档案类允许实现接口；
 * 档案类声明的变量是不可变的变量；
 * 档案类不能声明可变的变量，也不能支持实例初始化的方法。这就保证了，我们只能使用档案类形式的构造方法，避免额外的初始化对可变性的影响；
 * 档案类不能声明本地（native）方法。如果允许了本地方法，也就意味着打开了修改不可变变量的后门。
 */
public record Archive(String name,double price) {
    void print(){
        System.out.println("name="+name+",price="+price);
    }
}
