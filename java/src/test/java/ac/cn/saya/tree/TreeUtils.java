package ac.cn.saya.tree;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtils {

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param all  所有节点
     * @return 根节点信息
     */
    private static List<IndustryEntity> getChildrens(IndustryEntity root, List<IndustryEntity> all) {
        List<IndustryEntity> children = all.stream().filter(m -> {
            return Objects.equals(m.get_parentId(), root.get_id());
        }).map((m) -> {
                    m.setChildren(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }

    /**
     * 生成目录结构
     * @param nodes
     * @return
     */
    public static List<IndustryEntity> getFilePathTree(List<IndustryEntity> nodes) {
        Map<String, IndustryEntity> titleMap = nodes.stream().collect(Collectors.toMap(IndustryEntity::getTitle, Function.identity(), (k1, k2) -> k1));

        Map<String, Integer> map = new LinkedHashMap<>();
        Integer id = 1;
        for (IndustryEntity node:nodes) {
            String[] path = node.getTitle().split("/");
            System.err.println("----"+node.getTitle()+"----");
            String p = "";
            for (int j = 0; j < path.length; j++) {
                p += path[j] + "/";
                if (!map.containsKey(p.substring(0, p.length() - 1))) {
                    map.put(p.substring(0, p.length() - 1), id++);
                }
            }
        }
        //map.entrySet().forEach(e-> System.out.println(e.getKey()+"->"+e.getValue()));

        List<IndustryEntity> menus = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            IndustryEntity menu = new IndustryEntity();
            Integer values = entry.getValue();
            String[] keys = entry.getKey().split("/");
            menu.set_id(values);
            if (keys.length == 1) {
                // 一级路径
                menu.set_parentId(0);
                menu.setTitle(keys[0]);
                menu.setId(titleMap.containsKey(keys[0])?titleMap.get(keys[0]).getId():null);
                //menu.setPath(keys[0]);
            } else {
                String path = "";
                for (int i = 0; i < keys.length - 1; i++) {
                    path += keys[i] + "/";
                }
                menu.setTitle(keys[keys.length - 1]);
                menu.setId(titleMap.containsKey(entry.getKey())?titleMap.get(entry.getKey()).getId():null);
                //menu.setPath(String.join("/", keys));
                path = path.substring(0, path.length() - 1);
                menu.set_parentId(map.get(path));
            }
            menus.add(menu);
        }
        //获取父节点
        List<IndustryEntity> collect = menus.stream().filter(m -> m.get_parentId() == 0).map(
                (m) -> {
                    m.setChildren(getChildrens(m, menus));
                    return m;
                }
        ).collect(Collectors.toList());

        return collect;
    }

    // 文件目录相对路径列子
//    private static String[] fileList = new String[]{
//            "dist/favicon.ico",
//            "dist/index.html",
//            "dist/static/css/app.46c00deb.css",
//            "dist/static/css/chunk-vendors.b80cec6e.css",
//            "dist/static/css/dashboard.65e4cda8.css",
//            "dist/static/css/home.c7bb3066.css",
//            "dist/static/css/login.89e00d4a.css",
//            "dist/static/css/table.cf6aa91f.css",
//            "dist/static/fonts/element-icons.535877f5.woff",
//            "dist/static/fonts/element-icons.732389de.ttf",
//            "dist/static/img/img.146655c9.jpg",
//            "dist/static/img/login-bg.e2134055.jpg",
//            "dist/static/js/app.db875c52.js",
//            "dist/static/js/chunk-vendors.31d72191.js",
//            "dist/static/js/dashboard.988c89e7.js",
//            "dist/static/js/home.482bfd24.js",
//            "dist/static/js/login.78ac76b0.js",
//            "dist/static/js/table.eac1b16a.js",
//            "dist/table.json",
//            "dist/table.json",
//            "6666"
//    };
    private static IndustryEntity[] fileList = new IndustryEntity[]{
            new IndustryEntity("__1","dist/static/js/login.78ac76b0.js"),
            new IndustryEntity("__2","dist/static/js/table.eac1b16a.js"),
            new IndustryEntity("__3","dist/table.json"),
            new IndustryEntity("__4","6666")
    };

    public static void main(String args[]) throws IOException {
        List<IndustryEntity> collect = getFilePathTree(Arrays.asList(fileList));
//        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));
    }

}
