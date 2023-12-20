package com.example.application.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("test-event")
public class TestEvent extends ComponentEvent<HelloWorld> {
    public TestEvent(HelloWorld source, boolean fromClient) {
        super(source, fromClient);
    }
}
