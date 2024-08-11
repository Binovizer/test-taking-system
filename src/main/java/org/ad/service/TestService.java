package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Test;

import java.util.List;

/**
 * The type TestService
 *
 * @author nadeem Date : 10/08/24
 */
public interface TestService {
    Test createTest(Test test);

    Test getTestById(String id);

    List<Test> getAllTests();

    Question addQuestionToTest(String testId, Question question);
}
