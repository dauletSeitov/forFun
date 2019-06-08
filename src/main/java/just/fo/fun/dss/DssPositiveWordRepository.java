package just.fo.fun.dss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DssPositiveWordRepository extends JpaRepository<DssPositiveWord, Long> {

    @Query("select count(D) > 0 from DssPositiveWord D where D.word = :word ")
    Boolean contains (@Param("word") String word);

    @Query("update DssPositiveWord set isDeleted = 1 where word = :word")
    Long delete(String word);
}
