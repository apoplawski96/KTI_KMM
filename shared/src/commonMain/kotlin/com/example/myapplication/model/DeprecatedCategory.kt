package com.example.myapplication.model

import com.example.myapplication.model.subcategory.SubCategory

@Deprecated("Moved to TopCategory & SubCategory models")
enum class DeprecatedCategory {
    Android,
    Kotlin,
    Compose,
    Rx,
    DesignPatterns,
    Git,
    StateAndSharedFlow,
    Flow,
    ProgrammingParadigms,
    Coroutines,
    Other;
}