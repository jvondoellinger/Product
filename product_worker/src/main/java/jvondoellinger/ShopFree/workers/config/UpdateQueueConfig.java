package jvondoellinger.ShopFree.workers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

///  * EN-US: The class that contains the update queue configs!
///  * PT-BR: Classe que contém as configurações da fila de atualização!
@Configuration
@ConfigurationProperties(prefix = "aws.sqs.create_queue")
public class UpdateQueueConfig extends ListenerConfig {
}
