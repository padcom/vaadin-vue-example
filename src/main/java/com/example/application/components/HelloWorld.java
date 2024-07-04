package com.example.application.components;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.shared.Registration;

import elemental.json.Json;
import elemental.json.JsonValue;

@Tag("hello-world")
@JsModule("./src/hello-world.ts")
public class HelloWorld extends Component {
    public void setName(String name) {
        getElement().setProperty("name", name);
    }

    public String getValue() {
        return getElement().getProperty("value");
    }

    public void setValue(String value) {
        getElement().setProperty("value", value);
    }

    public void sayHello() {
        getElement().callJsFunction("sayHello");
    }

    public Registration addTestEventListener(ComponentEventListener<TestEvent> listener) {
        return addListener(TestEvent.class, listener);
    }

    @ClientCallable
    public String formatHelloMessage(String name) {
        return "Hello, " + name + "!";
    }

    @ClientCallable
    public JsonValue formatHelloObject(String name) {
        var result = Json.createObject();
        result.put("message", "Hello, " + name + "!");

        return result;
    }
}
