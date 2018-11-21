/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ricky
 */
@ManagedBean
@RequestScoped
public class DokterDanPasien {
  
    
    private String JenisPenyakit;   
    private String NamaDokter; 
    private String NamaPasien;
    private String NID;
    private String NIP;
    private String Penyakit;
    private String Spesialis;
    
    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
    
    public String getPenyakit() {
        return Penyakit;
    }

    public void setPenyakit(String Penyakit) {
        this.Penyakit = Penyakit;
    }
    
    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }
    
    public String getNamaPasien() {
        return NamaPasien;
    }

    public void setNamaPasien(String NamaPasien) {
        this.NamaPasien = NamaPasien;
    }
    
    public String getNamaDokter() {
        return NamaDokter;
    }

    public void setNamaDokter(String NamaDokter) {
        this.NamaDokter = NamaDokter;
    }
    
    public String getJenisPenyakit() {
        return JenisPenyakit;
    }

    public void setJenisPenyakit(String JenisPenyakit) {
        this.JenisPenyakit = JenisPenyakit;
    }

    public String getSpesialis() {
        return Spesialis;
    }

    public void setSpesialis(String Spesialis) {
        this.Spesialis = Spesialis;
    }
    
    
    
    //Menghubungkn keservlet
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
     public ArrayList getAll_dokter_dan_pasien() throws Exception{
        ArrayList getalldokterdanpasien=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT pasien.JenisPenyakit, dokter.NamaDokter, pasien.NamaPasien\n" +
                            "FROM dokter, pasien\n" +
                            "WHERE pasien.JenisPenyakit = dokter.Spesialis\n" +
                            "ORDER BY pasien.JenisPenyakit");
            while(rs.next()){
                DokterDanPasien obj_dopapi = new DokterDanPasien();
                obj_dopapi.setJenisPenyakit(rs.getString("JenisPenyakit"));
                obj_dopapi.setNamaDokter(rs.getString("NamaDokter"));
                obj_dopapi.setNamaPasien(rs.getString("NamaPasien"));
                getalldokterdanpasien.add(obj_dopapi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return getalldokterdanpasien;
    }
     
     public ArrayList getAll_dokter() throws Exception{
        ArrayList getalldokter=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from dokter");
            while(rs.next()){
                DokterDanPasien obj_dopapi = new DokterDanPasien();
                obj_dopapi.setNID(rs.getString("NID"));
                obj_dopapi.setNamaDokter(rs.getString("NamaDokter"));
                obj_dopapi.setSpesialis(rs.getString("Spesialis"));
                getalldokter.add(obj_dopapi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return getalldokter;
    }
    
     public ArrayList getAll_pasien() throws Exception{
        ArrayList getallpasien=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from pasien");
            while(rs.next()){
                DokterDanPasien obj_dopapi = new DokterDanPasien();
                obj_dopapi.setNIP(rs.getString("NIP"));
                obj_dopapi.setNamaPasien(rs.getString("NamaPasien"));
                obj_dopapi.setJenisPenyakit(rs.getString("JenisPenyakit"));
                obj_dopapi.setPenyakit(rs.getString("Penyakit"));
                getallpasien.add(obj_dopapi);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return getallpasien;
    }
     
    public String Edit_Dokter(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_NID = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from dokter where NID="+Field_NID);
          DokterDanPasien obj_dokter = new DokterDanPasien();
          rs.next();
          obj_dokter.setNID(rs.getString("NID"));
          obj_dokter.setNamaDokter(rs.getString("NamaDokter"));
          obj_dokter.setSpesialis(rs.getString("Spesialis"));
          sessionMap.put("EditDokter", obj_dokter);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditDokter.xhtml?faces-redirect=true";   
    }
    
    public String Edit_Pasien(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_NIP = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from pasien where NIP="+Field_NIP);
          DokterDanPasien obj_pasien = new DokterDanPasien();
          rs.next();
          obj_pasien.setNIP(rs.getString("NIP"));
          obj_pasien.setNamaPasien(rs.getString("NamaPasien"));
          obj_pasien.setJenisPenyakit(rs.getString("JenisPenyakit"));
          obj_pasien.setPenyakit(rs.getString("Penyakit"));
          sessionMap.put("EditPasien", obj_pasien);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/EditPasien.xhtml?faces-redirect=true";   
    }
    
  
    public String Update_Dokter(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_NID= params.get("Update_NID");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update dokter set NID=?, "
                    + "NamaDokter=?, Spesialis=? where NID=?");
            ps.setString(1, NID);
            ps.setString(2, NamaDokter);
            ps.setString(3, Spesialis);
            ps.setString(4, Update_NID);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    
    public String Update_Pasien(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_NIP= params.get("Update_NIP");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update dokter "
                    + "set NIP=?, NamaPasien=?, JenisPenyakit=?, Penyakit=?"
                    + " where NIP=?");
            ps.setString(1, NIP);
            ps.setString(2, NamaPasien);
            ps.setString(3, JenisPenyakit);
            ps.setString(4, Penyakit);
            ps.setString(5, Update_NIP);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    
    public String Update_Dapopi(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_JenisPenyakit= params.get("Update_JenisPenyakit");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update dokter, pasien "
                    + "set pasien.JenisPenyakit=?, dokter.NamaDokter=?, "
                    + "pasien.NamaPasien=?, where pasien.JenisPenyakit=?, "
                    + "dokter.Spesialis=pasien.JenisPenyakit");
            ps.setString(1, JenisPenyakit);
            ps.setString(2, NamaDokter);
            ps.setString(3, NamaPasien);
            ps.setString(4, Update_JenisPenyakit);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    public String tambah22nya(){
        Tambah_Dokter();
        Tambah_Pasien();
        return "/index.xhtml?faces-redirect=true";
    }
    public void Tambah_Dokter(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into "
                    + "dokter(NID, NamaDokter, Spesialis) "
                    + "value('"+NID+"','"+NamaDokter+"','"+Spesialis+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void Tambah_Pasien(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into "
                    + "pasien(NIP, NamaPasien, JenisPenyakit, Penyakit) "
                    + "value('"+NIP+"','"+NamaPasien+"','"+JenisPenyakit+"','"+Penyakit+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String Delete_Dokter(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_NID = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from dokter where NID=?");
         ps.setString(1, Field_NID);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    
    
    
    public String Delete_Pasien(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_NIP = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from pasien where NIP=?");
         ps.setString(1, Field_NIP);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    
    public DokterDanPasien(){
    
    }
}
