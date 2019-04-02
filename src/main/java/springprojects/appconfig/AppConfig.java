package springprojects.appconfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
@EnableWebMvc
@Configuration
@ComponentScan("springprojects")
//Configuration for transaction infrastructure
//Part 1
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer{

        @Value("${spring.datasource.url}")
        private String dataSourceUrl;

        @Value("${data.source.driver.class.name}")
        private String dataSourceDriverClassName;

        @Value("${data.source.user.name}")
        private String dataSourceUserName;

        @Value("${data.source.password}")
        private String dataSourcePassword;



        @Override
        // Comes from WebMvcConfigurer
        // Configure jackson mapper for parsing http requests(@ResponseBody)
        public void configureMessageConverters(
                List<HttpMessageConverter<?>> converters) {
            /* messageConverters.add(createXmlHttpMessageConverter());*/
            converters.add(new MappingJackson2HttpMessageConverter());
        }

        @Bean
        //For EntityManager Configuration(set up BD connection properties)
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em
                    = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());
            em.setPackagesToScan("springprojects");
            //Set JPA provider, Hibernate implements JPA specification
            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);
            em.setJpaProperties(additionalProperties());
            return em;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(dataSourceDriverClassName);
            dataSource.setUrl(dataSourceUrl);
            dataSource.setUsername(dataSourceUserName);
            dataSource.setPassword(dataSourcePassword);
            return dataSource;
        }

        @Bean
        //Configuration for transaction infrastructure
        //Part 2, see @EnableTransactionManagement
        public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(emf);
            return transactionManager;
        }

        Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "validate");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
            return properties;
        }
}
