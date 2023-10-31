package me.kaustubhodak.jautolayout.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Arrays;

@PageTitle("Home")
@Route(value = "")
@Uses(Icon.class)
public class HomeView extends Composite<AppLayout> {

    LayoutView layoutView;
    TextArea textArea;

    public HomeView() {

        H3 h3 = new H3("JAutoLayout");
        h3.setClassName(LumoUtility.Padding.Left.MEDIUM);
        getContent().addToNavbar(h3);

        textArea = new TextArea("Enter Visual Format in VFL");
        textArea.setValue("""
                             |-[child1(child3)]-[child3]-|
                             |-[child2(child4)]-[child4]-|
                             [child5(child4)]-|
                             V:|-[child1(child2)]-[child2]-|
                             V:|-[child3(child4,child5)]-[child4]-[child5]-|""");
        textArea.setWidthFull();

        var updateButton = new Button("Update");
        updateButton.setWidthFull();
        updateButton.addClickListener(e -> handleUpdate());

        var splitVerticalLayout = new VerticalLayout(textArea, updateButton);
        splitVerticalLayout.setHeightFull();

        layoutView = new LayoutView();
        var horizontalSplit = new SplitLayout(splitVerticalLayout, layoutView);

        horizontalSplit.setWidthFull();
        horizontalSplit.setHeightFull();
        horizontalSplit.setSplitterPosition(25);
        horizontalSplit.addSplitterDragendListener(e -> handleUpdate());

        getContent().setContent(horizontalSplit);

        updateButton.clickInClient();
    }

    private void handleUpdate() {
        layoutView.getElement().executeJs("return $0.clientWidth").then(width -> {
            layoutView.getElement().executeJs("return $0.clientHeight").then(height -> {
                layoutView.updateLayout(textArea.getValue(), width.asNumber(), height.asNumber());
            });
        });
    }
}
