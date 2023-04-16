package com.melihcan.service;

import com.melihcan.dto.request.SaleTicketRequestDto;
import com.melihcan.dto.request.UpdateOrganizationRequestDto;
import com.melihcan.exception.EErrorType;
import com.melihcan.exception.OrganizationException;
import com.melihcan.mapper.IOrganizationMapper;
import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.rabbitmq.model.SaleTicketModel;
import com.melihcan.rabbitmq.producer.SaleTicketProducer;
import com.melihcan.repository.IOrganizationRepository;
import com.melihcan.repository.entity.Organization;
import com.melihcan.utility.JwtTokenManager;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService extends ServiceManager<Organization,String > {

    private final IOrganizationRepository organizationRepository;

    private final JwtTokenManager jwtTokenManager;

    private final SaleTicketProducer saleTicketProducer;
    public OrganizationService(IOrganizationRepository organizationRepository, JwtTokenManager jwtTokenManager, SaleTicketProducer saleTicketProducer) {
        super(organizationRepository);
        this.organizationRepository = organizationRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.saleTicketProducer = saleTicketProducer;
    }

    public void save(CreateModel createModel) {
        Organization organization = IOrganizationMapper.INSTANCE.toOrganization(createModel);
        save(organization);
    }


    public Boolean updateDto(UpdateOrganizationRequestDto updateOrganizationRequestDto) {
        Optional<Long>authId = jwtTokenManager.getIdFromToken(updateOrganizationRequestDto.getToken());
        System.out.println(authId.get());
        if (authId.isEmpty()) throw new OrganizationException(EErrorType.INVALID_TOKEN);
        Optional<Organization> organization = organizationRepository.findOptionalByAuthid(authId.get());
        organization.get().setLastName(updateOrganizationRequestDto.getLastName());
        organization.get().setPhone(updateOrganizationRequestDto.getPhone());
        organization.get().setEmail(updateOrganizationRequestDto.getEmail());
        organization.get().setName(updateOrganizationRequestDto.getName());
        update(organization.get());

        return true;
    }



    public Boolean saleTicketDto(SaleTicketRequestDto saleTicketRequestDto) {
        Optional<Long>userId = jwtTokenManager.getIdFromToken(saleTicketRequestDto.getToken());
        Optional<Organization> organization = organizationRepository.findOptionalByAuthid(userId.get());
        if (userId.isEmpty()) throw new OrganizationException(EErrorType.INVALID_TOKEN);
        SaleTicketModel saleTicketModel = IOrganizationMapper.INSTANCE.toSaleTicketModel(saleTicketRequestDto);
        if(saleTicketModel==null) throw new OrganizationException(EErrorType.TICKET_NAME_ERROR);
        saleTicketModel.setOrganizationid(organization.get().getId());
        saleTicketProducer.createSendMessage(saleTicketModel);
        return true;
    }
}
