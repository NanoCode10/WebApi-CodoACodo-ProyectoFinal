package galeriaNFTs.application.services;

import java.util.ArrayList;

import galeriaNFTs.domain.models.Usuario;

import galeriaNFTs.domain.repositories.Ipersistencia.IPersistenciaUsuario;
import galeriaNFTs.infrastructure.persistencia.Implementation.MYSQLPersistenciaUserImpl;

public class UsuarioService implements IPersistenciaUsuario {

    private IPersistenciaUsuario sistemPersistencia = new MYSQLPersistenciaUserImpl();

    @Override
    public void saveUser(Usuario usuarios) {
        sistemPersistencia.saveUser(usuarios);
    }

    @Override
    public Usuario findByEmail(String email) {
        return sistemPersistencia.findByEmail(email);
    }

    @Override
    public Usuario autorizaLogin(String email, String password) {
        return sistemPersistencia.autorizaLogin(email, password);
    }

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        return sistemPersistencia.getAllUsuario();
    }

    @Override
    public void deleteUsuario(int id) {
        sistemPersistencia.deleteUsuario(id);
    }

}
