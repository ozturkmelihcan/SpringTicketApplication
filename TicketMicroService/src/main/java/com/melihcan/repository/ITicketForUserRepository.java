package com.melihcan.repository;

import com.melihcan.repository.entity.TicketForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketForUserRepository extends JpaRepository<TicketForUser,Long> {
}
