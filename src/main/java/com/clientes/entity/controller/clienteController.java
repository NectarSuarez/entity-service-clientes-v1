package com.clientes.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.entity.VO.cliente;
import com.clientes.entity.VO.program;
import com.clientes.entity.service.clienteService;

@RestController
@RequestMapping("cliente")
public class clienteController
{
	@Autowired
	clienteService cliService;
	
	@RequestMapping(value = "/GetCliente", method = RequestMethod.GET)
	public List<cliente> readCliente(@RequestParam(defaultValue = "") String name)
	{
		return cliService.readCliente(name);
	}
	
	@PostMapping(value = "/SetCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public program saveCliente(@RequestBody List<cliente> clienteList)
	{
		return cliService.saveCliente(clienteList);
	}
	
	@PostMapping(value = "/UpdateCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public program updateCliente(@RequestBody cliente cliente)
	{
		return cliService.updateCliente(cliente);
	}
	
	@PostMapping(value = "/DeleteCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public program deleteCliente(@RequestBody cliente cli)
	{
		return cliService.deleteCliente(cli);
	}
}
