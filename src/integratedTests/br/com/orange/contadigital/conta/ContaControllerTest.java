package br.com.orange.contadigital.conta;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ContaControllerTest {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MockMvc mockMvc;

    private Gson gson = new Gson();



}