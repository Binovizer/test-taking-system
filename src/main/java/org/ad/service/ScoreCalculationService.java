package org.ad.service;

import org.ad.model.Result;
import org.ad.model.Test;
import org.ad.model.TestAttempt;

/**
 * The type ScoreCalculationService
 *
 * @author nadeem Date : 10/08/24
 */
public interface ScoreCalculationService {
    Result calculate(Test testById, TestAttempt activeTestAttempt);
}
