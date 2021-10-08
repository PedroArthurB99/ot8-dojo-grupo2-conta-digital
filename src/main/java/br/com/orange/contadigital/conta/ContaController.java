package br.com.orange.contadigital.conta;

import br.com.orange.contadigital.exception.ObjetoErroDTO;
import br.com.orange.contadigital.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contas-digitais")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    @RequestMapping("/creditar/{id}")
    public void creditarEmConta(@PathVariable("id") Long id, @RequestBody OperacaoForm form) {
        ContaDigital conta = this.contaRepository.findById(id).orElseThrow(() -> new RegraNegocioException(new ObjetoErroDTO("id",
                "Não existe uma conta com esse id.")));
        conta.creditar(form.getValor());
    }
}
