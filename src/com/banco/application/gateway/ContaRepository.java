package com.banco.application.gateway;

import com.banco.domain.model.Conta;

public interface ContaRepository {
    void salvar(Conta conta);
    Conta buscarContaPorNumero(String numeroConta);

}
