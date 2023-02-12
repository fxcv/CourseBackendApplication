package me.springprojects.coursebackend.repositories;

import me.springprojects.coursebackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username =:username AND u.password =:password")
    public boolean checkIfCorrectLogin(String username, String password);

}
