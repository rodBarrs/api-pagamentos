package br.com.projeto.api_pagamentos.repository;

import br.com.projeto.api_pagamentos.model.Pagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByCodigoDebito(String codigoDebito);
    List<Pagamento> findByCpfCnpjPagador(String cpfCnpjPagador);
    List<Pagamento> findByStatus(StatusPagamento status);

}
