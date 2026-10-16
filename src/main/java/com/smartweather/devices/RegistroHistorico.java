package com.smartweather.devices;

import com.smartweather.observer.ObservadorClima;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// 1. Implementamos la interfaz del patrón
public class RegistroHistorico implements ObservadorClima {

    private final Path archivo;

    public RegistroHistorico() {
        archivo = Path.of("registros", "historial_climatico.txt").toAbsolutePath();

        try {
            Files.createDirectories(archivo.getParent());

            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
            }

            System.out.println("Archivo histórico inicializado en: " + archivo);

        } catch (IOException e) {
            throw new IllegalStateException("No se pudo crear el archivo histórico.", e);
        }
    }

    
    @Override
    public synchronized void actualizar(float temperatura, float humedad, float presion) {        

        String linea = String.format(
                "T: %.2f °C | H: %.2f %% | P: %.2f hPa%n",
                temperatura,
                humedad,
                presion
        );

        try {
            Files.writeString(
                    archivo,
                    linea,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("[REGISTRO HISTÓRICO] Lectura guardada en el archivo.");

        } catch (IOException e) {
            System.err.println("Error al guardar la lectura en el archivo: " + e.getMessage());
        }
    }
}