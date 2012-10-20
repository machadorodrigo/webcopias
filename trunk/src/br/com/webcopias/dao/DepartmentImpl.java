package br.com.webcopias.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.webcopias.model.Department;
import br.com.webcopias.utils.HibernateUtil;

public class DepartmentImpl extends GenericHibernate<Department> implements DepartmentDao{
	
	public Department getDepartment(String code) {
		Department department = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			department = (Department) session.get(Department.class, code);
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return department;
    }

	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentsList() {
		List<Department> department = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			department = session.createQuery("from Department").list();
			session.flush();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
        return department;
    }

}
