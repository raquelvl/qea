package br.ufpb.dcx.qea.excecoes;

import br.ufpb.dcx.qea.dtos.DetalhesDoProblema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;


@RestControllerAdvice
public class HandlerDeExcecoes {

    private static String URI_ERRO = "https://servidor:8080/v1/api/produtos";

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<DetalhesDoProblema> recursoNaoEncontrado(HttpClientErrorException hce) {
        DetalhesDoProblema problema = DetalhesDoProblema
                .builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(URI_ERRO)
                .title("Pergunta não encontrada")
                .detail("Não existe pergunta com esse id")
                .build();
        return new ResponseEntity<>(problema, HttpStatus.NOT_FOUND);
    }

}
