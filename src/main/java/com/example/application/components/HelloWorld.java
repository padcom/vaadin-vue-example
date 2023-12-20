package com.example.application.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.shared.Registration;

@Tag("hello-world")
@JsModule("./src/hello-world.ts")
public class HelloWorld extends Component {
    public void setName(String name) {
        getElement().setProperty("name", name);
    }

    public Registration addTestEventListener(ComponentEventListener<TestEvent> listener) {
        return addListener(TestEvent.class, listener);
    }
}
