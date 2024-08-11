package org.ad.service;

import org.ad.model.TestAttempt;

import java.util.List;
import java.util.Map;

/**
 * The type TestExecutionService
 *
 * @author nadeem Date : 10/08/24
 */
public interface TestExecutionService {
    void startTest(String testId, String userId);

    void endTest(String userId, String testId);

    Map<String, List<TestAttempt>> getTestResults(String testId);

    List<TestAttempt> getStudentTestResults(String userId, String testId);

    TestAttempt getActiveTestAttempt(String userId);
}
