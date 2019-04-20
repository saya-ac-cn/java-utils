package ac.cn.saya.jdbc.service;

import ac.cn.saya.jdbc.dao.MysqlDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MysqlWriteService
 * @ProjectName jdbc
 * @Description: TODO
 * @Author Saya
 * @Date: 2018/11/28 22:47
 * @Description:
 */

@Service("mysqlWriteService")
public class MysqlWriteService {

    @Resource
    @Qualifier("mysqlDAO")
    private MysqlDAO mysqlDAO;

    /**
     * 普通方式写入
     */
    public void useOrdinaryWriteMysql()
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 1000000; i++)
        {
            list.add((int)(1+Math.random()*10000000));
        }
        mysqlDAO.useOrdinaryWriteMysql(list);
    }

    /**
     * 采用事务的方式提交
     */
    public void useTransactionWriteMysql()
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 1000000; i++)
        {
            list.add((int)(1+Math.random()*10000000));
        }
        mysqlDAO.useTransactionWriteMysql(list);
    }

    /**
     * 采用批处理的方式提交
     */
    public void useBatchProcessingWriteMysql()
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 1000000; i++)
        {
            list.add((int)(1+Math.random()*10000000));
        }
        mysqlDAO.useBatchProcessingWriteMysql(list);
    }

    /**
     * 采用批处理+事务的方式提交
     */
    public void useBatchProcessingAndTransactionWriteMysql()
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 1000000; i++)
        {
            list.add((int)(1+Math.random()*10000000));
        }
        mysqlDAO.useBatchProcessingAndTransactionWriteMysql(list);
    }

}
