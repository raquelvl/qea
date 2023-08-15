package br.ufpb.dcx.qea.controladores;

import java.util.List;

import br.ufpb.dcx.qea.dtos.PerguntaDTO;
import br.ufpb.dcx.qea.dtos.RespostaDTO;
import br.ufpb.dcx.qea.entidades.Pergunta;
import br.ufpb.dcx.qea.entidades.Resposta;
import br.ufpb.dcx.qea.servicos.ServicoDePerguntasERespostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControladorDePerguntasERespostas {
	@Autowired
	private ServicoDePerguntasERespostas servicoDePerguntasERespostas;

	public ControladorDePerguntasERespostas(ServicoDePerguntasERespostas servicoDePerguntasERespostas) {
		super();
		this.servicoDePerguntasERespostas = servicoDePerguntasERespostas;
	}

	@PostMapping("/perguntas")
	public ResponseEntity<Pergunta> adicionaPergunta(@RequestBody PerguntaDTO pergunta) {
		return new ResponseEntity<Pergunta>(servicoDePerguntasERespostas.adicionaPergunta(pergunta),
				HttpStatus.CREATED);
	}

	@PostMapping("/perguntas/{id}/respostas")
	public ResponseEntity<Resposta> adicionaResposta(@PathVariable int id, @RequestBody RespostaDTO resposta) {
			return new ResponseEntity<Resposta>(servicoDePerguntasERespostas.adicionaResposta(id, resposta),
					HttpStatus.CREATED);
	}

	@GetMapping("/perguntas")
	public ResponseEntity<List<Pergunta>> getPerguntas(
			@RequestParam(name = "palavra-chave", required = false, defaultValue = "") String palavraChave) {
		return new ResponseEntity<List<Pergunta>>(servicoDePerguntasERespostas.getPerguntas(palavraChave),
				HttpStatus.OK);
	}
	
	@GetMapping("/perguntas/{id}/respostas")
	public ResponseEntity<List<Resposta>> getRespostas(@PathVariable long id) {
			return new ResponseEntity<List<Resposta>>(servicoDePerguntasERespostas.getRespostas(id),
					HttpStatus.OK);
	}

}
