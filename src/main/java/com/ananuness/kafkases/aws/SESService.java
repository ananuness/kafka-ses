package com.ananuness.kafkases.aws;

import java.io.IOException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SESService {
  static final String FROM = "beatriznunez601@gmail.com";
  static final String TO = "bianunez60@gmail.com";
  static final String SUBJECT = "Email with Kafka and SES"; 
  static final String BODY = "Hello, i am sending this with kafka and SES! :D";

  public static void sendEmail(ConsumerRecord<String, String> record) throws IOException {
    String Html = "<h1>Test Email with Kafka & SES</h1>"
    + "<p> key: " + record.key() + "<br>"
    + "value: " + record.value()  + "<br>"
    + "offset: " + record.offset() + "</p>";

    try {
      AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
        .withRegion(Regions.US_EAST_1).build();

      Content content = new Content().withCharset("UTF-8");
      Body body = new Body().withHtml(content.withData(Html)).withText(content.withData(BODY));

      SendEmailRequest request = new SendEmailRequest().withDestination(
        new Destination().withToAddresses(TO)
      ).withMessage(
        new Message().withBody(body).withSubject(content.withData(SUBJECT))
      ).withSource(FROM);

      client.sendEmail(request);
    } catch (Exception e) {
      System.out.println("error: " + e.getMessage());
    }
  }
}
