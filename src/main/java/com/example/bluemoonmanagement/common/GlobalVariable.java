package com.example.bluemoonmanagement.common;

import com.example.bluemoonmanagement.models.User;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import static com.example.bluemoonmanagement.api.UserAPI.getUser;

public class GlobalVariable {
    public static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    public static double screenWidth = screenBounds.getWidth() * 0.85;
    public static double screenHeight = screenBounds.getHeight() * 0.85;

    public static User USER = getUser(1);

}
