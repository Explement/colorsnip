module org.explement {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.github.kwhat.jnativehook;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires javafx.media;

    opens org.explement.controller to javafx.fxml;
    opens org.explement to javafx.fxml;

    exports org.explement;
    exports org.explement.controller;
    exports org.explement.service;
    exports org.explement.model;
    exports org.explement.utils;
}