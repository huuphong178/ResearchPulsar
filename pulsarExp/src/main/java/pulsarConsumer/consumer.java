/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pulsarConsumer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.ConsumerBuilder;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.SubscriptionType;
import pulsarCli.pulsarClient;

/**
 *
 * @author lap10538
 */
public class consumer {
    Consumer consumer;
    PulsarClient client;
    Thread consumerThread;
    public consumer(){
        client=pulsarClient.getIntanceCli();
    }
    public consumer(String topic, String subcription){
        client=pulsarClient.getIntanceCli();
        try {
            consumer = (Consumer) client.newConsumer()
                    .topic(topic)
                    .subscriptionName(subcription)
                    .ackTimeout(10, TimeUnit.SECONDS)
                    .subscriptionType(SubscriptionType.Exclusive)
                    .subscribe();
        } catch (PulsarClientException ex) {
            Logger.getLogger(consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        consumerThread=new Thread(){
            @Override
            public void run() {
                do {
                    try {
                        // Wait for a message
                        Message msg = consumer.receive();

                        System.out.printf("Message received: %s\n", new String(msg.getData()));

                        // Acknowledge the message so that it can be deleted by the message broker
                        consumer.acknowledge(msg);
                    } catch (PulsarClientException ex) {
                        Logger.getLogger(consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
              } while (true);
            }
            
        };
    }
    public void multi_topicSubscription(String subscription, List<String> topics){
        ConsumerBuilder consumerBuilder = client.newConsumer()
            .subscriptionName(subscription);
        CompletableFuture multiTopicConsumer = consumerBuilder
            .topics(topics)
            .subscribeAsync()
            .thenAccept((consumerAsync) -> {
                new Thread(){
                    @Override
                    public void run() {
                        do {
                            try {
                                Message msg = ((Consumer)consumerAsync).receive();
                                System.out.printf("Message async received: %s\n", new String(msg.getData()));
                            } catch (PulsarClientException e) {
                                e.printStackTrace();
                            }
                        } while (true);
                    }
                }.start();
        });
    }
    public void startReceiveMessage(){
        if(!consumerThread.isAlive()){
            consumerThread.start();
        }
    }
    
}
