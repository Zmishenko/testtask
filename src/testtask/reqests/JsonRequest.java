package testtask.reqests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import testtask.exceptions.RequestException;

public class JsonRequest {

	private JSONObject jsonResponse;

	public JsonRequest(String url) throws Exception {
		try {
			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

			httpClient.setRequestMethod("GET");

			int responseCode = httpClient.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				this.jsonResponse = new JSONObject(response.toString());
			} else {
				throw new RequestException("GET request not worked");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (RequestException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	public JSONObject getJsonResponse() {
		return jsonResponse;
	}
}