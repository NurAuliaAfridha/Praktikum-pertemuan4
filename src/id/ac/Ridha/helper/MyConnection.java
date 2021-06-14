package id.ac.Ridha.helper;

import id.ac.Ridha.model.MataKuliah;

import java.sql.*;
import java.util.ArrayList;

public class MyConnection {
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "db_java";
    private static String TIMEZONE = "?serverTimezone=Asia/Makassar";
    private static String USER = "root";
    private static String PASS = "";

    public Connection getConnection(){
        Connection con = null;

        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Sedang Menghubungkan Ke Database...");
            con = DriverManager.getConnection(URL+DB_NAME+TIMEZONE, USER, PASS);
            System.out.println("Berhasil Terkoneksi");
            return con;
        }catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        MyConnection myConnection = new MyConnection();

        MataKuliah mataKuliah;
        ArrayList<MataKuliah> mataKuliahArrayList = new ArrayList<>();
        Connection con = myConnection.getConnection();

        String querySelect = "SELECT * FROM makul";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(querySelect);
            while(resultSet.next()){
                mataKuliah = new MataKuliah(
                        resultSet.getInt("id_makul"),
                        resultSet.getString("nama_makul"),
                        resultSet.getString("singkatan_makul"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("kontak_dosen"),
                        resultSet.getBoolean("aktif")
                );
                mataKuliahArrayList.add(mataKuliah);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        for (MataKuliah value : mataKuliahArrayList){
            System.out.println(value.getIdMakul());
            System.out.println("\t");
            System.out.println(value.getNamaMakul());
            System.out.println("\t");
            System.out.println(value.getNamaDosen());
            System.out.println("\t");
            System.out.println();
        }
    }
}
