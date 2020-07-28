/*
 * Copyright 2020 Arthur Sadykov.
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
package com.github.isarthur.netbeans.editor.caseconverter;

import org.netbeans.junit.NbTestCase;

/**
 *
 * @author: Arthur Sadykov
 */
public class ToWindowsOrUnixPathConverterTest extends NbTestCase {

    private ToWindowsOrUnixPathConverter toWindowsOrUnixPathConverter;

    public ToWindowsOrUnixPathConverterTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        toWindowsOrUnixPathConverter = new ToWindowsOrUnixPathConverter();
    }

    public void testShouldConvertWindowsPathToUnixPath() {
        String actual = toWindowsOrUnixPathConverter.convert("C:\\some\\folder\\in\\some\\location");
        String expected = "C:/some/folder/in/some/location";
        assertEquals("Expected value not equal actual!", expected, actual);
    }

    public void testShouldConvertUnixPathToWindowsPath() {
        String actual = toWindowsOrUnixPathConverter.convert("C:/some/folder/in/some/location");
        String expected = "C:\\\\some\\\\folder\\\\in\\\\some\\\\location";
        assertEquals("Expected value not equal actual!", expected, actual);
    }
}
