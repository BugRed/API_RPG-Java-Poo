package com.bugred.API.repository;

import com.bugred.API.model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusRepository {
        // Criando a instancia que será usada
        // Todos o Repository vai funcionar como um banco de dados usando MAP para executar funções
        private final Map<Integer, Status> statusStorage = new HashMap<>();


        // Funções basicas de CRUD
        public List<Status> findAll() {
            return new ArrayList<>(statusStorage.values());
        }

        public Status findById(int id) {
            return statusStorage.get(id);
        }

        public void save(Status status) {
            statusStorage.put(status.getId(), status);
        }

        public boolean delete(int id) {
            return statusStorage.remove(id) != null;
        }

        public boolean exists(int id) {
            return statusStorage.containsKey(id);
        }

        public Map<Integer, Status> getStorage() {
            return statusStorage;
        }
    }

