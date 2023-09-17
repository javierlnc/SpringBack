package com.inn.cafe.DAO;

import com.inn.cafe.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByIdentityDocument(@Param("identityDocument") String identityDocument);
}
