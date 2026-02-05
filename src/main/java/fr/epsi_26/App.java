package fr.epsi_26;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        try(
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em  = emf.createEntityManager();
        ) {
            em.getTransaction().begin();
            Person person = new Person("L", "Renaud", 35);
            em.persist(person);
            em.getTransaction().commit();
        }
    }
}
