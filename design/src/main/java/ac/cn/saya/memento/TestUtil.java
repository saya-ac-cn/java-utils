package ac.cn.saya.memento;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-13 21:37
 * @Description:
 */

public class TestUtil {

    public static void main(String[] args) {
        //创建游戏角色
        GameRole gameRole = new GameRole();
        gameRole.setVit(100);
        gameRole.setDef(100);

        System.out.println("和 boss 大战前的状态");
        gameRole.display();

        //把当前状态保存 caretaker
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameRole.createMemento());

        System.out.println("和 boss 大战~~~");
        gameRole.setDef(30);
        gameRole.setVit(30);
        gameRole.display();

        System.out.println("大战后，使用备忘录对象恢复到站前");
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        System.out.println("恢复后的状态");
        gameRole.display();
    }

}
