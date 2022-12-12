package alex.silantev.dronzilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DronzillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronzillaApplication.class, args);
	}

}
