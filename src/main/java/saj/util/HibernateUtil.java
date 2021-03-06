package saj.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import saj.domain.Product;
import saj.domain.User;
  
public class HibernateUtil {
  
    private static  SessionFactory sessionFactory;
  
    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null) {
            
        	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        	configuration.addAnnotatedClass(Product.class);
        	configuration.addAnnotatedClass(User.class);
            ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
            registry.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
             
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        
        return sessionFactory;
    }
 
  
 
}