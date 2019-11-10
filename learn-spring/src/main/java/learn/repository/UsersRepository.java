package learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learn.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


}