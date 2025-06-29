package br.com.api.neomind.apirestneomind.resource;

import br.com.api.neomind.apirestneomind.dto.FornecedorDTO;
import br.com.api.neomind.apirestneomind.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    private FornecedorService fornecedorService;

    @GET
    public Response getAllFornecedores() {
        try {
            List<FornecedorDTO> fornecedores = fornecedorService.findAll();
            return Response.ok(fornecedores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erro interno do servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getFornecedorById(@PathParam("id") Long id) {
        try {
            FornecedorDTO fornecedor = fornecedorService.findById(id);
            return Response.ok(fornecedor).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erro interno do servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    public Response createFornecedor(@Valid FornecedorDTO fornecedorDTO) {
        try {
            FornecedorDTO novoFornecedor = fornecedorService.create(fornecedorDTO);
            return Response.status(Response.Status.CREATED)
                    .entity(novoFornecedor)
                    .build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erro interno do servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateFornecedor(@PathParam("id") Long id, @Valid FornecedorDTO fornecedorDTO) {
        try {
            FornecedorDTO fornecedorAtualizado = fornecedorService.update(id, fornecedorDTO);
            return Response.ok(fornecedorAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erro interno do servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFornecedor(@PathParam("id") Long id) {
        try {
            fornecedorService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Erro interno do servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}
