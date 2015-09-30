package saj.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import saj.domain.Product;
import saj.util.HibernateUtil;



public class ProductService {

        
    
    
    public List<Product> getAllProducts() throws Exception {
    	
    	 List<Product> productList  = new ArrayList<Product>();
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
        StatelessSession session = factory.openStatelessSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           productList = session.createQuery("FROM Product ").list(); 
           for (Iterator iterator = 
        		   productList.iterator(); iterator.hasNext();){
              Product product = (Product) iterator.next();
              product.setFinalPrice(product.getPrice()*(100 - product.getDiscount())/100);
              
           }
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        return productList;
     }
    
    
    public List<Product> getProducts(List<String> prmIDs) throws Exception {
    	
   	 List<Product> productList  = new ArrayList<Product>();
   	
   	SessionFactory factory = HibernateUtil.getSessionFactory();
       StatelessSession session = factory.openStatelessSession();
       Transaction tx = null;
       try{
          tx = session.beginTransaction();
          Query query = (Query) session.createQuery("FROM Product where id in (:ids) ");
          
          System.out.println("ids:" + prmIDs);
          query.setParameterList("ids", prmIDs);
          System.out.println(query.toString());
          
          productList = query.list();
          
          for (Iterator iterator = 
       		 productList.iterator(); iterator.hasNext();){
             Product product = (Product) iterator.next();
             product.setFinalPrice(product.getPrice()*(100 - product.getDiscount())/100);
             
          }
          tx.commit();
       }catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       }finally {
          session.close(); 
       }
       
       return productList;
    }
    
    
    
    public Product getProduct(String productID) throws Exception {
    	
   	 SessionFactory factory = HibernateUtil.getSessionFactory();
      StatelessSession session = factory.openStatelessSession();
      Product product = null;
       Transaction tx = null;
       try{
          tx = session.beginTransaction();
          
          Query query =  session.createQuery("FROM Product where id=:id"); 
          query.setString("id", productID);
          product = (Product) query.uniqueResult();
          
          tx.commit();
       }catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       }finally {
          session.close(); 
       }
       
       return product;
    }
    
    
}
