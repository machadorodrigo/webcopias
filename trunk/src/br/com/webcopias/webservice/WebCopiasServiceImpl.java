package br.com.webcopias.webservice;

import javax.jws.WebService;

import br.com.webcopias.service.CreateUser;

@WebService(serviceName="WebcopiasService",endpointInterface = "br.com.webcopias.webservice.WebCopiasService")
public class WebCopiasServiceImpl implements WebCopiasService {

	@Override
	public String createNewUser(
			String registration,
			String name,
			String email,
			String password,
			String copyLimit,
			String role){
		
		CreateUser createUser = new CreateUser(registration, name, email, password, copyLimit, role);
		
		return createUser.createNewUser();
	}

}
