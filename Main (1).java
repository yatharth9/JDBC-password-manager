/*Group 4
Yatharth Shah - 19BAI10071
Vedant Anand - 19BAI10130
Ravi kumar - 19BAI10124
Sheethal Krishna - 19BAI10149
 */


import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;

class Main {

    public static void input(String passwd)
    {
        String pwd = passwd;

        String site, ps;
        Scanner sc = new Scanner(System.in);

        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", pwd);

            System.out.println("Enter the Site, and the password");
            site = sc.nextLine();
            ps = sc.nextLine();

            String sqlbase = String.format("INSERT INTO `jdbc`.`main`\n" +
                    "(`site`,\n" +
                    " `password`)\n" +
                    " VALUES(\n" +
                    " \"%s\",\n" +
                    " \"%s\"\n" +
                    " );", site, ps);
            //System.out.println(sqlbase);

            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlbase);

        }
        catch (SQLException se) {
            do {
                System.out.println("SQL STATE: " + se.getSQLState());
                System.out.println("ERROR CODE: " + se.getErrorCode());
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                if (se.getMessage().compareTo("Access denied for user 'root'@'localhost' (using password: YES)") == 0) {
                    System.out.println("Incorrect password");
                    exit(1);
                }
                if (se.getErrorCode() == 1602)
                {
                    System.out.println("Error! Site already exists");
                    return;
                }
                se = se.getNextException();

            }
            while (se != null);
        }
        output(pwd);
        return;
    }

    public  static void output(String passwd)
    {
        String pwd = passwd;
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", pwd);
            String sqlbase  = "SELECT * FROM main;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlbase);
            System.out.println("The currently saved passwords are: ");
            while(resultSet.next())
            {
                System.out.println("Site\t\t" + resultSet.getString("site"));
                System.out.println("Password\t" + resultSet.getString("password"));
                System.out.println();
            }

        }
        catch (SQLException se) {
            do {
                System.out.println("SQL STATE: " + se.getSQLState());
                System.out.println("ERROR CODE: " + se.getErrorCode());
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                if (se.getMessage().compareTo("Access denied for user 'root'@'localhost' (using password: YES)") == 0) {
                    System.out.println("Incorrect password");
                    exit(1);
                }
                if (se.getErrorCode() == 1602)
                {
                    System.out.println("Error! Site already exists");
                    return;
                }
                se = se.getNextException();

            }
            while (se != null);
        }
        return;
    }
    public static void update(String passwd)
    {
        String pwd = passwd;

        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", pwd);

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Site, and the password");
            String site = sc.nextLine();
            String ps = sc.nextLine();

            String sql = String.format("DELETE FROM main WHERE site=\"%s\"", site);
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);

            String sqlbase = String.format("INSERT INTO `jdbc`.`main`\n" +
                    "(`site`,\n" +
                    " `password`)\n" +
                    " VALUES(\n" +
                    " \"%s\",\n" +
                    " \"%s\"\n" +
                    " );", site, ps);
            System.out.println(sqlbase);

            Statement statesmen = connection.createStatement();
            int results = statesmen.executeUpdate(sqlbase);

        }
        catch (SQLException se) {
            do {
                System.out.println("SQL STATE: " + se.getSQLState());
                System.out.println("ERROR CODE: " + se.getErrorCode());
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                if (se.getMessage().compareTo("Access denied for user 'root'@'localhost' (using password: YES)") == 0) {
                    System.out.println("Incorrect password");
                    exit(1);
                }
                if (se.getErrorCode() == 1602)
                {
                    System.out.println("Error! Site already exists");
                    return;
                }
                se = se.getNextException();

            }
            while (se != null);
        }
        output(pwd);
    }

    public static void delete(String passwd)
    {
        String pwd = passwd;

        try {


            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", pwd);
            System.out.println("Welcome to delete menu");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Site, and the password you want to delete");
            String site = sc.nextLine();
            String ps = sc.nextLine();

            String sql = String.format("DELETE FROM main WHERE site=\"%s\"", site);
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
        }
        catch (SQLException se) {
            do {
                System.out.println("SQL STATE: " + se.getSQLState());
                System.out.println("ERROR CODE: " + se.getErrorCode());
                System.out.println("MESSAGE: " + se.getMessage());
                System.out.println();
                if (se.getMessage().compareTo("Access denied for user 'root'@'localhost' (using password: YES)") == 0) {
                    System.out.println("Incorrect password");
                    exit(1);
                }
                if (se.getErrorCode() == 1602)
                {
                    System.out.println("Error! Site already exists");
                    return;
                }
                se = se.getNextException();

            }
            while (se != null);
        }
        output(pwd);
    }

    public static void main(String[] args) {
        String pw;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to password manager. \n Please enter your master password");
        pw = sc.nextLine();
        int userinp = 0;
        do {
            System.out.println("Welcome to password manager. Please choose an option");
            System.out.println(" 1. Insert");
            System.out.println(" 2. Update");
            System.out.println(" 3. View");
            System.out.println(" 4. Delete");
            System.out.println(" 5. Exit");
            userinp = sc.nextInt();

            switch (userinp)
            {
                case 1: input(pw);
                        break;

                case 2: update(pw);
                        break;

                case 3: output(pw);
                        break;

                case 4: delete(pw);
                        break;

                case 5: exit(0);

                default: System.out.println("Invalid option");
            }
        }
        while(userinp != 5);


    }
}