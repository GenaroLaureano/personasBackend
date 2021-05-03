/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gl.sga.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import mx.com.gl.sga.datos.PersonaDao;
import mx.com.gl.sga.domain.Persona;

/**
 *
 * @author laure
 */
@Stateless
@Path("/personas")
public class PersonaServiceRSDAO {
    
    @Inject
    private PersonaDao personaDAO; 
           
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas(){
        return personaDAO.findAllPersonas();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")//hace referencia al id de la persona un subpath
    public Persona encontrarPersona(@PathParam("id") int id){
        Persona persona = personaDAO.findPersonaById(new Persona(id));
        return persona;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarPersona(Persona persona){
        
            personaDAO.insertPersona(persona);
            return Response.ok().entity(persona).build();
       
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        
        Persona persona = personaDAO.findPersonaById(new Persona(id));
        if(persona != null){
           personaDAO.updatePersona(persona);
           return Response.ok().entity(personaModificada).build();
        }else{ 
            return Response.status(Status.NOT_FOUND).build();
        }
      
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersonaPorId(@PathParam("id") int id){
       
           personaDAO.deletePersona(new Persona(id));
            return Response.ok().build();
       
    }
    
    
    
}
