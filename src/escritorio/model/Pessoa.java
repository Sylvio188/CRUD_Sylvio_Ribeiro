
package escritorio.model;

public class Pessoa {
    int id;
    String nom;
    String email;
    String tel;

    public Pessoa() {
    }

    public Pessoa(int id, String nom, String email, String tel) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.tel = tel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
}
