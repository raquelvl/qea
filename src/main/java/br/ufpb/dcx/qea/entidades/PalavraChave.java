package br.ufpb.dcx.qea.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class PalavraChave {
	@Id @GeneratedValue
	private Long id;

	@Column(unique=true)
	private String palavraChave;

	@ManyToMany(mappedBy = "palavrasChave")
	@JsonIgnore
	private List<Pergunta> perguntas = new ArrayList<>();

	public PalavraChave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PalavraChave(String palavraChave) {
		super();
		this.palavraChave = palavraChave;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	
	public void adicionaPergunta(Pergunta pergunta) {
		perguntas.add(pergunta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((palavraChave == null) ? 0 : palavraChave.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PalavraChave other = (PalavraChave) obj;
		if (palavraChave == null) {
			if (other.palavraChave != null)
				return false;
		} else if (!palavraChave.equals(other.palavraChave))
			return false;
		return true;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
