package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Test;

import java.util.Map;

/**
 * The type InputService
 *
 * @author nadeem Date : 10/08/24
 */
public interface InputService {
    Map<Question, String> captureUserAnswers(Test test);
}
