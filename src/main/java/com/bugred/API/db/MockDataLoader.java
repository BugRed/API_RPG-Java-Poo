package com.bugred.API.db;

import com.google.gson.Gson;

// Essa classe faz o mock de dados para criar
// um banco de dados para testes na memoria
// nesse caso em um json chamado mockData em resources
public class MockDataLoader {

    // Criando uma instância di Gson para ser a única
    // usada na classe toda
    private static final Gson gson = new Gson();

    public static void loadMockData(PlayerService playService){

    }


}
