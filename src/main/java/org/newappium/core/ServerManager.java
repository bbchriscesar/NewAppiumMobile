package org.newappium.core;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;

public class ServerManager {
    private static AppiumDriverLocalService service;

    public static URL startServer() {
        int port = findFreePort();
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(port)
                .build();
        service.start();
        return service.getUrl();
    }

    public static void stopServer() {
        if (service != null) {
            service.stop();
        }
    }

    private static int findFreePort() {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("No available ports found", e);
        }
    }
}