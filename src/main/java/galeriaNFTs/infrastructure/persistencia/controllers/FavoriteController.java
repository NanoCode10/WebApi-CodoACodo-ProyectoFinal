package galeriaNFTs.infrastructure.persistencia.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import galeriaNFTs.application.services.FavoriteService;
import galeriaNFTs.domain.models.Favorite;
import galeriaNFTs.domain.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/favorite")
public class FavoriteController extends HttpServlet {

    private final ObjectMapper mapper;
    private final FavoriteService service;

    public FavoriteController() {
        this.mapper = new ObjectMapper();
        this.service = new FavoriteService();
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

    // OBTENER FAVORITOS POR USUARIO
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        configureCorsHeaders(resp);
        String idUserParam = req.getParameter("idUser");

        if (idUserParam != null) {
            Integer idUser = Integer.parseInt(idUserParam);
            ArrayList<Favorite> favorites = service.findByFavoriteUser(idUser);
            if (favorites != null) {
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(mapper.writeValueAsString(favorites));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Favoritos no encontrado");
            }

        } else {

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write("Favoritos no puede ser nulo");
        }
    }

    // GUARDAR USUARIOS
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        configureCorsHeaders(resp);

        Favorite favorite = mapper.readValue(req.getInputStream(), Favorite.class);

        service.saveFavorite(favorite);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Usuario creado exitosamente\"}");
    }

    // ELIMINAR USUARIOS
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idNft = req.getParameter("idnft");
        String idUser = req.getParameter("iduser");

        if (idNft != null && !idNft.isEmpty()) {
            int idNftInt = Integer.parseInt(idNft);
            int idUserInt = Integer.parseInt(idUser);
            service.deleteFavorite(idNftInt, idUserInt);
            resp.setStatus(200);
            resp.getWriter().write("EL NFT SE ELIMINO DE FAVORITOS");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID DEL USUARIO O EL ID NFT ES INVALIDO");
        }
    }

}
