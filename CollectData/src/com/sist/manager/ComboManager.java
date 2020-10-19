package com.sist.manager;

import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.manager.*;

public class ComboManager {
   public void comboAllData() {
      try{
         Document doc=Jsoup.connect(
               "http://shop.danawa.com/shopmain/?controller=goods&methods=index&categorySeq=3078")
               .get();
         Elements link=doc.select("a.btn_pc_buy");
         ComboVO vo=new ComboVO();
         for(int i=0; i<link.size(); i++){
            try {
            String url=link.get(i).attr("href");
            System.out.println(url);
            Document doc2=Jsoup.connect(url).get();
            
            System.out.println("com_no:"+i);
            System.out.println("cate_no:"+1);
            vo.setCom_no(i+1);
            vo.setCate_no(1);
            Elements tag_option_data=doc2.select("div.option_data");
               //Elements price=doc2.select("span.opt_price");
               for(int j=0;j<6;j++) {   
               //System.out.println(tag_option_data.get(j).html());
               try {   
                  vo.setTag_option_data(tag_option_data.html());
                
               //System.out.println("컴퓨터번호:"+url.substring(87));
               //System.out.println("제품명:"+name.get(j).text());
               //System.out.println("가격:"+price.get(j).text());
               }catch(Exception ex) {
                  ex.printStackTrace();
               }
                System.out.println(vo.getCate_no());
                
               }
               
            System.out.println("=======================");

//            // 3. dao로 전송
            ComboDAO dao=new ComboDAO();
            dao.comboInsert(vo);
            }catch(Exception ex) {
               ex.printStackTrace();
            }
         }
      } catch(Exception ex){
         System.out.println(ex.getMessage());
      }

   }

   public static void main(String[] args) {
      ComboManager c=new ComboManager();
      c.comboAllData();
   }
}