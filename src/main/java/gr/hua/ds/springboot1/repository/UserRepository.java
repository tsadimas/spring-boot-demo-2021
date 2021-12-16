package gr.hua.ds.springboot1.repository;

import gr.hua.ds.springboot1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="users")

public interface UserRepository extends JpaRepository<User, Integer> {
}

