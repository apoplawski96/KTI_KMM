package com.example.myapplication.model.subcategory

enum class TopCategory(
    val id: String,
    override val displayName: String,
) : CardDisplayable {
    ANDROID(id = "1", displayName = "Android"),
//    IOS(id = "2", displayName = "iOS"),
    GIT("3", "GIT"),
    REST("4", "Rest"),
//    DATA_STRUCTURES("5", "Data structures"),
//    ALGORITHMS("6", "Algorithms"),
    DESIGN_PATTERNS("7", "Design Patterns"),
    PROGRAMMING_PARADIGMS("13", "Programming Paradigms"),
//    REACT("8", "React"),
//    WEB("9", "Web"),
//    C_SHARP("10", "C#"),
//    DOT_NET("11", ".NET"),
//    ANGULAR("11", "Angular"),
//    GRAPH_QL("11", "Graph QL"),
//    SCRUM("11", "Scrum"),
//    CPP("11", "C++"),
    KOTLIN("12", "Kotlin");

    companion object {

        fun getForId(id: String): TopCategory? = values().toList().firstOrNull { category -> id == category.id }

        fun getForName(name: String): TopCategory? = values().toList().firstOrNull { category -> name == category.displayName }
    }
}

