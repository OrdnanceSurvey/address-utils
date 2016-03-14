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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provide common utility functions for postcodes.
 *
 * <p>
 * Note:
 *
 * <ul>
 * <li>The Royal Mail is the data authority
 * <ul>
 * <li>they reserve the right to change their data at any time
 * </ul>
 * </li>
 * <li>the only way to establish if a postcode is valid is to check the source postcode data
 * <li>these utility functions are only checked against current postcodes; historical and future postcodes may fail these tests.
 * </ul>
 *
 * <p>
 * Breakdown of a postcode.
 *
 * <p>
 * Royal Mail (2016, p.18) provide the following example:
 *
 * Using postcode "PO1 1AF" the components are:
 * 'PO'1 1AF  - postcode area
 *  PO'1' 1AF - postcode district
 *  PO1 '1'AF - postcode sector
 *  PO1 1'AF' - unit postcode
 *
 *  In the following checks we will assume component consists of all preceding components.
 *
 * <a href="http://www.royalmail.com/sites/default/files/docs/pdf/programmers_guide_edition_7_v5.pdf">Royal Mail (2016), Programmers' Guide</a>
 */
public final class PostcodeUtil {

    private static final Set<String> POSTCODE_AREAS = getPostcodeAreas();

    /**
     * test if at least district level postcode
     * // TODO agree definition with API, Data Delivery and Mobile teams
     * @param value String under test
     * @return true if the input is likely to be a postcode
     */
    public static boolean isLikelyPostcode(String value) {
        return isLikelyDistrictPostcode(value) || isLikelySectorPostcode(value) || isLikelyFullPostcode(value);
    }

    /**
     * @param value String under test
     * @return true if the input is a full postcode candidate, false if partial or highly unlikely
     */
    public static boolean isLikelyFullPostcode(String value) {
        return isLikelyUnitPostcode(value);
    }

    /**
     * Assuming the postcode 'PO1 1AF', the area postcode is 'PO'
     * @param value String under test
     * @return true if considered an area postcode
     */
    public static boolean isLikelyAreaPostcode(String value) {
        return POSTCODE_AREAS.contains(value.toUpperCase().trim());
    }

    /**
     * Assuming the postcode 'PO1 1AF', the district postcode is 'PO1'
     * @param value String under test
     * @return true if considered a district postcode
     */
    public static boolean isLikelyDistrictPostcode(String value) {
        Pattern pattern = Pattern.compile("^[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y]$");
        Matcher matcher = pattern.matcher(value.toUpperCase().trim());
        return matcher.find() && value.matches(".*\\d.*");
    }

    /**
     * Assuming the postcode 'PO1 1AF', the district postcode is 'PO1'
     *
     * Given A = alpha character and N = numeric character, this method will validate that the format
     * adheres to the patterns below and that the area postcode matches known values (data).
     *
     * Valid Format: AN, ANN, AAN, AANN, ANA, AANA
     *
     * @param value String under test
     * @return true if considered a district postcode
     */
    public static boolean isLikelyDistrictPostcodeStrict(String value) {
        Pattern pattern = Pattern.compile(District.PATTERN);
        Matcher matcher = pattern.matcher(value.trim());
        return matcher.find() && POSTCODE_AREAS.contains(value.toUpperCase().replaceAll("\\d.*", ""));
    }

    /**
     * Assuming the postcode 'PO1 1AF', the sector postcode is 'PO1 1'
     * @param value String under test
     * @return true if considered a sector postcode
     */
    public static boolean isLikelySectorPostcode(String value) {
        Pattern pattern = Pattern.compile("^[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y][ ]?[0-9]$");
        Matcher matcher = pattern.matcher(value.toUpperCase().trim());
        return matcher.find();
    }

    /**
     * Assuming the postcode 'PO1 1AF', the sector postcode is 'PO1 1'
     * @param value String under test
     * @return true if considered a sector postcode and contains a single space to differentiate the sector
     */
    public static boolean isLikelySectorPostcodeStrict(String value) {
        Pattern pattern = Pattern.compile("^[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y][ ][0-9]$");
        Matcher matcher = pattern.matcher(value.toUpperCase().trim());
        return matcher.find();
    }

    /**
     * A unit postcode is a fully completed postcode, such as 'PO1 1AF'
     * @param value String under test
     * @return true if considered a unit postcode
     */
    public static boolean isLikelyUnitPostcode(String value) {
        Pattern pattern = Pattern.compile("^[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y][ ]?[0-9][ABD-HJLNP-UW-Z][ABD-HJLNP-UW-Z]$");
        Matcher matcher = pattern.matcher(value.toUpperCase().trim());
        return matcher.find();
    }

    /**
     * A unit postcode is a fully completed postcode, such as 'PO1 1AF'
     * @param value String under test
     * @return true if considered a unit postcode and contains a single space to differentiate the sector and unit values
     */
    public static boolean isLikelyUnitPostcodeStrict(String value) {
        Pattern pattern = Pattern.compile("^[A-IK-PR-UWYZ]?[A-H,K-Y][0-9]?[0-9A-HJKMNP-Y][ ][0-9][ABD-HJLNP-UW-Z][ABD-HJLNP-UW-Z]$");
        Matcher matcher = pattern.matcher(value.toUpperCase().trim());
        return matcher.find();
    }

    private static Set<String> getPostcodeAreas() {
        Set<String> postcodeAreas = new HashSet<String>();
        ClassLoader classLoader = new PostcodeUtil().getClass().getClassLoader();
        File file = new File(classLoader.getResource("uk_postcode_areas").getFile());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine().trim();
                if (item != null && !item.isEmpty()) {
                    postcodeAreas.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("cannot load postcode areas");
        }
        return postcodeAreas;
    }

    /**
     * http://www.royalmail.com/sites/default/files/docs/pdf/programmers_guide_edition_7_v5.pdf, p.18
     */
    private static class District {
        private static final String A = "[a-zA-Z]";
        private static final String N = "[0-9]";
        private static final String D1 = A + N;
        private static final String D2 = A + N + N;
        private static final String D3 = A + A + N;
        private static final String D4 = A + A + N + N;
        private static final String D5 = A + N + A;
        private static final String D6 = A + A + N + A;
        private static final String PATTERN = "^(" + w(D1) + "|" + w(D2) + "|" + w(D3) + "|" + w(D4) + "|" + w(D5) + "|" + w(D6) + ")$";
        private static String w(String s) {
            return "(?:" + s + ")";
        }
    }
}
