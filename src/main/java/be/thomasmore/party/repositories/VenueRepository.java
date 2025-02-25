package be.thomasmore.party.repositories;


import be.thomasmore.party.model.Venue;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    // als er geen waarde wordt ingegeven voor de capaciteit dan is de parameter null en wordt de voorwaar
    //er na niet meer bekeken omdat er OR staat.
    @Query("SELECT v FROM Venue v WHERE " +
            "(:minCapacity IS NULL OR v.capacity >= :minCapacity) AND " +
            "(:maxCapacity IS NULL OR v.capacity <= :maxCapacity) AND " +
            "(:distanceToPublicTransportInKm IS NULL OR v.distanceFromPublicTransportInKm <= :distanceToPublicTransportInKm)")
    List<Venue> findByFilter(@Param("minCapacity") Integer minCapacity,
                             @Param("maxCapacity") Integer maxCapacity,
                             @Param("distanceToPublicTransportInKm") Integer distanceToPublicTransportInKm);

    @Query("SELECT v FROM Venue v WHERE (:minCapacity IS NULL OR v.capacity >= :minCapacity) " +
            "AND (:maxCapacity IS NULL OR v.capacity <= :maxCapacity) " +
            "AND (:maxDistanceToPublicTransport IS NULL OR v.distanceFromPublicTransportInKm <= :maxDistanceToPublicTransport) " +
            "AND (:filterFood IS NULL OR (v.foodProvided = :filterFood))")
    List<Venue> findByFood(@Param("filterFood") Boolean filterFood,
                           @Param("minCapacity") Integer minCapacity,
                           @Param("maxCapacity") Integer maxCapacity,
                           @Param("maxDistanceToPublicTransport") Integer maxDistanceToPublicTransport);

    @Query("SELECT v FROM Venue v WHERE (:minCapacity IS NULL OR v.capacity >= :minCapacity) " +
            "AND (:maxCapacity IS NULL OR v.capacity <= :maxCapacity) " +
            "AND (:distanceToPublicTransportInKm IS NULL OR v.distanceFromPublicTransportInKm <= :distanceToPublicTransportInKm) " +
            "AND (:filterIndoor IS NULL OR v.indoor = :filterIndoor) " +
            "AND (:filterOutdoor IS NULL OR v.outdoor = :filterOutdoor)")
    List<Venue> findByIndoorOutdoor(@Param("filterIndoor") Boolean filterIndoor,
                                    @Param("filterOutdoor") Boolean filterOutdoor,
                                    @Param("minCapacity") Integer minCapacity,
                                    @Param("maxCapacity") Integer maxCapacity,
                                    @Param("distanceToPublicTransportInKm") Integer distanceToPublicTransportInKm);


}
