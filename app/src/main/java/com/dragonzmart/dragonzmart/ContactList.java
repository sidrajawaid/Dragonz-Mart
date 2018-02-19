package com.dragonzmart.dragonzmart;

import java.util.ArrayList;

/**
 * Created by Sidra on 12/2/2017.
 */

public class ContactList {
    private String name;
    private int number;
    //ArrayList<String> name_and_number=new ArrayList<>();

    ContactList(String name,int number)
    {
        this.name=name;
        this.number=number;

    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
