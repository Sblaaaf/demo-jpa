package fr.epsi_26;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
// permet de garder les objet en cache pour optimisation


@Entity
public class Person implements Serializable {
    @Id
    private int id;
    private String nom;
    private String prenom;
    private int age;

    public Person() {
    }

    public Person(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {

    }


}
