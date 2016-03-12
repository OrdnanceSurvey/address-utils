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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PostcodeUtilTest {

    // Area postcodes
    private static final String AREA_POSTCODE_ORDNANCE_SURVEY = "SO";
    private static final String AREA_POSTCODE_BBC = "W";
    private static final String AREA_POSTCODE_BUCKINGHAM_PALACE = "SW";
    private static final String AREA_POSTCODE_HOUSES_OF_PARLIAMENT = "SW";
    private static final String AREA_POSTCODE_CAMDEN = "NW";
    private static final String AREA_POSTCODE_EDINBURGH_CASTLE = "EH";
    private static final String AREA_POSTCODE_ABERYSTWYTH_UNIVERSITY = "EH";
    private static final String AREA_POSTCODE_LANDS_END = "TR";
    private static final String AREA_POSTCODE_SHERWOOD_FOREST = "NG";
    private static final String AREA_POSTCODE_YORK = "YO";
    private static final String[] AREA_POSTCODES = { AREA_POSTCODE_ORDNANCE_SURVEY, AREA_POSTCODE_BBC,
            AREA_POSTCODE_BUCKINGHAM_PALACE, AREA_POSTCODE_HOUSES_OF_PARLIAMENT, AREA_POSTCODE_CAMDEN,
            AREA_POSTCODE_EDINBURGH_CASTLE, AREA_POSTCODE_ABERYSTWYTH_UNIVERSITY, AREA_POSTCODE_LANDS_END,
            AREA_POSTCODE_SHERWOOD_FOREST, AREA_POSTCODE_YORK };

    // District postcodes
    private static final String DISTRICT_POSTCODE_ORDNANCE_SURVEY = "SO16";
    private static final String DISTRICT_POSTCODE_BBC = "W1W";
    private static final String DISTRICT_POSTCODE_BUCKINGHAM_PALACE = "SW1A";
    private static final String DISTRICT_POSTCODE_HOUSES_OF_PARLIAMENT = "SW1A";
    private static final String DISTRICT_POSTCODE_CAMDEN = "NW1";
    private static final String DISTRICT_POSTCODE_EDINBURGH_CASTLE = "EH1";
    private static final String DISTRICT_POSTCODE_ABERYSTWYTH_UNIVERSITY = "EH1";
    private static final String DISTRICT_POSTCODE_LANDS_END = "TR19";
    private static final String DISTRICT_POSTCODE_SHERWOOD_FOREST = "NG21";
    private static final String DISTRICT_POSTCODE_YORK = "YO1";
    private static final String[] DISTRICT_POSTCODES = { DISTRICT_POSTCODE_ORDNANCE_SURVEY, DISTRICT_POSTCODE_BBC,
            DISTRICT_POSTCODE_BUCKINGHAM_PALACE, DISTRICT_POSTCODE_HOUSES_OF_PARLIAMENT, DISTRICT_POSTCODE_CAMDEN,
            DISTRICT_POSTCODE_EDINBURGH_CASTLE, DISTRICT_POSTCODE_ABERYSTWYTH_UNIVERSITY, DISTRICT_POSTCODE_LANDS_END,
            DISTRICT_POSTCODE_SHERWOOD_FOREST, DISTRICT_POSTCODE_YORK };

    // Sector postcodes
    private static final String SECTOR_POSTCODE_ORDNANCE_SURVEY = "SO16 0";
    private static final String SECTOR_POSTCODE_BBC = "W1W 5";
    private static final String SECTOR_POSTCODE_BUCKINGHAM_PALACE = "SW1A 1";
    private static final String SECTOR_POSTCODE_HOUSES_OF_PARLIAMENT = "SW1A 0";
    private static final String SECTOR_POSTCODE_CAMDEN = "NW1 0";
    private static final String SECTOR_POSTCODE_EDINBURGH_CASTLE = "EH1 2";
    private static final String SECTOR_POSTCODE_ABERYSTWYTH_UNIVERSITY = "EH1 2";
    private static final String SECTOR_POSTCODE_LANDS_END = "TR19 7";
    private static final String SECTOR_POSTCODE_SHERWOOD_FOREST = "NG21 9";
    private static final String SECTOR_POSTCODE_YORK = "YO1 7";
    private static final String[] SECTOR_POSTCODES = { SECTOR_POSTCODE_ORDNANCE_SURVEY, SECTOR_POSTCODE_BBC,
            SECTOR_POSTCODE_BUCKINGHAM_PALACE, SECTOR_POSTCODE_HOUSES_OF_PARLIAMENT, SECTOR_POSTCODE_CAMDEN,
            SECTOR_POSTCODE_EDINBURGH_CASTLE, SECTOR_POSTCODE_ABERYSTWYTH_UNIVERSITY, SECTOR_POSTCODE_LANDS_END,
            SECTOR_POSTCODE_SHERWOOD_FOREST, SECTOR_POSTCODE_YORK };

    // Unit postcodes to most precise type
    private static final String UNIT_POSTCODE_ORDNANCE_SURVEY = "SO16 0AS";
    private static final String UNIT_POSTCODE_BBC = "W1W 5QZ";
    private static final String UNIT_POSTCODE_BUCKINGHAM_PALACE = "SW1A 1AA";
    private static final String UNIT_POSTCODE_HOUSES_OF_PARLIAMENT = "SW1A 0AA";
    private static final String UNIT_POSTCODE_CAMDEN = "NW1 0NE";
    private static final String UNIT_POSTCODE_EDINBURGH_CASTLE = "EH1 2NG";
    private static final String UNIT_POSTCODE_ABERYSTWYTH_UNIVERSITY = "EH1 2NG";
    private static final String UNIT_POSTCODE_LANDS_END = "TR19 7AA";
    private static final String UNIT_POSTCODE_SHERWOOD_FOREST = "NG21 9JL";
    private static final String UNIT_POSTCODE_YORK = "YO1 7JN";
    private static final String[] UNIT_POSTCODES = { UNIT_POSTCODE_ORDNANCE_SURVEY, UNIT_POSTCODE_BBC,
            UNIT_POSTCODE_BUCKINGHAM_PALACE, UNIT_POSTCODE_HOUSES_OF_PARLIAMENT, UNIT_POSTCODE_CAMDEN,
            UNIT_POSTCODE_EDINBURGH_CASTLE, UNIT_POSTCODE_ABERYSTWYTH_UNIVERSITY, UNIT_POSTCODE_LANDS_END,
            UNIT_POSTCODE_SHERWOOD_FOREST, UNIT_POSTCODE_YORK };

    // Alternative test
    private static final String PLACE_ORDNANCE_SURVEY = "Ordnance Survey, Explorer House";
    private static final String PLACE_BBC = "180 Great Portland Street";
    private static final String PLACE_BUCKINGHAM_PALACE = "Buckingham Palace, London";
    private static final String PLACE_ONE_CHAR = "B";
    private static final String PLACE_TWO_CHAR = "BA";
    private static final String PLACE_THREE_CHAR = "BAT";
    private static final String PLACE_FOUR_CHAR = "BATH";
    private static final String[] ALTERNATIVES = {PLACE_ORDNANCE_SURVEY, PLACE_BBC, PLACE_BUCKINGHAM_PALACE,
            PLACE_ONE_CHAR, PLACE_TWO_CHAR, PLACE_THREE_CHAR, PLACE_FOUR_CHAR };

    @Test
    public void shouldMatchUnitPostcodesStrict() {
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_BBC);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_CAMDEN);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_LANDS_END);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(UNIT_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchAreaPostcodes() {
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_BBC);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_CAMDEN);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_LANDS_END);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(AREA_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchDistrictPostcodesStrict() {
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BBC);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_CAMDEN);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_LANDS_END);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchDistrictPostcodes() {
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BBC);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_CAMDEN);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_LANDS_END);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchSectorPostcodes() {
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_BBC);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_CAMDEN);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_LANDS_END);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchUnitPostcodes() {
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_BBC);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_CAMDEN);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_LANDS_END);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchDistrictPostcodesAsLikely() {
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BBC);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_CAMDEN);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_LANDS_END);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(DISTRICT_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchSectorPostcodesAsLikely() {
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_BBC);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_CAMDEN);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_LANDS_END);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(SECTOR_POSTCODE_YORK);
    }

    @Test
    public void shouldMatchUnitPostcodesAsLikely() {
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_ORDNANCE_SURVEY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_BBC);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_BUCKINGHAM_PALACE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_HOUSES_OF_PARLIAMENT);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_CAMDEN);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_EDINBURGH_CASTLE);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_ABERYSTWYTH_UNIVERSITY);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_LANDS_END);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_SHERWOOD_FOREST);
        checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(UNIT_POSTCODE_YORK);
    }

    @Test
    public void shouldNotMatchOtherThanDistrict() {
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(false, AREA_POSTCODES);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(false, false, SECTOR_POSTCODES);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(false, UNIT_POSTCODES);
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(false, ALTERNATIVES);
    }

    @Test
    public void shouldNotMatchOtherThanDistrictStrict() {
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(false, AREA_POSTCODES);
        //checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(false, false, SECTOR_POSTCODES);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(false, UNIT_POSTCODES);
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(false, ALTERNATIVES);
    }

    @Test
    public void shouldNotMatchOtherThanSector() {
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(false, AREA_POSTCODES);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(false, DISTRICT_POSTCODES);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(false, UNIT_POSTCODES);
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(false, ALTERNATIVES);
    }

    @Test
    public void shouldNotMatchOtherThanArea() {
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(false, DISTRICT_POSTCODES);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(false, SECTOR_POSTCODES);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(false, UNIT_POSTCODES);
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(false, PLACE_ORDNANCE_SURVEY, PLACE_BBC, PLACE_BUCKINGHAM_PALACE,
                PLACE_THREE_CHAR, PLACE_FOUR_CHAR );
    }

    @Test
    public void shouldNotMatchOtherThanUnit() {
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(false, AREA_POSTCODES);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(false, AREA_POSTCODES);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(false, DISTRICT_POSTCODES);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(false, SECTOR_POSTCODES);
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(false, ALTERNATIVES);
    }

    @Test
    public void shouldNotMatchPlacesWithFullOrLikelyPostcode() {
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_ORDNANCE_SURVEY));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_BBC));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_BUCKINGHAM_PALACE));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_ONE_CHAR));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_TWO_CHAR));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_THREE_CHAR));
        assertFalse(PostcodeUtil.isLikelyFullPostcode(PLACE_FOUR_CHAR));


        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_ORDNANCE_SURVEY));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_BBC));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_BUCKINGHAM_PALACE));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_ONE_CHAR));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_TWO_CHAR));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_THREE_CHAR));
        assertFalse(PostcodeUtil.isLikelyPostcode(PLACE_FOUR_CHAR));
    }

    @Test
    public void shouldNotMatchUnitPostcodesStrict() {
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(false, AREA_POSTCODES);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(false, DISTRICT_POSTCODES);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(false, SECTOR_POSTCODES);
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(false, withoutSpaces(UNIT_POSTCODES));
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(false, ALTERNATIVES);
    }

    private void checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(String s) {
        checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(true, s);
    }

    private void checkIsLikelyFullPostcodeAndIgnoreCaseAndSpacing(boolean isExpected, String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelyFullPostcode(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelyFullPostcode(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelyFullPostcode(s.toUpperCase()));
                assertTrue("spacing removed input", PostcodeUtil.isLikelyFullPostcode(s.replaceAll("\\s","")));
            } else {
                assertFalse("test input", PostcodeUtil.isLikelyFullPostcode(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelyFullPostcode(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelyFullPostcode(s.toUpperCase()));
                assertFalse("spacing removed input", PostcodeUtil.isLikelyFullPostcode(s.replaceAll("\\s","")));
            }
        }
    }

    private void checkIsLikelyPostcodeAndIgnoreCaseAndSpacing(String s) {
        assertTrue("test input", PostcodeUtil.isLikelyPostcode(s));
        assertTrue("lowercase input", PostcodeUtil.isLikelyPostcode(s.toLowerCase()));
        assertTrue("uppercase input", PostcodeUtil.isLikelyPostcode(s.toUpperCase()));
        assertTrue("spacing removed input", PostcodeUtil.isLikelyPostcode(s.replaceAll("\\s","")));
    }

    private void checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(String s) {
        checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(true, s);
    }

    private void checkIsLikelyAreaPostcodeAndIgnoreCaseAndSpacing(boolean isExpected, String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelyAreaPostcode(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelyAreaPostcode(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelyAreaPostcode(s.toUpperCase()));
                assertTrue("spacing removed input", PostcodeUtil.isLikelyAreaPostcode(s.replaceAll("\\s","")));
            } else {
                assertFalse("test input", PostcodeUtil.isLikelyAreaPostcode(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelyAreaPostcode(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelyAreaPostcode(s.toUpperCase()));
                assertFalse("spacing removed input", PostcodeUtil.isLikelyAreaPostcode(s.replaceAll("\\s","")));
            }
        }
    }

    private void checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(String s) {
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(true, s);
    }

    private void checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(boolean isExpected, String... strings) {
        checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(isExpected, true, strings);
    }

    private void checkIsLikelyDistrictPostcodeAndIgnoreCaseAndSpacing(boolean isExpected, boolean collapseSpaces,
                                                                      String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelyDistrictPostcode(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelyDistrictPostcode(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelyDistrictPostcode(s.toUpperCase()));
                if (collapseSpaces) {
                    assertTrue("spacing removed input", PostcodeUtil.isLikelyDistrictPostcode(s.replaceAll("\\s","")));
                }
            } else {
                assertFalse("test input", PostcodeUtil.isLikelyDistrictPostcode(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelyDistrictPostcode(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelyDistrictPostcode(s.toUpperCase()));
                if (collapseSpaces) {
                    assertFalse("spacing removed input", PostcodeUtil.isLikelyDistrictPostcode(s.replaceAll("\\s","")));
                }
            }
        }

    }

    private void checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(String s) {
        checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(true, s);
    }

    private void checkIsLikelyDistrictPostcodeStrictAndIgnoreCaseAndSpacing(boolean isExpected, String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.toUpperCase()));
                assertTrue("spacing removed input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.replaceAll("\\s","")));
            } else {
                assertFalse("test input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.toUpperCase()));
                assertFalse("spacing removed input", PostcodeUtil.isLikelyDistrictPostcodeStrict(s.replaceAll("\\s","")));
            }
        }
    }

    private void checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(String s) {
        checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(true, s);
    }

    private void checkIsLikelySectorPostcodeAndIgnoreCaseAndSpacing(boolean isExpected, String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelySectorPostcode(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelySectorPostcode(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelySectorPostcode(s.toUpperCase()));
                assertTrue("spacing removed input", PostcodeUtil.isLikelySectorPostcode(s.replaceAll("\\s","")));
            } else {
                // test strict for the negative
                assertFalse("test input", PostcodeUtil.isLikelySectorPostcodeStrict(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelySectorPostcodeStrict(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelySectorPostcodeStrict(s.toUpperCase()));
                assertFalse("spacing removed input", PostcodeUtil.isLikelySectorPostcodeStrict(s.replaceAll("\\s","")));
            }
        }
    }

    private void checkIsLikelyUnitPostcodeStrictAndIgnoreCase(String s) {
        checkIsLikelyUnitPostcodeStrictAndIgnoreCase(true, s);
    }

    private void checkIsLikelyUnitPostcodeStrictAndIgnoreCase(boolean isExpected, String... strings) {
        for (String s : strings) {
            if (isExpected) {
                assertTrue("test input", PostcodeUtil.isLikelyUnitPostcodeStrict(s));
                assertTrue("lowercase input", PostcodeUtil.isLikelyUnitPostcodeStrict(s.toLowerCase()));
                assertTrue("uppercase input", PostcodeUtil.isLikelyUnitPostcodeStrict(s.toUpperCase()));
            } else {
                assertFalse("test input", PostcodeUtil.isLikelyUnitPostcodeStrict(s));
                assertFalse("lowercase input", PostcodeUtil.isLikelyUnitPostcodeStrict(s.toLowerCase()));
                assertFalse("uppercase input", PostcodeUtil.isLikelyUnitPostcodeStrict(s.toUpperCase()));
            }
        }
    }

    private String[] withoutSpaces(String... strings) {
        String[] result = new String[strings.length];
        assert result.length == strings.length;
        for (int i = 0; i < strings.length; i++) {
            result[i] = strings[i].replaceAll("\\s","");
        }
        return result;
    }
}
