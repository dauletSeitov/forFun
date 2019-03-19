package just.fo.fun;

import com.querydsl.core.QueryMetadata;
import com.querydsl.core.types.*;
import com.querydsl.sql.*;
import com.querydsl.sql.dml.SQLInsertBatch;
import com.querydsl.sql.dml.SQLMergeBatch;
import com.querydsl.sql.dml.SQLUpdateBatch;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	private final Logger LOG = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Inject
	private DataSource dataSource;



	@Bean
	public com.querydsl.sql.Configuration querydslConfiguration() throws SQLException, IllegalAccessException, InvocationTargetException {

		DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
		SQLTemplates templates = new SQLTemplatesRegistry().getTemplates(metadata);
		if (templates instanceof OracleTemplates) {
			LOG.info("Execute workaround for Oracle");
//			Collection<Method> methods = Reflection.getAllMethods(templates.getClass(), true, true);
//			Optional<Method> optMethod = methods.stream().filter((method) -> "add".equals(method.getName())
//							&& (method.getParameterCount() == 2)
//			).findFirst();
//			if (!optMethod.isPresent()) {
//				LOG.error("Can't find method [add] in {} ({})", templates.getClass(), Arrays.asList(((Templates) templates).getClass().getMethods()));
//			} else {
//				Method mth = optMethod.get();
//				mth.setAccessible(true);
//				LOG.trace("Use method ADD: {}", mth.toGenericString());
//				mth.invoke(templates, Ops.DateTimeOps.DIFF_WEEKS, "trunc((cast({1} as date) - {0}) / 7)");
//				mth.invoke(templates, Ops.DateTimeOps.DIFF_DAYS, "trunc(cast({1} as date) - cast({0} as date))");
//				mth.invoke(templates, Ops.DateTimeOps.DIFF_HOURS, "trunc((cast({1} as date) - cast({0} as date)) * 24)");
//				mth.invoke(templates, Ops.DateTimeOps.DIFF_MINUTES, "trunc((cast({1} as date) - cast({0} as date)) * 1440)");
//				mth.invoke(templates, Ops.DateTimeOps.DIFF_SECONDS, "trunc(day from (cast({1} as date) - cast({0} as date)) * 86400)");
//			}
		}

		LOG.info("Use QueryDSL template for DB: [{}]", metadata.getDatabaseProductName());
		com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
		configuration.setExceptionTranslator(new SpringExceptionTranslator());
		configuration.addListener(new SQLBaseListener() );
		return configuration;
	}

	@Bean
	public SQLQueryFactory queryFactory() throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Provider<Connection> provider = new SpringConnectionProvider(dataSource);
		return new SQLQueryFactory(querydslConfiguration(), provider);
	}

//    @Bean
//    public PlatformTransactionManager transactionManager(final DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
