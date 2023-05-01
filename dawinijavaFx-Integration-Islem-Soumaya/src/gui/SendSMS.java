/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class SendSMS {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC27c995d4f0936ca3294d2a28ac1942a5";
  public static final String AUTH_TOKEN = "2b944182f34b4270cc993556736d78dc";

  public static void main(String[] args) {
    
  }
  
  
  public static void sendMessage(String phone, String body) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber(phone),
      new com.twilio.type.PhoneNumber("+12708196942"),
      body)

    .create();

    System.out.println(message.getSid());
  }
}
