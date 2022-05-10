package fr.objectware.entrainement;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.objectware.entrainement.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class MainKafkaSpring {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(MainKafkaSpring.class, args);



    }
}