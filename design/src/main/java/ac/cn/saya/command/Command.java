package ac.cn.saya.command;

/**
 * @Title: Command
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-08-05 22:17
 * @Description:
 * 创建命令接
 */

public interface Command {

    /**
     * 执行动作(操作)
     */
    public void execute();


    /**
     * 撤销动作(操作)
     */
    public void undo();

}
