package itmo.lab.web4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.annotation.PostConstruct;
import java.sql.SQLException;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
public class Web4Application {



//
//	@PostConstruct
//	public void init() {
//		try {
//			// Двойная проверка регистрации драйвера
//			Class.forName("org.postgresql.Driver");
//			java.sql.DriverManager.registerDriver(new Driver());
//			System.out.println("PostgreSQL driver successfully registered!");
//		} catch (ClassNotFoundException | SQLException e) {
//			System.err.println("Failed to register PostgreSQL driver:");
//			e.printStackTrace();
//			throw new RuntimeException("Failed to initialize database driver", e);
//		}
//	}

	public static void main(String[] args) {
		SpringApplication.run(Web4Application.class, args);
	}
}