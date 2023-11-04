package me.kaustubhodak.jautolayout.views.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import no.birkett.kiwi.Variable;
import org.JAutoLayout.SolverUtils.Solver;
import org.JAutoLayout.VFL.Parser;

import java.util.Arrays;

public class LayoutView extends Div {

    Parser parser;
    Solver solver;
    Notification notification;

    private final String[] keys = new String[]{"top", "left", "width", "height"};
    public LayoutView() {
        parser = new Parser();
        setWidthFull();
        setHeightFull();
        notification = new Notification();
        addClassNames(LumoUtility.Position.RELATIVE);
    }

    public void updateLayout(String vfl, Double width, Double height) {
        try {
            solver = new Solver(width.intValue(), height.intValue());

            var constraints = Arrays.stream(vfl.split("\\R")).toList();
            var parsedConstrains = parser.parse(constraints);
            var map = solver.solve(parsedConstrains);

            map.remove("container");
            removeAll();
            map.forEach((k, v) -> {
                Div div = new Div();
                div.addClassNames(
                        LumoUtility.Position.ABSOLUTE,
                        LumoUtility.Display.FLEX,
                        LumoUtility.JustifyContent.CENTER,
                        LumoUtility.AlignItems.CENTER,
                        LumoUtility.Border.ALL,
                        LumoUtility.BorderColor.CONTRAST_50,
                        LumoUtility.BorderRadius.SMALL
                );
                for (var key : keys) {
                    var value = v.getOrDefault(key, new Variable(0.0)).getValue();
                    div.getStyle().set(key, ((int) value) + "px");
                }
                div.add(new Text(k));
                add(div);
            });
        } catch (Exception e) {

            var closeButton = new Button(new Icon(VaadinIcon.CLOSE));
            closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            closeButton.addClickListener(ev -> {
                notification.close();
            });

            var notifLayout = new HorizontalLayout(new Text(e.getMessage()), closeButton);
            notifLayout.setAlignItems(FlexComponent.Alignment.CENTER);

            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setPosition(Notification.Position.TOP_END);
            notification.setDuration(0);
            notification.removeAll();
            notification.add(notifLayout);
            notification.open();
        }
    }
}
