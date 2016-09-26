package com.eshop.main.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.stream.JsonGenerationException;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ProductToJson {
	
	
	public  static SuggestItem createDummyObject(String id,String name,String description) {

		SuggestItem suggest = new SuggestItem();
		suggest.setValue(id);
		suggest.setLabel(name);
		suggest.setDesc(description);
		return suggest;

	}
	
	public static void saveJsonToFile(List<Product> productList, HttpServletRequest request){
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<SuggestItem> listToSave = new ArrayList<>();
		for(Product product : productList){
		SuggestItem suggest = createDummyObject(String.valueOf(product.getId()),
												product.getProductName(), 
												product.getDescription());
		listToSave.add(suggest);
		}
		try{
			// Convert object to JSON string and save into a file directly
			String fileName = request.getSession().getServletContext().getRealPath("/resources/");
			String path = fileName +  "productList.json";
			mapper.writeValue(new File(path), listToSave);
			System.out.println(path);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
