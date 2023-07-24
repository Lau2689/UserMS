package usermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usermicroservice.models.UserProducts;

public interface UserFavoriteProductsRepository extends JpaRepository <UserProducts, Integer> {

    /*@Query("select all from UserFavoriteProduct")
    List<UserFavoriteProducts> findAll();

    @Query("select a from UserFavoriteProduct a where a.name = :name")
    UserFavoriteProducts findById();

    UserFavoriteProducts deleteById();*/
}
