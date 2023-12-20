package com.example.application.views.about;

import com.example.application.components.HelloWorld;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    public MainView() {
        var hello = new HelloWorld();
        hello.setName("Jane");
        hello.addTestEventListener(event -> hello.setName("Marry"));
        add(hello);
    }
}
