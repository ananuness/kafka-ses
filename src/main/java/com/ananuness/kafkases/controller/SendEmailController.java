package com.ananuness.kafkases.controller;

import com.ananuness.kafkases.producer.EventProducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {
  @GetMapping("email/{value}")
  public void sendEmail(@PathVariable("value") String value) {
    EventProducer producer = new EventProducer();

    producer.execute(value);
  }
}
