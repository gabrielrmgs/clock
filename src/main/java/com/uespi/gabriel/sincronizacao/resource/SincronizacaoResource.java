package com.uespi.gabriel.sincronizacao.resource;

import java.time.LocalTime;
import java.util.List;

import com.uespi.gabriel.sincronizacao.dto.ClienteRequest;
import com.uespi.gabriel.sincronizacao.model.Cliente;
import com.uespi.gabriel.sincronizacao.model.Servidor;
import com.uespi.gabriel.sincronizacao.service.SincronizacaoService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/sd")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SincronizacaoResource {

    private final SincronizacaoService sincronizacaoService;

    public SincronizacaoResource(SincronizacaoService suiSincronizacaoService) {
        this.sincronizacaoService = suiSincronizacaoService;
    }

    @POST
    @Path("/criarServidor")
    public String criarServidor(ServidorRequest servidorRequest) {

        LocalTime horaServidor = LocalTime.parse(servidorRequest.horaServidor());
        Servidor servidor = sincronizacaoService.criarServidor(horaServidor);

        return "Servidor criado com hora " + servidor.getHoraServidor();
    }

    @Path("/sincronizarCliente")
    @POST
    public Response sincronizarCliente(ClienteRequest clienteRequest) {

        String resposta = sincronizacaoService.sincronizar(clienteRequest);

        return Response.ok(resposta).build();

    }

    @GET
    @Path("servidor")
    public Response buscarServidor() {

        Servidor servidor = sincronizacaoService.buscarServidor();

        if (servidor != null)
            return Response.ok(servidor).build();

        return Response.status(Status.NOT_FOUND).build();

    }

    @GET
    @Path("/clientes")
    public List<Cliente> listarClientes() {
        return sincronizacaoService.listarClientes();
    }

    @GET
    @Path("/clockLogico")
    public String buscarClockLogico() {
        return sincronizacaoService.buscarClockLogico().toString();
    }

    @GET
    @Path("/limparEstados")
    public Response limparTudo() {
        sincronizacaoService.limparTudo();
        return Response.ok().build();
    }
}
