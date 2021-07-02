package ac.cn.saya.tools;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Title: TreeTools
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 7/3/21 00:00
 * @Description:
 */

public class TreeTools {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("重庆市");
        input.add("上海市");
        input.add("四川省/成都市");
        input.add("四川省/自贡市/富顺县");
        input.add("四川省/自贡市/荣县");
        input.add("四川省/攀枝花市");
        List<TreeNode> root = new CopyOnWriteArrayList<>();
        for (int i=0;i<input.size();i++) {
            String[] chain = (input.get(i)).split("/");
            List<TreeNode> currentHierarchy = root;
            for(int j = 0; j < chain.length;j++){
                String wantedNode = chain[j];
                if(null == wantedNode || "".equals(wantedNode)){
                    continue;
                }
                List<TreeNode> lastHierarchy = currentHierarchy;
                // 遍历root是否已有该层级
                for(int k = 0; k < currentHierarchy.size();k++){
                    if(wantedNode.equals(currentHierarchy.get(k).getTitle())){
                        currentHierarchy = currentHierarchy.get(k).getChildren();
                        break;
                    }
                }
                if(lastHierarchy == currentHierarchy) {
                    String key;

                    if(j == chain.length - 1){
                        // 最后一级
                        key = input.get(i);
                    } else {
                        StringJoiner joiner = new StringJoiner("/","","/");
                        for (int k = 0; k <j+1 ; k++) {
                            joiner.add(chain[k]);
                        }
                        key = joiner.toString();
                    }
                    TreeNode newNode = new TreeNode(key,wantedNode,new ArrayList<TreeNode>());
                    // 文件，最后一个字符不是"/“符号
                    if(j== chain.length-1){
                        newNode.setChildren(new ArrayList<TreeNode>());
                    }
                    currentHierarchy.add(newNode);
                    currentHierarchy = newNode.getChildren();
                }
            }
        }
        return;
    }

}
