package co.apoplawski96.kti.questions.model

import co.apoplawski96.kti.questions.model.subcategory.SubCategory

enum class Category(val subCategories: List<SubCategory> = listOf()) {
    Android(subCategories = listOf()),
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