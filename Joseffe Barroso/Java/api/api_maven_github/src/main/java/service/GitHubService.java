package service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;


import model.Usuario;

public class GitHubService {

	public Usuario getUsuario(String username) throws ClientProtocolException, IOException {
	       
		Usuario usuario = null;
		
		HttpGet request = new HttpGet("https://api.github.com/users/" + username);
	       
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
               
                CloseableHttpResponse response = httpClient.execute(request)) {
           
            HttpEntity entity = response.getEntity();
           
            if(entity != null) {
               
                String result = EntityUtils.toString(entity);
               
                Gson gson = new Gson();
               
                usuario = gson.fromJson(result, Usuario.class);
            }
        }
		
		return usuario;
        
    }
}
