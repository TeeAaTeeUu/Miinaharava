package tatu.miinaharava;

public class Miinaharava {
    


    public static void main(String[] args) {
        Pelilauta pelilauta = new Pelilauta(15, 14, 45);
        pelilauta.tulostaRuudukko();
        System.out.println("väli");
        pelilauta.tulostaAvatutRuudut();
        System.out.println("testi");
        pelilauta.tulostaLasketutRuudut();
    }
}
