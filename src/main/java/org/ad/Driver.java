package org.ad;

import org.ad.model.*;
import org.ad.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/** Hello world! */
public class Driver {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User user =
                userService.addUser(
                        User.builder()
                                .firstName("Alice")
                                .lastName("Doe")
                                .email("alice.doe@email.com")
                                .role(UserRole.valueOf("student".toUpperCase()))
                                .build());

        TestServiceImpl testService = new TestServiceImpl();
        Test finalExamTest =
                testService.createTest(
                        Test.builder()
                                .title("Final Exam")
                                .description("Comprehensive exam covering all topics.")
                                .build());

        Question question1 =
                Question.builder()
                        .question("What is capital of France?")
                        .choices(Arrays.asList("A) London", "B) Paris", "C) Berlin", "D) Rome"))
                        .correctAnswer("B) Paris")
                        .marks(1)
                        .questionType(QuestionType.MULTIPLE_CHOICE)
                        .build();
        testService.addQuestionToTest(finalExamTest.getId(), question1);
        Question question2 =
                Question.builder()
                        .question("The earth revolves around the sub")
                        .choices(Arrays.asList("A) True", "B) False"))
                        .correctAnswer("A) True")
                        .marks(1)
                        .questionType(QuestionType.TRUE_FALSE)
                        .build();
        testService.addQuestionToTest(finalExamTest.getId(), question2);
        Question question3 =
                Question.builder()
                        .question("Explain the process of photosynthesis")
                        .correctAnswer("Synthesis of sunlight")
                        .marks(1)
                        .questionType(QuestionType.SHORT_ANSWER)
                        .build();
        testService.addQuestionToTest(finalExamTest.getId(), question3);

        ScoreCalculationService scoreCalculationService = new ScoreCalculationServiceImpl();
        TestExecutionService testExecutionService =
                new TestExecutionServiceImpl(testService, userService, scoreCalculationService);
        testExecutionService.startTest(finalExamTest.getId(), user.getId());

        AnswerSubmissionService answerSubmissionService =
                new AnswerSubmissionServiceImpl(userService, testService, testExecutionService);
        answerSubmissionService.submitAnswer(
                user.getId(), finalExamTest.getId(), question1.getId(), "B) Paris");
        answerSubmissionService.submitAnswer(
                user.getId(), finalExamTest.getId(), question2.getId(), "B) False");
        answerSubmissionService.submitAnswer(
                user.getId(), finalExamTest.getId(), question3.getId(), "Synthesis of sunlight");

        testExecutionService.endTest(user.getId(), finalExamTest.getId());

        Map<String, List<TestAttempt>> testResults =
                testExecutionService.getTestResults(finalExamTest.getId());
        System.out.println(testResults);
        List<TestAttempt> studentTestResults =
                testExecutionService.getStudentTestResults(user.getId(), finalExamTest.getId());
        System.out.println("studentTestResults = " + studentTestResults);
    }
}
