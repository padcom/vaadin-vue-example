package com.example.application.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.shared.Registration;

/**
 * This is the Java side/wrapper of the custom element.
 *
 * The @Tag defines what to use when creating DOM elements.
 * The @JsModule points to a file that contains the actual
 * definition of the component. In theory it should allow
 * for the build system to automatically detect changes to
 * the FE files but it doesn't. No idea why...
 */
@Tag("example-element")
@JsModule("./example/example.js")
@JsModule("./example/Example.ce.vue")
public class Example extends Component {
    static Logger log = LoggerFactory.getLogger(Example.class);

    /**
     * Getting the current value of a prop is done
     * using the `getElement().getPropertyu(name)` function.
     * Remember that the exchange format is always `String`
     * regardless of the processing being automatically done
     * by Vue.js
     */
    public String getValueprop() {
        return getElement().getProperty("value");
    }

    /**
     * Setting the current value of a prop is done
     * using the `getElement().setPropertyu(name, value)` function.
     * Remember that the exchange format is always `String`
     * regardless of the processing being automatically done
     * by Vue.js
     */
    public void setValue(String name) {
        log.info("Setting value as String using `getElement().setProperty(name, value)`");
        getElement().setProperty("value", name);
    }

    /**
     * This is a more sensible definition of access to a web component property.
     * It is automatically strongly typed.
     */
    private static final PropertyDescriptor<String, String> valueProp = PropertyDescriptors.propertyWithDefault("value", "William");

    public String getValueProperty() {
        return valueProp.get(this);
    }

    public void setValueProperty(String value) {
        log.info("Setting value as String using `PropertyDescriptor.set(value)`");
        valueProp.set(this, value);
    }

    /**
     * This is a definition of a custom event that is being emitted by
     * the example web component. It contains all the information
     * included in the event
     */
    @DomEvent("data-changed")
    public static class DataChangedEvent extends ComponentEvent<Example> {
        private final String newValue;

        public DataChangedEvent(
            Example source,
            boolean fromClient,
            @EventData("event.detail") String newValue
        ) {
            super(source, fromClient);
            this.newValue = newValue;
            log.info("Instantiated new DataExchangeEvent object with `newValue` equal " + newValue);
        }

        public String getNewValue() {
            return newValue;
        }
    }

    /**
     * This is how you allow listening for the custom event. It is in every
     * respect identical in how it is done with the `click` event by using
     * the `addClickListener`. The `addListener` method is `protected` so we
     * need to provide an API to access it and this is exactly what this
     * method does.
     */
    public Registration addDataChangedListener(ComponentEventListener<DataChangedEvent> listener) {
        log.info("Registering listener for `data-changed` event");

        return addListener(DataChangedEvent.class, listener);
    }
}
