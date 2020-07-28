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
import javax.swing.text.StyledDocument;
import com.github.isarthur.netbeans.editor.caseconverter.Converter;
import com.github.isarthur.netbeans.editor.caseconverter.ConverterFactory;
import org.netbeans.api.editor.EditorRegistry;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author Arthur Sadykov
 */
public abstract class ConvertToAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextComponent editor = EditorRegistry.lastFocusedComponent();
        String selectedText = editor.getSelectedText();
        if (selectedText == null) {
            return;
        }
        int selectionStartPosition = editor.getSelectionStart();
        Converter converter = ConverterFactory.newConverter(getConverterType());
        String convertedText = converter.convert(selectedText);
        StyledDocument document = (StyledDocument) editor.getDocument();
        NbDocument.runAtomic(document, () -> {
            try {
                document.remove(selectionStartPosition, selectedText.length());
                document.insertString(selectionStartPosition, convertedText, null);
                editor.select(selectionStartPosition, selectionStartPosition + convertedText.length());
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        });
    }

    protected abstract ConverterFactory.CONVERTER getConverterType();
}
