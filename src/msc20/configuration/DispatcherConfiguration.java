package msc20.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("msc20.components")
public class DispatcherConfiguration implements WebMvcConfigurer {


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("courses/resources/**").addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/update_remove_professor/resources/**")
				.addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/update_remove_professor/{pageId}/resources/**")
				.addResourceLocations("/WEB-INF/resources/");
	}
	
	@Bean
	public DataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:MyDB");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}
	
	
	private Properties getHibernateProperties() {

		Properties prop = new Properties();
		prop.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		//prop.put("hibernate.show_sql", true);
		prop.put("hibernate.format_sql", true);
		prop.put("hibernate.hbm2ddl.auto", "create");
		return prop;
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}

	@Bean
	public SessionFactory sessionFactory() {
		final LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setHibernateProperties(getHibernateProperties());
		lsfb.setPackagesToScan("msc20.components.model");
		try {
			lsfb.afterPropertiesSet();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return lsfb.getObject();

	}

}
