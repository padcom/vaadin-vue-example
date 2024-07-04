package com.example.application.views.about;

import com.example.application.components.HelloWorld;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    public MainView() {
        var hello = new HelloWorld();
        hello.setName("Jane");
        hello.addTestEventListener(event -> {
            System.out.println(event.getRandomNumber());
            hello.setName("new value is: " + event.getRandomNumber());
            hello.setValue("new value is: " + event.getRandomNumber());
        });

        var button = new Button("Say Hello");
        button.addClickListener(event -> {
            // What's happening here is quite extensive so let's re-iterate:
            // - the `button` is clicked in the browser which triggers the `onclick` event
            // - the `onclick` event is then transferred to the backend and this listener is called
            // - the `sayHello` method of HelloWorld Java class is called
            // - the `HelloWorld.sayHello` calls the `HelloWorldCE.sayHello` JavaScript method in the browser
            // - the `HelloWorldCE.sayHello` calls the exposed `HelloWorld.ce.vue/sayHello` function
            hello.sayHello();
        });

        add(button);
        add(hello);
    }
}
