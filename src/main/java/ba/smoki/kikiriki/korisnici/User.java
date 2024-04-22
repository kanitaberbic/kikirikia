package ba.smoki.kikiriki.korisnici;

import java.io.Serializable;

public class User implements Serializable {

    private String korisnickoIme;
    private String lozinka;
    private String kreditnaKartica;

    public User() {

    }

    public User(String korisnickoIme, String lozinka, String kreditnaKartica) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.kreditnaKartica = kreditnaKartica;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getKreditnaKartica() {
        return kreditnaKartica;
    }

    public void setKreditnaKartica(String kreditnaKartica) {
        this.kreditnaKartica = kreditnaKartica;
    }
}
