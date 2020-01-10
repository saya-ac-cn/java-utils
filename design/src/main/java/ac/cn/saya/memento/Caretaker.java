package ac.cn.saya.memento;

/**
 * @Title: Caretaker
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-13 21:35
 * @Description:
 * 守护者对象, 保存游戏角色的状态
 */

public class Caretaker {

    //如果只保存一次状态
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
