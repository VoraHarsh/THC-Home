package com.egen.thchome.service.kafka.producer;

import com.egen.thchome.entity.CustomerOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class ProducerServiceImpl {

    private final KafkaTemplate<String, CustomerOrder> orderKafkaTemplate;

    @Value("${kafka.topic.store.name}")
    private String STORE_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, CustomerOrder> orderKafkaTemplate){
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public boolean sendBatchOrder(List<CustomerOrder> customerOrderList){

        customerOrderList.forEach(order -> sendOrder(STORE_TOPIC, order));

        return true;
    }

    public void sendOrder(String topic, CustomerOrder customerOrder) {

        orderKafkaTemplate.executeInTransaction(transaction -> {
            ListenableFuture<SendResult<String, CustomerOrder>> future = transaction.send(topic, customerOrder);
            future.addCallback(new ListenableFutureCallback<SendResult<String, CustomerOrder>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(SendResult<String, CustomerOrder> stringCustomerOrderSendResult) {
                    RecordMetadata sentOrder = stringCustomerOrderSendResult.getRecordMetadata();
                    log.info("=> produced message {} {} {}", sentOrder.offset(), sentOrder.topic(), sentOrder.partition());
                }
            });
            return true;
        });
    }

}
