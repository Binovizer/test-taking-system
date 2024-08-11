package org.ad.service;

/**
 * The type AnswerSubmissionService
 *
 * @author nadeem Date : 10/08/24
 */
public interface AnswerSubmissionService {
    void submitAnswer(String userId, String testId, String questionId, String submittedAnswer);
}
