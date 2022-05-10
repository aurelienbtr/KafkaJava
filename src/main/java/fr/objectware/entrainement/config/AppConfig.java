package fr.objectware.entrainement.config;



import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.BytesSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

public final class AppConfig {

    private static final Config config = ConfigFactory.load();

    private AppConfig() {
        //default Constructor
    }

    public static final Properties getProducerConfig() {
        final Properties result = new Properties();
        result.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString("app.common.bootstrap-server"));
        result.setProperty(ProducerConfig.CLIENT_ID_CONFIG, config.getString("app.common.application-id"));
        result.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, BytesSerializer.class);
        result.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        result.put(ProducerConfig.ACKS_CONFIG, config.getString("app.producer.acks"));
        result.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, config.getString("app.producer.compression-type"));
        return result;

    }


    public static final Properties getConsumerConfig() {
        final Properties result = new Properties();
        result.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString("app.common.bootstrap-server"));

        /** ca va chercher les valeurs dans mon fichier .conf
         * ca ressemble a un json
         * ou il y a un objet app
         * contenant un objet common
         * avec un element bootstrap-server/ application-id
         *
         * ca rend le code plus propre en separant tout
         */

        result.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, config.getString("app.common.application-id"));
        result.setProperty(ConsumerConfig.GROUP_ID_CONFIG, config.getString("app.consumer.group-id"));
        result.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class);
        result.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        result.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getString("app.consumer.auto-offset-reset"));
        result.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, config.getString("app.consumer.enable-auto-commit"));
        return result;
    }

//    public static final Properties getCsvToJsonStreamConfig() {
//        final Properties result = new Properties();
//        result.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        result.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//    }

    public static String getTopicName() { return config.getString("app.common.topic-name");}
    public static String getTopicName2() { return config.getString("app.common.topic-name2");}


}
