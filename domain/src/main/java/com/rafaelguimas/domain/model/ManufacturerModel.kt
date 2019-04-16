package com.rafaelguimas.domain.model

data class ManufacturerModel(
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalPageCount: Int = 0,
    val wkda: HashMap<String, String> = hashMapOf()
)

