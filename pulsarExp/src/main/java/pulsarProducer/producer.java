/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pulsarProducer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import pulsarCli.pulsarClient;

/**
 *
 * @author lap10538
 */
public class producer {
    private Producer<byte[]> producer;

    public producer(String topic) {
        PulsarClient client=pulsarClient.getIntanceCli();
        try {
            producer= client.newProducer()
                    .topic(topic)
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .sendTimeout(10, TimeUnit.SECONDS)
                    .blockIfQueueFull(true)
                    .create();
        } catch (PulsarClientException ex) {
            Logger.getLogger(producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void sendMessage(String message){
        try {
            producer.send(message.getBytes());
        } catch (PulsarClientException ex) {
            Logger.getLogger(producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendAsync(String asyncmessage){
        producer.sendAsync(asyncmessage.getBytes()).thenAccept(msgId -> {
            System.out.printf("Message with ID %s successfully sent", msgId);
        });
    }
    public void closeProducer(){
        try {
            producer.close();
        } catch (PulsarClientException ex) {
            Logger.getLogger(producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void closeAsync(){
        producer.closeAsync()
        .thenRun(() -> System.out.println("Producer closed"));
        
    }
}
