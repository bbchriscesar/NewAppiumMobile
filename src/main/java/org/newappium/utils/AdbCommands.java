package org.newappium.utils;

import java.io.IOException;

public class AdbCommands {
    public static void closeECommerceApp() {
        try {
            String command = "adb shell am force-stop com.androidsample.generalstore";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Closed e-commerce app if it was open.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to close e-commerce app: " + e.getMessage());
        }
    }
}