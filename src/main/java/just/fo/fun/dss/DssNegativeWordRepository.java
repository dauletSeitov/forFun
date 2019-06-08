package just.fo.fun.dss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DssNegativeWordRepository extends JpaRepository<DssNegativeWord, Long> {

    @Query("select count(D) > 0 from DssNegativeWord D where D.word = :word ")
    Boolean contains (@Param("word") String word);

    @Query("update DssNegativeWord set isDeleted = 1 where word = :word")
    Long delete(String word);

}
