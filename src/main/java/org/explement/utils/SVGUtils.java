package org.explement.utils;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class SVGUtils {
    private SVGUtils() { }
    public static SVGPath getPipetteIcon() {
        SVGPath pipetteIcon = new SVGPath();
        pipetteIcon.setContent(
            "M12 9l-8.414 8.414A2 2 0 0 0 3 18.828v1.344a2 2 0 0 1-.586 1.414A2 2 0 0 1 3.828 21h1.344a2 2 0 0 0 1.414-.586L15 12" +
            " M18 9l.4.4a1 1 0 1 1-3 3l-3.8-3.8a1 1 0 1 1 3-3l.4.4 3.4-3.4a1 1 0 1 1 3 3z" +
            " M2 22l.414-.414"
        );

        pipetteIcon.setFill(Color.TRANSPARENT);
        pipetteIcon.setStroke(Color.WHITE);
        pipetteIcon.setStrokeWidth(2);
        return pipetteIcon;
    }
}
