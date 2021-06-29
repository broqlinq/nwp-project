package raf.nwp.aircompany.models;

public class User {

    private Integer id;
    private String username;
    private String password;
    private Type type;

    public enum Type { ADMIN, REGULAR }
}
