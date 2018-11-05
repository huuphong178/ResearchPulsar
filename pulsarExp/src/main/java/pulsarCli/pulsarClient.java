/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pulsarCli;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 *
 * @author lap10538
 */
public class pulsarClient {

    private static PulsarClient client=null; 
    private static String localClusterUrl = "pulsar://localhost:6650";
    public static PulsarClient getIntanceCli(){
        if(client==null){
      
            try {
                client = PulsarClient.builder()
                        .serviceUrl(localClusterUrl)
                        .build();
            } catch (PulsarClientException ex) {
                Logger.getLogger(pulsarClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 
        return client;
    }
    public void closeClient(){
        try {
            client.close();
        } catch (PulsarClientException ex) {
            Logger.getLogger(pulsarClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
