

package cn.code.zeus.practice.config
//
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
class KafkaAutoConfiguration {

    @Bean
    fun producerConfigs(@Autowired kafkaProperties: KafkaProperties): Map<String, Any> {
        val props = HashMap<String, Any>(kafkaProperties.buildProducerProperties())
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        return props
    }

    @Bean
    fun producerFactory(@Autowired kafkaProperties: KafkaProperties): ProducerFactory<String, Any> {
        return DefaultKafkaProducerFactory(producerConfigs(kafkaProperties))
    }

    @Bean
    fun kafkaTemplate(@Autowired kafkaProperties: KafkaProperties): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory(kafkaProperties))
    }

    @Bean
    fun consumerFactory(@Autowired kafkaProperties: KafkaProperties): ConsumerFactory<String, Any> {
        val jsonDeserializer = JsonDeserializer(Any::class.java)
        jsonDeserializer.addTrustedPackages("*")
        val props = HashMap<String, Any>(kafkaProperties.buildConsumerProperties())
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = jsonDeserializer
        return DefaultKafkaConsumerFactory(
            kafkaProperties.buildConsumerProperties(), StringDeserializer(), jsonDeserializer
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(@Autowired kafkaProperties: KafkaProperties): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory(kafkaProperties)

        return factory
    }

    @Bean //kafka客户端，在spring中创建这个bean之后可以注入并且创建topic,用于集群环境，创建对个副本
    fun adminClient(kafkaAdmin: KafkaAdmin): AdminClient? {
        return AdminClient.create(kafkaAdmin.config)
    }
}