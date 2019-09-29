package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Usuario;

/**
 * REST Web Service POST = CREATE GET = RETRIEVE PUT = UPDATE DELETE = DELETE
 *
 * @author gianlucampos
 */
@Path("db")
public class UsuarioWS {

    @Context
    private UriInfo context;

    public UsuarioWS() {
    }

//GETs--------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Get HELLO WORLD !";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuario/get/{login}")
    public String getUser(@PathParam("login") String login) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        UsuarioDAO dao = new UsuarioDAO();
        usuario = dao.buscar(usuario);
        Gson gson = new Gson();
        return gson.toJson(usuario);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuario/list")
    public String getUserList() {
        UsuarioDAO busca = new UsuarioDAO();
        List<Usuario> lista = busca.listar();
        Gson gson = new Gson();
        return gson.toJson(lista);
    }

//POSTs--------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/usuario/insert")
    public boolean insert(String content) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserir(usuario);
    }

//PUTs--------------------------------------------------------------------
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/usuario/update")
    public boolean update(String content) {
        Gson gson = new Gson();
        Usuario usuario = (Usuario) gson.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        return dao.atualizar(usuario);
    }

//DELs--------------------------------------------------------------------
    @DELETE
    @Path("/usuario/delete/{login}")
    public boolean delete(@PathParam("login") String login) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        UsuarioDAO dao = new UsuarioDAO();
        usuario = dao.buscar(usuario);
        return dao.excluir(usuario);
    }

//---------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/usuario/randomInsert")
    public boolean insertRandom() {
        Usuario usuario = createUserTest();
        UsuarioDAO dao = new UsuarioDAO();
        return dao.inserir(usuario);
    }

    public Usuario createUserTest() {
        Usuario usuario = new Usuario();
        usuario.setLogin("gianlucampos");
        usuario.setSenha("123");
        usuario.setPerfil("Programador Java Júnior");
        usuario.setEmail("gianlucampos97@gmail.com");
        return usuario;
    }

}
