package jvondoellinger.ShopFree.workers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


///  * EN-US:The class that contains the delete queue configs!
///  * PT-BR: Classe que contém as configurações da fila de exclusão!
@Configuration
@ConfigurationProperties(prefix = "aws.sqs.delete_queue")
public class DeleteQueueConfig extends ListenerConfig {
}
