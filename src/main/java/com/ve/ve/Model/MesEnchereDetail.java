/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.ve.Model;

/**
 *
 * @author Mbola
 */
public class MesEnchereDetail {
    private int idEnchere;
    private int idUtilisateur;
    private int idProduit;
    private String produit;
    private String categorie;
    private String description;
    private double prixMin;
    private String dateHeure;
    private String dateFin;
    private double duree;
    private int etat;
    private String nomGagnant;
    private double montant;
    private String statut;
    private int idGagnant;

    /**
     * @return the idUtilisateur
     */
    public int getIdEnchere() {
        return idEnchere;
    }

    /**
     * @param idEnchere the idUtilisateur to set
     */
    public void setEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    /**
     * @return the idUtilisateur
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * @param idUtilisateur the idUtilisateur to set
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * @return the idProduit
     */
    public int getIdProduit() {
        return idProduit;
    }

    /**
     * @param idProduit the idProduit to set
     */
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * @return the produit
     */
    public String getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(String produit) {
        this.produit = produit;
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the prixMin
     */
    public double getPrixMin() {
        return prixMin;
    }

    /**
     * @param prixMin the prixMin to set
     */
    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    /**
     * @return the dateHeure
     */
    public String getDateHeure() {
        return dateHeure;
    }

    /**
     * @param dateHeure the dateHeure to set
     */
    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }

    /**
     * @return the dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * @param dateFin the dateFin to set
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * @return the duree
     */
    public double getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(double duree) {
        this.duree = duree;
    }

    /**
     * @return the etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * @return the nomGagnant
     */
    public String getNomGagnant() {
        return nomGagnant;
    }

    /**
     * @param nomGagnant the nomGagnant to set
     */
    public void setNomGagnant(String nomGagnant) {
        this.nomGagnant = nomGagnant;
    }

    /**
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the idGagnant
     */
    public int getIdGagnant() {
        return idGagnant;
    }

    /**
     * @param idGagnant the idGagnant to set
     */
    public void setIdGagnant(int idGagnant) {
        this.idGagnant = idGagnant;
    }
}
