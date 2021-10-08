package br.com.orange.contadigital.conta;

import br.com.orange.contadigital.conta.cliente.Cliente;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ContaControllerTest {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();

    private Long id;

    @BeforeEach
    public void popularBase() {
        contaRepository.deleteAll();
        ContaDigital contaMarcelo = contaRepository.save(new ContaDigital("00123", new Cliente("Marcelo"), new BigDecimal(100)));
        this.id = contaMarcelo.getId();
    }

    @Test
    @DisplayName("Debitar com sucesso")
    public void debitarDeContaComSucesso() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(10));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Seu saldo atualizado é 90"));
    }

    @Test
    @DisplayName("Debitar com falha com saldo insuficiente")
    public void debitarDeContaComFalhaSaldoInsuficiente() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(150));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Debitar com falha com valor negativo")
    public void debitarDeContaComFalhaValorNegativo() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(-50));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Creditar com sucesso")
    public void creditarDeContaComSucesso() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(10));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/credito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Creditar com falha com valor zero")
    public void creditarDeContaComFalhaValorZero() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Creditar com falha com valor negativo")
    public void creditarDeContaComFalhaValorNegativo() throws Exception {
        OperacaoForm operacao = new OperacaoForm(new BigDecimal(-50));

        mockMvc.perform(MockMvcRequestBuilders.post("/contas/" + this.id + "/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(operacao)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}