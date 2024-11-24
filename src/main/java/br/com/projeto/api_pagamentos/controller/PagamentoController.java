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

    @GetMapping("/listar")
    public ResponseEntity<List<Pagamento>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarPagamentos());
    }


    @GetMapping("/listar/{codigoDebito}")
    public ResponseEntity<List<Pagamento>> listarPorCodigoDebito(@PathVariable Integer codigoDebito) {
        return ResponseEntity.ok(pagamentoService.listarPorCodigoDebito(codigoDebito));
    }

    @GetMapping("/cpfCnpj/{cpfCnpjPagador}")
    public ResponseEntity<List<Pagamento>> listarPorCpfCnpj(@PathVariable String cpfCnpjPagador) {
        return ResponseEntity.ok(pagamentoService.listarPorCpfCnpj(cpfCnpjPagador));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pagamento>> listarPorStatus(@PathVariable StatusPagamento status) {
        return ResponseEntity.ok(pagamentoService.listarPorStatus(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPagamento(@PathVariable Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
