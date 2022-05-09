package com.ananuness.kafkases.consumer;

import java.io.IOException;

public class Execute {
  public static void main(String[] args) throws IOException {
    System.out.println("Starting execution...");

    EventConsumer consumer = new EventConsumer();
    
    consumer.execute();

}
}
