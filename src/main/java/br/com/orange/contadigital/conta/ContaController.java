package br.com.orange.contadigital.conta;

import br.com.orange.contadigital.exception.ObjetoErroDTO;
import br.com.orange.contadigital.exception.RegraNegocioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    @RequestMapping("/{id}/credito")
    @Transactional
    public String deposito(@PathVariable("id") Long id, @RequestBody @Valid OperacaoForm form) {
        ContaDigital conta = this.contaRepository.findById(id).orElseThrow(() -> new RegraNegocioException(new ObjetoErroDTO("id",
                "Não existe uma conta com esse id.")));
        conta.deposito(form.getValor());
        contaRepository.save(conta);
        logger.info("Depósito realizado com sucesso no valor de: "+form.getValor()+" na conta: "+conta.getId());
        return "Seu saldo atualizado é " + conta.getSaldo();
    }

    @PostMapping
    @RequestMapping("/{id}/debito")
    @Transactional
    public String saque(@PathVariable("id") Long id, @RequestBody @Valid OperacaoForm form) {
        ContaDigital conta = this.contaRepository.findById(id).orElseThrow(() -> new RegraNegocioException(new ObjetoErroDTO("id",
                "Não existe uma conta com esse id.")));
        conta.saque(form.getValor());
        contaRepository.save(conta);
        logger.info("Saque realizado com sucesso no valor de: "+form.getValor()+" na conta: "+conta.getId());
        return "Seu saldo atualizado é " + conta.getSaldo();
    }
}
