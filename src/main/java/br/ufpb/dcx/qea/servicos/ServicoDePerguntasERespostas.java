package br.ufpb.dcx.qea.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.ufpb.dcx.qea.daos.PalavraChaveDAO;
import br.ufpb.dcx.qea.daos.PerguntaDAO;
import br.ufpb.dcx.qea.daos.RespostaDAO;
import br.ufpb.dcx.qea.dtos.PerguntaDTO;
import br.ufpb.dcx.qea.dtos.RespostaDTO;
import br.ufpb.dcx.qea.entidades.PalavraChave;
import br.ufpb.dcx.qea.entidades.Pergunta;
import br.ufpb.dcx.qea.entidades.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ServicoDePerguntasERespostas {
	@Autowired
	private PerguntaDAO repositorioDePerguntas;

	@Autowired
	private RespostaDAO repositorioDeRespostas;

	@Autowired
	private PalavraChaveDAO repositorioDePalavrasChave;

	public ServicoDePerguntasERespostas(PerguntaDAO repositorioDePerguntas, RespostaDAO repositorioDeRespostas,
			PalavraChaveDAO repositorioDePalavrasChave) {
		super();
		this.repositorioDePerguntas = repositorioDePerguntas;
		this.repositorioDeRespostas = repositorioDeRespostas;
		this.repositorioDePalavrasChave = repositorioDePalavrasChave;
	}

	public Pergunta adicionaPergunta(PerguntaDTO pergunta) {
		Pergunta aPergunta = new Pergunta(pergunta.getTexto());
		Pergunta perguntaSalva = repositorioDePerguntas.save(aPergunta);

		List<PalavraChave> asPalavrasChave = new ArrayList<>();

		List<String> palavrasChave = pergunta.extraiPalavrasChave();
		for (String palavra : palavrasChave) {
			System.out.println("PALAVRA_CHAVE =====> " + palavra.trim());
			PalavraChave umaPalavraChave;
			if (!repositorioDePalavrasChave.existsByPalavraChave(palavra.trim())) {
				System.out.println("ENTROOOUUUU");
				umaPalavraChave = new PalavraChave(palavra.trim());
				umaPalavraChave.adicionaPergunta(perguntaSalva);
				repositorioDePalavrasChave.save(umaPalavraChave);
			} else {
				umaPalavraChave = repositorioDePalavrasChave.findByPalavraChave(palavra.trim());
				System.out.println("PALAVRA CHAVE RECUPERADA ===> " + umaPalavraChave.getPalavraChave());
			}

			asPalavrasChave.add(umaPalavraChave);
		}

		perguntaSalva.setPalavrasChave(asPalavrasChave);
		Pergunta aPerguntaSalva = repositorioDePerguntas.save(perguntaSalva);
		return aPerguntaSalva;
	}

	public Resposta adicionaResposta(long id, RespostaDTO resposta) {
		if (repositorioDePerguntas.findById(id).isPresent()) {
			Resposta umaResposta = new Resposta(repositorioDePerguntas.findById(id).get(), resposta.getTexto());
			repositorioDeRespostas.save(umaResposta);
			return umaResposta;
		}

		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Pergunta nao existe.");
	}

	public List<Pergunta> getPerguntas(String palavraChave) {
		if (palavraChave.equals(""))
			return repositorioDePerguntas.findAll();

		Optional<PalavraChave> aPalavraChave = repositorioDePalavrasChave.findById(palavraChave);
		System.out.println("=========> palavraChave recuperada = " + aPalavraChave.get());
		if (aPalavraChave.isPresent()) {
			return aPalavraChave.get().getPerguntas();
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Palavra-chave nao encontrada: " + palavraChave);

	}

	public List<Resposta> getRespostas(long id) {
		if (repositorioDePerguntas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		return repositorioDeRespostas.findByPergunta(repositorioDePerguntas.findById(id).get());
	}

}
