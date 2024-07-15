package galeriaNFTs.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    private int id;
    private int idUsuario;
    private int nftId;
    private double precio;
    private String name;
    private String subname;
    private String contrato;
    private String image;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idUsuario ='" + idUsuario + '\'' +
                ", nftId ='" + nftId + '\'' +
                ", precio ='" + precio + '\'' +
                ", name ='" + name + '\'' +
                ", subname ='" + subname + '\'' +
                ", contrato ='" + contrato + '\'' +
                ", image ='" + image + '\'' +

                '}';
    }

}
