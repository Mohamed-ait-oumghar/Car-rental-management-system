package com.wheel.wheelhouse.mapper;
import com.wheel.wheelhouse.dto.ClientDto;
import com.wheel.wheelhouse.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    //Entity --> Dto
    public static ClientDto toDto(Client client){
        if(client == null) return null;

        ClientDto clientDto = new ClientDto();
        clientDto.setClientFirstName(client.getClientFirstName());
        clientDto.setClientLastName(client.getClientLastName());
        clientDto.setGender(client.getGender());
        clientDto.setClientCity(client.getClientCity());
        clientDto.setClientCountry(client.getClientCountry());

        return clientDto;

    }
    //Dto --> Entity
    public static Client toEntity(ClientDto clientDto) {
        if(clientDto == null) return null;

        Client client = new Client();
        client.setClientFirstName(clientDto.getClientFirstName());
        client.setClientLastName(clientDto.getClientLastName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setCin(clientDto.getCin());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setGender(clientDto.getGender());
        client.setClientCity(clientDto.getClientCity());
        client.setClientCountry(clientDto.getClientCountry());

        return client;

    }
}
