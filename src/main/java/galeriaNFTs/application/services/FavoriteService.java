package galeriaNFTs.application.services;

import java.util.ArrayList;

import galeriaNFTs.Implementation.persistencia.MYSQLPersistenciaFavoriteImpl;

import galeriaNFTs.domain.models.Favorite;
import galeriaNFTs.domain.models.Usuario;

import galeriaNFTs.repositories.Ipersistencia.IPersistenciaFavorite;

public class FavoriteService implements IPersistenciaFavorite {

    private IPersistenciaFavorite sistemaFavoritePersistencia = new MYSQLPersistenciaFavoriteImpl();

    @Override
    public void saveFavorite(Favorite favorite) {
        sistemaFavoritePersistencia.saveFavorite(favorite);
    }

    @Override
    public Usuario findByFavoriteUser(Integer idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByFavoriteUser'");
    }

    @Override
    public void deleteFavorite(int idNft, int idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFavorite'");
    }

}
