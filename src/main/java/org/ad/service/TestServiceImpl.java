package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Test;
import org.ad.util.IdGenerationUtil;

import java.util.*;

/**
 * The type TestServiceImpl
 *
 * @author nadeem Date : 10/08/24
 */
public class TestServiceImpl implements TestService {

    private final Map<String, Test> testMap;

    public TestServiceImpl() {
        testMap = new HashMap<>();
    }

    @Override
    public Test createTest(Test test) {
        String id = IdGenerationUtil.generateId();
        test.setId(id);
        test.setQuestions(getQuestions(test));
        testMap.put(id, test);
        System.out.println("Created test titled : " + test.getTitle());
        return test;
    }

    @Override
    public Test getTestById(String id) {
        return testMap.get(id);
    }

    @Override
    public Question addQuestionToTest(String testId, Question question) {
        Test test = testMap.get(testId);
        question.setId(IdGenerationUtil.generateId());
        List<Question> questions = test.getQuestions();
        questions.add(question);
        System.out.println("Added question : " + question.getQuestion() + " to test : " + testId);
        return question;
    }

    @Override
    public List<Test> getAllTests() {
        return new ArrayList<>(testMap.values());
    }

    private List<Question> getQuestions(Test test) {
        return test.getQuestions() == null ? new ArrayList<>() : test.getQuestions();
    }

    public static void main(String[] args) {
        TestServiceImpl testService = new TestServiceImpl();
        Test finalExamTest =
                testService.createTest(
                        Test.builder()
                                .title("Final Exam")
                                .description("Comprehensive exam covering all topics.")
                                .build());
        List<Test> allTests = testService.getAllTests();
        System.out.println("allTests = " + allTests);
        testService.addQuestionToTest(
                finalExamTest.getId(),
                Question.builder()
                        .question("What is capital of France?")
                        .choices(Arrays.asList("A) London", "B) Paris", "C) Berlin", "D) Rome"))
                        .correctAnswer("B) Paris")
                        .marks(1)
                        .build());
        allTests = testService.getAllTests();
        System.out.println("allTests = " + allTests);
    }
}
