package com.mycompany.cart;

public class Phone {

	private int age;
	private String carrier;
	private String id;
	private String imageUrl;
	private String name;
	private String snippet;

	public Phone(int age, String carrier, String id, String name,
			String imageUrl, String snippet) {
		this.age = age;
		this.carrier = carrier;
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.snippet = snippet;
	}

	public Phone() {

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

}
