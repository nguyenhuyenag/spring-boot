package core;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.idclass.User;
import core.repository.ClazzRepository;
import core.repository.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Autowired
	ClazzRepository clazzRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... params) throws Exception {

		int code = RandomUtils.nextInt(0, 999);
		String name = RandomStringUtils.randomAlphabetic(5);
		userRepository.save(new User(code, name));
		
	}

}
