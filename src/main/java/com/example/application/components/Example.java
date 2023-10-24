package com.example.application.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("example-element")
@JsModule("example/example.js")
public class Example extends Component {
    public Example(String name) {
        getElement().setProperty("name", name);
    }
}
