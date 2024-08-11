package org.ad.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * The type org.ad.util.IdGenerationUtil
 *
 * @author nadeem Date : 10/08/24
 */
@UtilityClass
public class IdGenerationUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
