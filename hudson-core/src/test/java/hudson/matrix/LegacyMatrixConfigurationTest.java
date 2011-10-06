/*
 * The MIT License
 *
 * Copyright (c) 2004-2011, Oracle Corporation, Inc., Nikita Levyankov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.matrix;

import hudson.model.AbstractProject;
import hudson.model.Hudson;
import hudson.model.Items;
import hudson.model.Result;
import hudson.tasks.LogRotator;
import java.io.File;
import java.net.URISyntaxException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.*;

/**
 * Test to verify legacy matrix project configuration loading.
 * <p/>
 * Date: 10/06/11
 *
 * @author Anton Kozak
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Hudson.class})
public class LegacyMatrixConfigurationTest {

    private File config;

    @Before
    public void setUp() throws URISyntaxException {
        config = new File(MatrixProject.class.getResource("/hudson/matrix").toURI());
    }

    /**
     * Tests unmarshalls MatrixProject configuration and checks whether properties are loaded correctly.
     *
     * @throws Exception if any.
     */
    @Test
    public void testLoadLegacyMatrixProject() throws Exception {
        MatrixProject project = (MatrixProject) Items.getConfigFile(config).read();
        project.setAllowSave(false);
        project.initProjectProperties();
        project.buildProjectProperties();
        assertEquals("/tmp/1", project.getProperty(AbstractProject.CUSTOM_WORKSPACE_PROPERTY_NAME).getValue());
        assertEquals(new Integer(7), project.getIntegerProperty(AbstractProject.QUIET_PERIOD_PROPERTY_NAME).getValue());

        assertTrue(project.getBooleanProperty(MatrixProject.RUN_SEQUENTIALLY_PROPERTY_NAME).getValue());
        assertEquals("!(label==\"win\" && DB==\"oracle\")",
            project.getProperty(MatrixProject.COMBINATION_FILTER_PROPERTY_NAME).getValue());
        assertEquals("label==\"unix\" && DB==\"mysql\"",
            project.getProperty(MatrixProject.TOUCH_STONE_COMBINATION_FILTER_PROPERTY_NAME).getValue());
        assertEquals(Result.SUCCESS,
            project.getResultProperty(MatrixProject.TOUCH_STONE_RESULT_CONDITION_PROPERTY_NAME).getValue());
        assertEquals(new LogRotator(7, 7, 7, 7),
            project.getLogRotatorProjectProperty(MatrixProject.LOG_ROTATOR_PROPERTY_NAME).getValue());
        AxisList axes = project.getAxesListProjectProperty(MatrixProject.AXES_PROPERTY_NAME).getValue();
        assertEquals(2, axes.size());
        assertEquals("DB", axes.get(0).getName());
        assertEquals(2, axes.get(0).getValues().size());
        assertEquals("oracle", axes.get(0).getValues().get(0));
        assertEquals("mysql", axes.get(0).getValues().get(1));
        assertEquals("label", axes.get(1).getName());
        assertEquals(2, axes.get(1).getValues().size());
        assertEquals("unix", axes.get(1).getValues().get(0));
        assertEquals("win", axes.get(1).getValues().get(1));
        //TODO add matrix configuration verification

    }
}
