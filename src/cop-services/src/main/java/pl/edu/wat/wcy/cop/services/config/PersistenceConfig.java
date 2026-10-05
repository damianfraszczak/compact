/**
 *
 */
package pl.edu.wat.wcy.cop.services.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "pl.edu.wat.wcy.cop.services.repositories.impl",}, repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableTransactionManagement
@Configuration
@EntityScan("pl.edu.wat.wcy.cop.domain")
// Configures persistence settings.
public class PersistenceConfig {
    @Value("${bonecp.url}")
    private String jdbcUrl;

    @Value("${bonecp.username}")
    private String jdbcUsername;

    @Value("${bonecp.password}")
    private String jdbcPassword;

    @Value("${bonecp.driverClass}")
    private String driverClass;

    @Value("${bonecp.idleMaxAgeInMinutes}")
    private Integer idleMaxAgeInMinutes;

    @Value("${bonecp.idleConnectionTestPeriodInMinutes}")
    private Integer idleConnectionTestPeriodInMinutes;

    @Value("${bonecp.maxConnectionsPerPartition}")
    private Integer maxConnectionsPerPartition;

    @Value("${bonecp.minConnectionsPerPartition}")
    private Integer minConnectionsPerPartition;

    @Value("${bonecp.partitionCount}")
    private Integer partitionCount;

    @Value("${bonecp.acquireIncrement}")
    private Integer acquireIncrement;

    @Value("${bonecp.statementsCacheSize}")
    private Integer statementsCacheSize;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
        dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
        dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
        dataSource.setPartitionCount(partitionCount);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setStatementsCacheSize(statementsCacheSize);
        return dataSource;
    }

    @Bean
    public PhysicalNamingStrategy physical() {
        return new PhysicalNamingStrategyStandardImpl();
    }

    @Bean
    public ImplicitNamingStrategy implicit() {
        return new ImplicitNamingStrategyLegacyJpaImpl();
    }
}
