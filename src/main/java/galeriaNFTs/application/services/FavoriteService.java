package galeriaNFTs.application.services;

import java.util.ArrayList;

import galeriaNFTs.domain.models.Favorite;
import galeriaNFTs.domain.repositories.Ipersistencia.IPersistenciaFavorite;
import galeriaNFTs.infrastructure.persistencia.Implementation.MYSQLPersistenciaFavoriteImpl;

public class FavoriteService implements IPersistenciaFavorite {

    private IPersistenciaFavorite sistemaFavoritePersistencia = new MYSQLPersistenciaFavoriteImpl();

    @Override
    public void saveFavorite(Favorite favorite) {
        sistemaFavoritePersistencia.saveFavorite(favorite);
    }

    @Override
    public ArrayList<Favorite> findByFavoriteUser(Integer idUser) {
        return sistemaFavoritePersistencia.findByFavoriteUser(idUser);
    }

    @Override
    public void deleteFavorite(String idNft, int idUser) {
        sistemaFavoritePersistencia.deleteFavorite(idNft, idUser);
    }

}
