package org.java.jdbc.view;

import java.util.List;

public class MoviesView {
    public void printMenu(){
        System.out.println("Choose action:");
        System.out.println("   1 - show all films;");
        System.out.println("   2 - add new film;");
        System.out.println("   3 - add actor to film;");
        System.out.println("   4 - find films this and last year;");
        System.out.println("   5 - delete films released earlier");
        System.out.println("   ----------------------------------------");
        System.out.println("   6 - show all actors;");
        System.out.println("   7 - add new actor;");
        System.out.println("   8 - find actors by film;");
        System.out.println("   9 - find actors who were directors at least one of the films;");
        System.out.println("   10 - find actors by the number of films in which they worked;");
        System.out.println("\n   11 - exit;");
    }

    public void printMsg(String msg){
        System.out.println(msg);
    }

    public void printListToChoose(List list){
        for(int i = 0; i<list.size(); i++){
            System.out.println(i+1+". "+list.get(i).toString());
        }
    }

    public void printList(List list){
        for(Object object: list){
            System.out.println(object.toString());
        }
    }
}
