package com.pboucher.mailsender.service.impl

import com.pboucher.mailsender.metier.Contact
import com.pboucher.mailsender.service.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap


@Service
class MailServiceImpl: MailService {

    private val EMAIL_SECRET_SANTA = "secretsanta@pierreboucher.fr"

    @Autowired
    private lateinit var emailSender: JavaMailSender

    override fun sendSimpleMail(contacts: HashMap<Contact, Contact>, montant: Int) {
        for ((key, value) in contacts) {
            val message: SimpleMailMessage = emailTemplate(key, value, montant)
            emailSender.send(message)
        }
    }

    override fun shuffleContacts(contacts: List<Contact>): HashMap<Contact, Contact> {
        val res = HashMap<Contact, Contact>()
        var randomContacts: MutableList<Contact> = ArrayList(contacts)
        randomContacts = shuffleList(randomContacts)
        for (i in contacts.indices) {
            res[contacts[i]] = randomContacts[i]
        }
        return res
    }

    /**
     * Collections.shuffle pouvais laisser un contact envoyer un cadeau a lui meme
     *
     * @param contacts
     * @return
     */
    private fun shuffleList(contacts: MutableList<Contact>): MutableList<Contact> {
        val oldContact: List<Contact> = ArrayList(contacts)
        Collections.shuffle(contacts)
        val save: List<Contact> = ArrayList(contacts)
        for (i in contacts.indices) {
            if (oldContact[i] == contacts[i]) {
                if (i < contacts.size - 1) {
                    contacts[i] = save[i + 1]
                    contacts[i + 1] = save[i]
                } else if (i == contacts.size - 1) {
                    contacts[i] = save[0]
                    contacts[0] = save[i]
                }
            }
        }
        return contacts
    }

    private fun emailTemplate(firstContact: Contact, secondContact: Contact, montant: Int): SimpleMailMessage {
        val message = SimpleMailMessage()
        message.setTo(firstContact.email)
        message.setFrom(EMAIL_SECRET_SANTA)
        message.setSubject("Secret Santa | Père noel secret")
        message.setText(
            "Chère ${firstContact.nom}, Tu devra offrir un cadeau à ${secondContact.nom} d'un montant maximum de ${montant}€".trimMargin()
        )
        return message
    }
}
