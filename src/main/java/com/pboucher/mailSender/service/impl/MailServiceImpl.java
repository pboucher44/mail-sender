package com.pboucher.mailSender.service.impl;

import com.pboucher.mailSender.metier.Contact;
import com.pboucher.mailSender.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MailServiceImpl implements MailService {

    private static final String EMAIL_SECRET_SANTA = "secretsanta@pierreboucher.fr";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMail(HashMap<Contact, Contact> contacts) {
        for (Map.Entry<Contact, Contact> contact: contacts.entrySet()) {
            SimpleMailMessage message = emailTemplate(contact.getKey(), contact.getValue());
            emailSender.send(message);
        }
    }

    @Override
    public HashMap<Contact, Contact> shuffleContacts(List<Contact> contacts) {
        HashMap<Contact, Contact> res = new HashMap<>();
        List<Contact> randomContacts = new ArrayList<>(contacts);

        Collections.shuffle(randomContacts);

        for (int i = 0; i<contacts.size(); i++) {
            res.put(contacts.get(i), randomContacts.get(i));
        }

        return res;
    }

    private SimpleMailMessage emailTemplate(Contact firstContact, Contact secondContact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(firstContact.getEmail());
        message.setFrom(EMAIL_SECRET_SANTA);
        message.setSubject("Secret Santa | Père noel secret");
        message.setText("Chère " + firstContact.getNom() +"\n Tu devra offrir un cadeau à " + secondContact.getNom() +" d'un montant maximum de 8€");
        return message;
    }
}
