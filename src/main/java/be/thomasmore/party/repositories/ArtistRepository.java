package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("SELECT v FROM Artist v WHERE v.artistName ILIKE CONCAT('%', :keyword, '%') " +
            "OR v.bio ILIKE CONCAT('%', :keyword, '%') " +
            "OR v.genre ILIKE CONCAT('%', :keyword, '%') " +
            "OR v.portfolio ILIKE CONCAT('%', :keyword, '%')")
    List<Artist> findByFilter(@Param("keyword") String keyword);


}
