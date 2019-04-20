package ac.cn.saya.jdbc.dao;/**
 * Created by Administrator on 2018/11/28 0028.
 */

import ac.cn.saya.jdbc.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: MemberMappin
 * @ProjectName jdbc
 * @Description: TODO
 * @Author Administrator
 * @Date: 2018/11/28 0028 14:07
 * @Description:
 * 会员DAO接口
 */
@Mapper
@Repository("memberMappin")
public interface MemberMappin {

    /*
     * @描述 获取会员信息列表
     * @参数  []
     * @返回值  java.util.List<ac.cn.saya.entity.MemberEntity>
     * @创建人  saya.ac.cn-刘能凯
     * @创建时间  2018/11/28 0028
     * @修改人和其它信息
     */
    public List<MemberEntity> getMemberList(MemberEntity entity);

}
