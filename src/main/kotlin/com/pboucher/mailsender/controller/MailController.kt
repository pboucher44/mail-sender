package com.pboucher.mailsender.controller

import com.pboucher.mailsender.metier.Contact
import com.pboucher.mailsender.service.MailService
import com.pboucher.mailsender.service.impl.MailServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource


@RestController
class MailController {

    @Resource
    private val mailService: MailService = MailServiceImpl()

    @RequestMapping(value = ["/contacts/{montant}"], method = [RequestMethod.POST])
    @ResponseBody
    fun contacts(@PathVariable("montant") montant: Int, @RequestBody contacts: List<Contact>): ResponseEntity<*>? {
        val shuffleContacts: HashMap<Contact, Contact> = mailService.shuffleContacts(contacts)
        mailService.sendSimpleMail(shuffleContacts, montant)
        return ResponseEntity.ok(contacts)
    }

}
