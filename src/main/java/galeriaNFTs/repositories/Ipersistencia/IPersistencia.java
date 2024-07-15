package galeriaNFTs.repositories.Ipersistencia;

import java.util.ArrayList;

import galeriaNFTs.domain.models.Usuario;

public interface IPersistencia {

    void saveUser(Usuario usuario);

    Usuario findByEmail(String email);

    Usuario autorizaLogin(String email, String password);

    ArrayList<Usuario> getAllUsuario();

    void deleteUsuario(int id);

}
