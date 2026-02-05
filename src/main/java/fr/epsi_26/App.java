package fr.epsi_26;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

    public class App {
        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();

            try {
                em.getTransaction().begin();

                // Find simple (livre1)
                Livre livre1 = em.find(Livre.class, 1);
                if (livre1 != null) {
                    System.out.println("Livre 1 trouvé : " + livre1.getTitre() + " de " + livre1.getAuteur());
                }

                // Nouveau Livre
                Livre nouveauLivre = new Livre("Harry Potter", "J.K. Rowling");
                em.persist(nouveauLivre);
                System.out.println("Nouveau livre inséré avec l'ID : " + nouveauLivre.getId());

                // Modifier livre5
                Livre livre5 = em.find(Livre.class, 5);
                if (livre5 != null) {
                    livre5.setTitre("Du plaisir dans la cuisine");
                    System.out.println("Livre 5 modifié.");
                }

                // Requête JPQL par titre
                String titreRecherche = "Germinal";
                TypedQuery<Livre> queryTitre = em.createQuery("SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class);
                queryTitre.setParameter("titre", titreRecherche);
                Livre livreParTitre = queryTitre.getSingleResult(); // Attention, lance une exception si pas trouvé
                System.out.println("Recherche par titre (" + titreRecherche + ") : " + livreParTitre);

                // Requête JPQL par auteur
                String auteurRecherche = "Gaston Pouet";
                TypedQuery<Livre> queryAuteur = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class);
                queryAuteur.setParameter("auteur", auteurRecherche);
                List<Livre> livresParAuteur = queryAuteur.getResultList();
                System.out.println("Livres de " + auteurRecherche + " :");
                for (Livre l : livresParAuteur) {
                    System.out.println("- " + l.getTitre());
                }

                // Supprimer un livre (livre3)
                Livre livreASupprimer = em.find(Livre.class, 3);
                if (livreASupprimer != null) {
                    em.remove(livreASupprimer);
                    System.out.println("Livre 3 supprimé.");
                }

                // Validation des modifications en base
                em.getTransaction().commit();

                // Afficher tous les livres
                TypedQuery<Livre> queryAll = em.createQuery("SELECT l FROM Livre l", Livre.class);
                List<Livre> tousLesLivres = queryAll.getResultList();

                System.out.println("--- LISTE DES LIVRES ---");
                for (Livre l : tousLesLivres) {
                    System.out.println(l.getId() + " : " + l.getTitre() + " - " + l.getAuteur());
                }

            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                em.close();
                emf.close();
            }
        }
    }