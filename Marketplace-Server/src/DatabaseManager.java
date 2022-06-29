import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.ArrayList;


public class DatabaseManager {
    //to be modified
    static final String DB_URL = "jdbc:mysql://localhost:3306/marketplace";
    static final String USER = "root";
    static final String PASS = "1412001BAmr";

    //private static String ImagesDirectory;

    public static Connection start_connection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addRegister(DataManager.RegistrationData data)
    {
        try{
            Connection connection = start_connection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer (email, name, mobile, Address, wallet,password) VALUES (?,?,?,?,?,?)");
            statement.setString(1,data.email());
            statement.setString(2,data.firstName());
            statement.setString(3, data.phone());
            statement.setString(4, data.address());
            statement.setInt(5,0);
            statement.setString(6, data.password());
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public boolean checkemail(String email)
    {
        try{
            Connection connection = start_connection();
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM customer where email = ?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            String pass="";
            while(resultSet.next())
            {
                pass=resultSet.getString("password");
            }
            if(pass=="")
            {
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public Boolean validate(DataManager.UserCredentials data)
    {
        try{
            Connection connection = start_connection();
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM customer where email = ? and password = ?");
            statement.setString(1, data.email());
            statement.setString(2, data.password());
            ResultSet resultSet = statement.executeQuery();
            String pass="";
            while(resultSet.next())
            {
                pass=resultSet.getString("password");
            }
            if(pass=="")
            {
                return false;
            }
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
    }

    public DataManager.MoneyAmount getWallet(String email)
    {
        try {
            Connection connection = start_connection();
            PreparedStatement statement = connection.prepareStatement("SELECT wallet from customer where email=?");
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            int pou=0;
            int pia=0;
            while (resultSet.next()) {
                pou = (int)(resultSet.getInt("wallet")/100);
                pia = (int)(resultSet.getInt("wallet")%100);
            }
            DataManager.MoneyAmount wallet = new DataManager.MoneyAmount(pou,pia);
            return wallet;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public String findFilestr(String name,File file)
    {
        File[] list = file.listFiles();
        String out="";
        if (list != null)
            for (File fil : list) {
                boolean flag = true;
                String s = fil.getName();
                for (int i = 0; i < name.length(); i++) {
                    if (name.charAt(i) != s.charAt(i))
                        flag = false;
                }
                if (flag) {
                    out=fil.getPath();
                }

            }
        return out;
    }



    public ArrayList<String> findFilearr(String name,File file) {
        File[] list = file.listFiles();
        ArrayList<String> arr = new ArrayList<String>();
        if (list != null)
            for (File fil : list) {
                boolean flag = true;
                String s = fil.getName();
                for (int i = 0; i < name.length(); i++) {
                    if (name.charAt(i) != s.charAt(i))
                        flag = false;
                }
                if (flag) {
                    arr.add(fil.getPath());
                }

            }
        return arr;
    }
    public ArrayList<String> getImages(int ID) {
        //convert to string
        String s = String.valueOf(ID);
        File file = new File("Marketplace-Server/database/images");
        ArrayList<String> arr = new ArrayList<String>();
        //arr -> arraylist of paths of matched images
        arr = findFilearr(s, file);
        return arr;


    }

    public String getIcon(int ID) {
        //convert to string
        String s = String.valueOf(ID);
        File file = new File("Marketplace-Server/database/icons");
        String str = findFilestr(s, file);
        return str;
    }
}
