package hu.nl.hibernate;

import java.util.List;


import hu.nl.hibernate.domain.Gebruiker;
import hu.nl.hibernate.domain.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main {
  private static SessionFactory factory;
  public static void main(String[] args) {
      try {
        factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
        System.err.println("Failed to create sessionFactory object." + ex);
        throw new ExceptionInInitializerError(ex);
      }
      Session session = factory.openSession();
      Transaction t = session.beginTransaction();

      //Aanmaken Objecten voor persistentie
      Log log = new Log(3,"Hallo Lois");
      Gebruiker gebruiker = new Gebruiker(1, "Lois Brandsema");

      //CRUD van Log

      session.save(log);

      Query query = session.createQuery("from LOG where id = :id");
      query.setParameter("id", log.getId());
      List<?> list = query.list();
      Log log1 = (Log)list.get(0);
      System.out.println("Select statement: " + log1.getMessage());

      log.setMessage("Ik doe een update");
      session.update(log);

      session.delete(log);


      //CRUD van Gebruiker

      session.save(gebruiker);

      Query query1 = session.createQuery("from Gebruiker where id = :id");
      query1.setParameter("id", gebruiker.getId());
      List<?> list1 = query.list();
      Gebruiker gebruiker1 = (Gebruiker)list1.get(0);
      System.out.println("Select statement: " + gebruiker1.getNaam());

      gebruiker.setNaam("Tim van der Meijs");
      session.update(gebruiker);

      session.delete(gebruiker);

      t.commit();
      factory.close();  
      session.close();   
  }
}
