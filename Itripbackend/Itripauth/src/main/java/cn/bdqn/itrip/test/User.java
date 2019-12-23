package cn.bdqn.itrip.test;

public class User {
    private int id;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
