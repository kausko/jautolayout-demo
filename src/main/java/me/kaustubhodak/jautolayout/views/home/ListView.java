package me.kaustubhodak.jautolayout.views.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class ListView extends VerticalLayout {
    private final List<ListItem> listItems;
    private int nextId;
    public ListView() {
        listItems = new ArrayList<>();
        nextId = 1;
        var addButton = new Button("Add Component");

        addButton.setWidthFull();
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickListener(e -> addComponent());

        this.add(addButton);
    }

    private void addComponent() {
        var newItem = new ListItem("Component " + nextId++, this);
        listItems.add(newItem);
        this.add(newItem);
    }

    public void deleteComponent(ListItem listItem) {
        listItems.remove(listItem);
        this.remove(listItem);
    }
}
