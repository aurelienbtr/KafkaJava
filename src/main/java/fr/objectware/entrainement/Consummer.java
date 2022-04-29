package fr.objectware.entrainement;

import fr.objectware.entrainement.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.util.Collections;

@Slf4j
public class Consummer {

    public static void main(String... args) {

        final KafkaConsumer<byte[], String> consumer = new KafkaConsumer<>(AppConfig.getConsumerConfig());



        consumer.subscribe(Collections.singleton(AppConfig.getTopicName())); // le consummer s'abonne à un ou plusieurs topics

        //ici ca fait une recup automatique de tout les messages du producer
        while (true) {
            ConsumerRecords<byte[], String> records; //cle c'est un tab de byte, et la valeur est en String,
            records = consumer.poll(Duration.ofSeconds((Integer.MAX_VALUE))); //chercher la donnees toutes les secondes max

            for (ConsumerRecord record : records) {
                log.info("Recuperation d'un msg dont le contenu est {}, partition:{}, offset:{}", record.value(), record.partition(), record.offset());

            }
        }

    }
}