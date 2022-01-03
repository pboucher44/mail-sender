package com.pboucher.mailsender.service

import com.pboucher.mailsender.metier.Contact


interface MailService {
    /**
     * Permet l'envoi de mail a tout les contacts entré en parametre
     *
     * @param contacts
     * @param montant
     */
    fun sendSimpleMail(contacts: HashMap<Contact, Contact>, montant: Int)

    /**
     * Permet de mélanger la liste des contact et de retourner un HashMap de qui offre un cadeau a qui
     *
     * @param contacts
     */
    fun shuffleContacts(contacts: List<Contact>): HashMap<Contact, Contact>
}
