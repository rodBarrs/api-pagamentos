package br.com.projeto.api_pagamentos.service;

import br.com.projeto.api_pagamentos.model.Pagamento;
import br.com.projeto.api_pagamentos.model.enumeration.MetodoPagamento;
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
        validarPagamento(pagamento);
        return pagamentoRepository.save(pagamento);
    }


    public Pagamento atualizarStatus(Long id, StatusPagamento novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento com Id " + id + " não encontrado!"));

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

    public List<Pagamento> listarPorCodigoDebito(Integer codigoDebito) {
        return pagamentoRepository.findByCodigoDebito(codigoDebito);
    }

    public List<Pagamento> listarPorCpfCnpj(String cpfCnpjPagador) {
        return pagamentoRepository.findByCpfCnpjPagador(cpfCnpjPagador);
    }

    public List<Pagamento> listarPorStatus(StatusPagamento status) {
        return pagamentoRepository.findByStatus(status);
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

    private void validarPagamento(Pagamento pagamento) {
        if (isPagamentoComCartao(pagamento) && isNumeroCartaoInvalido(pagamento)) {
            throw new IllegalArgumentException("O número do cartão é obrigatório para pagamentos com cartão de crédito ou débito.");
        }

        if (!isPagamentoComCartao(pagamento) && pagamento.getNumeroCartao() != null) {
            throw new IllegalArgumentException("O número do cartão não deve ser enviado para pagamentos que não são com cartão.");
        }
    }


    private boolean isPagamentoComCartao(Pagamento pagamento) {
        return pagamento.getMetodoPagamento() == MetodoPagamento.CARTAO_CREDITO ||
                pagamento.getMetodoPagamento() == MetodoPagamento.CARTAO_DEBITO;
    }

    private boolean isNumeroCartaoInvalido(Pagamento pagamento) {
        return pagamento.getNumeroCartao() == null || pagamento.getNumeroCartao().isEmpty();
    }

}