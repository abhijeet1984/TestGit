/**
 * 
 */
package com.foresters.oAuth;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author abhijeet.chaudhury
 *
 */
public class HibernateUtility {
	
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory()
			throws CustomOAuthException {

		try {
			if (sessionFactory == null) {
				Configuration cfg = new Configuration();
				sessionFactory = cfg.configure("com/resources/hibernate.cfg.xml").buildSessionFactory();
			}
		} catch (Exception e) {

			throw new CustomOAuthException(e.getMessage());
		}

		return sessionFactory;
	}


}
