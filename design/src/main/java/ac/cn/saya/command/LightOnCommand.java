package ac.cn.saya.command;

/**
 * @Title: LightOffCommand
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-05 22:20
 * @Description: 电灯开启信号
 */

public class LightOnCommand implements Command {

    /**
     * 聚合 LightReceiver
     */
    private LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    /**
     * 执行动作(操作)
     */
    @Override
    public void execute() {
        // 调用接收者的方法
        lightReceiver.on();
    }

    /**
     * 撤销动作(操作)
     */
    @Override
    public void undo() {
        // 调用接收者的方法
        lightReceiver.off();
    }
}
