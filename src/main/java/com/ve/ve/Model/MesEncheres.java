/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ve.ve.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ve.ve.Repository.PhotoEnchereRepository;

/**
 *
 * @author Mbola
 */
public class MesEncheres {
    private int idEnchere;
    private int produit;
    private String libelle;
    private String dateHeure;
    private double prixMin;
    private double duree;
    private String etat;
    private int idClient;
    private String produitEnchere;
    private String categorie;
    private String dateFin;
    private String statut;

    /**
     * @return the idEnchere
     */
    public int getIdEnchere() {
        return idEnchere;
    }

    /**
     * @param idEnchere the idEnchere to set
     */
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    /**
     * @return the produit
     */
    public int getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(int produit) {
        this.produit = produit;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
    public String getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * @return the idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the produitEnchere
     */
    public String getProduitEnchere() {
        return produitEnchere;
    }

    /**
     * @param produitEnchere the produitEnchere to set
     */
    public void setProduitEnchere(String produitEnchere) {
        this.produitEnchere = produitEnchere;
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
    private double montant;
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    private String nom;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    private String prenom;
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    private Object client;

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }
    private List<PhotoEnchere> photos;

    public List<PhotoEnchere> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoEnchere> photos) {
        this.photos = photos;
    }
    
}
