package just.fo.fun.property.repository;

import just.fo.fun.entities.Property;
import just.fo.fun.property.servise.PropertyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select P from Property P where P.isDeleted = false and P.code = :code")
    Property findOneByCodeNotDeleted(@Param("code") PropertyService.PropertyCode code);

}
