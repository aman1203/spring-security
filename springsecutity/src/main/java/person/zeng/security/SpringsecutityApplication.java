package person.zeng.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringsecutityApplication implements InitializingBean{

	public static void main(String[] args) {
		SpringApplication.run(SpringsecutityApplication.class, args);
	}
/**
 * 初始化bean
 */
  @Override
  public void afterPropertiesSet() throws Exception {
    //TODO
  }
}
