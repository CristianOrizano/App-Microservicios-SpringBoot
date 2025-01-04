package com.reservas.shared.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class ProducerFactoryConfig {
    @Autowired
    private KafkaProperties kafkaProperties; // Inyecta la configuración de Kafka desde application.yml o application.properties

    //componente que configura los productores de Kafka
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        var configs = new HashMap<String,Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServers());  // Configura los servidores de Kafka (brokers)
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);// Define el serializador para la clave del mensaje (en este caso, un String)
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class); // Define el serializador para el valor del mensaje (en este caso, un String)
        return new DefaultKafkaProducerFactory<>(configs);
    }

   // componente principal para enviar mensajes a un topic de Kafka
   // En este caso, se configuró para enviar mensajes de tipo String
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
