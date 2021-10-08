package br.com.orange.contadigital.conta;

import br.com.orange.contadigital.conta.cliente.Cliente;
import br.com.orange.contadigital.exception.ObjetoErroDTO;
import br.com.orange.contadigital.exception.RegraNegocioException;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
public class ContaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @PositiveOrZero
    private BigDecimal saldo;

    @Deprecated
    public ContaDigital() {
    }

    public ContaDigital(String numero, Cliente cliente, BigDecimal saldo) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void deposito(BigDecimal valor) {
        this.saldo.add(valor);
    }

    public void saque(BigDecimal valor) {
        if (this.saldo.compareTo(valor) == -1){
            throw new RegraNegocioException(new ObjetoErroDTO("valor", "Saldo insuficiente."));
        }
        this.saldo.subtract(valor);
    }
}
