package ac.cn.saya.jdbc.entity;/**
 * Created by Administrator on 2018/11/28 0028.
 */


import org.apache.ibatis.type.Alias;

/**
 * @Title: MemberEntity
 * @ProjectName jdbc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2018/11/28 0028 13:20
 * @Description:
 */
@Alias("MemberEntity")
public class MemberEntity {

    private Long id;
    private String name;
    private String createTime;

    public MemberEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
