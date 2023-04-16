package com.melihcan.service;

import com.melihcan.repository.ITicketForUserRepository;
import com.melihcan.repository.entity.TicketForUser;
import com.melihcan.utility.ServiceManager;

import org.springframework.stereotype.Service;

@Service
public class TicketForUserService extends ServiceManager<TicketForUser, Long> {

    private final ITicketForUserRepository ticketForUserRepository;


    public TicketForUserService(ITicketForUserRepository ticketForUserRepository) {
        super(ticketForUserRepository);
        this.ticketForUserRepository = ticketForUserRepository;
    }


    public void saveTicketForUser(TicketForUser ticketForUser) {
        if (ticketForUser == null) return;
        save(ticketForUser);
        System.out.println(ticketForUser);
    }
}
