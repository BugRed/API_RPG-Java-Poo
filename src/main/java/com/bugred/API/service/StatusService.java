package com.bugred.API.service;


import com.bugred.API.model.Status;
import com.bugred.API.repository.StatusRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Service é quem vai interagir com o repository para evitar expor codigos
public class StatusService {

    // Criando uma instancia de service
    private static StatusService instance;
    // Criando instancia de repository
    private final StatusRepository repository = new StatusRepository();


    // Id inicial para status e character
    private static AtomicInteger nextStatusId = new AtomicInteger(1);


    /*
        FUNÇÕES PARA STATUS
    */

    // Recuperando a instancia ou criando se não houver
    public static StatusService getInstance() {
        if (instance == null) {
            instance = new StatusService();
        }
        return instance;
    }

    // Utilizando o repository para executar ações no banco

    public List<Status> findAll() {
        return repository.findAll();
    }

    public Status findById(int id) {
        return repository.findById(id);
    }

    // Criar status
    public Status create(Status status) {
        int id = nextStatusId.getAndIncrement();
        status.setId(id);
        repository.save(status);
        return status;
    }

    public Status update(int id, Status updatedStatus) {
        if (!repository.exists(id)) return null;
        updatedStatus.setId(id);
        repository.save(updatedStatus);
        return updatedStatus;
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

}

