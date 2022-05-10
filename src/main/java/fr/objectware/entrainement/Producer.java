package fr.objectware.entrainement;

import fr.objectware.entrainement.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Producer { //celui qui envoie les msgs

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String messageJson) throws JSONException {
        log.info("Le msg que j'envoie");
        log.info(String.valueOf(messageJson));
        this.kafkaTemplate.send(AppConfig.getTopicName(), messageJson);


    }


    public void sendMsg2(String messageJson) throws JSONException {
        log.info("Le 2 msg que j'envoie");
        log.info(String.valueOf(messageJson));
        this.kafkaTemplate.send(AppConfig.getTopicName2(), messageJson); // un autre topic
    }



}
