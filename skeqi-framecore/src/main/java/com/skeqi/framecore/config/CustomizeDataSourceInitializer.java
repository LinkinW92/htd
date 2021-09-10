package com.skeqi.framecore.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


@Slf4j
@Configuration
public class CustomizeDataSourceInitializer {

	@Value("classpath:sql/mes-init.sql")
	private Resource sqlInit;

	@Value("${initdb.init-status}")
	private boolean initStatus;

	@Value("${initdb.version}")
	private String version;

	@Value("${initdb.update-version}")
	private String updateVersion;

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource){
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator databasePopulator(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		if(initStatus && updateVersion.equals(version)){
			log.info("init db version: {}",version);
			populator.addScript(sqlInit);
		}else {
            if(!version.equals(updateVersion)){
            	log.info("update db version: {}",updateVersion);
				ResourceLoader resourceLoader = new DefaultResourceLoader();
				String versions=String.format("%s%s.sql","sql/update-",updateVersion);
				Resource sql = resourceLoader.getResource(versions);
				populator.addScript(sql);
			}
		}
		return populator;
	}
}
