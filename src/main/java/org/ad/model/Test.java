package org.ad.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The type Test
 *
 * @author nadeem Date : 10/08/24
 */
@Data
@Builder
public class Test {
    private String id;
    private String title;
    private String description;
    private List<Question> questions;
    private String instructions;
}
