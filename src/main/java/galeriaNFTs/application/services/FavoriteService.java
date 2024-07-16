package galeriaNFTs.application.services;

import java.util.ArrayList;

import galeriaNFTs.Implementation.persistencia.MYSQLPersistenciaFavoriteImpl;

import galeriaNFTs.domain.models.Favorite;

import galeriaNFTs.repositories.Ipersistencia.IPersistenciaFavorite;

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
    public void deleteFavorite(int idNft, int idUser) {
        sistemaFavoritePersistencia.deleteFavorite(idNft, idUser);
    }

}
