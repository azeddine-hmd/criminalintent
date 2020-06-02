package com.innocent.learn.criminalintent.model

import java.util.*

data class Crime(
    val uuid: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false
)
