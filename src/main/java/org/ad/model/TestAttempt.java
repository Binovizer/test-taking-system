package org.ad.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * The type TestAttempt
 *
 * @author nadeem Date : 10/08/24
 */
@Data
@Builder
public class TestAttempt {
    private String id;
    private String testId;
    private String userId;
    private Map<Question, String> questionToAnswersSubmission;
    private int totalQuestions;
    private int correctAnswers;
    private String totalMarks;
    private String marksObtained;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private TestAttemptStatus status;
}
