package com.example.WepApplications.service;

import com.example.WepApplications.dao.CarRepository;
import com.example.WepApplications.dto.MessageDto;
import com.example.WepApplications.model.Car;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;
@Service
public class InventoryService {

    @Autowired
    private CarRepository carRepository;



    public static <T> List<T>
    getListFromIterator(Iterator<T> iterator) {

        // Create an empty list
        List<T> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEachRemaining(list::add);

        // Return the List
        return list;

    }

        public String doInventory() throws IOException, TimeoutException {


        //setup connection with rabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {

                String msg_mq = new String(body, "UTF-8");


                //split the message
                String[] tokens = msg_mq.split(",");
                String msg_email = tokens[0];
                String msg_content = tokens[1];
                String msg_date = tokens[2];

                //process the message

                //generating CSV file
                Date date = new Date ();
                try {
                     date =new SimpleDateFormat("dd/MM/yyyy").parse(msg_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=cars_" + msg_date + ".csv";


                Iterable<Car> iterator_cars =  carRepository.findBySoldDate(date);
                List<Car> list_cars = getListFromIterator(iterator_cars.iterator());


                //List<Car> cars = listCars.

                ICsvBeanWriter csvWriter = new CsvBeanWriter( new FileWriter("cars.csv")/*response.getWriter()*/, CsvPreference.STANDARD_PREFERENCE);
                String[] csvHeader = {"Car ID", "Name", "Price", "Seats","Sold Price","Customer Name"};
                String[] nameMapping = {"id", "name", "price", "seats","soldPrice","customerName"};


                csvWriter.writeHeader(csvHeader);

                for (Car car : list_cars) {
                    csvWriter.write(car, nameMapping);
                }

                csvWriter.close();
                // send the email
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sspringtest@gmail.com", "spring+123456");
                    }
                });

                try {
                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress("sspringtest@gmail.com", false));

                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg_email));
                    msg.setSubject(msg_date);
                    msg.setContent(msg_content, "text/html");
                    msg.setSentDate(new Date());

                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(msg_content, "text/html");

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    MimeBodyPart attachPart = new MimeBodyPart();

                    attachPart.attachFile("C://Users//HP//Desktop//WepApplicationsB//cars.csv");
                    multipart.addBodyPart(attachPart);
                    msg.setContent(multipart);
                    Transport.send(msg);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        };
        channel.basicConsume("inventory_queue", true, consumer);



 return "Message sent ! ";
    }


    public String doInventory(MessageDto msg) throws IOException, TimeoutException {


String msgValue = msg.getValue();

                //split the message
                String[] tokens = msgValue.split(",");
                String msg_email = tokens[0];
                String msg_content = tokens[1];
                String msg_date = tokens[2];

                //process the message

                //generating CSV file
                Date date = new Date ();
                try {
                    date =new SimpleDateFormat("dd/MM/yyyy").parse(msg_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=cars_" + msg_date + ".csv";


                Iterable<Car> iterator_cars =  carRepository.findBySoldDate(date);
                List<Car> list_cars = getListFromIterator(iterator_cars.iterator());


                //List<Car> cars = listCars.

                ICsvBeanWriter csvWriter = new CsvBeanWriter( new FileWriter("cars.csv")/*response.getWriter()*/, CsvPreference.STANDARD_PREFERENCE);
                String[] csvHeader = {"Car ID", "Name", "Price", "Seats","Sold Price","Customer Name"};
                String[] nameMapping = {"id", "name", "price", "seats","soldPrice","customerName"};

                csvWriter.writeHeader(csvHeader);

                for (Car car : list_cars) {
                    csvWriter.write(car, nameMapping);
                }

                csvWriter.close();
                // send the email
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("sspringtest@gmail.com", "spring+123456");
                    }
                });

                try {
                    Message msgg = new MimeMessage(session);
                    msgg.setFrom(new InternetAddress("sspringtest@gmail.com", false));

                    msgg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg_email));
                    msgg.setSubject(msg_date);
                    msgg.setContent(msg_content, "text/html");
                    msgg.setSentDate(new Date());

                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(msg_content, "text/html");

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    MimeBodyPart attachPart = new MimeBodyPart();

                    attachPart.attachFile("C://Users//HP//Desktop//WepApplicationsB//cars.csv");
                    multipart.addBodyPart(attachPart);
                    msgg.setContent(multipart);
                    Transport.send(msgg);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }



        return "Message sent ! ";
    }
}
