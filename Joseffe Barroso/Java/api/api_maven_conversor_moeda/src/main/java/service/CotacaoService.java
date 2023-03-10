package service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class CotacaoService {

	public float getConversao(String conversor) throws ClientProtocolException, IOException {

		HttpGet request = new HttpGet("https://economia.awesomeapi.com.br/json/last/" + conversor);

		float valorMoeda = 0;

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();

			if (entity != null) {

				String result = EntityUtils.toString(entity);

				JSONObject payload = new JSONObject(result);

				conversor = conversor.replace("-", "");

				// System.out.print(payload.getJSONObject(conversor).get("ask"));

				valorMoeda = (float) Double.parseDouble(payload.getJSONObject(conversor).get("ask").toString());
			}

			return valorMoeda;
		}
	}
}
