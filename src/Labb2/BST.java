package Labb2;

import java.util.ArrayList;
import java.util.Iterator;

public class BST <E extends Comparable> implements Iterable <E> {

    //roten på träden och storlek
    protected TreeNode root;
    protected int size = 0;


    public void add(E s) {
        if (root == null) {
            root = new TreeNode(s); //Kollar om roten inte har något värde
        } else {

            TreeNode parent = null; //Nod som kommer hålla föräldern till den nod vi går till
            TreeNode current = root;//Håller nuvarande nod som rot

            while (current != null) {   //Så länge det inte är tomt i current utförs loopen

                if (s.compareTo(current.data) < 0) {    //Är värdet mindre än det som finns i current data
                    parent = current;                   //Sätter om parent till current
                    current = current.left;             //current stegar sedan till vänster i trädet
                }

                else if (s.compareTo(current.data) > 0) {//Är värdet högre sker det tvärtom mot föregående.
                    parent = current;
                    current = current.right;
                } else {
                    return;
                }
            }
            //När current har ett nullvärde lägger den till en ny trädnod, antingen till vänster om det var mindre
            //eller höger om det var större
            if (s.compareTo(parent.data) < 0) {
                parent.left = new TreeNode(s);
            } else {
                parent.right = new TreeNode(s);
            }
        }
        size++;
    }


    public void remove(E s) {

        TreeNode parent = null;     //Föräldern till pekare
        TreeNode current = root;    //Pekare

        //Medan pekaren inte pekar på ett null-värde
        while (current != null) {
            //Kollar till vänster
            if (s.compareTo(current.data) < 0) {
                parent = current;
                current = current.left;
                //Kollar till höger
            } else if (s.compareTo(current.data) > 0) {
                parent = current;
                current = parent.right;
            } else {
                break;  //Hittat
            }
        }

        if(current.left == null) {  //Inget till vänster om current
            if(parent == null) {    //Kopplar parent till högra barnet av current
                root = current.right;
            } else {
                //Peka om parents högra eller vänstra nod till elementet som ska tas bort
                if (s.compareTo(parent.data) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            //Vänsterbarn finns
            TreeNode parentOfRight = current;
            TreeNode rightMost = current.left;

            //Letar upp den nod som har högst värde i vänstra delträdet
            while(rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            //Kopierar värdet så att current får det högsta värdet i vänstra delträdet
            current.data = rightMost.data;

            //Tar bort det högsta värdet
            if(parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            } else {
                parentOfRight.left = rightMost.left;
            }
        }
        size--;
    }


    public boolean find (E data){
        TreeNode <E> current = root;    //Börjar från roten

        while (current != null) {       //Så länge current inte har null-värde

            if (data.compareTo(current.data) <0){
                current = current.left; //Går vänster i trädet
            }
            else if (data.compareTo(current.data) > 0){
                current = current.right;//Går höger i trädet
            }
            else
                return true;    //Returnera om element är hittat
        }
        return false;           //Returnera om element ej är hittat
    }

    public Iterator <E> iterator (){
        return new inOrderIterator();
    }


    private class inOrderIterator implements Iterator <E> {

        //ArrayList som håller värden som sedan next och hasNext går igenom
        private ArrayList <E> lista = new ArrayList<>();
        private int current = 0;

        //Konstruktor
        public inOrderIterator (){
            inorder();
        }

        private void inorder () {
            inorder(root);
        }

        private void inorder(TreeNode <E> root) {
            if (root == null) return;   //Listan tom
            inorder(root.left);         //Går vänster
            lista.add(root.data);
            inorder(root.right);        //Går höger

        }

        @Override
        public boolean hasNext() {
            //Så länge current är mindre än listan
            if (current < lista.size())
                return true;

            return false;
        }

        @Override
        public E next() {
            //Returnerar listans innehåll och stegar igenom med ++
            return lista.get(current++);
        }
    }

    public int size (){
        //Storlek på trädet
        return size;
    }

    public E min() {
        TreeNode <E> parent = null; //Föräldern till pekaren
        TreeNode <E> current = root;//Pekaren som utgår från roten
        //Medan current inte har ett null värde stegar den igenom
        while (current != null){
            parent = current;   //Parent får currents värde
            current=current.left;//Current får det värde som finns till vänster om det = det minsta

        }
        return parent.data;

    }

    public E max () {
        TreeNode <E> parent = null;
        TreeNode <E> current = root;
        while (current != null){    //samma som ovan fast tvärtom
            parent = current;
            current=current.right;
        }
        return parent.data;
    }

}



