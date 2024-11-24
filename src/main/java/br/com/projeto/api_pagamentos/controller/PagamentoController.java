package br.com.projeto.api_pagamentos.controller;

import br.com.projeto.api_pagamentos.model.Pagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import br.com.projeto.api_pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping("/novo")
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.criarPagamento(pagamento));
    }

    @PatchMapping("/atualizar-status/{id}")
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusPagamento novoStatus) {
        return ResponseEntity.ok(pagamentoService.atualizarStatus(id, novoStatus));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Pagamento>> listarTodosPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarPagamentos());
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Pagamento>> listarPagamentosAtivos() {
        return ResponseEntity.ok(pagamentoService.listarPagamentosAtivos());
    }

    @GetMapping("/listar-codigo_debito/{codigoDebito}")
    public ResponseEntity<List<Pagamento>> listarPorCodigoDebito(@PathVariable Integer codigoDebito) {
        return ResponseEntity.ok(pagamentoService.listarPorCodigoDebito(codigoDebito));
    }

    @GetMapping("/listar-cpf-cnpj/{cpfCnpjPagador}")
    public ResponseEntity<List<Pagamento>> listarPorCpfCnpj(@PathVariable String cpfCnpjPagador) {
        return ResponseEntity.ok(pagamentoService.listarPorCpfCnpj(cpfCnpjPagador));
    }

    @GetMapping("/listar-status/{status}")
    public ResponseEntity<List<Pagamento>> listarPorStatus(@PathVariable StatusPagamento status) {
        return ResponseEntity.ok(pagamentoService.listarPorStatus(status));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirPagamento(@PathVariable Long id) {
        String mensagem = pagamentoService.excluirPagamento(id);
        return ResponseEntity.ok(mensagem);
    }

}
