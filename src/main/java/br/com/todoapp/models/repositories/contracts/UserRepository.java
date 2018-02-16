package br.com.todoapp.models.repositories.contracts;


import br.com.todoapp.models.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);
}
