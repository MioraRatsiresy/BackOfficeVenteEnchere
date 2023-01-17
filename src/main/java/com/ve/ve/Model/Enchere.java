package com.ve.ve.Model;

public class Enchere {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int produit;
    public int getProduit() {
        return produit;
    }
    public void setProduit(int produit) {
        this.produit = produit;
    }
    private String libelle;
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    private String dateHeure;
    public String getDateHeure() {
        return dateHeure;
    }
    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }
    private double prixMin;
    public double getPrixMin() {
        return prixMin;
    }
    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }
    private double duree; //en heure ou minute
    public double getDuree() {
        return duree;
    }
    public void setDuree(double duree) {
        this.duree = duree;
    }
    private String etat ;//0 en cours // 7 fini
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
}
