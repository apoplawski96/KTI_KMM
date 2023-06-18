package com.example.myapplication.questions.model

import com.example.myapplication.questions.model.subcategory.SubCategory

@Deprecated("Moved to TopCategory & SubCategory models")
enum class DeprecatedCategory(val subCategories: List<SubCategory> = listOf()) {
    Android,
    Kotlin(subCategories = listOf()),
    Compose(subCategories = listOf()),
    Rx(subCategories = listOf()),
    DesignPatterns(subCategories = listOf()),
    Git(subCategories = listOf()),
    StateAndSharedFlow(subCategories = listOf()),
    Flow(subCategories = listOf()),
    ProgrammingParadigms(subCategories = listOf()),
    Coroutines(),
    Other,
}