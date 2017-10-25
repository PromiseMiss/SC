package com.gym.shancai.utils;


import android.text.TextUtils;

/**
  * 赚到家加密工具
  *  
  * @author guoyiming
  */ 
public class AesUtil2 {  
     // 混淆string
     private final static String secretKey = "abceEabdasdfewEadsfew;234TQze=";  
     // 向量  
     private final static String iv = "01234567" ;  
     // 加解密统一使用的编码方式  
     private final static String encoding = "utf-8" ;  
        
     /** 
      * 加密 
      *  
      *
      */ 
     public static String encode(String plainText) {
         String aaa=plainText+secretKey;
        aaa= Base64.encode(aaa.getBytes());
//        char t;
        char[] content=aaa.toCharArray();
//        t=content[0];
//        content[0]=content[content.length-1];
//        content[content.length-1]=t;
        content=(String.valueOf(content)+secretKey).toCharArray();
        aaa= Base64.encode(String.valueOf(content).getBytes());
        content=aaa.toCharArray();
//        t=content[0];
//        content[0]=content[content.length-1];
//        content[content.length-1]=t;
         return String.valueOf(content);  
     }  
        
     /** 
      * 解密 
      *  
      * @param encryptText 加密文本 
      * @return 
      * @throws Exception 
      */ 
     public static String decode(String encryptText) {
                  char t;
         if (TextUtils.isEmpty(encryptText))
             return "";
         char[] content=encryptText.toCharArray();
       t=content[0];
       content[0]=content[content.length-1];
       content[content.length-1]=t;
         
         byte[] aa= Base64.decode(String.valueOf(content));
         String dd=new String(aa).replaceAll(secretKey, "");
         
         content=dd.toCharArray();
         t=content[0];
         content[0]=content[content.length-1];
         content[content.length-1]=t;

         Logger.e("~~~","~~~"+content);
         aa= Base64.decode(String.valueOf(content));

         dd=new String(aa).replaceAll(secretKey, "");
         return dd;  
     }  
} 