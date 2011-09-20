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
package org.jboss.ballroom.client.widgets.forms;

import java.util.Arrays;

import com.google.gwt.junit.client.GWTTestCase;

import org.junit.Test;

/**
 * @author David Bosschaert
 */
public class UnitBoxItemTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "org.jboss.ballroom.Framework";
    }

    @Test
    public void testUnixBoxItem() {
        UnitBoxItem<String> ubi = new UnitBoxItem<String>("amount", "units", "Amount", String.class);
        assertEquals("amount", ubi.getName());
        assertEquals("units", ubi.getUnitItem().getName());
        assertEquals("Amount", ubi.getTitle());

        ubi.textBox.setText("Hello");
        assertEquals("Hello", ubi.getValue());

        ubi.setValue("Bye");
        assertEquals("Bye", ubi.textBox.getText());

        ubi.clearValue();
        assertEquals("", ubi.textBox.getText());
        assertEquals("", ubi.getValue());

        ubi.setChoices(Arrays.asList("a", "b"), null);
        assertNull(ubi.getUnitItem().getValue());

        ubi.setChoices(Arrays.asList("pounds", "kilos"), "kilos");
        assertEquals("kilos", ubi.getUnitItem().getValue());
        assertEquals(2, ubi.unitBox.getItemCount());
        assertEquals("kilos", ubi.unitBox.getItemText(0));
        assertEquals("pounds", ubi.unitBox.getItemText(1));
        assertEquals("List box should be a drop-down type", 1, ubi.unitBox.getVisibleItemCount());

        ubi.unitBox.setSelectedIndex(1);
        assertEquals("pounds", ubi.getUnitItem().getValue());
    }
}
