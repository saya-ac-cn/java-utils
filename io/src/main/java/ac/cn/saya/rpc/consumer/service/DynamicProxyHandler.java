package ac.cn.saya.rpc.consumer.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Title: DynamicProxyHandler
 * @ProjectName java-utils
 * @Description: TODO
 * @Author saya
 * @Date: 2020/4/24 0024 11:21
 * @Description:
 */

public class DynamicProxyHandler implements InvocationHandler {

    private Class object;

    public DynamicProxyHandler(Class object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 建立远程连接
        Socket socket = new Socket("127.0.0.1", 9000);
        // 要调用的类、方法、参数
        String apiName = object.getName();
        String methodName = method.getName();
        // 为了鉴别方法的重载，这里需要传入参数类型
        Class[] paramTypes = method.getParameterTypes();
        // 传输类信息，请求远程执行结果
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeUTF(apiName);
        outputStream.writeUTF(methodName);
        outputStream.writeObject(paramTypes);
        outputStream.writeObject(args);

        // 接收返回的结果
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        Object object = inputStream.readObject();
        inputStream.close();
        outputStream.close();
        socket.close();

        return object;

    }
}
