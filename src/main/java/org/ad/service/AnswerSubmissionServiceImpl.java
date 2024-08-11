package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Test;
import org.ad.model.TestAttempt;
import org.ad.model.User;

/**
 * The type AnswerSubmissionServiceImpl
 *
 * @author nadeem Date : 10/08/24
 */
public class AnswerSubmissionServiceImpl implements AnswerSubmissionService {

    private UserService userService;
    private TestService testService;
    private TestExecutionService testExecutionService;

    public AnswerSubmissionServiceImpl(
            UserService userService,
            TestService testService,
            TestExecutionService testExecutionService) {
        this.userService = userService;
        this.testService = testService;
        this.testExecutionService = testExecutionService;
    }

    @Override
    public void submitAnswer(
            String userId, String testId, String questionId, String submittedAnswer) {
        // Todo: Write Validations if time permits
        User user = userService.getUserById(userId);
        Test test = testService.getTestById(testId);
        TestAttempt activeTestAttempt = testExecutionService.getActiveTestAttempt(userId);
        for (Question question : test.getQuestions()) {
            if (question.getId().equals(questionId)) {
                activeTestAttempt.getQuestionToAnswersSubmission().put(question, submittedAnswer);
            }
        }
        System.out.println(
                "Answer for question : " + questionId + " submitted by user : " + userId);
    }
}
