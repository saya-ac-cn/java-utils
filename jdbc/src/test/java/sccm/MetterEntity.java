package sccm;

/**
 * @Title: MetterEntity
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/6 21:28
 * @Description:
 */

public class MetterEntity {

    private long collectorId;

    private long meterId;

    public MetterEntity() {
    }

    public MetterEntity(long collectorId, long meterId) {
        this.collectorId = collectorId;
        this.meterId = meterId;
    }

    public long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(long collectorId) {
        this.collectorId = collectorId;
    }

    public long getMeterId() {
        return meterId;
    }

    public void setMeterId(long meterId) {
        this.meterId = meterId;
    }
}
