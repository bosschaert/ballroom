package org.jboss.as.console.client.samples;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import org.jboss.as.console.client.model.BeanFactory;
import org.jboss.as.console.client.model.Record;
import org.jboss.as.console.client.widgets.ContentHeaderLabel;
import org.jboss.as.console.client.widgets.DefaultPager;
import org.jboss.as.console.client.widgets.Framework;
import org.jboss.as.console.client.widgets.tables.DefaultCellTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heiko Braun
 * @date 7/12/11
 */
public class TableSample implements Sample {


    private Framework framework = GWT.create(Framework.class);

    private BeanFactory factory;
    private DefaultCellTable<Record> table;
    private ListDataProvider<Record> dataProvider;

    public TableSample() {
        factory = (BeanFactory)framework.getBeanFactory();
    }

    @Override
    public String getName() {
        return "Cell Tables";
    }

    @Override
    public Widget asWidget() {

        VerticalPanel layout = new VerticalPanel();
        layout.setStyleName("fill-layout-width");
        layout.getElement().setAttribute("style", "padding:15px;");

        // desc

        ContentHeaderLabel title = new ContentHeaderLabel("CellTables");
        layout.add(title);

        HTML desc = new HTML();
        desc.setHTML("<p>A simple paging table example.");

        layout.add(desc);

        table = new DefaultCellTable<Record>(6);

        // selection model
        final SingleSelectionModel<Record> selectionModel = new SingleSelectionModel<Record>();
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                Record selectedRecord = selectionModel.getSelectedObject();
                // do something with the record
            }
        });
        table.setSelectionModel(selectionModel);

        dataProvider = new ListDataProvider<Record>();
        dataProvider.addDataDisplay(table);

        TextColumn<Record> nameColumn = new TextColumn<Record>() {
            @Override
            public String getValue(Record record) {
                return record.getKey();
            }
        };

        TextColumn<Record> valueColumn = new TextColumn<Record>() {
            @Override
            public String getValue(Record record) {
                return record.getValue();
            }
        };

        table.addColumn(nameColumn, "Name");
        table.addColumn(valueColumn, "Value");

        layout.add(table);

        DefaultPager pager = new DefaultPager();
        pager.setDisplay(table);

        layout.add(pager);

        // add some data
        populateTable(14);


        return layout;
    }


    private void populateTable(int numRecords)
    {
        List<Record> records = new ArrayList<Record>(numRecords);
        for(int i=0; i<numRecords; i++)
        {
            Record rec = factory.record().as();
            rec.setKey("rec - "+ i);
            rec.setValue(System.currentTimeMillis()*Math.random()+"");

            records.add(rec);
        }

        updateTable(records);
    }

    private void updateTable(List<Record> records)
    {
        dataProvider.setList(records);
        if(!records.isEmpty() && table.getSelectionModel()!=null)
            table.getSelectionModel().setSelected(records.get(0), true);
    }

}
