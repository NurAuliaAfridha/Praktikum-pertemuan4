package id.ac.Ridha.ui;


import id.ac.Ridha.helper.MyConnection;
import id.ac.Ridha.model.MataKuliah;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MataKuliahRead extends JFrame {
    private JPanel panel1;
    private JTable matakuliahTable;
    private JScrollPane scrollPanel;

    private void createUIComponents() {

        MataKuliah mataKuliah;
        ArrayList<MataKuliah> mataKuliahArrayList = new ArrayList<>();
        MyConnection myConnection = new MyConnection();
        Connection con = myConnection.getConnection();

        String selectQuery = "SELECT * FROM makul";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            while(resultSet.next())
            {
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
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        Object[][] row = new Object[mataKuliahArrayList.size()][6];

        for (int i = 0; i < mataKuliahArrayList.size(); i++)
        {
            row[i][0] = mataKuliahArrayList.get(i).getIdMakul();
            row[i][1] = mataKuliahArrayList.get(i).getNamaMakul();
            row[i][2] = mataKuliahArrayList.get(i).getSingkatanMakul();
            row[i][3] = mataKuliahArrayList.get(i).getNamaDosen();
            row[i][4] = mataKuliahArrayList.get(i).getKontakDosen();
            row[i][5] = mataKuliahArrayList.get(i).isAktif();
        }

        String mataKuliahHeader[] = {"ID", "MATAKULIAH", "SINGKATAN", "DOSEN", "KONTAK", "AKTIF"};
        TableModel tableModel = new DefaultTableModel(row, mataKuliahHeader);
        matakuliahTable = new JTable(tableModel);

    }

        public static void main(String[] args) {
            MataKuliahRead mataKuliahRead = new MataKuliahRead();
            mataKuliahRead.setContentPane(new MataKuliahRead().panel1);
            mataKuliahRead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mataKuliahRead.createUIComponents();
            mataKuliahRead.pack();
            mataKuliahRead.setLocationRelativeTo(null);
            mataKuliahRead.setVisible(true);
        }


    }


