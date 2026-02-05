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

            // CREATION
            Person person = new Person("L", "Renaud", 35);
            em.persist(person);

            // READ
            Person person2 = em.find(Person.class, 2);
            if (null != person2) {
                System.out.println(person2);
            }

            // UPDATE
            Person personUPDATE = em.find(Person.class, 1);
            if (null != personUPDATE) {
                personUPDATE.setNom("LOURGOUILLOUX");
                personUPDATE.setPrenom("Bob");
                personUPDATE.setAge(28);
            }

            // DELETE
            Person person3 = em.find(Person.class, 3);
            if (null != person3) {
                em.remove(person3);
            }

            System.out.println(person.getId());
            em.getTransaction().commit();
        }
    }
}
