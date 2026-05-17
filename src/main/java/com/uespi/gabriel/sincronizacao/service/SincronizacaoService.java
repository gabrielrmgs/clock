package com.uespi.gabriel.sincronizacao.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.uespi.gabriel.sincronizacao.dto.ClienteRequest;
import com.uespi.gabriel.sincronizacao.model.Cliente;
import com.uespi.gabriel.sincronizacao.model.Servidor;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SincronizacaoService {

    private Servidor servidor;
    private LocalTime clockLogico;
    private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        this.clientes = new ArrayList<>();
    }

    public Servidor criarServidor(LocalTime horaServidor) {
        Servidor novoServidor = new Servidor();
        novoServidor.setHoraServidor(horaServidor);

        this.servidor = novoServidor;
        this.clockLogico = null;
        this.clientes.clear();
        return this.servidor;
    }

    public String sincronizar(ClienteRequest clienteRequest) {
        if (this.servidor == null) {
            return "Crie o servidor antes de sincronizar clientes.";
        }

        Cliente clienteNovo = new Cliente();

        clienteNovo.setNomeCliente(clienteRequest.nomeCliente());

        LocalTime horaLocalCliente = LocalTime.parse(clienteRequest.horaLocal());
        LocalTime horaEnvioCliente = LocalTime.parse(clienteRequest.horaEnvio());

        clienteNovo.setHoraLocalCliente(horaLocalCliente);
        clienteNovo.setHoraEnvioCliente(horaEnvioCliente);

        this.clientes.add(clienteNovo);

        int totalSegundosClientes = 0;
        for (Cliente cliente : clientes) {

            int horaLocalEmSegundos = cliente.getHoraLocalCliente().toSecondOfDay();
            totalSegundosClientes += horaLocalEmSegundos;

        }

        int media = (totalSegundosClientes + servidor.getHoraServidor().toSecondOfDay()) / (clientes.size() + 1);

        this.clockLogico = LocalTime.ofSecondOfDay(media);

        calcularDiferencas();

        ordenarListaClientes();

        return this.clientes.toString();

    }

    public void calcularDiferencas() {

        for (Cliente cliente : clientes) {

            if (clockLogico.isAfter(cliente.getHoraLocalCliente())) {

                int diferenca = clockLogico.toSecondOfDay() - cliente.getHoraLocalCliente().toSecondOfDay();
                int horaAtualizadaEnvio = cliente.getHoraEnvioCliente().toSecondOfDay() + diferenca;
                cliente.setHoraEnvioTratada(criarHoraDoDia(horaAtualizadaEnvio));
                cliente.setDifHora("+ " + LocalTime.ofSecondOfDay(diferenca).toString());

            } else if (clockLogico.isBefore(cliente.getHoraLocalCliente())) {

                int diferenca = cliente.getHoraLocalCliente().toSecondOfDay() - clockLogico.toSecondOfDay();
                int horaAtualizadaEnvio = cliente.getHoraEnvioCliente().toSecondOfDay() - diferenca;
                cliente.setHoraEnvioTratada(criarHoraDoDia(horaAtualizadaEnvio));
                cliente.setDifHora("- " + LocalTime.ofSecondOfDay(diferenca).toString());

            } else {
                cliente.setHoraEnvioTratada(cliente.getHoraEnvioCliente());
                cliente.setDifHora("+ 00:00");
            }
        }
    }

    public void ordenarListaClientes() {
        this.clientes.sort(Comparator.comparing(Cliente::getHoraEnvioTratada, Comparator.nullsLast(Comparator.naturalOrder())));
    }

    private LocalTime criarHoraDoDia(int segundos) {
        int segundosNoDia = 24 * 60 * 60;
        return LocalTime.ofSecondOfDay(Math.floorMod(segundos, segundosNoDia));
    }

    public Servidor buscarServidor() {
        if (this.servidor != null) {
            return this.servidor;
        }
        return null;
    }

    public LocalTime buscarClockLogico() {
        if (this.clockLogico != null) {
            return this.clockLogico;
        }

        if (this.servidor != null)
            return this.servidor.getHoraServidor();

        return LocalTime.MIDNIGHT;
    }

    public List<Cliente> listarClientes() {
        return Collections.unmodifiableList(this.clientes);
    }

    public void limparTudo() {
        this.servidor = null;
        this.clockLogico = null;
        this.clientes.clear();
    }
}
