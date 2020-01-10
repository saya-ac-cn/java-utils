package ac.cn.saya.memento;

/**
 * @Title: GameRole
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-13 21:30
 * @Description: 游戏角色
 */

public class GameRole {

    private int vit;
    private int def;


    /**
     * 创建 Memento ,即根据当前的状态得到 Memento
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(vit, def);
    }

    /**
     * 从备忘录对象，恢复 GameRole 的状态
     *
     * @param memento
     */
    public void recoverGameRoleFromMemento(Memento memento) {
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    /**
     * 显示当前游戏角色的状态
     */
    public void display() {
        System.out.println("游戏角色当前的攻击力:" + this.vit + " 防御力: " + this.def);
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
