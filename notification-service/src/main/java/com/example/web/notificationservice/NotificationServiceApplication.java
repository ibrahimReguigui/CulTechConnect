package com.example.web.notificationservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.messaging.handler.annotation.Payload;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notification", groupId = "notificationId")
    public void handleNotification(@Payload Notification notification) {
        log.info(notification.toString());
        log.info(notification.getContent());
        log.info(notification.getTime().toString());
        log.info(notification.getUserId());

        /*try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Configure the ObjectMapper to use a custom date format for "time" field
            objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Modify the notification string to convert it into a valid JSON format
            notification = notification.replace("Notification(", "{");
            notification = notification.replace("userId=", "\"userId\":");
            notification = notification.replace("content=", "\"content\":\"");
            notification = notification.replace(", time=", "\",\"time\":\"");
            notification = notification.replace(")", "\"}");

            JsonNode jsonNode = objectMapper.readTree(notification);

            String userId = jsonNode.get("userId").asText();
            String content = jsonNode.get("content").asText();
            String time = jsonNode.get("time").asText();

            log.info("User ID: {}", userId);
            log.info("Content: {}", content);
            log.info("Time: {}", time);

            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            Date date = sdf.parse(time);
            log.info(date.toString());
            Notification notification1 = Notification.builder().content(content).userId(userId).time(date).build();
            log.info(notification1.toString());

        } catch (Exception e) {
            log.error("Error processing Notification: {}", e.getMessage());
        }*/
    }
}
