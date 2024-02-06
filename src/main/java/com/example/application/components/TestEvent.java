package com.example.application.components;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("test-event")
public class TestEvent extends ComponentEvent<HelloWorld> {
    private final Double randomNumber;

    /**
     * @param source
     * @param fromClient
     * @param randomNumber
     */
    public TestEvent(
        HelloWorld source,
        boolean fromClient,
        @EventData("event.detail.someValue") Double randomNumber
    ) {
        super(source, fromClient);
        this.randomNumber = randomNumber;
    }

    public Double getRandomNumber() {
        return this.randomNumber;
    }
}
