package galeriaNFTs.repositories.Ipersistencia;

import java.util.ArrayList;

import galeriaNFTs.domain.models.Favorite;

public interface IPersistenciaFavorite {

    void saveFavorite(Favorite favorite);

    ArrayList<Favorite> findByFavoriteUser(Integer idUser);

    void deleteFavorite(String idNft, int idUser);

}
