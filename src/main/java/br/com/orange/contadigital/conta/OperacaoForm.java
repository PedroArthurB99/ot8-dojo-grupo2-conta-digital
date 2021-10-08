package br.com.orange.contadigital.conta;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OperacaoForm {

    @Positive
    private BigDecimal valor;

    @Deprecated
    public OperacaoForm() {
    }

    public OperacaoForm(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
