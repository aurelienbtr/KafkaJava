package fr.objectware.entrainement;

import com.google.gson.Gson;
import fr.objectware.entrainement.config.AppConfig;
import fr.objectware.entrainement.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.json.JSONException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class Consummer {
    @KafkaListener(topics = "client-v1-topic", groupId = "777")
    public void consume(String playerJson) throws IOException {

        log.info("consumme :");
        log.info(playerJson);

        Player p = new Gson().fromJson(playerJson, Player.class);
        log.info(p.getFirstname());
    }
}
