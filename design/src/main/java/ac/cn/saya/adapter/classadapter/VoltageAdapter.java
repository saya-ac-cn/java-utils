package ac.cn.saya.adapter.classadapter;

/**
 * @Title: VoltageAdapter
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-18 21:51
 * @Description:
 */

public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //获取到 220V 电压
        int srcV = output220V();
        int dstV = srcV/44;
        //转成 5v
        return dstV;
    }
}
