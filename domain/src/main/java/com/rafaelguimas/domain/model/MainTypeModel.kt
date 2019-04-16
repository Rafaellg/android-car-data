package com.rafaelguimas.domain.model

data class MainTypeModel(
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalPageCount: Int = 0,
    val wkda: HashMap<String, String> = hashMapOf()
)

