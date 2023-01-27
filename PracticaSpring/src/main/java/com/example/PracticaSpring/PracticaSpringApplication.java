package com.example.PracticaSpring;


import com.example.PracticaSpring.Entity.Laptop;
import com.example.PracticaSpring.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class PracticaSpringApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PracticaSpringApplication.class, args);
		LaptopRepository laptop = context.getBean(LaptopRepository.class);
		Laptop laptop1 = new Laptop(null,"Apple","Plata",2000.0,true, LocalDate.of(2020,10,26));
		Laptop laptop2 = new Laptop(null,"Huawei","negro",1000.0,true, LocalDate.of(2023,9,23));
		Laptop laptop3 = new Laptop(null,"Acer","blanco",1200.0,true, LocalDate.of(2021,11,14));
		Laptop laptop4 = new Laptop(null,"pollito","azul",2000.0,true, LocalDate.of(2020,12,12));

		laptop.save(laptop1);
		laptop.save(laptop2);
		laptop.save(laptop3);
		laptop.save(laptop4);



	}

}
