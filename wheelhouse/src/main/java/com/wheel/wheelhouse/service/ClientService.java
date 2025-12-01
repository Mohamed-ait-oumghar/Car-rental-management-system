package com.wheel.wheelhouse.service;

import com.wheel.wheelhouse.customException.ClientDeletionException;
import com.wheel.wheelhouse.dto.CarDto;
import com.wheel.wheelhouse.dto.ClientDto;
import com.wheel.wheelhouse.entity.Car;
import com.wheel.wheelhouse.entity.Client;
import com.wheel.wheelhouse.mapper.CarMapper;
import com.wheel.wheelhouse.mapper.ClientMapper;
import com.wheel.wheelhouse.repository.ClientRepository;
import com.wheel.wheelhouse.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    ClientRepository clientRepository;

    OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    //Create a client
    public Client createClient(ClientDto clientDto) {

        Client client   =   new Client();

        client.setClientFirstName(clientDto.getClientFirstName());
        client.setClientLastName(clientDto.getClientLastName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setCin(clientDto.getCin());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setGender(clientDto.getGender());
        client.setClientCity(clientDto.getClientCity());
        client.setClientCountry(clientDto.getClientCountry());

        return clientRepository.save(client);
    }
    //Get client by id
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    //get by client first name
    public List<Client> getByFirstName(String clientFirstName) {
        return clientRepository.findByClientFirstName(clientFirstName);
    }
    //get by phone number

    public Optional<Client> getByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    //Get client by CIN
    public Optional<Client> getByCin(String cin) {
        return clientRepository.findByCin(cin);
    }

    //Pagination
    public Page<ClientDto> getAllClients(Pageable pageable) {

        Page<Client> clientsPage = clientRepository.findAll(pageable);

        return clientsPage.map(ClientMapper::toDto);
    }

    //update client
    public ClientDto updateClient(String cin, ClientDto updateClientDto){

        Client existingClient  = clientRepository.findByCin(cin)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + cin));

        if (updateClientDto.getClientFirstName() != null){
            existingClient.setClientFirstName(updateClientDto.getClientFirstName());
        }
        if (updateClientDto.getClientLastName() != null){
            existingClient.setClientLastName(updateClientDto.getClientLastName());
        }
        if (updateClientDto.getPhoneNumber() != null){
            existingClient.setPhoneNumber(updateClientDto.getPhoneNumber());
        }
        if (updateClientDto.getDateOfBirth() != null){
            existingClient.setGender(updateClientDto.getGender());
        }
        if (updateClientDto.getClientCity() != null){
            existingClient.setClientCity(updateClientDto.getClientCity());
        }
        if (updateClientDto.getClientCountry() != null){
            existingClient.setClientCountry(updateClientDto.getClientCountry());
        }

        Client saved = clientRepository.save(existingClient);

        return ClientMapper.toDto(saved);
    }

    //delete client
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        boolean hasOrders = orderRepository.existsByClient_ClientId(id);

        if (hasOrders) {
            throw new ClientDeletionException(
                    "Cannot delete client with id " + id + " because they have existing orders."
            );
        }
        clientRepository.deleteById(id);
    }

}
