package org.ad.service;

import org.ad.model.*;
import org.ad.util.IdGenerationUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type TestExecutionServiceImpl
 *
 * @author nadeem Date : 10/08/24
 */
public class TestExecutionServiceImpl implements TestExecutionService {

    // In memory DB
    private final Map<String, List<TestAttempt>> usersTestAttempts;
    private final Map<String, TestAttempt> activeTests;
    private final Map<String, Map<String, List<TestAttempt>>> testToTestAttempts;

    private final ScoreCalculationService scoreCalculationService;
    private final TestService testService;
    private final UserService userService;

    public TestExecutionServiceImpl(
            TestService testService,
            UserService userService,
            ScoreCalculationService scoreCalculationService) {
        usersTestAttempts = new HashMap<>();
        activeTests = new HashMap<>();
        testToTestAttempts = new HashMap<>();

        this.testService = testService;
        this.userService = userService;
        this.scoreCalculationService = scoreCalculationService;
    }

    @Override
    public void startTest(String testId, String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User Doesn't exist");
        }
        if (activeTests.containsKey(userId)) {
            throw new RuntimeException("User is already in another test");
        }
        Test test = testService.getTestById(testId);
        if (test == null) {
            throw new RuntimeException("Test Doesn't exist");
        }

        String id = IdGenerationUtil.generateId();
        TestAttempt testAttempt =
                TestAttempt.builder()
                        .id(id)
                        .testId(testId)
                        .status(TestAttemptStatus.STARTED)
                        .questionToAnswersSubmission(new HashMap<>())
                        .startTimestamp(LocalDateTime.now())
                        .build();
        activeTests.put(userId, testAttempt);

        System.out.println("Student : " + userId + " has started the test : " + testId);
    }

    @Override
    public void endTest(String userId, String testId) {
        Test testById = testService.getTestById(testId);
        TestAttempt activeTestAttempt = activeTests.get(userId);

        Result result = scoreCalculationService.calculate(testById, activeTestAttempt);
        activeTestAttempt.setMarksObtained(result.getMarksObtained());
        activeTestAttempt.setTotalQuestions(testById.getQuestions().size());
        activeTestAttempt.setCorrectAnswers(result.getCorrectAnswers());
        activeTestAttempt.setStatus(TestAttemptStatus.COMPLETED);
        activeTestAttempt.setEndTimestamp(LocalDateTime.now());

        TestAttempt testAttempt = activeTests.get(userId);
        List<TestAttempt> attemptedTests =
                usersTestAttempts.getOrDefault(userId, new ArrayList<>());
        attemptedTests.add(testAttempt);
        usersTestAttempts.put(userId, attemptedTests);

        Map<String, List<TestAttempt>> userTestAttemptMap =
                testToTestAttempts.getOrDefault(testId, new HashMap<>());
        userTestAttemptMap.put(userId, new ArrayList<>(attemptedTests));
        testToTestAttempts.put(testId, userTestAttemptMap);

        System.out.println("Test : " + testId + " completed by student : " + userId);
    }

    @Override
    public Map<String, List<TestAttempt>> getTestResults(String testId) {
        System.out.println("Test Results each user for Test Id: " + testId);
        return this.testToTestAttempts.get(testId);
    }

    @Override
    public List<TestAttempt> getStudentTestResults(String userId, String testId) {
        System.out.println("Test Results for user: " + userId + " for test Id: " + testId);
        return usersTestAttempts.get(userId).stream()
                .filter(testAttempt -> testAttempt.getTestId().equals(testId))
                .collect(Collectors.toList());
    }

    @Override
    public TestAttempt getActiveTestAttempt(String userId) {
        TestAttempt testAttempt = activeTests.get(userId);
        if (testAttempt == null || testAttempt.getStatus() != TestAttemptStatus.STARTED) {
            throw new RuntimeException("Test isn't active for this user");
        }
        return testAttempt;
    }
}
