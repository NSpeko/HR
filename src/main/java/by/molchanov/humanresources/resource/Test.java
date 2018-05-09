package by.molchanov.humanresources.resource;

import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomException;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {
    @Override
    public String toString() {
        return "Test{}";
    }

    public static void main(String[] args) {
        String s = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        String s4 = new String("Java");
        s3 = s4.intern();
        System.out.println(s3 == s2);
        System.out.println(s4 == s);
    }
}