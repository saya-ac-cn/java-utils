package ac.cn.saya.jdbc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class JDBCBaseConnection {

    /**
     * 基类Dao
	 * 自动实现相关的hibernate变量
     * 把配置文件中的been注入到此
     */
    @Resource
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSession getSqlSession(){
        //打开SqlSession会话
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

}
