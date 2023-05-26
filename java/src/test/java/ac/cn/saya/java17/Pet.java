package ac.cn.saya.java17;

/**
 * @author saya
 * @title: Pet
 * @projectName java-utils
 * @description: TODO
 * @date: 2023/5/26 22:03
 * @description: 定义一个密封接口Pet，它的实现类只能是Dog, Cat这两个，其他的实现类均不允许
 */

public sealed interface Pet permits Dog, Cat {

    void print();

}