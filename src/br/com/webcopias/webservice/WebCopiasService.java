package br.com.webcopias.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public interface WebCopiasService {
	
	@WebMethod
	public String createNewUser(
			@WebParam(name="matricula") String registration,
			@WebParam(name="nome") String name,
			@WebParam(name="email") String email,
			@WebParam(name="senha") String password,
			@WebParam(name="limiteCopias") String copyLimit,
			@WebParam(name="papeis") String role);
}
