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
package com.github.isarthur.netbeans.editor.encloser;

import org.netbeans.junit.NbTestCase;

/**
 *
 * @author: Arthur Sadykov
 */
public class SingleQuotesEncloserTest extends NbTestCase {

    private SingleQuotesEncloser singleQuotesEncloser;

    public SingleQuotesEncloserTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        singleQuotesEncloser = new SingleQuotesEncloser();
    }

    public void testEncloseText() {
        String actual = singleQuotesEncloser.enclose("expression");
        String expected = "'expression'";
        assertEquals("Expected value not equal actual!", expected, actual);
    }
}
