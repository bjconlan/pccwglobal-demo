package com.github.bjconlan.pccwglobaldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the demo application which defines the spring boot
 * application configuration details (all defaults hence no explicit config)
 * and starts the web server and application context.
 *
 * @since 0.0.1
 */
@SpringBootApplication
public class PCCWGlobalDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PCCWGlobalDemoApplication.class, args);
	}
}


