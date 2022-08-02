package de.bht.s68161.gvis.graph;

public enum Attributes {
    LABEL("ui.label"),
    STYLESHEET("ui.stylesheet"),
    CLASS("ui.class")
    ;

    private final String value;

    Attributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
