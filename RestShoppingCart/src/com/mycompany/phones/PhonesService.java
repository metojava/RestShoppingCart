package com.mycompany.phones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.mycompany.entities.Product;

@Path("/phones")
public class PhonesService {

	@GET
	@Path("/{id}")
	@Produces({ "application/xml", "application/json" })
	public Phone find(@PathParam("id") String id) {
		Phone returnPhone = new Phone();
		List<Phone> phones = readPhones();
		for (Iterator iterator = phones.iterator(); iterator.hasNext();) {
			Phone phone = (Phone) iterator.next();
			if (phone.getId().equalsIgnoreCase(id))
				returnPhone = phone;
		}
		return returnPhone;
	}

	@GET
	@Path("/list")
	@Produces({ "application/xml", "application/json" })
	public List<Phone> findAllPhones() {
		return readPhones();
	}

	@GET
	@Path("/{from}/{to}")
	@Produces({ "application/xml", "application/json" })
	public List<Phone> findRange(@PathParam("from") Integer from,
			@PathParam("to") Integer to) {

		List<Phone> rangePhones = new ArrayList<>();
		List<Phone> phones = readPhones();
		for (int i = from; i < to; i++) {
			rangePhones.add(phones.get(i));
		}
		return rangePhones;
	}

	@GET
	@Path("/phonesFile")
	@Produces({ "application/json" })
	public Response getPhonesFile() {
		File file = new File("files/phones.json");
		return Response.ok(file, MediaType.APPLICATION_JSON).build();
	}

	public ArrayList<Phone> readPhones() {

		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jf = new JsonFactory();
		ArrayList<Phone> phones = null;

		TypeReference<ArrayList<Phone>> tr = new TypeReference<ArrayList<Phone>>() {
		};
		try {
			phones = mapper.readValue(new File("files/phones.json"), tr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phones;
	}

}
