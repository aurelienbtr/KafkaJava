package fr.objectware.entrainement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import fr.objectware.entrainement.config.AppConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer{ //celui qui envoie les msgs

    public static void main (String... args) {

        List<String> DATAS = new ArrayList<>();
        IntStream.range(0,100).forEach(i -> DATAS.add("message kafka " + i));

        
        // on cree un produceur kafka : celui qui envoie les msgs
        KafkaProducer<byte[], String> producer = new KafkaProducer<>(AppConfig.getProducerConfig());


        for (int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<byte[], String>(AppConfig.getTopicName(), DATAS.get(i)));

        producer.close();



        Runtime.getRuntime().addShutdownHook(new Thread(producer::close));





    }
}
