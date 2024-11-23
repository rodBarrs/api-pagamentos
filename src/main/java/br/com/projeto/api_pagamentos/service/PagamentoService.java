package br.com.projeto.api_pagamentos.service;

import br.com.projeto.api_pagamentos.model.Pagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import br.com.projeto.api_pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> buscarPorCodigoDebito(String codigoDebito) {
        return pagamentoRepository.findByCodigoDebito(codigoDebito);
    }

    public List<Pagamento> buscarPorCpfCnpj(String cpfCnpj) {
        return pagamentoRepository.findByCpfCnpjPagador(cpfCnpj);
    }

    public List<Pagamento> buscarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findByStatus(status);
    }

    public Pagamento excluirPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        if (pagamento.getStatus() == StatusPagamento.PENDENTE) {
            pagamento.setAtivo(false);
            return pagamentoRepository.save(pagamento);
        }
        throw new RuntimeException("Somente pagamentos com status 'Pendente de Processamento' podem ser excluídos!");
    }
}
