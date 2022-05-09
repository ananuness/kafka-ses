package com.ananuness.kafkases.producer;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class EventProducer {
  private Producer<String, String> producer;

  public EventProducer() {
    this.producer = createProducer();
  }

  private Producer<String, String> createProducer() {
    if (producer != null) return producer;

    Properties properties = new Properties();

    properties.put("bootstrap.servers", "localhost:9092");
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("serializer.class", "kafka.serializer.DefaultEncoder");

    return new KafkaProducer<String, String>(properties);
  }

  public void execute(String value) {
    String key = UUID.randomUUID().toString();

    System.out.println("Sending message...");

    ProducerRecord<String, String> record = new ProducerRecord<String,String>("SESTopic", key, value);

    producer.send(record);
    producer.flush();
    producer.close();

    System.out.println("Message has send succesfully!" + value);
  }
}
