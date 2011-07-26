/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.ballroom.client.widgets.window;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * Collection of feedback windows.
 * Info, Confirmation, Alert, etc.
 * @author Heiko Braun
 * @date 3/2/11
 */
public class Feedback {

    public static void confirm(String title, String message, final ConfirmationHandler handler)
    {
        final DefaultWindow window = new DefaultWindow(title);

        int width = 320;
        int height = 240;

        window.setWidth(width);
        window.setHeight(height);

        window.setGlassEnabled(true);

        DockLayoutPanel panel = new DockLayoutPanel(Style.Unit.PX);
        panel.setStyleName("default-window-content");

        HTML text = new HTML(message);
        panel.add(text);

        ClickHandler confirmHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                handler.onConfirmation(true);
                window.hide();
            }
        };

        ClickHandler cancelHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                handler.onConfirmation(false);
                window.hide();
            }
        };

        DialogueOptions options = new DialogueOptions("OK", confirmHandler, "Cancel", cancelHandler);

        Widget content = new WindowContentBuilder(panel, options).build();

        window.setWidget(content);

        window.center();
    }

    public interface ConfirmationHandler
    {
        void onConfirmation(boolean isConfirmed);
    }
}
