package org.ad.model;

import lombok.Builder;
import lombok.Data;

/**
 * The type Result
 *
 * @author nadeem Date : 10/08/24
 */
@Data
@Builder
public class Result {
    private String marksObtained;
    private int correctAnswers;
}
