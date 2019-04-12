package com.rafaelguimas.domain.model

data class ManufacturerModel(
    val page: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val wkda: HashMap<String, String>
)

