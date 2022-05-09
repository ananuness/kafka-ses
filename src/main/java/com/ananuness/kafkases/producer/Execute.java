package com.ananuness.kafkases.producer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Execute {
  public static void main(String[] args) {
    System.out.println("Starting execution...");

    EventProducer producer = new EventProducer();

    producer.execute("New Value!");
  }
}
