package service;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import model.Filme;

public class FilmesService {

	public HashMap<Integer, Filme> getFilmes() throws ClientProtocolException, IOException {

		HashMap<Integer, Filme> filmes = new HashMap<Integer, Filme>();

		HttpGet request = new HttpGet("https://sujeitoprogramador.com/r-api/?api=filmes");

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				String result = EntityUtils.toString(entity);

				JSONArray payload = new JSONArray(result);
				

				for (int i = 0; i < payload.length(); i++) {
					JSONObject filmeRecebido = payload.getJSONObject(i);
					
					Filme filme = new Filme();
					
					filme.setId(filmeRecebido.getInt("id"));
					filme.setNome(filmeRecebido.getString("nome"));
					filme.setSinopse(filmeRecebido.getString("sinopse"));
					
					filmes.put(i + 1, filme);
				
				}
			}

			return filmes;
		}

	}

}
