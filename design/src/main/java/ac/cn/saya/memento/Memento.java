package ac.cn.saya.memento;

/**
 * @Title: Memento
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-13 21:28
 * @Description:
 * 要保存的对象单元
 */

public class Memento {

    //攻击力
    private int vit;
    //防御力
    private int def;

    public Memento(int vit, int def) {
        this.vit = vit;
        this.def = def;
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
