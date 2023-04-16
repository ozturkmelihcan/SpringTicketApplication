package com.melihcan.repository;

import com.melihcan.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {


    Ticket findByTitle(String title);
}
