package saj.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
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
           List products = session.createQuery("FROM Product ").list(); 
           for (Iterator iterator = 
        		   products.iterator(); iterator.hasNext();){
              Product product = (Product) iterator.next();
              product.setFinalPrice(product.getPrice()*(100 - product.getDiscount())/100);
              productList.add(product);
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
}
