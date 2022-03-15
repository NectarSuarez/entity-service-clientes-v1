package com.clientes.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.entity.DAO.clienteDAO;
import com.clientes.entity.VO.cliente;
import com.clientes.entity.VO.program;

@Service
public class clienteService {
	
	@Autowired
	clienteDAO cliDAO;
	
	
	public List<cliente> readCliente(String name)
	{
		return cliDAO.readCliente(name);
	}
	
	public program saveCliente(List<cliente> clienteList)
	{
		return cliDAO.saveCliente(clienteList, "A");
	}
	
	public program updateCliente(cliente cliente)
	{
		return cliDAO.updateCliente(cliente);
	}
	
	public program deleteCliente(cliente cli)
	{
		return cliDAO.deleteCliente(cli);
	}

}
