package galeriaNFTs.application.services;

import java.util.ArrayList;

import galeriaNFTs.Implementation.persistencia.MYSQLPersistenciaImpl;
import galeriaNFTs.domain.models.Usuario;
import galeriaNFTs.repositories.Ipersistencia.IPersistencia;

public class UsuarioService implements IPersistencia {

    private IPersistencia sistemPersistencia = new MYSQLPersistenciaImpl();

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
