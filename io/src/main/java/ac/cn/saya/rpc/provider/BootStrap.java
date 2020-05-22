package ac.cn.saya.rpc.provider;

import ac.cn.saya.rpc.api.service.UserService;
import ac.cn.saya.rpc.provider.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: BootStrap
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/24 0024 10:50
 * @Description:
 */

public class BootStrap {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while (true){
                Socket socket = serverSocket.accept();
                // 读取接收到的参数
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String apiClassName = inputStream.readUTF();
                String methodName = inputStream.readUTF();
                Class[] paramTypes = (Class[]) inputStream.readObject();
                Object[] param = (Object[]) inputStream.readObject();
                // 服务注册，找到具体的实现类
                Class clazz = null;
                if ((UserService.class.getName()).equals(apiClassName)){
                    clazz = UserServiceImpl.class;
                }

                // 通过反射执行UserServiceImpl的方法
                Method method = clazz.getMethod(methodName, paramTypes);
                Object result = method.invoke(clazz.newInstance(), param);

                // 返回结果给客户端(序列化)
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
                outputStream.flush();

                // 关闭连接
                outputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
