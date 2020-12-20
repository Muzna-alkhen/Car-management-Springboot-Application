package com.example.WepApplications.service;

import com.example.WepApplications.dto.MessageDto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Service
public class InventoryService {
    public String doInventory(MessageDto msg) throws IOException, TimeoutException {

        System.out.println("*******************"+msg);
        //setup connection with rabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //defining the queue
        channel.queueDeclare("inventory_queue", false, false, false, null);

        // send the message to rabbitMQ
        channel.basicPublish("", "inventory_queue", null, (msg.getValue()).getBytes());

        //close the channel and the connection:
        channel.close();
        connection.close();

 return "Message sent ! ";
    }
}
