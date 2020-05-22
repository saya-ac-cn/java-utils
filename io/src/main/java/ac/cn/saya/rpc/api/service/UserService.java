package ac.cn.saya.rpc.api.service;

import ac.cn.saya.rpc.api.entity.UserEntity;

/**
 * @Title: UserService
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/24 0024 10:47
 * @Description:
 */

public interface UserService {

    /**
     * 查询用户
     * @param param
     * @return
     */
    public UserEntity queryUser(UserEntity param);

}
