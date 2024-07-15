package galeriaNFTs.Implementation.persistencia;

import galeriaNFTs.domain.models.Usuario;
import galeriaNFTs.infrastructure.persistencia.database.DataConecction;
import galeriaNFTs.repositories.Ipersistencia.IPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class MYSQLPersistenciaImpl implements IPersistencia {

    private Connection conexion;

    public MYSQLPersistenciaImpl() {
        this.conexion = DataConecction.getConnection();
    }

    // METODO PARA GUARDAR UN USUARIO EN LA BASE DE DATOS
    @Override
    public void saveUser(Usuario usuario) {

        // PRIMER PASO PARA OBTNER CONEXION
        String insertSQL = "INSERT INTO usuarios (nombre, apellido, email, password) VALUES (?,?,?,?)";
        String checkEmailSQL = "SELECT COUNT(*) FROM usuarios WHERE email = ?";

        try {

            // PASO UNO: COMPROBAR SI EL EMAIL YA EXISTE
            PreparedStatement checkEmailStmt = conexion.prepareStatement(checkEmailSQL);
            checkEmailStmt.setString(1, usuario.getEmail());
            ResultSet rs = checkEmailStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new RuntimeException("El email ya está registrado en la base de datos.");
            }
            rs.close();
            checkEmailStmt.close();

            // PASO DOS PREPARAR LA QUERY
            PreparedStatement preparador = conexion.prepareStatement(insertSQL);
            preparador.setString(1, usuario.getNombre());
            preparador.setString(2, usuario.getApellido());
            preparador.setString(3, usuario.getEmail());
            preparador.setString(4, usuario.getPassword());
            preparador.executeUpdate();
            preparador.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /// METODO PARA TRAER UN USUARIOS DE LA BASE DE DATOS
    @Override
    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try {
            PreparedStatement preparador = conexion.prepareStatement(sql);
            preparador.setString(1, email);

            ResultSet resultados = preparador.executeQuery();
            if (resultados.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultados.getInt("id"));
                usuario.setNombre(resultados.getString("nombre"));
                usuario.setApellido(resultados.getString("apellido"));
                usuario.setPassword(resultados.getString("password"));
                usuario.setEmail(resultados.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    // METODO VERIFICA LOS DATOS PARA LA AUTENTICACION DE USUARIOS,
    // VERIFICA SI EL EMAIL Y LA CONTRASEÑA EXISTEN EN LA BASE DE DATOS
    @Override
    public Usuario autorizaLogin(String email, String password) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

        try {
            PreparedStatement preparador = conexion.prepareStatement(sql);
            preparador.setString(1, email);
            preparador.setString(2, password);

            ResultSet resultados = preparador.executeQuery();
            if (resultados.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultados.getInt("id"));
                usuario.setNombre(resultados.getString("nombre"));
                usuario.setApellido(resultados.getString("apellido"));
                usuario.setPassword(resultados.getString("password"));
                usuario.setEmail(resultados.getString("email"));
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// METODO PARA TRAER TODOS LOS REGISTRO DE LA BASE DE DATOS
    @Override
    public ArrayList<Usuario> getAllUsuario() {
        // PRIMER PASO PARA OBTNER CONEXION
        String insertSQL = "SELECT * FROM usuarios";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // PREPARAR LA QUERY
        try {
            PreparedStatement preparador = conexion.prepareStatement(insertSQL);
            ResultSet tablaResultSet = preparador.executeQuery();

            while (tablaResultSet.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(tablaResultSet.getInt("id"));
                usuario.setNombre(tablaResultSet.getString("nombre"));
                usuario.setApellido(tablaResultSet.getString("apellido"));
                usuario.setEmail(tablaResultSet.getString("email"));
                usuario.setPassword(tablaResultSet.getString("password"));

                usuarios.add(usuario);

            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// METODO PARA ELIMINAR UN REGISTRO DE LA BASE DE DATOS
    @Override
    public void deleteUsuario(int id) {
        // PRIMER PASO PARA OBTNER CONEXION
        String deleteSQL = "DELETE FROM usuarios WHERE id = ?";

        try {
            // PASO DOS PREPARAR LA QUERY
            PreparedStatement preparador = this.conexion.prepareStatement(deleteSQL);
            preparador.setInt(1, id);

            // EJECUTAR LA QUERY
            preparador.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
