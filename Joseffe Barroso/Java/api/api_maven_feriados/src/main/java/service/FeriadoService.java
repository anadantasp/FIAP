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

public class FeriadoService {

	public HashMap<String, String> getFeriados() throws ClientProtocolException, IOException {
		
		HashMap<String, String> feriados = new HashMap<String, String>();
		String dataFormatada;

		HttpGet request = new HttpGet("https://brasilapi.com.br/api/feriados/v1/2023");

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				String result = EntityUtils.toString(entity);

				JSONArray payload = new JSONArray(result);;

				for(int i = 0; i < payload.length(); i++) {
					JSONObject feriado = payload.getJSONObject(i);
					
					dataFormatada = feriado.getString("date");
					dataFormatada = dataFormatada.substring(8, 10) + "/" + dataFormatada.substring(5, 7) + "/" + dataFormatada.substring(0, 4);
					
					feriados.put(dataFormatada, feriado.getString("name"));
					
				}
			}

			return feriados;
		}
	}
}
