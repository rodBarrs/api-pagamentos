package br.com.projeto.api_pagamentos.model;

import br.com.projeto.api_pagamentos.model.enumeration.MetodoPagamento;
import br.com.projeto.api_pagamentos.model.enumeration.StatusPagamento;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer codigoDebito;

    private String cpfCnpjPagador;

    private String numeroCartao;

    private Double valorPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private boolean ativo;


}
