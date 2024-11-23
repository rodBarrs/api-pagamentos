package br.com.projeto.api_pagamentos.controller;

import br.com.projeto.api_pagamentos.model.Pagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import br.com.projeto.api_pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/codigo-debito/{codigoDebito}")
    public List<Pagamento> buscarPorCodigoDebito(@PathVariable String codigoDebito) {
        return pagamentoService.buscarPorCodigoDebito(codigoDebito);
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public List<Pagamento> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return pagamentoService.buscarPorCpfCnpj(cpfCnpj);
    }

    @GetMapping("/status/{status}")
    public List<Pagamento> buscarPorStatus(@PathVariable StatusPagamento status) {
        return pagamentoService.buscarPorStatus(status);
    }

    @PatchMapping("/excluir-logico/{id}")
    public Pagamento excluirLogico(@PathVariable Long id) {
        return pagamentoService.excluirPagamento(id);
    }

}
