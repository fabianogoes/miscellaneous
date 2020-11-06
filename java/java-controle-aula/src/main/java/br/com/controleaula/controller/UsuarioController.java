package br.com.controleaula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controleaula.framework.BaseController;
import br.com.controleaula.framework.BaseService;
import br.com.controleaula.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends BaseController<Usuario> {

	public UsuarioController(){
		super.setClazz(Usuario.class);
	}
	
	@Qualifier("usuarioService")
	@Autowired
	public void setService(BaseService<Usuario> service){
		super.service = service;
	}
	
}
