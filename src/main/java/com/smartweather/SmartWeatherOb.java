package com.smartweather;

import com.smartweather.devices.PantallaLCD;
import com.smartweather.devices.RegistroHistorico;
import com.smartweather.devices.SistemaDeAlerta;
import com.smartweather.subject.EstacionMeteorologica;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartWeatherOb {

    public static void main(String[] args) {

        // 1. Crear el Sujeto (Estación Meteorológica)
        EstacionMeteorologica estacion = new EstacionMeteorologica();

        // 2. Crear los Observadores (Dispositivos)
        PantallaLCD pantalla = new PantallaLCD();
        SistemaDeAlerta alertas = new SistemaDeAlerta();
        RegistroHistorico registro = new RegistroHistorico();

        // 3. Suscribir los dispositivos directamente a la estación
        // (Asumiendo que la estación tiene un método registrarObservador)
        estacion.registrarObservador(pantalla);
        estacion.registrarObservador(alertas);
        estacion.registrarObservador(registro);

        System.out.println("\nSmartWeather está funcionando (Patrón Observer Clásico).");
        System.out.println("Se generará una medición cada 5 segundos.");
        System.out.println("Presiona ENTER para detener el programa.\n");

        Random random = new Random();
        ScheduledExecutorService programador = Executors.newSingleThreadScheduledExecutor();

        // Simular mediciones periódicamente
        programador.scheduleAtFixedRate(() -> {
            try {
                double temperatura = -5 + random.nextDouble() * 45;
                double humedad = 5 + random.nextDouble() * 95;
                double presion = 980 + random.nextDouble() * 55;

                // Al actualizar las mediciones, la estación notificará automáticamente a los observadores
                estacion.setMediciones((float) temperatura, (float) humedad, (float) presion);

            } catch (Exception e) {
                System.err.println("Error en la medición: " + e.getMessage());
            }
        }, 0, 5, TimeUnit.SECONDS);

        // Mantener el programa activo hasta presionar ENTER
        try (Scanner teclado = new Scanner(System.in)) {
            teclado.nextLine();
        }

        // Detener el temporizador
        programador.shutdownNow();

        // Opcional: Desuscribir los dispositivos si se requiere antes de cerrar
        estacion.eliminarObservador(pantalla);
        estacion.eliminarObservador(alertas);
        estacion.eliminarObservador(registro);

        try {
            if (!programador.awaitTermination(3, TimeUnit.SECONDS)) {
                System.err.println("El programador no se detuvo a tiempo.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSmartWeather se ha detenido.");
    }
}