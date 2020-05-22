package ac.cn.saya.rpc.consumer;

import ac.cn.saya.rpc.api.entity.UserEntity;
import ac.cn.saya.rpc.api.service.UserService;
import ac.cn.saya.rpc.consumer.service.DynamicProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @Title: BootStrap
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/24 0024 11:37
 * @Description:
 */

public class BootStrap {

    public static void main(String[] args) {
        UserService instance = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                new DynamicProxyHandler(UserService.class));
        UserEntity user = instance.queryUser(new UserEntity("saya", 99));
        System.out.println(user);
    }

}
