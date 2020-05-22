package ac.cn.saya.rpc.provider.service;


import ac.cn.saya.rpc.api.entity.UserEntity;
import ac.cn.saya.rpc.api.service.UserService;

/**
 * @Title: UserServiceImpl
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/24 0024 10:51
 * @Description:
 */

public class UserServiceImpl implements UserService {

    /**
     * 查询用户
     *
     * @param param
     * @return
     */
    @Override
    public UserEntity queryUser(UserEntity param) {
        if (null != param){
            return new UserEntity(param.getName(),param.getAge()+1);
        }
        return new UserEntity("系统用户",999999);
    }
}
