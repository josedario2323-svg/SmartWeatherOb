package com.smartweather.devices;

import com.smartweather.observer.ObservadorClima;

public class SistemaDeAlerta implements ObservadorClima {
    @Override
    public void actualizar(float temperatura, float humedad, float presion) {
        System.out.println("=== [SISTEMA DE ALERTA] ===");
        if (temperatura > 35.0f) {
            System.out.println("⚠️ ¡ALERTA! Temperatura extremadamente alta: " + temperatura + " °C");
        } else if (temperatura < 0.0f) {
            System.out.println("⚠️ ¡ALERTA! Temperatura bajo cero: " + temperatura + " °C");
        }
        if (humedad > 85.0f) {
            System.out.println("⚠️ ¡ALERTA! Humedad crítica detectada: " + humedad + " %");
        }
        System.out.println();
    }
}