package com.inetum.apigenerationloans.mapper;

import com.inetum.apigenerationloans.dto.LoanDTO;
import com.inetum.apigenerationloans.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "client.clientId", target = "clientId")
    LoanDTO toDTO(Loan entity);

}
