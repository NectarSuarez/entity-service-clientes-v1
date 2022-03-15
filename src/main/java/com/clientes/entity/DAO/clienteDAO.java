package com.clientes.entity.DAO;

import org.json.*;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.clientes.entity.VO.cliente;
import com.clientes.entity.VO.program;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@Repository
public class clienteDAO
{
	public List<cliente> readCliente(String name)
	{
		String fileName = "file.json";
		List<cliente> clienteList 	  = new ArrayList<cliente>();
		List<cliente> cliFilteredList = new ArrayList<cliente>();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	        CollectionType collectionTypes = mapper.getTypeFactory().constructCollectionType(List.class, cliente.class);
	        clienteList = mapper.readValue(new File(fileName), collectionTypes);
	        
	        if (name != "")
	        {
	        	for (int i = 0; i < clienteList.size(); i++)
		        {
		        	if (clienteList.get(i).getName().contains(name))
		        	{
		        		cliFilteredList.add(clienteList.get(i));
					}
				}
	        	return cliFilteredList;
	        }
	        
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return clienteList;
	}
	
	public program saveCliente(List<cliente> clienteList, String type)
	{
		int aux = 0;
		JSONArray 		jsonArrayObj	= new JSONArray();
		File 			file			= new File("file.json");
		List<cliente>	auxList 		= new ArrayList<cliente>();
		program 		pro 			= new program();
		
		/*
		 * U = Update, para indicar que fue actualizado el listado
		 * D = Delete, para indicar que se eliminó un objeto del listado
		 * A = Add, para indicar que se van agregar nuevos objetos a un listado exiustente, si no existe se crea uno nuevo. 
		*/
		
		if (type == "U" || type == "D")
		{
			for (int i = 0; i < clienteList.size(); i++)
			{
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", clienteList.get(i).getId());
				jsonObj.put("name", clienteList.get(i).getName());
				jsonObj.put("email", clienteList.get(i).getEmail());
				
				jsonArrayObj.put(jsonObj);
			}
			
			if (type == "U")
				pro.setMessage("Se actualizó correctamente");
			else
				pro.setMessage("Se eliminó correctamente");
			
			pro.setName("saveCliente");
		}
		else if (type == "A")
		{
			
			auxList = readCliente(null);
			if (auxList.size() != 0)
			{
				for (int i = 0; i < auxList.size(); i++)
				{
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("id", auxList.get(i).getId());
					jsonObj.put("name", auxList.get(i).getName());
					jsonObj.put("email", auxList.get(i).getEmail());
					
					aux = auxList.get(i).getId();
					jsonArrayObj.put(jsonObj);
				}
			}
			
			aux++;
			
			for (int i = 0; i < clienteList.size(); i++)
			{
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", i + aux);
				jsonObj.put("name", clienteList.get(i).getName());
				jsonObj.put("email", clienteList.get(i).getEmail());
				
				jsonArrayObj.put(jsonObj);
			}
			
			pro.setMessage("Se añadieron los nuevos clientes correctamente");
			pro.setName("saveCliente");
			
		}
		
		try
		{
			FileWriter fileSave = new FileWriter(file);
			fileSave.write(jsonArrayObj.toString());
			fileSave.flush();
			fileSave.close();
			
		} catch (Exception e)
		{
			pro.setMessage(e.getMessage());
			System.out.print(e.getMessage());
		}
		
		return pro;
	}
	
	public program updateCliente(cliente cli)
	{
		int status = 0;
		List<cliente> clienteList = new ArrayList<cliente>();
		program pro = new program();
		
		clienteList = readCliente(null);
		
		for (int i = 0; i < clienteList.size(); i++)
		{
			if (clienteList.get(i).getId() == cli.getId())
			{
				clienteList.get(i).setEmail(cli.getEmail());
				clienteList.get(i).setName(cli.getName());
				status = 1;
				break;
			}
		}
		
		if (status == 1)
		{
			try
			{
				pro = saveCliente(clienteList, "U");
			} catch (Exception e)
			{
				System.out.print(e.getMessage());
			}
		}
		else
		{
			pro.setMessage("No se pudo actualizar ya que no se encontró el cliente");
			pro.setName("updateCliente");
		}
		return pro;
	}
	
	public program deleteCliente(cliente cli)
	{
		int status = 0;
		List<cliente> clienteList = new ArrayList<cliente>();
		program pro = new program();
		
		clienteList = readCliente(null);
		
		for (int i = 0; i < clienteList.size(); i++)
		{
			if (clienteList.get(i).getId() == cli.getId())
			{
				clienteList.remove(i);
				status = 1;
				break;
			}
		}
		
		if (status == 1)
		{
			try
			{
				pro = (saveCliente(clienteList, "D"));
			} catch (Exception e)
			{
				System.out.print(e.getMessage());
			}
		}
		else
		{
			pro.setMessage("No se pudo eliminar porque no se encontró el cliente");
			pro.setName("deleteCliente");
		}
		
		return pro;
	}
}
