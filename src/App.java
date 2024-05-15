public class App {
    public static void main(String[] args) throws Exception {
        
        Alimento a1 = new Alimento("fragole", 3.5, 4);
        Alimento a2 = new Alimento("banane", 4, -1);
        Alimento a3 = new Alimento("pasta", 0.99, 65);
        Alimento a4 = new Alimento("farina", 2.99, 178);
        Alimento a5 = new Alimento (a1);
        Alimento a6 = new Alimento (a2);
        Alimento a7 = new Alimento (a3);
        Alimento a8 = new Alimento (a4);

        System.out.println(a1==a5);
        System.out.println(a1.equals(a5));


        Carrello c1= new Carrello(15);
        c1.setAlimento(a1);
        c1.setAlimento(a2);
        c1.setAlimento(a3);
        c1.setAlimento(a4);
        c1.setAlimento(a5);
        c1.setAlimento(a6);
        c1.setAlimento(a7);
        c1.setAlimento(a8);

        System.out.println(c1);


        Alimento costoElevato = new Alimento("mela d'oro", 150.0, 500);
        c1.setAlimento(costoElevato); // non lo inserisce poiché il prezzo è troppo elevato
        System.out.println(c1);


        c1.removeProductByNameEvoluted("banane");
        System.out.println(c1);


        Carrello c2 = new Carrello(c1);

        System.out.println(c1==c2);
        System.out.println(c1.equals(c2));

        c2.removeProductByNameEvoluted("fragole");

        System.out.println(c1.controlla(c2));

        System.out.println(a1.compareCosto(a5));


    }
}
