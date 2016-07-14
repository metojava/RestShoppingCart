package com.mycompany.cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import com.mycompany.entities.Product;

public class ProductUtil {

	public static Product getProduct(String id) {
		Product p = null;
		try {
			URL url = new URL(
					"http://localhost:8080/AffableBeanRest/rest/entities.product/"
							+ id);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("unable to get data"
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String got = "",obj = "";
			while((got=br.readLine())!=null)
			{
				obj+=got;
			}
			conn.disconnect();
			ObjectMapper mapper = new ObjectMapper();
		    p = mapper.readValue(obj, Product.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}
	
	public static float getProductPrice(String productId)
	{
		Product p = getProduct(productId);
		return p.getPrice().floatValue();
	}
}
