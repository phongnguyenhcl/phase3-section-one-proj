package com.simplilearn.phase2endproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.simplilearn.phase2endproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	/**
	 * find the user with the specified username
	 * @param username
	 * @return the found user
	 */
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String username);
	
	/**
	 * update user's hobby based on user's ID
	 * @param id
	 * @param hobby
	 */
	@Modifying
	@Query("UPDATE User u set u.hobby = :hobby where u.id = :id")
	void updateHobby(@Param(value = "id") int id, @Param(value = "hobby") String hobby);
}
