//package itmo.lab.web4.configurations;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DatabaseConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.hikari")
//    public HikariDataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/data");
//        dataSource.setUsername("user");
//        dataSource.setPassword("passwd");
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        return dataSource;
//    }
//}