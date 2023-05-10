package ac.cn.saya.tree;

import java.util.List;

public class IndustryEntity{

    private List<Double> size;

    private String id;

    private String type;

    private String title;

    private String version;

    private List<IndustryEntity> children;

    private Integer _id;

    private Integer _parentId;

    public List<Double> getSize() {
        return size;
    }

    public void setSize(List<Double> size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<IndustryEntity> getChildren() {
        return children;
    }

    public void setChildren(List<IndustryEntity> children) {
        this.children = children;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer get_parentId() {
        return _parentId;
    }

    public void set_parentId(Integer _parentId) {
        this._parentId = _parentId;
    }

    public IndustryEntity() {
    }

    public IndustryEntity(String title) {
        this.title = title;
    }

    public IndustryEntity(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
