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

import com.github.isarthur.netbeans.editor.encloser.EncloserFactory;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;

/**
 *
 * @author Arthur Sadykov
 */
@ActionID(
        category = "Edit",
        id = "nb.editor.ui.actions.EncloseInParenthesisAction"
)
@ActionRegistration(
        displayName = "#CTL_EncloseInParenthesisAction"
)
@ActionReferences({
    @ActionReference(path = "Shortcuts", name = "D-9")
})
@NbBundle.Messages("CTL_EncloseInParenthesisAction=Enclose in Parenthesis")
public class EncloseInParenthesisAction extends EncloseInAction {

    @Override
    protected EncloserFactory.ENCLOSER getEncloserType() {
        return EncloserFactory.ENCLOSER.PARENTHESIS;
    }
}
