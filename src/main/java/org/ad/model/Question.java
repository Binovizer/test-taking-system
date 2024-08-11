package org.ad.model;

import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.util.List;

/**
 * The type Question
 *
 * @author nadeem Date : 10/08/24
 */
@Data
@Builder
public class Question {
    private String id;
    private String question;
    private List<String> choices;
    private QuestionType questionType;
    private String correctAnswer;
    private int marks;
}
