package just.fo.fun.category.repository;

import just.fo.fun.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select C from Category C where C.isDeleted = false and C.name = :categoryName")
    Category findOneNotDeletedByNameNotDeleted(@Param("categoryName") String categoryName);

    @Query("select C from Category C where C.isDeleted = false and UPPER(C.name) like CONCAT('%', UPPER(:searchText), '%')")
    List<Category> findCategoryBySearchTextNotDeleted(@Param("searchText") String searchText);

    @Query("update Category set isDeleted = true where id = :categoryId")
    void delete(@Param("categoryId") Long categoryId);
}
