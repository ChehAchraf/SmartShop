package com.ecomm.smartshop.identity.mapper;

import com.ecomm.smartshop.customer.dto.ClientRequest;
import com.ecomm.smartshop.customer.dto.ClientResponse;
import com.ecomm.smartshop.identity.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    ClientResponse toResponse(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "CLIENT")
    @Mapping(target = "fidelityLevel", ignore = true)
    @Mapping(target = "totalOrders", ignore = true)
    @Mapping(target = "totalSpent", ignore = true)
    Client toEntity(ClientRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateEntityFromRequest(ClientRequest request, @MappingTarget Client client);
}
