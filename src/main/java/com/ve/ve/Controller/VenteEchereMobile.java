package com.ve.ve.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ve.ve.Model.MessageNotification;
import com.ve.ve.Repository.AdminLoginRepository;
import com.ve.ve.Repository.CategorieRepository;

@Controller
public class VenteEchereMobile {
    @Autowired
    private AdminLoginRepository user;

    @Autowired 
    private CategorieRepository categorie;

    // @PostMapping("/token")
    // public String sendPnsToDevice(HttpServletRequest request) {
    //     MessageNotification msg=new MessageNotification();
    //     msg.setBody(request.getParameter("body"));
    //     msg.setTarget(request.getParameter("target"));
    //     msg.setTitle(request.getParameter("title"));
    //     return this.sendPnsToDevice(msg);
    // }
    // public String sendPnsToDevice(MessageNotification notificationRequestDto) {
    //     Message message = Message.builder()
    //             .setToken(notificationRequestDto.getTarget())
    //             .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
    //             .putData("content", notificationRequestDto.getTitle())
    //             .putData("body", notificationRequestDto.getBody())
    //             .build();

    //     String response = null;
    //     try {
    //         response = FirebaseMessaging.getInstance().send(message);
    //     } catch (FirebaseMessagingException e) {
    //         System.out.println("Fail to send firebase notification"+e);
    //     }

    //     return response;
    // }
    

 

}
