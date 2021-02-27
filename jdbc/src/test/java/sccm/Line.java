package sccm;

import java.util.List;

/**
 * @Title: Line
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/8/6 21:35
 * @Description:
 */

public class Line {

    private List<MetterEntity> list;

    private String content;

    public Line() {
    }

    public Line(List<MetterEntity> list, String content) {
        this.list = list;
        this.content = content;
    }

    public List<MetterEntity> getList() {
        return list;
    }

    public void setList(List<MetterEntity> list) {
        this.list = list;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
