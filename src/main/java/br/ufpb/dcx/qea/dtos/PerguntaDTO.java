package br.ufpb.dcx.qea.dtos;

import java.util.Arrays;
import java.util.List;

public class PerguntaDTO {
	private String texto;
	private String palavrasChave;
	private String separador = ",";

	public PerguntaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerguntaDTO(String texto, String palavrasChave, String separador) {
		super();
		this.texto = texto;
		this.palavrasChave = palavrasChave;
		this.separador = separador;
	}

	public List<String> extraiPalavrasChave() {
		return Arrays.asList(palavrasChave.split(separador));
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public String getSeparador() {
		return separador;
	}

	public void setSeparador(String separador) {
		this.separador = separador;
	}

}
