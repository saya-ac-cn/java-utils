package ac.cn.saya.jdbc;

import ac.cn.saya.jdbc.service.MysqlWriteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		/// SpringApplication.run(JdbcApplication.class, args);
		SpringApplication springApplication = new SpringApplication(JdbcApplication.class);
		// 禁止命令行设置参数
		springApplication.setAddCommandLineProperties(false);
		ApplicationContext context = springApplication.run(args);
		System.out.println("jdbc项目正在启动");
		MysqlWriteService run = context.getBean(MysqlWriteService.class);
		//run.useOrdinaryWriteMysql();
		//run.useTransactionWriteMysql();
		//run.useBatchProcessingWriteMysql();
		//run.useBatchProcessingAndTransactionWriteMysql();
		run.mysqlDump();
	}
}
