package com.melihcan.service;

import com.melihcan.dto.request.CreateTicketRequestDto;
import com.melihcan.exception.EErrorType;
import com.melihcan.exception.TicketException;
import com.melihcan.mapper.ITicketMapper;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.repository.ITicketRepository;
import com.melihcan.repository.entity.Ticket;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends ServiceManager<Ticket, Long> {

    private final ITicketRepository ticketRepository;



    public TicketService(ITicketRepository ticketRepository ) {
        super(ticketRepository);
        this.ticketRepository = ticketRepository;

    }

    // model alacak
    public Boolean createTicket(BuyTicketModel buyTicketModel) {
        Ticket ticket = new Ticket();
        ticket.setTime(buyTicketModel.getTime());
        ticket.setKoltukno(buyTicketModel.getKoltukno());
        ticket.setUserid(buyTicketModel.getUserid());
        System.out.println("ticket mıcroservıce create ticket metodu time" + ticket.getTime());
        System.out.println("ticket mıcroservıce create ticket metodu time" + ticket.getKoltukno());
        System.out.println("ticket mıcroservıce create ticket metodu time" + ticket.getUserid());
        save(ticket);
        return true;
    }


}
