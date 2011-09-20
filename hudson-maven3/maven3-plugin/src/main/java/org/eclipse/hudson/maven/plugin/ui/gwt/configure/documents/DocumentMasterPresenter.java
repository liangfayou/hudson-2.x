/*******************************************************************************
 *
 * Copyright (c) 2010-2011 Sonatype, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: 
 *
 *   
 *     
 *
 *******************************************************************************/ 

package org.eclipse.hudson.maven.plugin.ui.gwt.configure.documents;

import org.eclipse.hudson.maven.plugin.ui.gwt.configure.documents.internal.DocumentMasterPresenterImpl;
import org.eclipse.hudson.maven.plugin.ui.gwt.configure.workspace.WorkspacePresenter;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;
import com.google.inject.ImplementedBy;

/**
 * Manages the display for editing documents.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 2.1.0
 */
@ImplementedBy(DocumentMasterPresenterImpl.class)
public interface DocumentMasterPresenter
    extends WorkspacePresenter
{
    @DefaultLocale("en_US")
    interface MessagesResource
        extends Messages
    {
        @DefaultMessage("Remove document ?")
        String removeTitle();

        @DefaultMessage("Remove document: {0} ?")
        String removeMessage(String context);
    }

    DocumentMasterView getView();

    void doRefresh();

    void doAdd();

    void doRemove();
}