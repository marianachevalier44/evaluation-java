package com.freestack.evaluation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class UberApi {

    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myPostGreSqlEntityManager");
//on appelle persistence xml
    public static void enrollUser(UberUser uberUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(uberUser);
        em.getTransaction().commit();
        em.close();
    }

    public static void enrollDriver(UberDriver uberDriver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(uberDriver);
        em.getTransaction().commit();
        em.close();
    }
//
    public static Booking bookOneDriver(UberUser uberUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//je besoin de savoir si il y a un chauffeur disponible, je regarde dans une liste les chauffeurs disponible avec un query
        List<UberDriver> listofAvailbleDrivers = (List<UberDriver>) em.createQuery("SELECT d FROM UberDriver d WHERE d.available = true").setMaxResults(1).getResultList();
        //si ma liste contiens +0
        if (listofAvailbleDrivers.size() > 0){
            //je vais creer un reservation
            Booking booking = new Booking();
            //je vais prendre le premier chauffeur disponible de la liste
            UberDriver uberDriver = listofAvailbleDrivers.get(0);
            //la reservation prend: un objet user, un chauffeur, una date de reservation(maintenant)[new Date], la fin de la reservation: je la connais pas encore=null et maintenant le chauffeur il est indisponible
            booking.setUberUser(uberUser);
            booking.setUberDriver(uberDriver);
            booking.setStart_of_the_booking(new Date());
            booking.setEnd_of_the_booking(null);
            uberDriver.setAvailable(false);
            //je comunique avec la bdd pour lui donner tous ses infos
            em.persist(booking);
            em.getTransaction().commit();
            em.close();
            //je retourne la reservation et sinon, c'est null
            return booking;
        }
        return null;
    }

    public static void finishBooking(Booking booking1) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //dans la reservation je il aura une date de fin de reservation qui fait que la chaffeur maintenant il est disponible a nouveau
        booking1.setEnd_of_the_booking(new Date());
        booking1.getUberDriver().setAvailable(true);
        em.persist(booking1);
        em.getTransaction().commit();
        em.close();
    }


    public static void evaluateDriver(Booking booking1, int evaluationOfTheUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        booking1.setEvaluation(evaluationOfTheUser);
        em.persist(evaluationOfTheUser);
        em.getTransaction().commit();
        em.close();
    }
//c'est juste un requete pour avoir la liste des courses/reservations faites
    public static List<Booking> listDriverBookings(UberDriver uberDriver) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Booking b where b.uberDriver = :uberDriver").setParameter("uberDriver", uberDriver)
            .getResultList();
        List<Booking> result = (List<Booking>) query.getResultList();
        em.close();
        return result;

    }
    //pour lister
    public static List<Booking> listUnfinishedBookings() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Booking b WHERE b.finishBooking IS NULL");
        List<Booking> result1 = (List<Booking>)query.getResultList();
        return result1;
    }

//je vais prendre la moyenne avec la requete
    public static float meanScore(UberDriver uberDriver) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT AVG(b.evaluation) FROM Booking b WHERE b.uberDriver = :uberDriver");
        //parameter query pour un seul element
        query.setParameter("uberDriver", uberDriver);
        Double result = (Double) query.getSingleResult();
        //il faut caster Double car la moyenne d'un integer c'est un float

        return result.floatValue();
    }
}
