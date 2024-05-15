public class Alimento {
    private String nome = "undefined";
    private double costo = 0.0;
    private int scadenzaIN = 10;

    public Alimento () {};
    public Alimento (String nome, double costo, int scadenzaIN){
        setCosto(costo);
        setNome(nome);
        setScadenzaIN(scadenzaIN);
    }
    public Alimento (Alimento a){
        this(a.nome, a.costo, a.scadenzaIN);
    }


    public String getNome() {
        return nome;
    }

    public double getCosto() {
        return costo;
    }

    public int getScadenzaIN() {
        return scadenzaIN;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setCosto(double costo) {
        if(costo >=0)
            this.costo = costo;
    }

    public void setScadenzaIN(int scadenzaIN) {
        this.scadenzaIN = scadenzaIN;
    }


    public boolean equals (Object obj){
        boolean verifica=false;
        if (this == obj){
            verifica=true;
        }else{
            if(obj instanceof Alimento){
                Alimento a = (Alimento)obj;
                if(a.nome.equalsIgnoreCase(this.nome)){
                    if(a.costo == this.costo && a.scadenzaIN == this.scadenzaIN)
                        verifica=true;
                }
            }
        }

        return verifica;
    }



    public String toString (){
        String s="";
        s+=String.format("nome alimento: %s; \n", this.nome);
        s+=String.format("costo prodotto: %.2f \n", this.costo);
        s+=String.format("il prodotto scade tra: %d gg \n", this.scadenzaIN);
        return s;
    }
}

