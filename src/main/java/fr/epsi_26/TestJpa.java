package fr.epsi_26;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJpa {

    public static void main(String[] args) {
        // Création EntityManagerFactory (point d'entrée)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        // Création EntityManager (connexion/contexte)
        EntityManager em = emf.createEntityManager();

        // Vérification
        System.out.println("Connexion à la base de données réussie via Docker !");

        // Fermeture des ressources
        em.close();
        emf.close();
    }
}