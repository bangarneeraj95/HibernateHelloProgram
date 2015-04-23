package com.javatpoint.mypackage;
import java.util.Iterator;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ast.tree.DisplayableNode;

import antlr.collections.List;

public class CritearaSqlQuery {
		private static void  retriveFunction(Criteria c) {
		  Iterator <Employee>itr = c.list().iterator();
		     while(itr.hasNext()){
	      Employee p =itr.next();
	      System.out.println("emp_Id : "+p.getId());
	      System.out.println("FirstName : "+p.getFirstName());
	      System.out.println("LastName : "+p.getLastName());
	      	System.out.println("---------------------------");
	}
				
			}
		private static void  retriveManagerData(Criteria c) {
			  Iterator <Manager>itr = c.list().iterator();
			     while(itr.hasNext()){
			    	 Manager p =itr.next();
		      System.out.println("emp_Id : "+p.getId());
		      System.out.println("FirstName : "+p.getSalary());
		      System.out.println("LastName : "+p.getLocation());
		      	System.out.println("---------------------------");
		}
					
				}
		public static void main(String[] args) {
		Session session=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();;
	Transaction t=session.beginTransaction();
	CritearaSqlQuery obj = new CritearaSqlQuery();
/*	//*******Display all record Employee class**********
	
	obj.display(session);
	
//******	Example of HCQL to get the 1th to 5th record
  obj.limitFecthRecord(session);
  
//****  Example of HCQL to get the records whose salary is greater than 10000
  obj.greaterThenSalary(session);
    */
  //Example of HCQL to get the records in ascending order on the basis of salary
   obj.fetchorderByRecord(session);
  
//  We can fetch data of a particular column by projection such as name etc. Let's see the simple example of projection that prints data of NAME column of the table only.
	 /*  Criteria c=session.createCriteria(Employee.class);  
  c.setProjection(Projections.property("firstName"));  
 Iterator <Employee>itr = c.list().iterator();
  while(itr.hasNext()){
Employee p =itr.next();
System.out.println("FirstName : "+p.getFirstName());
}*/



  










  }

private void fetchorderByRecord(Session session) {
	  Criteria c=session.createCriteria(Manager.class);  
	  c.addOrder(Order.asc("salary"));  
	 	  retriveFunction(c);
			
		}



////////********  greaterThenSalary*****////////////
	private void greaterThenSalary(Session session) {
		Criteria c=session.createCriteria(Manager.class);  
		  c.add(Restrictions.gt("salary",25000));//salary is the propertyname  
	      retriveFunction(c);
		}
//////// ******** limitFecthRecord*****////////////
	private void limitFecthRecord(Session session) {
		Criteria c=session.createCriteria(Employee.class);  
		c.setFirstResult(1);  
		c.setMaxResults(5);
		   retriveFunction(c);
		     
	}
////////******** display*****////////////
	public void display(Session session){
		Criteria c = session.createCriteria(Employee.class);
		   retriveFunction(c);
	    
	}
}