package com.bugred.API.service;

import com.bugred.API.model.Status;
import com.bugred.API.repository.StatusRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class StatusService {

    private static StatusService instance;
    private final StatusRepository repository = new StatusRepository("src/main/resources/data/status.json");
    private static final AtomicInteger nextStatusId = new AtomicInteger(1);

    private StatusService() {
        // Recarrega os dados do repositório e ajusta o nextStatusId
        List<Status> statusList = repository.findAll();
    }

    public static StatusService getInstance() {
        if (instance == null) {
            instance = new StatusService();
        }
        return instance;
    }

    public List<Status> findAll() {
        return repository.findAll();
    }

    public Status findById(UUID id) {
        return repository.findById(id);
    }

    public Status create(Status status) {
        UUID id = UUID.randomUUID();
        status.setId(id);
        repository.save(status);
        return status;
    }

    public Status update(UUID id, Status updatedStatus) {
        if (!repository.exists(id)) return null;
        updatedStatus.setId(id);
        repository.save(updatedStatus);
        return updatedStatus;
    }

    public boolean delete(UUID id) {
        return repository.delete(id);
    }

    public void addStatus(Status status) {
        UUID id = UUID.randomUUID();
        status.setId(id);
        repository.save(status);
    }
}
