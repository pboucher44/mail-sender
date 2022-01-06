package com.pboucher.mailsender.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class GiftIdea (
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val price: String,
    val image: String
)
