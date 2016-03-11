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

package uk.os.elements;

import org.junit.Test;
import uk.os.elements.address.utils.PostcodeUtil;

import static org.junit.Assert.assertTrue;

public class PostcodeUtilTest {

    private static final String FULL_POSTCODE_ORDNANCE_SURVEY = "SO16 0AS";
    private static final String FULL_POSTCODE_BBC = "W1W 5QZ";
    private static final String FULL_POSTCODE_BUCKINGHAM_PALACE = "SW1A 1AA";
    private static final String FULL_POSTCODE_HOUSES_OF_PARLIAMENT = "SW1A 0AA";
    private static final String FULL_POSTCODE_CAMDEN = "NW1 0NE";
    private static final String FULL_POSTCODE_EDINBURGH_CASTLE = "EH1 2NG";
    private static final String FULL_POSTCODE_ABERYSTWYTH_UNIVERSITY = "EH1 2NG";
    private static final String FULL_POSTCODE_LANDS_END = "TR19 7AA";
    private static final String FULL_POSTCODE_SHERWOOD_FOREST = "NG21 9JL";
    private static final String FULL_POSTCODE_YORK = "YO1 7JN";


    @Test
    public void shouldMatchFullPostcodes() {
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_ORDNANCE_SURVEY);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_BBC);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_BUCKINGHAM_PALACE);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_CAMDEN);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_EDINBURGH_CASTLE);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_LANDS_END);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_SHERWOOD_FOREST);
        checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchFullPostcodesAsLikely() {
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_ORDNANCE_SURVEY);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_BBC);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_BUCKINGHAM_PALACE);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_CAMDEN);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_EDINBURGH_CASTLE);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_LANDS_END);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_SHERWOOD_FOREST);
        checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(FULL_POSTCODE_YORK);
    }

    private void checkFullPostcodeIsLikelyPostcodeAndIgnoreCaseAndSpacing(String s) {
        assertTrue("test input", PostcodeUtil.isLikelyPostcode(s));
        assertTrue("lowercase input", PostcodeUtil.isLikelyPostcode(s.toLowerCase()));
        assertTrue("uppercase input", PostcodeUtil.isLikelyPostcode(s.toUpperCase()));
        assertTrue("spacing removed input", PostcodeUtil.isLikelyPostcode(s.replaceAll("\\s","")));
    }

    private void checkLikelyFullPostcodeAndIgnoreCaseAndSpacing(String s) {
        assertTrue("test input", PostcodeUtil.isLikelyFullPostcode(s));
        assertTrue("lowercase input", PostcodeUtil.isLikelyFullPostcode(s.toLowerCase()));
        assertTrue("uppercase input", PostcodeUtil.isLikelyFullPostcode(s.toUpperCase()));
        assertTrue("spacing removed input", PostcodeUtil.isLikelyFullPostcode(s.replaceAll("\\s","")));
    }
}
