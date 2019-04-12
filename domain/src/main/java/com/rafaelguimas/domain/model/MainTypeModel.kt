package com.rafaelguimas.domain.model

data class MainTypeModel(
    val page: Int,
    val pageSize: Int,
    val totalPageCount: Int,
    val wkda: HashMap<String, String>
)

