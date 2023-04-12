package com.melihcan.repository;

import com.melihcan.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {


    Optional<UserProfile> findOptionalByAuthid(Long authid);
}
