package com.example.application.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.shared.Registration;

import elemental.json.Json;
import elemental.json.JsonValue;

/**
 * This is the Java side/wrapper of the custom element.
 *
 * The @Tag defines what to use when creating DOM elements.
 * The @JsModule points to a file that contains the actual
 * definition of the component.
 */
@Tag("hello-world")
@JsModule("./src/hello-world.ts")
public class HelloWorld extends Component {
    static Logger log = LoggerFactory.getLogger(HelloWorld.class);

    /**
     * Getting the current value of a prop is done
     * using the `getElement().getProperty(name)` function.
     * Remember that the exchange format is always `String`
     * regardless of the processing being automatically done
     * by Vue.js
     */
    public void setName(String name) {
        log.info("Setting value as String using `getElement().setProperty(name, '" + name + "')`");
        getElement().setProperty("name", name);
    }

    /**
     * This is a more sensible definition of access to a web component property.
     * It is automatically strongly typed.
     */
    private static final PropertyDescriptor<String, String> valueProp = PropertyDescriptors.propertyWithDefault("value", "William");

    public String getValue() {
        log.info("Getting value as String using `PropertyDescriptor.get()`");

        return valueProp.get(getElement());
    }

    public void setValue(String value) {
        log.info("Setting value as String using `PropertyDescriptor.set(value, '" + value + "')`");

        valueProp.set(getElement(), value);
    }

    /**
     * This is an example of how to call an exposed method from
     * the Vue.js-based component. See HelloWorld.ce.vue for the definition.
     * Remember that the parameters need to be serializable - just like props.
     */
    public void sayHello() {
        getElement().callJsFunction("sayHello");
    }

    /**
     * This is how you allow listening for the custom event. It is in every
     * respect identical in how it is done with the `click` event by using
     * the `addClickListener`. The `addListener` method is `protected` so we
     * need to provide an API to access it and this is exactly what this
     * method does.
     */
    public Registration addTestEventListener(ComponentEventListener<TestEvent> listener) {
        log.info("Registering listener for `test-event` event");

        return addListener(TestEvent.class, listener);
    }

    /**
     * This is how a function that can be called from the backend is define.
     * To call this method, get a hold of the host element (see host-el.ts)
     * and call element.$server.formatHelloMessage('some text').
     * This will be an async call that will resolve to the value returned
     * by this function
     */
    @ClientCallable
    public String formatHelloMessage(String name) {
        return "Hello, " + name + "!";
    }

    /**
     * This is the same as `formatHelloMessage(name)` but shows how to return
     * a structured response (a JSON).
     */
    @ClientCallable
    public JsonValue formatHelloObject(String name) {
        var result = Json.createObject();
        result.put("message", "Hello, " + name + "!");

        return result;
    }
}
