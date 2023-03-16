package model;

import java.util.HashMap;

public class Filme {

	private int id;
	private String nome;
	private String sinopse;
	private HashMap <Integer, Comentario> comentarios = new HashMap<Integer, Comentario>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public HashMap<Integer, Comentario> getComentarios() {
		return comentarios;
	}
	
	int idComentario;
	public void addComentario(int idComentario, String nomeUsuario, String comentarioUsuario) {
		
		Comentario comentario = new Comentario();
		
		comentario.setId(idComentario);
		comentario.setComentario(comentarioUsuario);
		comentario.setNomeUsuario(nomeUsuario);
		
		comentarios.put(idComentario, comentario);	
		
	}
	
	public void deletarComentario(Integer id) {
		if (comentarios.containsKey(id)) {
     	   comentarios.remove(id);
     	   System.out.printf("Comentario excluído com sucesso!");
        }
        else
     	   System.out.printf("Comentario não encontrado!");
	}
	
	public void exibirIdComentario() {
		comentarios.forEach((chave, valor) -> {
            System.out.println("ID: " + chave + " - " + valor.getComentario());
          });
	}
	
	public void exibirComentarioCompleto() {
		comentarios.forEach((chave, valor) -> {
            System.out.println("ID: " + chave + " - " + valor.getComentario() + " - feito por: " + valor.getNomeUsuario());
          });
	}
	
	
	
	
}
