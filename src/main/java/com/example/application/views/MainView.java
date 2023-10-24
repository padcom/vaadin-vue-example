package com.example.application.views;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.application.components.Example;
import com.example.application.services.DataService;
import com.google.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * This is the main application view.
 * It mainly displays the Example component and nothing else
 */
@Route(value = "")
@PageTitle("Test page")
public class MainView extends VerticalLayout {
    Logger log = LoggerFactory.getLogger(MainView.class);
    private Gson gson = new Gson();
    private DataService service;

    public MainView(DataService service) {
        this.service = service;
        var example = new Example();

        /**
         * This uses the `Example.addDataChangedListener()` method to
         * hook up to the event emitted by the custom component
         */
        example.addDataChangedListener(e -> {
            log.info("Event `data-change` has fired!");
            Notification.show("Data has been changed to " + e.getNewValue());
        });


        var button = new Button("Change prop");
        button.addClickListener(e -> {
            // Here we're using the `service` to get
            // some data for serialization
            var data = this.service.fetchAllCompanies();
            // In here we're toggling between a string and JSON
            // so that we can see some changes to the displayed
            // content in the web component
            if (example.getValueprop() == "John Doe") {
                example.setValue(gson.toJson(data));
            } else {
                example.setValueProperty("John Doe");
            }
        });

        var sayHelloButton = new Button("Say hello (see console!)");
        sayHelloButton.addClickListener(e -> example.sayHello(example.getValueProperty()));

        add(example, button, sayHelloButton);
    }
}
