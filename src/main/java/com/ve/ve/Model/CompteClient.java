package com.ve.ve.Model;

public class CompteClient {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private double montant;
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    private int etat;
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    private int Clientid; 
    public int getClientid() {
        return Clientid;
    }
    public void setClientid(int clientid) {
        Clientid = clientid;
    }
    private int actionTransaction;
    public int getActionTransaction() {
        return actionTransaction;
    }
    public void setActionTransaction(int actionTransaction) {
        this.actionTransaction = actionTransaction;
    }
}
