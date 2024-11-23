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

    public Pagamento criarPagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarStatus(Long id, StatusPagamento novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento com Id " + id +" não encontrado!"));

        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_SUCESSO) {
            throw new IllegalStateException("Não é possível alterar o status de um pagamento já processado com sucesso.");
        }
        if (pagamento.getStatus() == StatusPagamento.PROCESSADO_FALHA && novoStatus != StatusPagamento.PENDENTE) {
            throw new IllegalStateException("Pagamentos com falha só podem voltar para Status Pendente.");
        }

        pagamento.setStatus(novoStatus);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
        }

    public void excluirPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado!"));

        if (pagamento.getStatus() == StatusPagamento.PENDENTE) {
            pagamento.setStatus(StatusPagamento.INATIVO);
            pagamentoRepository.save(pagamento);
        } else {
            throw new IllegalStateException("Somente pagamentos pendentes podem ser excluídos.");
        }
    }

}