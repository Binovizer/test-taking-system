package org.ad.service;

import org.ad.model.Question;
import org.ad.model.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The type CommandLineInputService
 *
 * @author nadeem Date : 10/08/24
 */
public class CommandLineInputService implements InputService {
    @Override
    public Map<Question, String> captureUserAnswers(Test test) {
        Map<Question, String> questionAnswers = new HashMap<>();
        Scanner in = new Scanner(System.in);
        List<Question> questions = test.getQuestions();
        questions.forEach(
                question -> {
                    System.out.println(question.getQuestion());
                });
        in.close();
        return questionAnswers;
    }
}
