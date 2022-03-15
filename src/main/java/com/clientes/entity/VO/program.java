package com.clientes.entity.VO;

public class program
{
	private int id;
	private String Name;
	private String message;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public program(int id, String name, String message) {
		super();
		this.id = id;
		Name = name;
		this.message = message;
	}
	public program() {
		super();
	}
}