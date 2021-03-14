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
    public void sendSimpleMail(HashMap<Contact, Contact> contacts, int montant) {
        for (Map.Entry<Contact, Contact> contact: contacts.entrySet()) {
            SimpleMailMessage message = emailTemplate(contact.getKey(), contact.getValue(), montant);
            emailSender.send(message);
        }
    }

    @Override
    public HashMap<Contact, Contact> shuffleContacts(List<Contact> contacts) {
        HashMap<Contact, Contact> res = new HashMap<>();
        List<Contact> randomContacts = new ArrayList<>(contacts);

        randomContacts = shuffleList(randomContacts);

        for (int i = 0; i<contacts.size(); i++) {
            res.put(contacts.get(i), randomContacts.get(i));
        }

        return res;
    }

    /**
     * Collections.shuffle pouvais laisser un contact envoyer un cadeau a lui meme
     *
     * @param contacts
     * @return
     */
    private List<Contact> shuffleList(List<Contact> contacts) {
        List<Contact> oldContact = new ArrayList<>(contacts);
        Collections.shuffle(contacts);
        List<Contact> save = new ArrayList<>(contacts);

        for (int i = 0; i < contacts.size(); i++) {
            if (oldContact.get(i) == contacts.get(i)) {
                if (i < contacts.size()-1) {
                    contacts.set(i, save.get(i+1));
                    contacts.set(i+1, save.get(i));
                } else if (i == contacts.size()-1) {
                    contacts.set(i, save.get(0));
                    contacts.set(0, save.get(i));
                }
            }
        }

        return contacts;
    }

    private SimpleMailMessage emailTemplate(Contact firstContact, Contact secondContact, int montant) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(firstContact.getEmail());
        message.setFrom(EMAIL_SECRET_SANTA);
        message.setSubject("Secret Santa | Père noel secret");
        message.setText("Chère " + firstContact.getNom() +"\n Tu devra offrir un cadeau à " + secondContact.getNom() +" d'un montant maximum de " + montant + "€");
        return message;
    }
}
