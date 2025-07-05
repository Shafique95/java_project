package com.basystems.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.basystems.ebs.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM users WHERE email_or_phone = :emailOrPhone LIMIT 1", nativeQuery = true)
	User findFirstByEmailOrPhone(@Param("emailOrPhone") String emailOrPhone);


}
