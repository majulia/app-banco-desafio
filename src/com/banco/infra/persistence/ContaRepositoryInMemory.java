package com.banco.infra.persistence;

import com.banco.application.repository.ContaRepository;
import com.banco.domain.model.Conta;

import java.util.HashMap;
import java.util.Map;

public class ContaRepositoryInMemory implements ContaRepository {
    private Map<String, Conta> contaMap = new HashMap<>();

    @Override
    public void salvar(Conta conta) {
        contaMap.put(conta.getNumero(), conta);
    }

    @Override
    public Conta buscarContaPorNumero(String numeroConta) {
        return contaMap.get(numeroConta);
    }
}
