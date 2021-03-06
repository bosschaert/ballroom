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

package org.jboss.ballroom.client.widgets.tools;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.ballroom.client.widgets.forms.ComboBox;

/**
 * @author Heiko Braun
 * @date 2/28/11
 */
public class ToolStrip extends HorizontalPanel{

    private HorizontalPanel left;
    private HorizontalPanel right;

    public ToolStrip() {
        super();
        setStyleName("default-toolstrip");

        left = new HorizontalPanel();
        right = new HorizontalPanel();

        add(left);
        add(right);

        left.getElement().getParentElement().setAttribute("width", "50%");
        right.getElement().getParentElement().setAttribute("width", "50%");
        right.getElement().getParentElement().setAttribute("align", "right");
    }

    public void addToolButton(ToolButton button)
    {
        left.add(button);
    }

    public void addToolButtonRight(ToolButton button)
    {
        button.getElement().setAttribute("style", ";margin-right:5px;");
        button.addStyleName("toolstrip-button-secondary");
        right.add(button);

    }

    public boolean hasButtons() {
        return left.getWidgetCount()>0 || right.getWidgetCount()>0;
    }

    public void addToolWidget(Widget widget) {
        left.add(widget);
    }

    public void addToolWidgetRight(Widget widget) {

        right.add(widget);
    }
}
