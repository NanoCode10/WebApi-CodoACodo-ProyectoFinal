package galeriaNFTs.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    protected int id;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String password;

    @Override
    public String toString() {
        return "Usuario{" +
          "id=" + id +
          ", username='" + nombre + '\'' +
          ", apellido='" + apellido + '\'' +
          ", password='" + password + '\'' +
          ", email='" + email + '\'' +
          '}';
    }

}
