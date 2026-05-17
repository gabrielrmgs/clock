package com.uespi.gabriel.sincronizacao.model;

import java.time.LocalTime;
import java.util.Objects;

public class Cliente {
    String nomeCliente;
    LocalTime horaLocalCliente;
    LocalTime horaEnvioCliente;
    String difHora;


    public Cliente() {
    }

    public Cliente(String nomeCliente, LocalTime horaLocalCliente, LocalTime horaEnvioCliente, String difHora) {
        this.nomeCliente = nomeCliente;
        this.horaLocalCliente = horaLocalCliente;
        this.horaEnvioCliente = horaEnvioCliente;
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
        return Objects.equals(nomeCliente, cliente.nomeCliente) && Objects.equals(horaLocalCliente, cliente.horaLocalCliente) && Objects.equals(horaEnvioCliente, cliente.horaEnvioCliente) && Objects.equals(difHora, cliente.difHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCliente, horaLocalCliente, horaEnvioCliente, difHora);
    }

    @Override
    public String toString() {
        return "{" +
            " nomeCliente='" + getNomeCliente() + "'" +
            ", horaLocalCliente='" + getHoraLocalCliente() + "'" +
            ", horaEnvioCliente='" + getHoraEnvioCliente() + "'" +
            ", difHora='" + getDifHora() + "'" +
            "}";
    }

    
}
