package me.kaustubhodak.jautolayout.views.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ListItem extends HorizontalLayout {
    TextField textField;
    Button deleteButton;

    public ListItem(String componentName, ListView parent) {
        textField = new TextField();
        textField.setValue(componentName);
        textField.setWidthFull();

        deleteButton = new Button(new Icon(VaadinIcon.CLOSE));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        deleteButton.addClickListener(e -> deleteSelf(parent));

        this.add(textField);
        this.add(deleteButton);
        this.setWidthFull();
    }

    private void deleteSelf(ListView parent) {
        parent.deleteComponent(this);
    }
}
