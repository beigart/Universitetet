package Labb2;

import java.util.*;

public class Hashtabell <E extends Comparable <E> > implements Iterable <E> {

    private LinkedList <E> [] hashtabell;
    private int storlek = 15;
    private int antalElement;

    public int getAntalElement (){  //Returnerar antal element
        return antalElement;
    }

    //Hashtabell skapas utefter den storlek som är satt
    public Hashtabell () {
        hashtabell = new LinkedList[storlek];
    }

    //Returnerar hashvärde på genrisk typ
    private int hash (E s){
        int h = s.hashCode();
        if (h < 0){
            h = -h;
        }
        return h % storlek;
    }

    //Skapa en ny hashtabell och öka storleken med två
    private void rehash() {
        LinkedList<E> gamlaVarden = new LinkedList<>(); //Listan som håller gamla värden
        for(int i = 0; i < storlek; i++){               //Går igenom hela listan
            if(hashtabell[i] != null) {                  //Om det finns en tabell i listan
                for(E s: hashtabell[i]){                 //Tar alla värden i tabellen
                    gamlaVarden.add(s);                 //Kopierar in dem i listan
                }
            }
        }
        storlek = storlek * 2;                          //Dubblar storleken på hashtabellen
        antalElement = 0;
        hashtabell = new LinkedList[storlek];            //Ny lista skapas

        for(E s: gamlaVarden) {
            this.add(s);                                //Lägger till alla gamla värden i den nya listan
        }
    }

    public boolean contains(E indata) {
        int pos = hash(indata);         //Beräknar vilken pos värdet borde finnas på

        if(hashtabell[pos] != null) {    //Går till positionen så länge den har ett värde
            return hashtabell[pos].contains(indata); //Hämtar det som finns på positionen
        }
        return false;                   //Annars finns inte värdet
    }

    public void add(E s) {
        int hink = hash(s); //Hämtar position från hashfunktion
        if(contains(s)){
            return;         //Om värdet redan finns, returneras att det är hittat
        }

        if((double)antalElement / (double)storlek > 0.75){  //Kolla om vi använt 75% eller mer av listan. rehahsa ananrs
            rehash();
        }
        if(hashtabell[hink] == null) {
            hashtabell[hink] = new LinkedList<>();   // Om hinken är tom, skapa en ny LinkedList
        }
        hashtabell[hink].add(s);                     //Lägger till på postion s i hashtabell
        antalElement++;
    }


    public boolean remove(E s) {
        if(!contains(s)) {
            return false;       //Finns inte elementet returneras false
        }
        int pos = hash(s);      //räkna ut värde för elementet vi skickar in för att ta reda på vart elementet borde finnas

        if(hashtabell[pos] != null) {    //Kolla att det finns något värde på pos

            boolean res = hashtabell[pos].remove(s); //Anropar remove på den pos som ska tas bort
            if(hashtabell[pos].isEmpty()) {
                hashtabell[pos] = null;  //Om pos är tom tilldelas pos null
            }
            antalElement--;             //Minska med 1
            return res;                 //Returnera
        }
        return false;
    }

    public Iterator<E> iterator() {
        return new HashIterator();
    }

    private class HashIterator implements Iterator<E> {
        private ArrayList<E> lista = new ArrayList<>();
        private int nuvarande = 0;                      //Börjar på 0

        public HashIterator() {
            for(int i = 0; i < storlek; i++) {          //Går igenom ArrayList
                if(hashtabell[i] != null){               //Kollar så den har värden inlagda
                    for(E s: hashtabell[i]){             //För alla värden i hashtabellen
                        lista.add(s);                   //Läggs dem in i ArrayListen
                    }
                }
            }
        }
        public boolean hasNext() {
            if(nuvarande < lista.size()){           //Kollar att nuvarande är mindre än storleken
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return lista.get(nuvarande++);      //Returnerar det värdet vi är på och ökar nuvarande med 1
        }
    }


    public E min(){
        E mindre =null;

        //Ta fram första värdet och lägger den i mindre
        for(int i =0; i<storlek;i++){  //letar upp ett startvärde
            mindre=hashtabell[i].getFirst();
            break;
        }

        //Gå igenom hela tabellen och jämför varje iteration med "mindre"
        for (int i=0;i<storlek;i++){
            if(hashtabell[i] !=null){
                for(E e:hashtabell[i]){
                    if(mindre.compareTo(e)>0){
                        mindre=e;
                    }
                }
            }
        }return mindre;
    }

    //Ta fram första värdet och lägg i mera
    public E max(){
        E mera =null;
        for(int i =0; i<storlek;i++){
            mera=hashtabell[i].getFirst();
            break;
        }

        //Gå igenom hela tabellen och jämför varje iteration med "max"
        for (int i=0;i<storlek;i++){
            if(hashtabell[i] !=null){
                for(E e:hashtabell[i]){
                    if(mera.compareTo(e)<0){
                        mera=e;
                    }
                }
            }
        }return mera;
    }

}
