package br.com.victor.realmeet.domain.repository;

import br.com.victor.realmeet.domain.entity.Allocation;
import br.com.victor.realmeet.domain.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndActive(Long id, Boolean active);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Room r SET r.active = false WHERE r.id = :roomId")
    void deactivate(Long roomId);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Room r SET r.name = :name, r.seats = :seats WHERE r.id = :roomId")
    void updateRoom(@Param("roomId") Long roomId, @Param("name") String name, @Param("seats") Integer seats);


    @Query(
            "SELECT r " +
                    "FROM Room r " +
                    "WHERE (:name IS NULL OR r.name = :name) AND " +
                    "(:active IS NULL OR r.active = :active)"
    )
    Page<Room> findAllWinthFilter(
            @Param("name") String name,
            @Param("active") Boolean active,
            Pageable pageable
    );
}
