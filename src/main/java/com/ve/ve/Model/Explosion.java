package com.vehicule.vehicule.Model;

@SyncAnnotation(sync = true)
public class Explosion {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String idpoint;
    public String getIdpoint() {
        return idpoint;
    }
    public void setIdpoint(String idpoint) {
        this.idpoint = idpoint;
    }
    private String dateexplosion;
    public String getDateexplosion() {
        return dateexplosion;
    }
    public void setDateexplosion(String dateexplosion) {
        this.dateexplosion = dateexplosion;
    }
    private double quantiteVato;
    public double getQuantiteVato() {
        return quantiteVato;
    }
    public void setQuantiteVato(double quantiteVato) {
        this.quantiteVato = quantiteVato;
    }
    private int etat;
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    private int envoyeur1;
    public int getEnvoyeur1() {
        return envoyeur1;
    }
    public void setEnvoyeur1(int envoyeur1) {
        this.envoyeur1 = envoyeur1;
    }
    private int envoyeur2;
    public int getEnvoyeur2() {
        return envoyeur2;
    }
    public void setEnvoyeur2(int envoyeur2) {
        this.envoyeur2 = envoyeur2;
    }
}
