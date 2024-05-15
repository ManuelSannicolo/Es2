public class Carrello {
    private Alimento [] carrello;
    private double costoMax = 100;
    //private int capacity = 35;
    private int numAlimenti = 0;
    private double costoCorrente=0;



    public Carrello (){
        this(35);
    }

    public Carrello (int max){
        if(max <=35 && max>0)
            carrello=new Alimento[max];
        else
            carrello=new Alimento[35];
    }


    public Carrello (Carrello c){
            this(c.carrello.length);
            for (int i = 0; i < c.numAlimenti; i++) {
                setAlimento(c.carrello[i]);
            }
            this.costoMax=c.costoMax;
    }




    public Alimento getProdotto (int pos){
        Alimento result=null;;
        if(pos>=0 && pos<carrello.length){
            if(carrello[pos]!=null)
                result = new Alimento (carrello[pos]);
        }

        return result;
    }



    public int setAlimento ( Alimento a){
        int result=-1;
        if( numAlimenti < carrello.length && a!=null && (costoCorrente+a.getCosto())<=costoMax){
            carrello[numAlimenti]= new Alimento(a);
            int i= numAlimenti;
            boolean verifica=false;
            while (i >= 1 && !verifica){
                if(carrello[i].getScadenzaIN() < carrello[i-1].getScadenzaIN()){
                    //scambio
                    Alimento temp = carrello[i];
                    carrello[i]=carrello[i-1];
                    carrello[i-1]=temp;
                    temp=null;
                }else
                    verifica=true;
                i--;
            }
            result=i;
            numAlimenti++;
            costoCorrente+=a.getCosto();
        }

        return result;
    }


    public double mediaCosto (){
        double media;
        double somma=0;
        for (int i = 0; i < this.numAlimenti; i++) {
            somma+=this.carrello[i].getCosto();
        }
        double nDouble = (double)numAlimenti;
        media=somma/nDouble;
        return media;
    }



    public int searchProduct(String name){
        int i =0;
        int result=-1;
        boolean trovato=false;
        while (!trovato && i < numAlimenti){
            if(this.carrello[i].getNome().equalsIgnoreCase(name)){
                trovato=true;
                result=i;
            }
            else
                i++;
        }

        return result;
    }



    public int removeProductByName(String name){
        int pos = searchProduct(name);
        if(pos!=-1){
            costoCorrente-=this.carrello[pos].getCosto();
            this.carrello[pos]=null;
            for (int i = pos; i < numAlimenti; i++) {
                this.carrello[i]=this.carrello[i+1];
            }
            this.carrello[--numAlimenti]=null;
        }

        return pos;
    }



    public int removeProductByNameEvoluted(String name){
        int pos = searchProduct(name);
        int cont=0;
        while(pos!=-1){
            costoCorrente-=this.carrello[pos].getCosto();
            this.carrello[pos]=null;
            for (int i = pos; i < numAlimenti; i++) {
                this.carrello[i]=this.carrello[i+1];
            }
            this.carrello[--numAlimenti]=null;
            cont++;
            pos= searchProduct(name);
        }

        return cont;
    }



    public int removeProductByPosition(int pos){
        if(pos>=0 && pos < carrello.length){
            if(this.carrello[pos]!=null){
                costoCorrente-=this.carrello[pos].getCosto();
                this.carrello[pos]=null;
                for (int i = pos; i < numAlimenti; i++) {
                    this.carrello[i]=this.carrello[i+1];
                }
                this.carrello[--numAlimenti]=null;
                
            } 
        }

        return pos;
    }


    public int removeUnderMean(){
        int cont=0;
        for (int i = 0; i < numAlimenti; i++) {
            if(this.carrello[i].getScadenzaIN()<=0){
                removeProductByPosition(i);
                cont++;
            }
        }

        return  cont;
    }



    public Carrello AlimentiContenentiSubString(String s){
        Carrello result = new Carrello (this.carrello.length);
        s=s.toLowerCase();
        for (int i = 0; i < numAlimenti; i++) {
            String corrente = this.carrello[i].getNome().toLowerCase();
            if(corrente.contains(s))
                result.setAlimento(this.carrello[i]);
        }

        return result;
    }


    public void svuotaCarrello(){
        for (int i = 0; i < numAlimenti; i++) {
            this.carrello[i]=null;
        }
        this.costoCorrente=0;
        this.numAlimenti=0;
    }


    public int controlla(Carrello c){
        int cont=0;
        for (int i = 0; i < numAlimenti; i++) {
            String corrente = this.carrello[i].getNome();
            if(c.searchProduct(corrente)!=-1)
                cont++;
            
        }

        return cont;

    }



    public boolean equals (Object obj){
        boolean verifica = false;
        if (this == obj)
            verifica=true;
        else{
            if(obj instanceof Carrello){
                Carrello c = (Carrello) obj;
                if(c.numAlimenti == this.numAlimenti){
                    int i=0;
                    boolean diversi = false;
                    while (!diversi && i< numAlimenti){
                        if(!(this.carrello[i].equals(c.carrello[i])))
                            diversi = true;
                        i++;
                    }

                    if(!diversi)
                        verifica=true;
                }
            }
        }


        return verifica;
    }



    public String toString(){
        String s="";
        for (int i = 0; i < numAlimenti; i++) {
            s+=String.format("Alimento n. %d: \n", (i+1));
            s+=this.carrello[i].toString();
            s+="\n";
        }

        s+=String.format("Costo totale della spesa: %.2f", this.costoCorrente);
        return s;
    }

}
