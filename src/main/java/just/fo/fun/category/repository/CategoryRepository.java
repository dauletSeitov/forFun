package just.fo.fun.category.repository;

import just.fo.fun.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select C from Category C where C.isDeleted = false and C.name = :categoryName")
    Category findOneNotDeletedByName(@Param("categoryName") String categoryName);

}
