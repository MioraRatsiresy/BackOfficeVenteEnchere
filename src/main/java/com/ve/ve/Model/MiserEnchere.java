package com.ve.ve.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MiserEnchere")
public class MiserEnchere {
      // Attributes
      @Id
      private int _id;
      private int idEnchere;
      public int getIdEnchere() {
          return idEnchere;
      }
      public void setIdEnchere(int idEnchere) {
          this.idEnchere = idEnchere;
      }
      private int idclient;
      public int getIdclient() {
        return idclient;
    }
    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }
    private double montant;
      public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    private String dateheure;
    public String getDateheure() {
        return dateheure;
    }
    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }

}
