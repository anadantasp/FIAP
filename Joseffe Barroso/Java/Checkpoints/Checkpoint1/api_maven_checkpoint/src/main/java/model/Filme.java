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
	public void addComentario(String nomeUsuario, String comentarioUsuario) {
		
		Comentario comentario = new Comentario();
		
		int id;
		int maiorID = 0;
		
		if(comentarios.size() == 0) {
			id = 1;
		}else {
			for(Integer i: comentarios.keySet()) {
				if(i > maiorID) {
					maiorID = i;
				}
			}
			
			id = maiorID + 1;

		}
		
		comentario.setId(id);
		comentario.setComentario(comentarioUsuario);
		comentario.setNomeUsuario(nomeUsuario);
		
		comentarios.put(id, comentario);	
		
	}
	
	public void deletarComentario(Integer id) {
		if (comentarios.containsKey(id)) {
     	   comentarios.remove(id);
     	   System.out.printf("Comentario excluído com sucesso!");
        }
        else
     	   System.out.printf("Comentario não encontrado!");
	}
	
	public void exibirComentario() {
		comentarios.forEach((chave, valor) -> {
            System.out.printf("\nID: " + chave + " - Comentário: " + valor.getComentario() + " - feito por: " + valor.getNomeUsuario() + valor.getId());
          });
	}
	
	
	
	
}
