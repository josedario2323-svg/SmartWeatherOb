package com.smartweather.observer;

public interface SujetoClima {
    void registrarObservador(ObservadorClima observador);
    void eliminarObservador(ObservadorClima observador);
    void notificarObservadores();
}