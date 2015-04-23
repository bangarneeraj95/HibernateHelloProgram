package com.javatpoint.mypackage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.ast.tree.DisplayableNode;

public class SQLQueryDisplayWay {
//	  Method to  READ all the employees using Scalar Query 
	public void display(Session session){
		SQLQuery query = session.createSQLQuery("select * from emp1000 where firstName='neeraj' order by lastName");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		//System.out.println("Total Number Of Records : "+l1.size());
		 List data = query.list();

	     for(Object object : data)
	     {
	        Map row = (Map)object;
	        System.out.print("First Name: " + row.get("firstName")); 
	        System.out.println(", Salary: " + row.get("lastName")); 
	     }    //persisting th
	}
	//   Method to  READ all the employees using Entity Query 
	public void display1(Session session){
		SQLQuery query = session.createSQLQuery("select * from emp1000   ");
	 query.addEntity(Employee.class);
		List   l =query.list();
			System.out.println("Total Number Of Records : "+l.size());
			Iterator it = l.iterator();
		
			while(it.hasNext())
			{
		Object o= (Object) it.next();
			Employee p = (Employee)o;
			System.out.println("FirstName : "+p.getFirstName());
			System.out.println("LastName : "+p.getLastName());
				System.out.println("---------------------------");

		 }
	}
public static void main(String[] args) {
	try
	{
			SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
 	Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		SQLQueryDisplayWay e=new SQLQueryDisplayWay();
  // e.display(session); // e.display1(session);
		Query q=session.createQuery("update Employee set firstName=:n where id=:i");  
		q.setParameter("n","neeraj bangra");  
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
	t.commit();
	   System.err.println("Failed to create sessionFactory object." );

	session.close();
	
	System.out.println("successfully saved");
	}
	catch(Exception e){System.out.println(e);}
}
}
