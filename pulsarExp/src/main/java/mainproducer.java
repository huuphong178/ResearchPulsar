
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import pulsarCli.pulsarClient;
import pulsarProducer.producer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lap10538
 */
public class mainproducer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PulsarClientException {
//        PulsarClient client=pulsarClient.getIntanceCli();
//        Producer<byte[]> producer = client.newProducer()
//        .topic("my-topic")
//        .create();
       String topic="topic-3";
       producer Producer=new producer(topic);
       System.err.println("Producer create topic: "+topic);
        do {            
           try {
               String s;
               BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
               System.out.print("Enter a message: ");
               s = ob.readLine();
               Producer.sendMessage(s);
               System.out.println("Send message: "+s);
           } catch (IOException ex) {
               Logger.getLogger(mainproducer.class.getName()).log(Level.SEVERE, null, ex);
           }
        } while (true);
    }
    
}
