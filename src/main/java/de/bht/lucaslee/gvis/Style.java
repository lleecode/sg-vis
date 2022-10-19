package de.bht.lucaslee.gvis;

public class Style {

    public enum Size {
        S,
        M,
        L;
    }

    public enum Color {
        RED("rgb(230,20,20)"),
        GREEN("rgb(20,230,20)"),
        BLUE("rgb(20,20,230)"),
        TEAL("rgb(20,160,160)"),
        VIOLET("rgb(160,20,160)");

        public final String rgb;

        private Color(String rgb) {
            this.rgb = rgb;
        }
    }
}
