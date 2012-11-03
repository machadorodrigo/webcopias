package br.com.webcopias.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.webcopias.dao.RoleImpl;
import br.com.webcopias.dao.UserImpl;
import br.com.webcopias.model.CentralCopy;
import br.com.webcopias.model.CentralHistory;
import br.com.webcopias.model.Department;
import br.com.webcopias.model.Discipline;
import br.com.webcopias.model.Document;
import br.com.webcopias.model.Parameter;
import br.com.webcopias.model.Role;
import br.com.webcopias.model.Service;
import br.com.webcopias.model.User;
import br.com.webcopias.model.UserRequest;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	private HibernateUtil() {

	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.configure("/hibernate.cfg.xml");

				serviceRegistry = new ServiceRegistryBuilder().applySettings(
						configuration.getProperties()).buildServiceRegistry();

				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Department.class);
				configuration.addAnnotatedClass(Discipline.class);
				configuration.addAnnotatedClass(CentralCopy.class);
				configuration.addAnnotatedClass(CentralHistory.class);
				configuration.addAnnotatedClass(Document.class);
				configuration.addAnnotatedClass(Parameter.class);
				configuration.addAnnotatedClass(Service.class);
				configuration.addAnnotatedClass(UserRequest.class);

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//				SchemaExport se = new SchemaExport(configuration);
//				se.create(true, true);

			} catch (Throwable ex) {
				throw new ExceptionInInitializerError(ex);
			}

			return sessionFactory;

		} else {
			return sessionFactory;
		}
	}

//	public static void main(String[] args) {
//		HibernateUtil.getSessionFactory();
//		
//		User user = new User();
//		UserImpl userImpl = new UserImpl();
//		Set<Role> roleset = new HashSet<Role>();
//		
//		Role role = new Role();
//		RoleImpl roleImpl = new RoleImpl();
//		
//		role.setDescription("Administrador");
//		role.setName("ROLE_ADM");
//		roleset.add(role);
//		roleImpl.save(role);
//		
//		role.setDescription("Operador");
//		role.setName("ROLE_OPERATOR");
//		roleset.add(role);
//		roleImpl.save(role);
//		
//		role.setDescription("Professor");
//		role.setName("ROLE_TEACHER");
//		roleset.add(role);
//		roleImpl.save(role);
//		
//		role.setDescription("Chefe de Departamento");
//		role.setName("ROLE_BOSS");
//		roleset.add(role);
//		roleImpl.save(role);
//		
//		role.setDescription("Administrador");
//		role.setName("ROLE_ADM");
//		roleset.add(role);
//		
//		user.setActive(true);
//		user.setCopyLimit(20);
//		user.setEmail("adm@webcopias.com");
//		user.setName("Administrador");
//		user.setPassword("adm");
//		user.setRegistration("adm");
//		user.setRegistrationDate(new Date());
//		user.setRole(roleset);
//		
//		userImpl.save(user);
//	}
}
