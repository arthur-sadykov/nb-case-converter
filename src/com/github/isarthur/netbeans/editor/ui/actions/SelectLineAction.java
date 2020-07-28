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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.document.LineDocument;
import org.netbeans.api.editor.document.LineDocumentUtils;
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
        id = "nb.editor.ui.actions.SelectLineAction"
)
@ActionRegistration(
        displayName = "#CTL_SelectLineAction"
)
@ActionReferences({
    @ActionReference(path = "Shortcuts", name = "D-9")
})
@Messages("CTL_SelectLineAction=Select Line Without NL")
public class SelectLineAction implements ActionListener {

    @Override public void actionPerformed(ActionEvent e) {
        JTextComponent editor = EditorRegistry.lastFocusedComponent();
        LineDocument document = (LineDocument) editor.getDocument();
        try {
            int firstNonWhitespaceIndexInLine =
                    LineDocumentUtils.getLineFirstNonWhitespace(
                            document,
                            LineDocumentUtils.getLineStart(document, editor.getCaretPosition()));
            int lastNonWhitespaceIndexInLine =
                    LineDocumentUtils.getLineLastNonWhitespace(
                            document,
                            editor.getCaretPosition());
            editor.select(firstNonWhitespaceIndexInLine, lastNonWhitespaceIndexInLine + 1);
        } catch (BadLocationException ex) {
        }
    }
}
