package com.smartweather.subject;

import java.util.ArrayList;
import java.util.List;

import com.smartweather.observer.ObservadorClima;
import com.smartweather.observer.SujetoClima;

public class EstacionMeteorologica implements SujetoClima {
    private List<ObservadorClima> observadores;
    private float temperatura;
    private float humedad;
    private float presion;

    public EstacionMeteorologica() {
        this.observadores = new ArrayList<>();
    }

    @Override
    public void registrarObservador(ObservadorClima observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorClima observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (ObservadorClima observador : observadores) {
            observador.actualizar(temperatura, humedad, presion);
        }
    }

    // Método para simular la actualización de datos climáticos
    public void setMediciones(float temperatura, float humedad, float presion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        notificarObservadores(); // ¡Aquí ocurre la magia del Observer!
    }
}