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

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import static junit.framework.TestCase.assertEquals;
import org.netbeans.jellytools.EditorOperator;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.ProjectsTabOperator;
import org.netbeans.jellytools.actions.OpenAction;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jellytools.nodes.ProjectRootNode;
import org.netbeans.jemmy.operators.JEditorPaneOperator;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author: Arthur Sadykov
 */
public class SelectLineActionTest extends JellyTestCase {

    private static final String SAMPLE_PROJECT_NAME = "SampleProject";
    private JEditorPaneOperator editorPaneOperator;
    private EditorOperator editorOperator;

    public SelectLineActionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return createModuleTest(SelectLineActionTest.class);
    }

    @Override
    public void setUp() throws IOException {
        openProjects(getDataDir().getAbsolutePath().concat(File.separator).concat(SAMPLE_PROJECT_NAME));
        ProjectsTabOperator projectsTabOperator = ProjectsTabOperator.invoke();
        ProjectRootNode projectRootNode = projectsTabOperator.getProjectRootNode(SAMPLE_PROJECT_NAME);
        Node node = new Node(projectRootNode, "Source Packages|sampleproject|" + SAMPLE_PROJECT_NAME);
        OpenAction openAction = new OpenAction();
        openAction.performAPI(node);
        editorOperator = new EditorOperator("SampleProject.java");
        editorPaneOperator = editorOperator.txtEditorPane();
    }

    @Override
    public void openProjects(String... projects) throws IOException {
        try {
            Class<?> openProjectsClass = getClass().getClassLoader().loadClass(
                    "org.netbeans.api.project.ui.OpenProjects");
            Class<?> projectManagerClass = getClass().getClassLoader().loadClass(
                    "org.netbeans.api.project.ProjectManager");
            Method getDefaultOpenProjectsMethod = openProjectsClass.getMethod("getDefault");
            Object openProjectsInstance = getDefaultOpenProjectsMethod.invoke(null);
            Method getOpenProjectsMethod = openProjectsClass.getMethod("getOpenProjects");
            List<Object> newProjects = new ArrayList<>();
            Object pr;
            for (String p : projects) {
                Method getDefaultMethod = projectManagerClass.getMethod("getDefault");
                Object projectManagerInstance = getDefaultMethod.invoke(null);
                Method findProjectMethod = projectManagerClass.getMethod("findProject", FileObject.class);
                pr = findProjectMethod.invoke(projectManagerInstance, FileUtil.toFileObject(new File(p)));
                Object openProjectsArray = getOpenProjectsMethod.invoke(openProjectsInstance);
                boolean alreadyOpened = false;
                for (int i = 0; i < Array.getLength(openProjectsArray); i++) {
                    if (pr.equals(Array.get(openProjectsArray, i))) {
                        alreadyOpened = true;
                    }
                }
                if (!alreadyOpened) {
                    newProjects.add(pr);
                }
            }
            Class<?> projectClass = getClass().getClassLoader().loadClass("org.netbeans.api.project.Project");
            Object projectsArray = Array.newInstance(projectClass, newProjects.size());
            for (int i = 0; i < newProjects.size(); i++) {
                Array.set(projectsArray, i, newProjects.get(i));
            }
            Method openMethod = openProjectsClass.getMethod("open", new Class<?>[]{projectsArray.getClass(),
                Boolean.TYPE});
            openMethod.invoke(openProjectsInstance, projectsArray, false);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | ClassNotFoundException | NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void testShouldSelectLine() {
        editorOperator.setCaretPosition("branch", true);
        editorOperator.pushKey(KeyEvent.VK_9, KeyEvent.CTRL_DOWN_MASK);
        int selectionStart = editorPaneOperator.getSelectionStart();
        int selectionEnd = editorPaneOperator.getSelectionEnd();
        int expectedSelectionStart =
                editorPaneOperator.getPositionByText("String string = \"master branch in origin repository\";");
        int expectedSelectionEnd =
                expectedSelectionStart + "String string = \"master branch in origin repository\";".length();
        assertEquals("Expected line start position was not equal to actual --->",
                expectedSelectionStart, selectionStart);
        assertEquals("Expected line end position was not equal to actual --->",
                expectedSelectionEnd, selectionEnd);
    }
}
