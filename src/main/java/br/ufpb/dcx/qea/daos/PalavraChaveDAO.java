package br.ufpb.dcx.qea.daos;

import br.ufpb.dcx.qea.entidades.PalavraChave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PalavraChaveDAO extends JpaRepository<PalavraChave, String> {
	boolean existsByPalavraChave(String palavra);
	PalavraChave findByPalavraChave(String palavra);
}
