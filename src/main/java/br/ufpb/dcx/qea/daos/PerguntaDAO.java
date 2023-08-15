package br.ufpb.dcx.qea.daos;

import br.ufpb.dcx.qea.entidades.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PerguntaDAO extends JpaRepository<Pergunta, Long> {

}
