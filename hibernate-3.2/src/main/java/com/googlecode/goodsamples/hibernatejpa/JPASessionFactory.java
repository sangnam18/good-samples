package com.googlecode.goodsamples.hibernatejpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

class JPASessionFactory {
	private static Log log = LogFactory.getLog(JPASessionFactory.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	AnnotationConfiguration cfg = new AnnotationConfiguration();
        	cfg = (AnnotationConfiguration) cfg.configure();
        	cfg.addAnnotatedClass(MessageUsingJPA.class);
        	//cfg.addClass(JPAMessage.class);
        	//cfg.addPackage("com/googlecode/goodsamples/hibernatejpa/");
            return cfg.buildSessionFactory();
        }
        catch (Throwable ex) {
            log.error("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() {
    	return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	public static void shutdown() {
		sessionFactory.close();
	}
}
