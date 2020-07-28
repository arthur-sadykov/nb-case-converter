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
package com.github.isarthur.netbeans.editor.ui.actions;

import com.github.isarthur.netbeans.editor.caseconverter.ConverterFactory;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author Arthur Sadykov
 */
@ActionID(
        category = "Edit",
        id = "nb.editor.ui.actions.ConvertToUpperSnakeCaseAction"
)
@ActionRegistration(
        displayName = "#CTL_ConvertToUpperSnakeCaseAction"
)
@ActionReferences({
    @ActionReference(path = "Shortcuts", name = "D-4")
})
@Messages("CTL_ConvertToUpperSnakeCaseAction=Convert to UPPER_SNAKE_CASE")
public class ConvertToUpperSnakeCaseAction extends ConvertToAction {

    @Override
    protected ConverterFactory.CONVERTER getConverterType() {
        return ConverterFactory.CONVERTER.UPPERSNAKECASE;
    }
}
