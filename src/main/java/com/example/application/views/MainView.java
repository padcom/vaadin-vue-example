package com.example.application.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.application.components.Example;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Test page")
public class MainView extends VerticalLayout {
    Logger logger = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        var example = new Example("John");

        add(example);
    }
}
