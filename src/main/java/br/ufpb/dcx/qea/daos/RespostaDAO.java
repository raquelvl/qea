package br.ufpb.dcx.qea.daos;

import br.ufpb.dcx.qea.entidades.Pergunta;
import br.ufpb.dcx.qea.entidades.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RespostaDAO extends JpaRepository<Resposta, Long> {
	List<Resposta> findByPergunta(Pergunta pergunta);
}
