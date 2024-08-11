package org.ad.model;

import lombok.Builder;
import lombok.Data;

/**
 * The type User
 *
 * @author nadeem Date : 10/08/24
 */
@Data
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
