package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppCtx {
    
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
    	DataSource ds = new DataSource();
    	ds.setDriverClassName("com.mysql.jdbc.Driver");
    	ds.setUrl("jdbc:mysql://localhost/spring5fs?useUnicode=true&character_set_server=utf8mb4");
    	ds.setUsername("spring5");
    	ds.setPassword("spring5");
    	ds.setInitialSize(2);
    	ds.setMaxActive(10);
    	ds.setTestWhileIdle(true);
    	ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);
    	ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
    	return ds;
    }
    
    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}
