package saj.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import saj.domain.Dress;
import saj.util.HibernateUtil;



public class DressService {

    public List<Dress> getDresses() {
        List<Dress> dressList  = new ArrayList<Dress>();
    	
    	Dress dress = new Dress();
        dress.setName("White Silk Cotton Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(1);
        dressList.add(dress);
        
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        

        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        dress = new Dress();
        dress.setName("Orange Chudidhar");
        dress.setPrice(150.00);
        dress.setOrderID(2);
        dressList.add(dress);
        
        
        return dressList;
    }
    
    
    
    public List<Dress> getAllDresses() throws Exception {
    	
    	 List<Dress> dressList  = new ArrayList<Dress>();
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
        StatelessSession session = factory.openStatelessSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           List dresses = session.createQuery("FROM Dress D ORDER BY D.orderID ").list(); 
           for (Iterator iterator = 
        		   dresses.iterator(); iterator.hasNext();){
              Dress dress = (Dress) iterator.next(); 
              System.out.print("First Name: " + dress.getId()); 
              System.out.print("  Last Name: " + dress.getName()); 
              System.out.println("  Salary: " + dress.getPrice()); 
              
              dressList.add(dress);
           }
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        return dressList;
     }
}
