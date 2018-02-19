package com.dragonzmart.dragonzmart;

/**
 * Created by Sidra on 12/2/2017.
 */

public class GetData {
    String IP;
    int number;
    String keyword;
    String region;
    ContactList contactList;

    public GetData(String IP, int number, String keyword, String region, ContactList contactList) {
        this.IP = IP;
        this.number = number;
        this.keyword = keyword;
        this.region = region;
        this.contactList = contactList;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public String getIP() {

        return IP;
    }

    public int getNumber() {
        return number;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getRegion() {
        return region;
    }

    public ContactList getContactList() {
        return contactList;
    }
}
