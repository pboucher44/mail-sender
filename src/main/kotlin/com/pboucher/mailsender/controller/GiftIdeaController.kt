package com.pboucher.mailsender.controller

import com.pboucher.mailsender.entity.GiftIdea
import com.pboucher.mailsender.repository.GiftIdeaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/gift")
class GiftIdeaController (@Autowired val giftIdeaRepository: GiftIdeaRepository){

    @GetMapping
    fun getAllGiftIdea(): ResponseEntity<List<GiftIdea>> {
        val giftIdea = giftIdeaRepository.findAll()
        return ResponseEntity.ok(giftIdea)
    }

    @PostMapping
    fun createGiftIdea(@RequestBody request: GiftIdea): ResponseEntity<GiftIdea> {
        val giftIdea = giftIdeaRepository.save(GiftIdea(
            id = request.id,
            name = request.name,
            price = request.price,
            image = request.image
        ))
        return ResponseEntity(giftIdea, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun removeGiftIdea(@PathVariable("id") id: String): ResponseEntity<HttpStatus> {
        giftIdeaRepository.deleteById(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
