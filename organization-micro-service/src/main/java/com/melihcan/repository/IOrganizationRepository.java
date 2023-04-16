package com.melihcan.repository;

import com.melihcan.repository.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrganizationRepository extends MongoRepository<Organization,String> {
    Optional<Organization> findOptionalByAuthid(Long authid);
}
