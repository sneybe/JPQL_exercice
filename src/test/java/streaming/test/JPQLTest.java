/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Test;
import streaming.entity.Film;

/**
 *
 * @author Administrateur
 */
public class JPQLTest {
    
    @Test
    public void req1(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
     Query query = em.createQuery("SELECT f FROM Film f WHERE f.id=4");
     
     Film film =(Film) query.getSingleResult();
     Assert.assertEquals("Fargo", film.getTitre());
    
}
    
    @Test
    public void req2(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (f) FROM Film f");
        long nbFilms= (long) query.getSingleResult();
        Assert.assertEquals(4L, nbFilms);
    }
     @Test
    public void req3(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT min(f.annee)FROM Film f ");
        int  anneemin= (int) query.getSingleResult();
        Assert.assertEquals( 1968, anneemin);
    }
     @Test
    public void req4(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery(""
                + "SELECT COUNT (l) "
                + "FROM Lien l JOIN l.film f"
                + " WHERE f.titre='Big Lebowski' ");
        long  lien= (long) query.getSingleResult();
        Assert.assertEquals( 0, lien);
}
    
     @Test
    public void req5(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT ( f ) "
                + "FROM  Film f JOIN f.realisateurs r "
                + "WHERE r.nom = 'Polanski'" );
        long  f1= (long) query.getSingleResult();
        System.out.println(f1);
        Assert.assertEquals(2, f1);

    }
    
     @Test
    public void req6(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT ( f ) "
                + "FROM  Film f JOIN f.acteurs a "
                + "WHERE a.nom = 'Polanski'" );
        long  f2= (long) query.getSingleResult();
        System.out.println(f2);
        

    }
    
    @Test
    public void req7(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT ( f ) "
                + "FROM  Film f JOIN f.acteurs a JOIN f.realisateurs r  "
                + "WHERE a.nom = 'Polanski'"
                + "AND r.nom = 'Polanski'" );
        long  f3= (long) query.getSingleResult();
        System.out.println(f3);
        
        
      }
    
    @Test
    public void req8(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT titre(f. "
                + "FROM  Film f JOIN f.acteurs a JOIN f.realisateurs r  "
                + "WHERE a.nom = 'Polanski'"
                + "AND r.nom = 'Polanski'" );
        long  f3= (long) query.getSingleResult();
        System.out.println(f3);
    
    
    }
      }