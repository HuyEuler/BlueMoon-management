package com.example.bluemoonmanagement.common;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class GlobalVariable {
    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    public static double screenWidth = screenBounds.getWidth() * 0.75;
    public static double screenHeight = screenBounds.getHeight() * 0.75;

}
