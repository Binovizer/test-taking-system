package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Result;
import org.ad.model.Test;
import org.ad.model.TestAttempt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type ScoreCalculationServiceImpl
 *
 * @author nadeem Date : 10/08/24
 */
public class ScoreCalculationServiceImpl implements ScoreCalculationService {
    @Override
    public Result calculate(Test test, TestAttempt activeTestAttempt) {
        Result result = Result.builder().build();
        AtomicInteger correctAnswers = new AtomicInteger();
        AtomicInteger marksObtained = new AtomicInteger();
        activeTestAttempt
                .getQuestionToAnswersSubmission()
                .forEach(
                        (attemptedQuestion, submittedAnswer) -> {
                            for (Question q : test.getQuestions()) {
                                if (q.getId().equals(attemptedQuestion.getId())
                                        && q.getCorrectAnswer().equals(submittedAnswer)) {
                                    correctAnswers.getAndIncrement();
                                    marksObtained.getAndIncrement();
                                }
                            }
                        });
        result.setCorrectAnswers(correctAnswers.get());
        result.setMarksObtained(String.valueOf(marksObtained.get()));
        return result;
    }
}
