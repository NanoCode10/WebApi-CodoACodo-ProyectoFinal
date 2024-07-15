package galeriaNFTs.repositories.Ipersistencia;

import java.util.ArrayList;

import galeriaNFTs.domain.models.Favorite;
import galeriaNFTs.domain.models.Usuario;

public interface IPersistenciaFavorite {

    void saveFavorite(Favorite favorite);

    Usuario findByFavoriteUser(Integer idUser);

    void deleteFavorite(int idNft, int idUser);

}
