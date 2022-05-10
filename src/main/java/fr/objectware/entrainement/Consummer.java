package fr.objectware.entrainement;

import com.google.gson.Gson;
import fr.objectware.entrainement.config.AppConfig;
import fr.objectware.entrainement.model.Messages;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class Consummer {

    final KafkaConsumer<byte[], String> consumer = new KafkaConsumer<>(AppConfig.getConsumerConfig());
    private final List<Messages> messages = new ArrayList<>();
    private final List<Messages> messages2 = new ArrayList<>();


    @KafkaListener(topics = "client-v1-topic", groupId = "777")
    public void consume(String tweetJson) throws IOException {


        //ici c'est les tweets de bertoune
        Messages message = new Gson().fromJson(tweetJson, Messages.class);
        log.info("consume sur client v1 topic :");
        log.info(message.getMsg());
        messages.add(message);

    }


    @KafkaListener(topics = "client-v2-topic", groupId = "777")
    public void consume2(String messageJson) throws IOException {

        //ici c'est les autres tweets
        Messages message = new Gson().fromJson(messageJson, Messages.class);
        log.info("consume sur client v2 :");
        log.info(message.getMsg());
        messages2.add(message);

    }

    public List<Messages> ListMsg() {
        return messages;
    }

    public List<Messages> ListMsg2() {
        return messages2;
    }

    public List<Messages> getAllMsg() {

        List<Messages> allMsg = new ArrayList<>();
        allMsg.addAll(messages);
        allMsg.addAll(messages2);

        List<Messages> messagesTries = allMsg.stream().sorted(Comparator.comparingInt(Messages::getOrdre)).collect(Collectors.toList());
        return messagesTries;
    }

    public List<Messages> getAllMsgBetween2(String user1, String user2) {
        List<Messages> allMsg = new ArrayList<>();
        allMsg.addAll(messages);
        allMsg.addAll(messages2);
        List<Messages> msgEntre2 = new ArrayList<>();

        for (Messages m : allMsg)
        {
            if((m.getUserPseudoSortant().equals(user1) && m.getUserPseudo().equals(user2)) || (m.getUserPseudoSortant().equals(user2) && m.getUserPseudo().equals(user1)))
            {
                msgEntre2.add(m);
            }
        }

        List<Messages> messagesTries = msgEntre2.stream().sorted(Comparator.comparingInt(Messages::getOrdre)).collect(Collectors.toList());
        return messagesTries;
    }


}
