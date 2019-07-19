package just.fo.fun.configuration;

import com.querydsl.sql.*;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	@Inject
	private DataSource dataSource;

	@Bean
	public com.querydsl.sql.Configuration querydslConfiguration() throws SQLException{

		DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
		SQLTemplates templates = new SQLTemplatesRegistry().getTemplates(metadata);
		com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
		configuration.setExceptionTranslator(new SpringExceptionTranslator());
		configuration.addListener(new SQLBaseListener() );
		return configuration;
	}

	@Bean
	public SQLQueryFactory queryFactory() throws SQLException {
		Provider<Connection> provider = new SpringConnectionProvider(dataSource);
		return new SQLQueryFactory(querydslConfiguration(), provider);
	}

}
