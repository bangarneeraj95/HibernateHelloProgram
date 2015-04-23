package com.javatpoint.mypackage;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class simplesqlquery {
	public static void main(String[] args) {
		try
		{
				SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	 	Session session=factory.openSession();
			Transaction t=session.beginTransaction();
			SQLQueryDisplayWay e=new SQLQueryDisplayWay();
	  // e.display(session); // e.display1(session);
			Query q=session.createQuery("update Employee set firstName=:n where id=:i");  
			q.setParameter("n","neeraj ");  
			q.setParameter("i",115);  
			  
	int status=q.executeUpdate();  
	System.out.println(status);  
			
		//	Example to get total salary of all the employees

		    Query q1=session.createQuery("select sum(salary) from Manager");  
		    List<Manager> list=q1.list();  
		        Iterator<Manager> itr=list.iterator();  
		        while(itr.hasNext()){  
		            System.out.println(itr.next());  
		    }  
		        
				//	Example to get between limit , record of all the employees
		        org.hibernate.Query query= session.createQuery("from Employee");  
	        query.setFirstResult(1);  
		        query.setMaxResults(4);
		        java.util.List l=query.list();
		        Iterator it = l.iterator();

		        while(it.hasNext())
		        {
		        Object o= (Object) it.next();
		        Employee p = (Employee)o;
		        System.out.println("FirstName : "+p.getFirstName());
		        System.out.println("LastName : "+p.getLastName());
		        	System.out.println("---------------------------");

		        }
		t.commit();
		   System.err.println("Failed to create sessionFactory object." );

		session.close();
		
		System.out.println("successfully saved");
		}
		catch(Exception e){System.out.println(e);}
	}
}
