package just.fo.fun.dss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DssNegativeWordRepository extends JpaRepository<DssNegativeWord, Long> {

    @Query("select count(D) > 0 from DssNegativeWord D where D.word = :word and D.isDeleted = false")
    Boolean contains (@Param("word") String word);

    @Modifying
    @Query("update DssNegativeWord set isDeleted = true where word = :word")
    Integer setIsDeletedTrue(@Param("word") String word);


}
