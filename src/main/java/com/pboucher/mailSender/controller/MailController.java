package com.pboucher.mailSender.controller;

import com.pboucher.mailSender.metier.Contact;
import com.pboucher.mailSender.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class MailController {

    @Resource
    private MailService mailService;

    @RequestMapping(value="/contacts/{montant}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity contacts(@PathVariable("montant") int montant, @RequestBody List<Contact> contacts) {
        HashMap<Contact, Contact> shuffleContacts = mailService.shuffleContacts(contacts);
        mailService.sendSimpleMail(shuffleContacts, montant);
        return ResponseEntity.ok(contacts);
    }
}
