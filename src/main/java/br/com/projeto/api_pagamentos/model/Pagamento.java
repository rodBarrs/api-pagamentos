package br.com.projeto.api_pagamentos.model;

import br.com.projeto.api_pagamentos.model.enumeration.MetodoPagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer codigoDebito;

    @Column(nullable = false)
    private String cpfCnpjPagador;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status = StatusPagamento.PENDENTE;
}




