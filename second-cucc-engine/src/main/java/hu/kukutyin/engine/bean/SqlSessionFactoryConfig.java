package hu.kukutyin.engine.bean;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "hu.kukutyin.engine.dao",
        markerInterface = hu.kukutyin.engine.dao.mapper.MyBatisMapper.class,
        sqlSessionFactoryRef = "sqlSessionFactory")
public class SqlSessionFactoryConfig {

    @Autowired
    @Qualifier("mySql")
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml");
        sqlSessionFactory.setMapperLocations(resources);
        return sqlSessionFactory.getObject();
    }
}
