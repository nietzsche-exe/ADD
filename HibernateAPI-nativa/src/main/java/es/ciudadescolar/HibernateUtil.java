package es.ciudadescolar;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil 
{
	
	private static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";

	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() 
	{
		if ( factory == null)
		{
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
			
			standardServiceRegistryBuilder.configure(HIBERNATE_CONFIG_FILE);
			
			StandardServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();
			
			MetadataBuilder metaDataBuilder = new MetadataSources(standardRegistry).getMetadataBuilder();
			
			Metadata metaData = metaDataBuilder.build();
			
			SessionFactoryBuilder sfb = metaData.getSessionFactoryBuilder();
			
			factory = sfb.build();  	//una por cada BD	
		}
		return factory;

	}
	
	public static void closeFactory() 
	{
		if (factory!=null) 
		{
			factory.close();
		}		
	}

}
