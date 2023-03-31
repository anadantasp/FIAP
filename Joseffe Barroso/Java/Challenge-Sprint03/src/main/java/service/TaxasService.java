package service;

import java.io.IOException;
import java.math.BigDecimal;
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

public class TaxasService {

public HashMap<String, Double> getTaxas() throws ClientProtocolException, IOException {
		
		HashMap<String, Double> taxas = new HashMap<String, Double>();
		String nome;
		BigDecimal valor;
		double valores;
		HttpGet request = new HttpGet("https://brasilapi.com.br/api/taxas/v1");

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				String result = EntityUtils.toString(entity);

				JSONArray payload = new JSONArray(result);;

				for(int i = 0; i < payload.length(); i++) {
					JSONObject taxa = payload.getJSONObject(i);
					
					nome = taxa.getString("nome");
					valor = (BigDecimal) taxa.get("valor");
					valores = valor.doubleValue();
					taxas.put(nome, valores);
					
				}
			}

			return taxas;
		}
	}
}
