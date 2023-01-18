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
public class StatistiqueChiffreAffaire {
    private double montant;
    private int mois;
    private String nomMois;

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
     * @return the mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @param mois the mois to set
     */
    public void setMois(int mois) {
        this.mois = mois;
    }

    /**
     * @return the nomMois
     */
    public String getNomMois() {
        return nomMois;
    }

    /**
     * @param nomMois the nomMois to set
     */
    public void setNomMois(String nomMois) {
        this.nomMois = nomMois;
    }
}
