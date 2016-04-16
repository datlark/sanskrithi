package saj.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.mysql.jdbc.StringUtils;

import saj.domain.Product;
import saj.util.HibernateUtil;

public class ProductService {

	public List<Product> getAllProducts() throws Exception {

		List<Product> productList = new ArrayList<Product>();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			productList = session.createQuery("FROM Product ").list();
			for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				product.setFinalPrice(product.getPrice()
						* (100 - product.getDiscount()) / 100);

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productList;
	}

	public List<Product> getProducts(List<String> prmIDs) throws Exception {

		List<Product> productList = new ArrayList<Product>();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = (Query) session
					.createQuery("FROM Product where id in (:ids) ");

			System.out.println("ids:" + prmIDs);
			query.setParameterList("ids", prmIDs);
			System.out.println(query.toString());

			productList = query.list();

			for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				product.setFinalPrice(product.getPrice()
						* (100 - product.getDiscount()) / 100);

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productList;
	}

	
	public List<Product> getProducts(String productType, List<String> productSize,List<String> productPrice) throws Exception {

		List<Product> productList = new ArrayList<Product>();

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			String SQL = "FROM Product where ";
			String whereClause = "";
			
			boolean hasProductTypeFilter = !StringUtils.isNullOrEmpty(productType);
			boolean hasSizeFilter = productSize!=null && productSize.size() > 0;
			boolean hasPriceFilter = productPrice!=null && productPrice.size() > 0;
			
			if (hasProductTypeFilter){
				whereClause = "type = :type";
			}
			
			if (hasSizeFilter){
				
				if(whereClause != "") whereClause = whereClause + " and "; 
				whereClause = whereClause + "size in :sizes";
			}
			
			if (hasPriceFilter){
				
				 String priceCondition = "";
   				 int minPrice = 0;
				 int maxPrice = 10;
				 boolean discountFilter = false;

				
				for (Iterator iterator = productPrice.iterator(); iterator
						.hasNext();) {
					String price = (String) iterator.next();
				
					
					if(!StringUtils.isNullOrEmpty(price)){
						
						
						
						if(price.equals("0")){
							 
							discountFilter = true;
							
							
						}else if(price.equals("1")){
							
							minPrice = 0;
							
							if(maxPrice < 10){
								maxPrice = 10;
							}
							
							
							
						}else if(price.equals("2")){
							
							if(minPrice > 10){
								minPrice = 10;
							}
							
							if(maxPrice < 50){
								maxPrice = 50;
							}
							
							
							
						}else if(price.equals("3")){
							
							if(minPrice > 50){
								minPrice = 50;
							}
							
							
							maxPrice = 9999;
							
							
							
						}
					
						
						
						
					}
				
					
				}
				
				if (discountFilter){
					priceCondition = priceCondition + " discount > 0 and ";
				}
				
				priceCondition = priceCondition + "price > " + minPrice + " and price < " + maxPrice;
				
				
				if( priceCondition != null){
					
					
					
					
					//if Where Clause has something more append and condition
					if (whereClause != "") {
						whereClause = whereClause + " and " + priceCondition;
					}else{
						
						whereClause =  priceCondition;
					}
				}
				
				System.out.println("--------------------->PRice Condition:" + priceCondition);
			}
			
			
			SQL = SQL + whereClause;
			
			Query query = (Query) session.createQuery(SQL);

			if(hasProductTypeFilter){
				
				query.setParameter("type", productType);
				
			}
			
			
			if (hasSizeFilter){
				
				query.setParameterList("sizes", productSize);
				
			}

			
			
			productList = query.list();

			for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				product.setFinalPrice(product.getPrice()
						* (100 - product.getDiscount()) / 100);

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productList;
	}
	
	
	
	public Product getProduct(String productID) throws Exception {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		StatelessSession session = factory.openStatelessSession();
		Product product = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("FROM Product where id=:id");
			query.setString("id", productID);
			product = (Product) query.uniqueResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return product;
	}

	
}
