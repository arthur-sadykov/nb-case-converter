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
package com.github.isarthur.netbeans.editor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Arthur Sadykov
 */
public class StringUtils {

    public static String capitalizePattern(String text, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        StringBuilder stringBuilder = new StringBuilder(Byte.MAX_VALUE);
        int last = 0;
        while (matcher.find()) {
            stringBuilder.append(text.substring(last, matcher.start()));
            stringBuilder.append(matcher.group().toUpperCase());
            last = matcher.end();
        }
        stringBuilder.append(text.substring(last));
        return stringBuilder.toString();
    }

    public static String capitalizeFirstChar(String text) {
        return text.isEmpty() ? text : text.substring(0, 1).toUpperCase().concat(text.substring(1));
    }

    public static String unCapitalizeFirstChar(String text) {
        return text.isEmpty() ? text : text.substring(0, 1).toLowerCase().concat(text.substring(1));
    }

    public static String replaceDuplicatesWithUnique(String text, String pattern, String replacement) {
        return text.replaceAll(pattern, replacement);
    }

    public static String stripLeadingPatternFrom(String text, String pattern) {
        return text.replaceFirst("^" + escapeMetaChars(pattern), ""); //NOI18N
    }

    public static String stripTrailingPatternFrom(String text, String pattern) {
        return text.replaceFirst(escapeMetaChars(pattern) + "$", ""); //NOI18N
    }

    public static String prefixPatternWith(String text, String pattern, String prefix) {
        return text.replaceAll("(" + pattern + ")", prefix + "$1"); //NOI18N
    }

    public static String puncuateWithUnique(String text, String punctuationMark) {
        return text.endsWith(punctuationMark) ? text : text.concat(punctuationMark);
    }

    public static String escapeMetaChars(String text) {
        String pattern = "([\\<\\>\\(\\)\\[\\]\\{\\}\\\\\\^\\-\\=\\$\\!\\ \\|\\?\\*\\+\\.])"; //NOI18N
        return text.replaceAll(pattern, "\\\\$1"); //NOI18N
    }

    private StringUtils() {
        super();
    }
}
