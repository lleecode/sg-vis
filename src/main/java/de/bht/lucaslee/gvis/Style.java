package de.bht.lucaslee.gvis;

public class Style {

    public enum Size {
        S("15", "2", "4", "30", "2"),
        M("20", "3", "6", "40", "3"),
        L("30", "4", "8", "50", "4");

        public final String textSize;
        public final String edgeWidth;
        public final String edgeTextPadding;
        public final String nodeSize;
        public final String nodeWidth;

        private Size(String textSize,
                     String edgeWidth, String edgeTextPadding,
                     String nodeSize, String nodeWidth
        ) {
            this.textSize = textSize;
            this.edgeWidth = edgeWidth;
            this.edgeTextPadding = edgeTextPadding;
            this.nodeSize = nodeSize;
            this.nodeWidth = nodeWidth;
        }
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
