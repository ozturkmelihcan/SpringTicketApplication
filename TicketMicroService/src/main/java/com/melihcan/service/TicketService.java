package com.melihcan.service;

import com.melihcan.mapper.ITicketMapper;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.rabbitmq.model.SaleTicketModel;
import com.melihcan.repository.ITicketRepository;
import com.melihcan.repository.entity.Ticket;
import com.melihcan.repository.entity.TicketForUser;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends ServiceManager<Ticket, Long> {

    private final ITicketRepository ticketRepository;

    private final TicketForUserService ticketForUserService;

    public TicketService(ITicketRepository ticketRepository, TicketForUserService ticketForUserService) {
        super(ticketRepository);
        this.ticketRepository = ticketRepository;


        this.ticketForUserService = ticketForUserService;
    }

    // alış ve satış metodlarım olacak


    public void createTicket(SaleTicketModel saleTicketModel) {
        Ticket ticket = ITicketMapper.INSTANCE.toTicket(saleTicketModel);
        ticket.setOrganizationid(saleTicketModel.getOrganizationid());
        System.out.println(saleTicketModel.getOrganizationid());
        save(ticket);
    }


    public void buyTicket(BuyTicketModel buyTicketModel) {

        Ticket ticket = ticketRepository.findByTitle(buyTicketModel.getTitle());

        System.out.println(ticket);
        if (ticket != null) {
            if (ticket.getTitle().equals(buyTicketModel.getTitle())) {
                ticket.setAmount(ticket.getAmount() - buyTicketModel.getAmount());
                TicketForUser ticketForUser = new TicketForUser();
                ticketForUser.setPrice(ticket.getPrice());
                ticketForUser.setTicketid(ticket.getId());
                ticketForUser.setAmount(buyTicketModel.getAmount());
                ticketForUser.setPrice(ticket.getPrice());
                ticketForUser.setUserid(buyTicketModel.getUserid());
                ticketForUserService.saveTicketForUser(ticketForUser);
                save(ticket);
            }
            System.out.println(ticket);
        }
        //   throw new TicketException(EErrorType.TICKET_NOT_FOUND);


    }
}
