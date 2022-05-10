package fr.objectware.entrainement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.objectware.entrainement.config.AppConfig;
import fr.objectware.entrainement.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer { //celui qui envoie les msgs

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String playerJson) throws JSONException {
        log.info("Le msg que j'envoie");
        log.info(String.valueOf(playerJson));
        this.kafkaTemplate.send(AppConfig.getTopicName(), playerJson);

    }
}
