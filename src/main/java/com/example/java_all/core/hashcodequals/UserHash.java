package com.example.java_all.core.hashcodequals;

import org.apache.catalina.User;

import java.util.Objects;

public class UserHash {

    public String name;
    public int id;

    public UserHash(String name,int id) {
        this.name=name;
        this.id=id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if( !(o instanceof UserHash)) return false;
        UserHash userHash =(UserHash) o;
        return id==userHash.id && Objects.equals(name,userHash.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,id);
    }
}
