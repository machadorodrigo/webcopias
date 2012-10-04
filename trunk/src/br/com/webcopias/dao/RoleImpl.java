package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Role;
import br.com.webcopias.utils.HibernateUtil;


public class RoleImpl extends GenericHibernate<Role> implements RoleDao{
	
	public Role getRole(String registration) {
		Role role = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			role = (Role) session.get(Role.class, registration);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return role;
    }
	
	@SuppressWarnings("unchecked")
	public List<Role> getRolesByList(String name) {
        Session session = null;
        List<Role> roles = null;
        
        try{
        	session = HibernateUtil.getSessionFactory().openSession();
        	org.hibernate.Query query = session.createQuery("from Role where name in ("+name+")");
        	roles = query.list();
        }catch(HibernateException e){
        	e.printStackTrace();
        }finally{
        	session.close();
        }
        
        return  roles;
    }
	
}
