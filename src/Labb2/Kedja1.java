package Labb2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Kedja1 <E extends Comparable> implements Iterable <E>  {

    private Node <E> huvud; //Första elementet i listan
    private Node <E> svans; //Sista elementet i lsitan
    private int size = 0;   //Storlek på listan

    //Kostruktor
    public Kedja1 (){
        huvud = null;
    }


    public E getFirst (){
        if (huvud == null){
            throw new NoSuchElementException(); //Om första värdet är null = felmeddelande listan tom
                                                //annars returnera första elementets data
        }
        return huvud.data;
    }

    //Lägger till element först i listan
    public void addFirst (E data){
        Node <E> nyNod = new Node<>();     //Nytt element skapas
        nyNod.data = data;                 //Datan som skickas till metoden tilldelas data för noden
        nyNod.next = huvud;                //Den nya noden ska pekas om till huvdet på listan
        huvud = nyNod;                     //Huvudet pekar på den nya noden
        size++;                            //Listans storlek ökar med 1
    }

    public void removeFirst (){
        if (huvud == null){                 //Om huvud ej har värde = felmeddelande. listan tom
            throw new NoSuchElementException();
        }
        huvud = huvud.next;                 //Det som huvudet pekar på tilldelas första platsen i listan
        size--;                             //Listan minskas med 1
    }

    //Iterator
    public Iterator <E> iterator (){
        return new LinkedListIterator ();
    }

    private class LinkedListIterator implements Iterator <E> {
        private Node <E> pekare = huvud;    //Kopia av första elementet

        @Override
        public boolean hasNext() {          //Kollar om pekaren har ett nästa element
            return (pekare != null);
        }

        @Override
        public E next() {
            E data = pekare.data;           //Hämtar data
            pekare = pekare.next;           //Pekar på nästa elementet i listan

            return data;                    //Returnerar data
        }
    }

    //Tar bort element på specifik position.
    public void removeAt (int pos){
        if (pos < 0 || pos > size) {    //Om position är mindre än noll eler större än listan kastas felmeddelande
            throw new IndexOutOfBoundsException();
        }
        else {
            if (pos == 0){              //Om positionen är noll anropas metoden removeFirst
                removeFirst();
            }
            else {
                Node <E> tidigare = huvud;//Tar kopia av förstapekaren

                for (int i = 1; i < pos; i++){  //For-loop som går upp till postionen som ska tas bort
                    tidigare = tidigare.next;
                }
                Node <E> nuvarande = tidigare.next; //Tar nuvarande position och går förbi
                tidigare.next = nuvarande.next;     //det element som ska plcokas bort????
                size--;                             //Listan minskas med 1
            }
        }
    }

    public void addAt (int pos, E data){
        if (pos < 0 || pos > size) {        //Om position är mindre än noll eler större än listan kastas felmeddelande
            throw new IndexOutOfBoundsException();
        }

        if (pos == 0) {
            addFirst(data);     //Om pos ska läggas på första platsen anropas addFirst.
        }
        else {
            Node <E> nuvarande = huvud; //Tar kopia av förstapekaren

            for (int i = 1; i < pos; i++){
                nuvarande = nuvarande.next; //For-loop som går upp till postionen där element ska läggas till
            }

            Node <E> temp = nuvarande.next; //Temporär pekare
            Node <E> nyNod = new Node<>();  //Nytt element skapas
            nyNod.data = data;
            nuvarande.next = nyNod;         //Efter det nuvarande elementet ska det nya elementet sättas in
            nyNod.next = temp;              //Nya noden ska peka på det gamla värdet som är i temp
            size++;                         //Storleken ökas med 1
        }

    }

    public void addLast (E data){

        if (huvud == null){
            addFirst(data); //om huvudet är null, anropa addfirst för att lägga till element
        }
        else {
            Node <E> tmp = huvud;   //Kopia av första elementet
            while (tmp.next != null){   //Så länge nästa element inte är null, körs loppen
                tmp = tmp.next;         //tmp pekar på nästa elements värde..
            }
            tmp.next = new Node<>(data);    //..som sedan skapar ett nytt element men det data som är inskickad
            size++;                         //Storleken ökar med 1
        }
    }

    public void removeLast (){
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }
        else if (size == 1){
            huvud = svans = null;       //Kollar om första och sista elementet är samma
            size = 0;
        }
        else {
            Node <E> tidigare = huvud;  //Kopia av första elementet

            for (int i = 0; i < size - 2; i++){
                tidigare = tidigare.next;   //Går igenom listan
            }

            svans = tidigare;   //Sista elementet tilldelas det nästsista elementet
            svans.next = null;  //Det som kommer efter sista elementet tilldelas null-värde
            size--;
        }
    }

    public boolean contains (E data){
        Node <E> hittad = huvud;        //Tar kopia på första elementet

        while (hittad != null) {        //Loopen körs så länge hittad inte har ett null-värde
            if (hittad.data.equals(data)){
                return true;            //Om hittad innehåller data efterfrågad data, returnera sant.
            }
            hittad = hittad.next;       //Stegar igenom varje element med pekare
        }
        return false;
    }

    public E getAt (int pos){
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException();  //Kollar att pos är inom intervallet på listan
        }
        Node <E> head = huvud;      //Kopia av första elementet

        for (int i = 0; i < pos; i++){
            head = head.next;           //Går igenom listan
        }
        return head.data;
    }

    //Metoden returnerar storleken på listan
    public int size (){
        return size;
    }


    //Lägger in element i storleksordning eller bokstavsordning
    public void addInOrder(E data) {
        Node<E> nyNod = new Node<>();
        nyNod.data = data;              //Ny nod skapas

        //Om det inte finns något värde i listan, placera allra först
        if (huvud == null || nyNod.data.compareTo(huvud.data) <= 0){
            Node<E> temp = huvud;
            huvud = nyNod;
            nyNod.next = temp;
        }
        else {
            //Är värdet mindre eller lika med än det de jämförs med placeras det före
            if (nyNod.data.compareTo(huvud.data) <= 0){
                Node<E> temp = huvud;
                huvud = nyNod;
                nyNod.next = temp;
            }else {

                //Placerar nästa pekaren av den förra noden på den nya inkommande.
                //Sedan pekar den imkommande nodens nästa pekare på den nuvarande noden.
                //Detta gör att previous pekar på den nya noden och att den nya noden
                //pekar på current
                Node<E> previous = huvud;
                Node<E> current = huvud.next;
                while (previous.next != null && nyNod.data.compareTo(previous.next.data) > 0){
                    previous = previous.next;
                    current = current.next;
                }
                previous.next = nyNod;
                nyNod.next = current;
            }
        }
        size++;

    }
}

