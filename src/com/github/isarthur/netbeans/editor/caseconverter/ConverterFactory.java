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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openide.util.Exceptions;

/**
 *
 * @author Arthur Sadykov
 */
public abstract class ConverterFactory {

    public static Converter newCamelCaseConverter() {
        return new ToCamelCaseConverter();
    }

    public static Converter newHyphenCaseConverter() {
        return new ToHyphenCaseConverter();
    }

    public static Converter newUpperhyphenCaseConverter() {
        return new ToUpperHyphenCaseConverter();
    }

    public static Converter newLowerCaseConverter() {
        return new ToLowerCaseConverter();
    }

    public static Converter newPascalCaseConverter() {
        return new ToPascalCaseConverter();
    }

    public static Converter newSentenceCaseConverter() {
        return new ToSentenceCaseConverter();
    }

    public static Converter newSnakeCaseConverter() {
        return new ToSnakeCaseConverter();
    }

    public static Converter newUppersnakeCaseConverter() {
        return new ToUpperSnakeCaseConverter();
    }

    public static Converter newTitleCaseConverter() {
        return new ToTitleCaseConverter();
    }

    public static Converter newUpperCaseConverter() {
        return new ToUpperCaseConverter();
    }

    public static Converter newWindowsOrUnixPathConverter() {
        return new ToWindowsOrUnixPathConverter();
    }

    public static Converter newConverter(CONVERTER type) {
        Converter converter;
        String methodName = getFactoryMethodName(type);
        try {
            Method method = ConverterFactory.class.getDeclaredMethod(methodName);
            converter = (Converter) method.invoke(ConverterFactory.class);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Exceptions.printStackTrace(ex);
            converter = (String text) -> text;
        }
        return converter;
    }

    private static String getFactoryMethodName(CONVERTER type) {
        if (type == CONVERTER.WINDOWS_OR_UNIX_PATH) {
            return "newWindowsOrUnixPathConverter"; //NOI18N
        }
        String result = "new" //NOI18N
                + type.toString().substring(0, 1).toUpperCase()
                + type.toString().substring(1).toLowerCase();
        if (result.contains("case")) { //NOI18N
            result = result.replace("case", "Case"); //NOI18N
        }
        return result + "Converter"; //NOI18N
    }

    private ConverterFactory() {
        super();
    }

    public enum CONVERTER {
        CAMELCASE,
        HYPHENCASE,
        UPPERHYPHENCASE,
        LOWERCASE,
        PASCALCASE,
        SENTENCECASE,
        SNAKECASE,
        UPPERSNAKECASE,
        TITLECASE,
        UPPERCASE,
        WINDOWS_OR_UNIX_PATH,
    }
}
