package ac.cn.saya.command;

/**
 * @Title: NoCommand
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-05 22:27
 * @Description:
 * 没有任何命令，即空执行: 用于初始化每个按钮, 当调用空命令时，对象什么都不做
 */

public class NoCommand extends RemoteController implements Command {

    /**
     * 执行动作(操作)
     */
    @Override
    public void execute() {

    }

    /**
     * 撤销动作(操作)
     */
    @Override
    public void undo() {

    }
}
