package com.egen.thchome.service.kafka.consumer;

import com.egen.thchome.entity.CustomerOrder;

import com.egen.thchome.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl {

    @Autowired
    StoreService storeService;

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.store.name}",
            groupId = "${kafka.topic.store.groupId}")
    public void consumeStoreData(CustomerOrder customerOrder,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID)String partitionId,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC)String topicName,
                                     @Header(KafkaHeaders.OFFSET)String offsetValue
    ){
        log.info("Consumed Message: Order Id: {}, from Partition ID: {}, topic : {} , offset : {}",
                customerOrder.getOrderId(), partitionId, topicName, offsetValue);

        //storeService.getStoreOrdersBasedOnTime(customerOrder);
    }
}
