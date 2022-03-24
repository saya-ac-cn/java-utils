package ac.cn.saya.devops;
import java.io.File;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * 拉取代码
 * @Title: PullCodeUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2022/3/24 13:34
 * @Description:
 */

public class PullCodeUtil {

    public static void main(String[] args) {
        String baseUrl = "https://git.uino.com/kuaijian/chengdu/base/manager/";
        String module = "vlib-x";//args[0];
        String user = "liunengkai";
        String pass = "-zSzRPej7jkgU5U4Qc_b";
        String branch = "master";//args[1];
        String revision = "";//args[2];

        PullCodeUtil gfxly = new PullCodeUtil(module, user, pass, branch, revision);

        int getPullCode = gfxly.pull(baseUrl + module);
        if (getPullCode == 0) {
            System.out.println("检出代码成功===0");
        } else if (getPullCode == 1) {
            System.exit(1);
        } else if (getPullCode == 2) {
            System.exit(2);
        } else if (getPullCode == 3) {
            System.exit(3);
        } else if (getPullCode == 4) {
            System.exit(4);
        } else {
            System.out.println("检出代码未知异常===5");
            System.exit(5);
        }
        int getBranchCode = gfxly.checkoutBranch();
        if (getBranchCode == 0) {
            System.out.println("检出分支成功===0");
        } else if (getPullCode == 6) {
            System.exit(6);
        } else {
            System.out.println("检出分支未知异常===7");
            System.exit(7);
        }
        int getRevisionCode = gfxly.checkoutRevision();
        if (getRevisionCode == 0) {
            System.out.println("检出版本成功===0");
        } else if (getPullCode == 8) {
            System.exit(8);
        } else {
            System.out.println("检出版本未知异常===9");
            System.exit(9);
        }
    }

    private String module;
    private String user;
    private String pass;
    private String branch;
    private String revision;
    private String git_config;

    public PullCodeUtil(String module, String user, String pass, String branch, String revision){
        this.module = module;
        this.user = user;
        this.pass = pass;
        this.branch = branch;
        this.revision = revision;
        this.git_config = "./basedir/" + module + "/.git";
    }

    /**
     * 通过url拉取代码
     * @param gitUrl
     * @return
     */
    public int pull(String gitUrl){
        String pullMsg = "";
        // 标记拉取代码的标志
        int pullFlag = 0;
        // 提供用户名和密码的验证
        UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(
                this.user, this.pass);
        // 指定要加载的代码路径
        File dir = new File("./basedir/" + this.module);
        // 判断代码路径下是否有内容，如果有就删除
        if(dir.exists()){
            deleteFolder(dir);
        }

        Git git = null;
        try {
            git = Git.cloneRepository().setURI(gitUrl)
                    .setDirectory(dir).setCredentialsProvider(provider).call();
            pullMsg = "检出代码成功 success";
        } catch (org.eclipse.jgit.api.errors.TransportException e){
            e.printStackTrace();
            pullMsg = "用户名NAME或密码PASSWORD错误或远程链接URL错误 failed";
            pullFlag = 1;
        } catch (org.eclipse.jgit.api.errors.JGitInternalException e) {
            e.printStackTrace();
            pullMsg = "已经存在了项目的下载目录，并且目录正在被操作 failed";
            pullFlag = 2;
        } catch (GitAPIException e) {
            e.printStackTrace();
            pullMsg = "调用GitAPI异常，failed";
            pullFlag = 3;
        } catch (NoClassDefFoundError e) {
            e.printStackTrace();
            pullMsg = "未找到相应的类文件异常，failed";
            pullFlag = 4;
        } finally {
            System.out.println(pullMsg +"--code--"+ pullFlag);
            if (git != null) {
                git.close();
            }
        }

        return pullFlag;
    }
    /**
     * 检出分支
     * @param branchName
     * @return
     */
    public int checkoutBranch(){
        String checkoutMsg = "";
        int checkoutFlag = 0;

        if (this.branch.equals("master")) {
            checkoutMsg = "Check out code OK. ->" + this.branch;
            System.out.println(checkoutMsg +"--code--"+ checkoutFlag);
            return checkoutFlag;
        }
        Git git = null;
        try {
            git = Git.open( new File(this.git_config) );
            //列出所有的分支名称
            List<Ref> branchList = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            for (Ref ref : branchList){
                if (this.branch.equals(ref.getName())) {
                    System.out.println("代码分支列表中存在给定分支");
                }
            }
            git.checkout().setName("origin/" + this.branch).setForce(true).call();
            checkoutMsg = "检出分支代码 success! code OK ->" + this.branch;
        } catch (Exception e) {
            e.printStackTrace();
            checkoutMsg = "检出分支代码 failed ! ->" + this.branch;
            checkoutFlag = 6;
        } finally {
            System.out.println(checkoutMsg +"--code--"+ checkoutFlag);
            if (git != null) {
                git.close();
            }
        }

        return checkoutFlag;
    }
    /**
     * 检出代码
     * @param revision
     * @return
     */
    public int checkoutRevision(){
        String checkoutMsg = "";
        int checkoutFlag = 0;
        if (this.revision == null || this.revision.length() == 0) {
            checkoutMsg = "Check out code OK. ->" + this.revision;
            System.out.println(checkoutMsg +"--code--"+ checkoutFlag);
            return checkoutFlag;
        }
        Git git = null;
        try {
            git = Git.open( new File(this.git_config) );
            git.checkout().setName( this.revision ).setForce(true).call();
            checkoutMsg = "检出代码版本 success! code OK. ->" + this.revision;
        } catch (Exception e) {
            e.printStackTrace();
            checkoutMsg = "检出代码版本 failed ! ->" + this.revision;
            checkoutFlag = 8;
        } finally {
            System.out.println(checkoutMsg +"--code--"+ checkoutFlag);
            if (git != null) {
                git.close();
            }
        }
        return checkoutFlag;
    }
    /**
     * 删除目录
     * @param file
     */
    private void deleteFolder(File file){
        try {
            if (file.isFile() || file.list().length==0) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                for (File getFile: files) {
                    deleteFolder(getFile);
                    getFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
