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
        
        Query query =em.createQuery("SELECT f.titre "
                + "FROM Film f JOIN "
                + "f.realisateurs r "
                + "JOIN f.genre g "
                + "JOIN f.pays p "
                + "WHERE r.nom = 'Polanski'"
                + "AND g.nom = 'Horreur'" 
                + "AND p.nom='UK'");
        String  f4= (String) query.getSingleResult();
        System.out.println(f4);
        Assert.assertEquals("Le bal des vampires", f4);
    
    
    }
    
    
     @Test
    public void req9(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r "
                + "WHERE r.nom = 'Coen'"
                +"AND r.prenom='Joel'");
        long  f5= (long) query.getSingleResult();
        System.out.println(f5);
       
      }
    
    @Test
    public void req10(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r "
                + " WHERE r.nom='Coen' "
                + " AND r.prenom='Ethan'"
                
                + "INTERSECT "
                
                +" SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r "
                + "WHERE r.nom = 'Coen' "
                 + "AND r.prenom='Joel'"
                );
        long  f6= (long) query.getResultList().size();
        System.out.println(f6+"s");
       
      }
    
    @Test
    public void req11(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r JOIN f.acteurs a "
                + " WHERE r.nom='Coen' "
                + " AND r.prenom='Ethan' "
                + " AND a.nom='Buscemi' "
                +"  AND a.prenom='Steve' "
                
                + "INTERSECT "
                
                +" SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r  JOIN f.acteurs a "
                + "WHERE r.nom = 'Coen' "
                 + "AND r.prenom='Joel' "
                + "AND a.nom='Buscemi' "
                + "AND a.prenom='Steve' "
                );
        long  f7= (long) query.getResultList().size();
        System.out.println(f7+"a");
       
      }
    @Test
    public void req12(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r JOIN f.acteurs a JOIN f.genre g "
                + " WHERE r.nom='Coen' "
                + " AND r.prenom='Ethan' "
                + "AND a.nom='Buscemi' "
                +" AND a.prenom='Steve' "
                + "AND g.nom='policier' "
                
                + "INTERSECT "
                
                +" SELECT  (f) "
                + "FROM Film f JOIN "
                + "f.realisateurs r  JOIN f.acteurs a JOIN f.genre g "
                + "WHERE r.nom = 'Coen' "
                 + "AND r.prenom='Joel' "
                + "AND a.nom='Buscemi' "
                + "AND a.prenom='Steve' "
                +" AND g.nom='policier' "
                );
        long  f8= (long) query.getResultList().size();
        System.out.println(f8+"req12");
       
      }
    @Test
    public void req13(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (s) "
                + "FROM Saison s JOIN "
                + " s.serie se "
                + " WHERE se.titre='Dexter'"
                );
        long  f9= (long) query.getSingleResult();
        System.out.println(f9);
       
      }
     @Test
    public void req14(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (e) "
                + "FROM Episode e JOIN "
                + " e.saison s JOIN s.serie se "
                + " WHERE s.numSaison='8' "
                +"AND se.titre='Dexter' "
                );
        long  f10= (long) query.getSingleResult();
        System.out.println(f10);
       
      }
    
      @Test
    public void req15(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (e) "
                + "FROM Episode e JOIN "
                + " e.saison s JOIN s.serie se "
                + " WHERE se.titre='Dexter' "
                );
        long  f11= (long) query.getSingleResult();
        System.out.println(f11);
       
      }
    @Test
    public void req16(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (l) "
                + "FROM Lien l "
                + "JOIN "
                + "l.film f "
                + "JOIN "
                + "f.genre g "
                + "JOIN "
                + "f.pays p "
                + "WHERE g.nom='Policier' "
                + "AND p.nom='USA' "
                );
        long  f12= (long) query.getSingleResult();
        System.out.println("****"+f12);
       
      }
     @Test
    public void req17(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT COUNT (l) "
                + "FROM Lien l "
                + "JOIN "
                + "l.film f "
                + "JOIN "
                + "f.genre g "
                + "JOIN "
                + "f.acteurs a "
                + "WHERE g.nom='Horreur' "
                + "AND a.nom='Polanski' "
                );
        long  f13= (long) query.getSingleResult();
        System.out.println("**"+f13);
       
      }
    
        @Test
    public void req18(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f "
                + "JOIN "
                + "f.genre g "
                + "JOIN "
                + "f.acteurs a "
                + "WHERE g.nom='Horreur' "
           
                +"EXCEPT "
                
                +"SELECT (f) "
                +"FROM Film f "
                +"JOIN "
                +"f.genre g "
                +"JOIN "
                +"f.acteurs a "
                +"WHERE g.nom='Horreur' "
                +" AND a.nom='Polanski' ");
        
        long  f14= (long) query.getResultList().size();
        System.out.println("*"+f14);
       
      }
    
       
        @Test
    public void req19(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f "
                + "JOIN "
                + "f.acteurs a "
                + "WHERE a.nom='Polanski' "
           
                +"INTERSECT "
                
                +"SELECT (f) "
                +"FROM Film f "
                +"JOIN "
                +"f.acteurs a "
                +"WHERE a.nom!='Polanski' ");
        
        long  f15= (long) query.getResultList().size();
        System.out.println("//"+f15);
       
      }
    
         @Test
    public void req20(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Query query =em.createQuery("SELECT  (f) "
                + "FROM Film f "
                + "JOIN "
                + "f.acteurs a "
                + "WHERE a.nom='Polanski' "
                
           
                +"UNION "
                
                +"SELECT (f) "
                +"FROM Film f "
                +"JOIN "
                +"f.genre g "
                +"WHERE g.nom='Horreur' ");
        
        long  f16= (long) query.getResultList().size();
        System.out.println("."+f16);
       
      }
    
         }