package com.bugred.API.repository;

import com.bugred.API.model.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class StatusRepository {

    private final Map<UUID, Status> statusStorage = new HashMap<>();
    private final String filePath;
    private final Gson gson = new Gson();

    public StatusRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    public List<Status> findAll() {
        return new ArrayList<>(statusStorage.values());
    }

    public Status findById(UUID id) {
        return statusStorage.get(id);
    }

    public void save(Status status) {
        statusStorage.put(status.getId(), status);
        saveToFile();
    }

    public boolean delete(UUID id) {
        boolean removed = statusStorage.remove(id) != null;
        if (removed) saveToFile();
        return removed;
    }

    public boolean exists(UUID id) {
        return statusStorage.containsKey(id);
    }

    private void loadFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]"); // JSON vazio válido
                }
            }
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Status>>() {}.getType();
                List<Status> statusList = gson.fromJson(reader, listType);
                if (statusList != null) {
                    for (Status status : statusList) {
                        statusStorage.put(status.getId(), status);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar status do arquivo: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(statusStorage.values(), writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar status no arquivo: " + e.getMessage());
        }
    }
}
