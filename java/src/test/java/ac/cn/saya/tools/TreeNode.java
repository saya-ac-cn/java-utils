package ac.cn.saya.tools;

import java.util.List;

/**
 * @Title: TreeNode
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 7/3/21 00:03
 * @Description:
 */

public class TreeNode {

    private String key;

    private String title;

    private List<TreeNode> children;

    public TreeNode(String key, String title, List<TreeNode> children) {
        this.key = key;
        this.title = title;
        this.children = children;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
