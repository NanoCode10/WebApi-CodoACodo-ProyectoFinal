package galeriaNFTs.infrastructure.persistencia.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import galeriaNFTs.application.services.UsuarioService;
import galeriaNFTs.domain.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {

    private final ObjectMapper mapper;
    private final UsuarioService service;

    public UsuarioController() {
        this.mapper = new ObjectMapper();
        this.service = new UsuarioService();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // configura los encabezados CORS
        configureCorsHeaders(resp);
    }

    private void configureCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*"); // aca colocan la direccion de donde viene la peticion
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "content-type, authorization");
    }

    // TRAER USUARIOS POR MAIL O TODOS
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        configureCorsHeaders(resp);
        String email = req.getParameter("email");

        // Verificar si el parámetro "email" está presente en la solicitud
        if (email != null) {
            Usuario usuario = service.findByEmail(email);
            if (usuario != null) {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(mapper.writeValueAsString(usuario));

            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Usuario no encontrado ");

            }

        } else {
            ArrayList<Usuario> usuarios = service.getAllUsuario();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(mapper.writeValueAsString(usuarios));
            resp.getWriter().write(email);
        }
    }

    // GUARDAR USUARIOS
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        configureCorsHeaders(resp);

        Usuario usuario = mapper.readValue(req.getInputStream(), Usuario.class);

        service.saveUser(usuario);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Usuario creado exitosamente\"}");
    }

    // ELIMINAR USUARIOS
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idString = req.getPathInfo().substring(1);

        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            service.deleteUsuario(id);
            resp.setStatus(200);
            resp.getWriter().write("EL USUARIO CON ID " + idString + " FUE ELIMINADO CORRECTAMENTE");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID DE USUARIO INVALIDO " + idString);
        }
    }

}
