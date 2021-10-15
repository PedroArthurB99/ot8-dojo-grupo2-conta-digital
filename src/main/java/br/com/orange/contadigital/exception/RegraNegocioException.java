package br.com.orange.contadigital.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegraNegocioException extends RuntimeException{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjetoErroDTO objetoErroDTO;

    public RegraNegocioException(ObjetoErroDTO objetoErroDTO) {
        logger.warn(objetoErroDTO.toString());
        this.objetoErroDTO = objetoErroDTO;
    }

    public ObjetoErroDTO getObjetoErroDTO() {
        return objetoErroDTO;
    }
}
