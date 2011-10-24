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
package org.hudsonci.model.project.property;

import org.apache.commons.lang3.StringUtils;
import org.hudsonci.api.model.IJob;

/**
 * String property for project.
 * <p/>
 * Date: 9/22/11
 *
 * @author Nikita Levyankov
 */
public class StringProjectProperty extends BaseProjectProperty<String> {

    public StringProjectProperty(IJob job) {
        super(job);
    }

    @Override
    protected String prepareValue(String candidateValue) {
        return StringUtils.trimToNull(candidateValue);
    }

    /**
     * {@inheritDoc}
     */
    public boolean allowOverrideValue(String cascadingValue, String candidateValue) {
        return !StringUtils.equalsIgnoreCase(cascadingValue, candidateValue);
    }
}