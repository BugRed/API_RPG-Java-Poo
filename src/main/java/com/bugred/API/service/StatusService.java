package com.bugred.API.service;

import com.bugred.API.model.Status;
import com.bugred.API.repository.StatusRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatusService {

    private static StatusService instance;
    private final StatusRepository repository = new StatusRepository("data/status.json");
    private static final AtomicInteger nextStatusId = new AtomicInteger(1);

    private StatusService() {
        // Recarrega os dados do repositório e ajusta o nextStatusId
        List<Status> statusList = repository.findAll();
        if (!statusList.isEmpty()) {
            int maxId = statusList.stream().mapToInt(Status::getId).max().orElse(0);
            nextStatusId.set(maxId + 1);
        }
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

    public Status findById(int id) {
        return repository.findById(id);
    }

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

    public void addStatus(Status status) {
        int id = nextStatusId.getAndIncrement();
        status.setId(id);
        repository.save(status);
    }
}
