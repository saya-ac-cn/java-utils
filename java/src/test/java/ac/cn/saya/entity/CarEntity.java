package ac.cn.saya.entity;

/**
 * @Title: CarEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-05-21 21:52
 * @Description:
 */

public class CarEntity implements Comparable<CarEntity> {

    private String uid;
    private int weight;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CarEntity() {
    }
    public CarEntity(String uid, int weight) {
        this.uid = uid;
        this.weight = weight;
    }

    @Override
    public int compareTo(CarEntity o) {
        if (this.weight - o.getWeight() > 0)
            return 1;
        else if (this.weight - o.getWeight() == 0)
            return 0;
        else
            return -1;
    }
}
