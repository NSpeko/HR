package by.molchanov.humanresources.resource;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.security.AESEncryption;
import by.molchanov.humanresources.validator.UserDataValidation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test {

    public static void main(String[] args) throws CustomDAOException, SQLException, InterruptedException {

        System.out.println(UserDataValidation.isEmailAddressCorrect("a@mail.com"));

//        JobVacancy jobVacancy = new JobVacancy(2, "Lul", null, "Nice job vacancy", JobVacancyStatusType.OPEN);
//        JobVacancyDAO jobVacancyDAO = new JobVacancyDAO();
//        jobVacancy = jobVacancyDAO.persist(jobVacancy);
//        System.out.println(jobVacancy.toString());
//        System.out.println("-------------------------------------");
//
//        jobVacancy.setName("New name");
//        jobVacancyDAO.update(jobVacancy);
//        jobVacancy = jobVacancyDAO.findById(jobVacancy.getId());
//        System.out.println(jobVacancy.toString());
//        System.out.println("-------------------------------------");
//
//        jobVacancyDAO.delete(jobVacancy);

//        Integer a = 10;
//        int b = 1;
//        int c = a+b;
//        int d = b+a;
//        System.out.println(c);
//        System.out.println(d);
//        List<JobVacancy> list = jobVacancyDAO.findAll();
//        for (JobVacancy u: list) {
//            System.out.println(u.toString());
//        }

//        method(0);
//        List selection;
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/human_resources?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC", "root",
//                "password"))  {
//            try (PreparedStatement statement = connection.prepareStatement("SELECT u_id, u_email, u_role, u_password, u_firstname," +
//                    " u_lastname, u_organization_id FROM user ")) {
//                try (ResultSet set = statement.executeQuery()) {
//
//                }
//            } catch (SQLException e) {
//                throw new CustomDAOException(e);
//            }
//        }

//        User user = new User("vlad=4", "123", "vladislav", "m", 0, UserType.ADMIN);
//
//        JobRequestDAO dao = new JobRequestDAO();
//        List<JobRequest> list = dao.findAll();
//        for (JobRequest u: list) {
//            System.out.println(u.toString());
//        }

        ConnectionPool.getInstance().closePool();
//        dao.persist(user);
//        String s = "vlad";
//        String s1 = "vlad";
//        AESEncryption aesEncryption = new AESEncryption();
//        String s2 = aesEncryption.encryptionOfString(s);
//        String s3 = aesEncryption.encryptionOfString(s2);
//        System.out.println(s2);
//        System.out.println(s3);

//        String str1 = "String 1";
//        String str2 = new String("String 2");
//
//        String str3 = str2.substring(0, 8);
//
//        System.out.println("+" + str2 + "+");
//        System.out.println("+" + str3 + "+");
    }

//    public static void method(int i) throws InterruptedException {
//
//        System.out.println(i++);
//        Thread.sleep(4);
//        method(i);
//    }
}