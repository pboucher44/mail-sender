package com.pboucher.mailSender.service;

import com.pboucher.mailSender.metier.Contact;

import java.util.HashMap;
import java.util.List;

public interface MailService {

    /**
     * Permet l'envoi de mail a tout les contacts entré en parametre
     *
     * @param contacts
     * @param montant
     */
    void sendSimpleMail(HashMap<Contact, Contact> contacts, int montant);

    /**
     * Permet de mélanger la liste des contact et de retourner un HashMap de qui offre un cadeau a qui
     *
     * @param contacts
     */
    HashMap<Contact, Contact> shuffleContacts(List<Contact> contacts);
}
