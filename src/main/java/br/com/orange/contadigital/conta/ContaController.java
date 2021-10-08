package br.com.orange.contadigital.conta;

import br.com.orange.contadigital.exception.ObjetoErroDTO;
import br.com.orange.contadigital.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;


    @PostMapping
    @RequestMapping("/{id}/credito")
    @Transactional
    public void deposito(@PathVariable("id") Long id, @RequestBody OperacaoForm form) {
        ContaDigital conta = this.contaRepository.findById(id).orElseThrow(() -> new RegraNegocioException(new ObjetoErroDTO("id",
                "Não existe uma conta com esse id.")));
        conta.deposito(form.getValor());

    }

    @PostMapping
    @RequestMapping("/{id}/debito")
    @Transactional
    public void saque(@PathVariable("id") Long id, @RequestBody OperacaoForm form) {
        ContaDigital conta = this.contaRepository.findById(id).orElseThrow(() -> new RegraNegocioException(new ObjetoErroDTO("id",
                "Não existe uma conta com esse id.")));
        conta.saque(form.getValor());
    }
}
