package co.apoplawski96.kti.questions.domain.interactors

import co.apoplawski96.kti.questions.domain.QuestionsRepository
import co.apoplawski96.kti.questions.model.Question

class GetQuestionsShuffled(private val questionsRepository: QuestionsRepository) {

    operator fun invoke(): List<Question> = questionsRepository.getAllQuestions()
        .filter { question -> question.answer.isNotEmpty() }.shuffled()
}