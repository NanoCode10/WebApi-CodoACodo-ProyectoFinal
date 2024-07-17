package galeriaNFTs.infrastructure.persistencia.Implementation;

import galeriaNFTs.domain.models.Favorite;

import galeriaNFTs.domain.repositories.Ipersistencia.IPersistenciaFavorite;
import galeriaNFTs.infrastructure.persistencia.database.DataConecction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class MYSQLPersistenciaFavoriteImpl implements IPersistenciaFavorite {

    private Connection conexion;

    public MYSQLPersistenciaFavoriteImpl() {
        this.conexion = DataConecction.getConnection();
    }

    // METODO PARA GUARDAR UN FAVORITO EN LA BASE DE DATOS
    @Override
    public void saveFavorite(Favorite favorite) {

        // PRIMER PASO PARA OBTNER CONEXION
        String insertSQL = "INSERT INTO favoritos (id, id_usuario, nft_id, precio,name, subname, contrato, image) VALUES (?,?,?,?,?,?,?,?)";

        // PASO DOS EJECUTAR LA QUERY
        try {

            PreparedStatement preparador = conexion.prepareStatement(insertSQL);
            preparador.setInt(1, favorite.getId());
            preparador.setInt(2, favorite.getIdUsuario());
            preparador.setString(3, favorite.getNftId());
            preparador.setDouble(4, favorite.getPrecio());
            preparador.setString(5, favorite.getName());
            preparador.setString(6, favorite.getSubname());
            preparador.setString(7, favorite.getContrato());
            preparador.setString(8, favorite.getImage());
            preparador.executeUpdate();
            preparador.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // METODO PARA OBTENER TODOS LOS FAVORITOS DE LA BASE DE DATOS
    @Override
    public ArrayList<Favorite> findByFavoriteUser(Integer idUser) {
        String sql = "SELECT * FROM favoritos WHERE id_usuario = ?";
        ArrayList<Favorite> favorites = new ArrayList<>();

        try {
            PreparedStatement preparador = conexion.prepareStatement(sql);
            preparador.setInt(1, idUser);

            ResultSet resultados = preparador.executeQuery();
            while (resultados.next()) {
                Favorite favorite = new Favorite();
                favorite.setId(resultados.getInt("id"));
                favorite.setIdUsuario(resultados.getInt("id_usuario"));
                favorite.setNftId(resultados.getString("nft_id"));
                favorite.setPrecio(resultados.getDouble("precio"));
                favorite.setName(resultados.getString("name"));
                favorite.setSubname(resultados.getString("subname"));
                favorite.setContrato(resultados.getString("contrato"));
                favorite.setImage(resultados.getString("image"));
                favorites.add(favorite);
            }
            return favorites;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // METODO PARA ELIMINAR UN FAVORITO DE LA BASE DE DATOS
    @Override
    public void deleteFavorite(String idNft, int idUser) {
        // PRIMER PASO PARA OBTNER CONEXION
        String deleteSQL = "DELETE FROM Favoritos WHERE nft_id = ? AND id_usuario = ?";
        try {
            // PASO DOS PREPARAR LA QUERY
            PreparedStatement preparador = this.conexion.prepareStatement(deleteSQL);
            preparador.setString(1, idNft);
            preparador.setInt(2, idUser);

            // EJECUTAR LA QUERY preparador.executeUpdate();
            preparador.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
