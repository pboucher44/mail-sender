package com.pboucher.mailsender.repository

import com.pboucher.mailsender.entity.GiftIdea
import org.springframework.data.mongodb.repository.MongoRepository

interface GiftIdeaRepository: MongoRepository<GiftIdea, String> {
    fun save(gift: GiftIdea): GiftIdea
}
