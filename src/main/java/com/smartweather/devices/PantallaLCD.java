package com.smartweather.devices;

import com.smartweather.observer.ObservadorClima;

public class PantallaLCD implements ObservadorClima {
    @Override
    public void actualizar(float temperatura, float humedad, float presion) {
        System.out.println("=== [PANTALLA LCD] ===");
        System.out.println("Temperatura: " + temperatura + " °C");
        System.out.println("Humedad: " + humedad + " %");
        System.out.println("Presión: " + presion + " hPa\n");
    }
}