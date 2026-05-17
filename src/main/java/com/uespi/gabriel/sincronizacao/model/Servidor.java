package com.uespi.gabriel.sincronizacao.model;

import java.time.LocalTime;

public class Servidor {

    private LocalTime horaServidor;


    public Servidor() {
    }

    public LocalTime getHoraServidor() {
        return this.horaServidor;
    }

    public void setHoraServidor(LocalTime horaServidor) {
        this.horaServidor = horaServidor;
    }
    
    
}
