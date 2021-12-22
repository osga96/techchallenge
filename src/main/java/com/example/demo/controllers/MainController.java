package com.example.demo.controllers;

import com.example.demo.entities.Subscription;
import com.example.demo.mail.EmailService;
import com.example.demo.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private EmailService emailService;


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/findSubscriptionDetail")
    public String findByEmail(String email, Model model) {

        if (subscriptionRepository.findByEmail(email) == null) {
            return "noResults";
        }
        model.addAttribute("subscription", subscriptionRepository.findByEmail(email));
        return "subscriptionDetail";
    }

    @GetMapping("/cancelSubscription")
    public String cancelSubscription(String email, Model model) {

        if (subscriptionRepository.findByEmail(email) == null) {
            return "noResults";
        }
        subscriptionRepository.delete(subscriptionRepository.findByEmail(email));
        return "subscriptionDetail";
    }

    @GetMapping("/subscribe")
    public String showSubscribeToNewsletterScreen(Model model) {
        model.addAttribute("subscription", new Subscription());
        return "subcriptionScreen";
    }



    @PostMapping("/subscribe")
    public String addSubscription(@ModelAttribute Subscription subscription, Model model) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = format.parse(subscription.getStringBirthDate());
            subscription.setBirthDate(parsedDate);

        } catch (ParseException e) {
            return "error";
        }

        subscriptionRepository.save(subscription);
        emailService.sendSimpleMessage(subscription.getEmail(), "Thank you for subscribing to our newsletter!", "Lorem ipsum");
        model.addAttribute("subscription", subscription);
        return "success";

    }

    @PostMapping(path="/deleteAll")
    public void deleteAllSubscriptions() {
        subscriptionRepository.deleteAll();
    }


    @ExceptionHandler(ConnectException.class)
    public String handleException() {
        return "mailServiceUnavailable";
    }

}