package learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import learn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByFirstname(String firstname);

	User findUserByLastname(String lastname);

	List<User> findAllByLastname(String lastname);

	List<User> findDistinctUserByFirstnameOrLastname(String firstname, String lastname);

	List<User> findByFirstnameIgnoreCase(String firstname);

	List<User> findByFirstnameAllIgnoreCase(String firstname);

	List<User> findAllByOrderByFirstnameAsc(); // don't miss "by"

	List<User> findAllByOrderByEmailAddressDesc();

	// JPQL
	@Query(value = "SELECT u FROM User u ORDER BY email_address")
	List<User> JPQL();

	// JPQL
	@Query(value = "SELECT m FROM User m WHERE m.firstname LIKE :input%")
	List<User> startWith(@Param("input") String input);

	// Native SQL
	@Query(value = "SELECT * FROM t_user ORDER BY email_address DESC", nativeQuery = true)
	List<User> nativeSQL();

}
