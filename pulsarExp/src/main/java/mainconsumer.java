
import java.util.Arrays;
import java.util.List;
import pulsarConsumer.consumer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lap10538
 */
public class mainconsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> topics = Arrays.asList(
            "topic-1",
            "topic-2",
            "topic-3"
        );
        String subscriptionName="phongnguyen178";
        consumer con=new consumer();
        con.multi_topicSubscription(subscriptionName, topics);

//        String topic="huuphong178";
//        String subscriptionName="phongnguyen4";
//        consumer con=new consumer(topic,subscriptionName);
//        con.startReceiveMessage();
//        System.err.println("Subscribe topic huuphong178");
    }
    
}
