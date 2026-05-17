package com.uespi.gabriel.sincronizacao.model;

import java.time.LocalTime;
import java.util.Objects;

public class Cliente {
    String nomeCliente;
    LocalTime horaLocalCliente;
    LocalTime horaEnvioCliente;
    LocalTime horaEnvioTratada;
    String difHora;


    public Cliente() {
    }

    public Cliente(String nomeCliente, LocalTime horaLocalCliente, LocalTime horaEnvioCliente, LocalTime horaEnvioTratada, String difHora) {
        this.nomeCliente = nomeCliente;
        this.horaLocalCliente = horaLocalCliente;
        this.horaEnvioCliente = horaEnvioCliente;
        this.horaEnvioTratada = horaEnvioTratada;
        this.difHora = difHora;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalTime getHoraLocalCliente() {
        return this.horaLocalCliente;
    }

    public void setHoraLocalCliente(LocalTime horaLocalCliente) {
        this.horaLocalCliente = horaLocalCliente;
    }

    public LocalTime getHoraEnvioCliente() {
        return this.horaEnvioCliente;
    }

    public void setHoraEnvioCliente(LocalTime horaEnvioCliente) {
        this.horaEnvioCliente = horaEnvioCliente;
    }

    public LocalTime getHoraEnvioTratada() {
        return this.horaEnvioTratada;
    }

    public void setHoraEnvioTratada(LocalTime horaEnvioTratada) {
        this.horaEnvioTratada = horaEnvioTratada;
    }

    public String getDifHora() {
        return this.difHora;
    }

    public void setDifHora(String difHora) {
        this.difHora = difHora;
    }

    public Cliente nomeCliente(String nomeCliente) {
        setNomeCliente(nomeCliente);
        return this;
    }

    public Cliente horaLocalCliente(LocalTime horaLocalCliente) {
        setHoraLocalCliente(horaLocalCliente);
        return this;
    }

    public Cliente horaEnvioCliente(LocalTime horaEnvioCliente) {
        setHoraEnvioCliente(horaEnvioCliente);
        return this;
    }

    public Cliente horaEnvioTratada(LocalTime horaEnvioTratada) {
        setHoraEnvioTratada(horaEnvioTratada);
        return this;
    }

    public Cliente difHora(String difHora) {
        setDifHora(difHora);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(nomeCliente, cliente.nomeCliente) && Objects.equals(horaLocalCliente, cliente.horaLocalCliente) && Objects.equals(horaEnvioCliente, cliente.horaEnvioCliente) && Objects.equals(horaEnvioTratada, cliente.horaEnvioTratada) && Objects.equals(difHora, cliente.difHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCliente, horaLocalCliente, horaEnvioCliente, horaEnvioTratada, difHora);
    }

    @Override
    public String toString() {
        return "{" +
            " nomeCliente='" + getNomeCliente() + "'" +
            ", horaLocalCliente='" + getHoraLocalCliente() + "'" +
            ", horaEnvioCliente='" + getHoraEnvioCliente() + "'" +
            ", horaEnvioTratada='" + getHoraEnvioTratada() + "'" +
            ", difHora='" + getDifHora() + "'" +
            "}";
    }
    
}
