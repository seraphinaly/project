package com.sist.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ComboDAO {

         private Connection conn;
         private PreparedStatement ps;
         private final String URL="jdbc:oracle:thin:@211.238.142.208:1521:XE";
         public ComboDAO(){
            try
            {
               Class.forName("oracle.jdbc.driver.OracleDriver");
            }catch(Exception ex) {}
         }
         public void getConnection(){
            try{
               conn=DriverManager.getConnection(URL,"hr","happy");
            }catch(Exception ex) {}
         }

         public void disConnection(){
            try{
               if(ps!=null) ps.close();
               if(conn!=null) conn.close();
            }catch(Exception ex) {}
         }

         public void comboInsert(ComboVO vo)
         {
            try{
               getConnection();
               
               String sql="INSERT INTO change_spec VALUES("
                       +"?,?,?)";
               ps.setInt(1, vo.getCom_no());
               ps.setInt(2, vo.getCate_no());
               ps.setString(3, vo.getTag_option_data());
               ps=conn.prepareStatement(sql);
               
               ps.executeUpdate();
            } catch(Exception ex){
               System.out.println(ex.getMessage());
            } finally{
               disConnection();
            }
         }
}