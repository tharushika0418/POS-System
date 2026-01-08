package cmjd_106.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cmjd_106.project.demo.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    //Advanced custom queries
    Optional <User> findByUsername(String username);   

    
}
