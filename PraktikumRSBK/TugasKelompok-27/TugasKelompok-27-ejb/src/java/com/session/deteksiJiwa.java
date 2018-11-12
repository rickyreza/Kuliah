/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Ricky
 */
@Stateless
@LocalBean
public class deteksiJiwa {

    String jawaban;
    
    String jawaban1 = "Ya";
    String jawaban2 = "Ya";
    String jawaban3 = "Ya";
    String jawaban4 = "Tidak";
    String jawaban5 = "Tidak";
    
    int result;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    DecimalFormat a = new DecimalFormat("0.0");
    int jawabanbenar = 0;
    
    public Integer cekAnswer1(String jawaban) {
        method(jawaban, jawaban1);
        return result;
    }
    

    public Integer cekAnswer2(String jawaban) {
        method(jawaban, jawaban2);
        return result;
    }
    
    public Integer cekAnswer3(String jawaban) {
        method(jawaban, jawaban3);
        return result;
    }
    
    public Integer cekAnswer4(String jawaban) {
        method(jawaban, jawaban4);
        return result;
    }
    
    public Integer cekAnswer5(String jawaban) {
        method(jawaban, jawaban5);
        return result;
    }
    
    public Integer setScore(int a, int b, int c, int d, int e){
        int s = a + b + c + d + e;
        int m = 100 - (s * 20);
        return m;
    }
    
   public void method(String jawabandp, String jawabanas){
        String news = String.valueOf(jawabandp);
        if(news.equals(jawabanas)){
            result = jawabanbenar + 1;
        }else{
            result = 0;
        }
    }


    
    
   
  
}
