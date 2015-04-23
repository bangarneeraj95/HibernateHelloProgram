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

public class StoreData {
	public void display(Session session,Transaction t){
		Query qry = session.createQuery("from Employee");
	   // qry.setParameter(0,111);

		java.util.List l =qry.list();
		System.out.println("Total Number +9Of Records : "+l.size());
		Iterator it = l.iterator();

		while(it.hasNext())
		{
			Object o = (Object) it.next();
			Employee p = (Employee)o;
			System.out.println(" Name : "+p.getFirstName());
			System.out.println("lastname : "+p.getLastName());
			System.out.println("---------------------------");

		}		
	}
		/*int  i=112;
		Object o=session.load(Employee.class,new Integer( i));
		Employee s=(Employee)o;
		// For loading Transaction scope is not necessary...
		System.out.println("Loaded object emp name is___"+s.getFirstName()+" last name is"+s.getLastName());
	}*/
public static void main(String[] args) {
	try
	{
	//creating configuration object
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file
	
	//creating seession factory object
	SessionFactory factory=cfg.buildSessionFactory();
	
	//creating session object
	Session session=factory.openSession();
	
	//creating transaction object
	Transaction t=session.beginTransaction();
		Employee e2=new Employee();
	Manager e1=new Manager();
/*	e1.setId(121);
	e1.setLocation("indore");
	e1.setSalary(50000);*/

/*	StoreData  sd= new StoreData();
	sd.display(session, t);

/*	
	Query qry = session.createQuery("from Employee p where p.id=112 ");


	java.util.List l =qry.list();
	System.out.println("Total Number Of Records : "+l.size());
	Iterator it = l.iterator();

	while(it.hasNext())
	{
		Object o = (Object) it.next();
		Employee p = (Employee)o;
		System.out.println("Product Name : "+p.getFirstName());
		System.out.println("Product Price : "+p.getLastName());
		System.out.println("---------------------------");

	}		*/
/*	SQLQuery query = session.createSQLQuery("select  firstName, lastName  from emp1000  e, manager m where e.id=m.id ");
	query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	//System.out.println("Total Number Of Records : "+l1.size());
	 List data = query.list();

     for(Object object : data)
     {
        Map row = (Map)object;
        System.out.print("First Name: " + row.get("firstName")); 
        System.out.println(", Salary: " + row.get("lastName")); 
     } */   //persisting the object
     /*     e2.setId(113);
          e2.setFirstName("rr");
	session.delete(e2);*/
//transaction is commited
	 // session.persist(e1);
	Query query=session.createQuery("from Employee  where firstName like 'a%'order by firstName");  
	query.setFirstResult(1);  
	query.setMaxResults(4);  
	List l=query.list();
	Iterator it = l.iterator();

	while(it.hasNext())
	{
		Object o = (Object) it.next();
		Employee p = (Employee)o;
		System.out.println("Product Name : "+p.getFirstName());
		System.out.println("Product Price : "+p.getLastName());
	}
		t.commit();
	session.close();
	
	System.out.println("successfully saved");
	}
	catch(Exception e){System.out.println(e);}
}
}
