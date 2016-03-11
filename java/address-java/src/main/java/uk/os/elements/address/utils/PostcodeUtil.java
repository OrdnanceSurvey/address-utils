/*
 * Copyright (C) 2016 Ordnance Survey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.os.elements.address.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class PostcodeUtil {

    private PostcodeUtil() {}

    /**
     * @param value String under test
     * @return true if the input is a postcode candidate from partial or complete input
     */
    public static boolean isLikelyPostcode(String value) {
        Pattern pattern = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]?");
        Matcher matcher = pattern.matcher(value.toUpperCase());
        return matcher.find() && value.matches(".*\\d.*");
    }

    /**
     * @param value String under test
     * @return true if the input is a full postcode candidate, false if partial or highly unlikely
     */
    public static boolean isLikelyFullPostcode(String value) {
        Pattern pattern = Pattern.compile("[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y][ ]?[0-9][ABD-HJLNP-UW-Z][ABD-HJLNP-UW-Z]");
        Matcher matcher = pattern.matcher(value.toUpperCase());
        return matcher.find() && value.matches(".*\\d.*");
    }
}
