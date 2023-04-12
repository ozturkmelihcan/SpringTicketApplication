package com.melihcan.repository;

import com.melihcan.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth>findOptionalByUsernameAndPassword(String username,String password);
    @Query("select count (*)>0 from Auth  a where a.username=?1")
    Boolean isUsername(String usernama);
}
