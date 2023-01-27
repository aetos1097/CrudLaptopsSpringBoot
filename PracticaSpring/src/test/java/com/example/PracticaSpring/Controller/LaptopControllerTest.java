package com.example.PracticaSpring.Controller;

import com.example.PracticaSpring.Entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)//puerto aleatorio
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;//funciona aunque bote error

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @DisplayName("probando test")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/laptops",Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList(response.getBody());//Se hace un casteo

    }

    @Test
    void findById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/laptops/1",Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        //Indicar las cabaceras para neviar con JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//lo que traera
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));// lo que devolvera

        String json = """
                {
                    "marca": "LG",
                    "precio": 1020.0,
                    "disponible": true,
                    "fechaLanzamiento": "2023-09-23",
                    "color": "rojo"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);//se crea la peticion
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("LG", result.getMarca());;
    }
}