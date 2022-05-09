package com.ananuness.kafkases.consumer;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ananuness.kafkases.aws.SESService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class EventConsumer {
  private KafkaConsumer<String, String> consumer;

  public EventConsumer() {
    this.consumer = createConsumer();
  }

  private KafkaConsumer<String, String> createConsumer() {
    if (consumer != null) return consumer;

    Properties properties = new Properties();

    properties.put("bootstrap.servers", "localhost:9092");
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put("group.id", "default");

    return new KafkaConsumer<String, String>(properties);
  }

  public void execute() throws IOException {
    List<String> topics = new ArrayList<String>();

    topics.add("SESTopic");

    consumer.subscribe(topics);

    System.out.println("Starting to read messages and send emails...");

    boolean run = true;

    while (run) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));

      for (ConsumerRecord<String, String> record : records) {
        SESService.sendEmail(record);

        System.out.println("Saved message!");

        if (record.value().equals("close")) {
          run = false;
        }
      }
    }
  }
}
