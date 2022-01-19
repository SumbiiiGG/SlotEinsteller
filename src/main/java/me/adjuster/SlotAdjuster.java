package me.adjuster;

import me.adjuster.graphics.Window;
import me.adjuster.graphics.screen.Screen;
import me.adjuster.graphics.screen.screens.StartScreen;


public class SlotAdjuster {
    public static Window window;
    public static Screen currentScreen;
    public static void main(String[] args) {
        window = new Window();
        currentScreen = new StartScreen();
    }

}
