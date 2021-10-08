package br.com.orange.contadigital.conta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ContaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private Long idCliente;

    private BigDecimal saldo;

    @Deprecated
    public ContaDigital() {
    }

    public ContaDigital(String numero, Long idCliente, BigDecimal saldo) {
        this.numero = numero;
        this.idCliente = idCliente;
        this.saldo = saldo;
    }

    public void creditar(BigDecimal valor) {
        this.saldo.add(valor);
    }
}
