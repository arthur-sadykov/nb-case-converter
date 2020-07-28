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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import org.openide.util.Exceptions;

/**
 *
 * @author Arthur Sadykov
 */
public abstract class EncloserFactory {

    public static Encloser newDoublequotesEncloser() {
        return new DoubleQuotesEncloser();
    }

    public static Encloser newQuotesEncloser() {
        return new SingleQuotesEncloser();
    }

    public static Encloser newParenthesisEncloser() {
        return new ParenthesisEncloser();
    }

    public static Encloser newBracketsEncloser() {
        return new BracketsEncloser();
    }

    public static Encloser newBracesEncloser() {
        return new BracesEncloser();
    }

    public static Encloser newCommasEncloser() {
        return new CommasEncloser();
    }

    public static Encloser newEncloser(ENCLOSER type) {
        Encloser encloser = null;
        String methodName = getFactoryMethodName(type);
        try {
            Method method = EncloserFactory.class.getDeclaredMethod(methodName);
            encloser = (Encloser) method.invoke(EncloserFactory.class);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Exceptions.printStackTrace(ex);
        }
        Objects.requireNonNull(encloser, () -> "encloser was null inside newEncloser method."); //NOI18N
        return encloser;
    }

    private static String getFactoryMethodName(ENCLOSER type) {
        return "new"
                + type.toString().substring(0, 1).toUpperCase()
                + type.toString().substring(1).toLowerCase()
                + "Encloser";
    }

    private EncloserFactory() {
        super();
    }

    public enum ENCLOSER {
        DOUBLEQUOTES,
        QUOTES,
        PARENTHESIS,
        BRACKETS,
        BRACES,
        COMMAS
    }
}
