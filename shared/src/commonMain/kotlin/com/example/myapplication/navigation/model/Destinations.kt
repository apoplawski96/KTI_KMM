package co.apoplawski96.kti.navigation.model

object Destinations {

    object Menu : NavigationDestination {

        override val route: String = "destination_menu"
        override val genericRoute: String = "destination_menu"
        override val arguments: List<NavArgument> = listOf()
    }

    object QuestionsQuiz : NavigationDestination {

        override val route: String
            get() = "destination_quiz"
        override val genericRoute: String
            get() = "destination_quiz"
        override val arguments: List<NavArgument>
            get() = listOf()
    }

    object QuestionsList : NavigationDestination {

        override val route: String
            get() = "destination_list"
        override val genericRoute: String
            get() = "destination_list"
        override val arguments: List<NavArgument>
            get() = listOf()
    }

    object Dogs : NavigationDestination {

        override val route: String
            get() = "destination_dogs"
        override val genericRoute: String
            get() = "destination_dogs"
        override val arguments: List<NavArgument>
            get() = listOf()
    }

    object Categories : NavigationDestination {

        override val route: String
            get() = "destination_categories"
        override val genericRoute: String
            get() = "destination_categories"
        override val arguments: List<NavArgument>
            get() = listOf()
    }
}