package co.apoplawski96.kti.questions.domain.interactors

import com.example.myapplication.legacy.QuestionsRepository
import com.example.myapplication.model.Question

class GetQuestionsShuffled(private val questionsRepository: QuestionsRepository) {

    operator fun invoke(): List<Question> = questionsRepository.getAllQuestions()
        .filter { question -> question.answer.isNotEmpty() }.shuffled()
}