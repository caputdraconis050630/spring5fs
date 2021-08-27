package com.caput.sp5chap02;

import com.caput.sp5chap02.chap02.AppContext;
import com.caput.sp5chap02.chap02.Greeter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Sp5Chap02Application {

	public static void main(String[] args) {
//		SpringApplication.run(Sp5Chap02Application.class, args);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g = ctx.getBean("greeter", Greeter.class);

		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}

}
