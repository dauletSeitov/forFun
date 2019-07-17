package just.fo.fun.user.repository;

import just.fo.fun.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.isDeleted = false and u.login = :login")
    User findOneByLogin(@Param("login") String login);

    @Query("select u from User u where u.isDeleted = false and u.id = :id")
    User findOneNotDeleted(@Param("id") Long id);

}
