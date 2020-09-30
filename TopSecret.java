import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class TopSecret
{
  private String cipher;
  private static StringBuffer encry;
 
 /**Reads textfile and returns contents as StringBuffer*/

 public static StringBuffer loadfile(String pathname)
      throws IOException
   {
      File file = new File(pathname);
      StringBuffer strBuffer = new StringBuffer ((int)file.length());
      BufferedReader input=new BufferedReader(new FileReader(file));
      
      int ch = 0;
      while ((ch=input.read()) != -1)
         strBuffer.append((char)ch);
         
      input.close();
      
      return strBuffer;
   }
   
   /*Returns textfile with pathname that contains given string*/
   
   public static void writeFile(String pathname, String str)
      throws IOException
   {
    
      Writer writer = null;
      try
      {
         writer= new FileWriter(pathname, false);
      }
      catch(IOException ex)
      {
         System.out.println("***Cannot create/open " + pathname + " ***");
         System.exit(1);
      }
      
      PrintWriter output=new PrintWriter(writer);
      output.print(str);
      output.close(); 
   }
   /**Makes cipher using user inputed password and returns cipher as string.*/
   
   public String makeCipher()
   {
      Scanner kb = new Scanner(System.in);
      
      System.out.println("Enter passcode: ");
      String passcode = kb.nextLine();
      
      passcode = passcode.toLowerCase();
      StringBuffer code = new StringBuffer();
      
      while(passcode.length()>0)
       {
         char check = passcode.charAt(0);
         String checks=Character.toString(check);
         code.append(checks);
         passcode = passcode.replaceAll(checks,""); 
        
       }
  
             
       String encode = code.toString();
       
       StringBuffer backwardsalpha = new StringBuffer("zyxwvutsrqponmlkjihgfedcba");
       
       for(int r = 0; r < encode.length(); r++)//removes characters in password from backwards alpha
       {
            char chh = encode.charAt(r);
            String thing = Character.toString(chh);
            int pos = backwardsalpha.indexOf(thing);
            backwardsalpha.deleteCharAt(pos);
         
       }
       
       String backwards = backwardsalpha.toString();
 
       cipher = encode + backwards;
       
       return cipher; 
      
  }
  
  /**Encrypts StringBuffer and returns String with encyrption*/
  
  public String encrypt(StringBuffer sb)
  {
   String alphabet = "abcdefghijklmnopqrstuvwxyz";
   String cipher = makeCipher();
   System.out.println(cipher);
   String s = sb.toString().toLowerCase();
   StringBuffer encrypted = new StringBuffer();
   int i = 0;
   
   for(int v = 0; v < s.length(); v++)
   {
      char a = s.charAt(v);
      
      if(Character.isLetter(a))
      {
         int pos = alphabet.indexOf(a);
         char b = cipher.charAt(pos);
         encrypted.append(b);
      }
      else
      {
         encrypted.append(a);
      }
   
   }
   
    String ency = encrypted.toString();
    
    char spaces = '0';
   
    for(int jd=97; jd <= 122; jd++)//finds char to replace spaces
    {
      
      String s123 = Character.toString((char) jd);
      int count = ency.length()-ency.replaceAll(s123,"").length();
     
      if(count == 0 && i == 0)
      {
         spaces = (char) jd;
         i++;
      
      } 
      
      count = 0;
 
    }
      
    String space = Character.toString(spaces);
    ency = ency.replaceAll(" ",space); 
    char periods = '0';
    int u = 0;
     
    for(int md=97; md <= 122; md++)//finds char to replace periods
    {
      
      String s124 = Character.toString((char) md);
      int sum = ency.length()-ency.replaceAll(s124,"").length();
     
      if(sum == 0 && u == 0)
      {
         periods = (char) md;
         u++;
      
      } 
      
      sum = 0;
 
    }
    
    String period = Character.toString(periods);
    ency = ency.replaceAll("\\.", period);
    ency = space + period + ency;
    
      
     
    return ency;
  }
  
  /**Decrypt StringBuffer and returns String decrypted*/
  
  public String decrypt(StringBuffer sb)
  {
   String alphabet = "abcdefghijklmnopqrstuvwxyz";
   String cipher = makeCipher();
   System.out.println(cipher);
   String s = sb.toString().toLowerCase();
   StringBuffer decrypted = new StringBuffer();
   
   String spaces = Character.toString(s.charAt(0));
   s = s.replaceAll(spaces," ");
   
   String periods = Character.toString(s.charAt(1));
   s = s.replaceAll(periods,".");
   
   
    for(int v = 0; v < s.length(); v++)//decrypts characters that are letters
   {
      char a = s.charAt(v);
      
      if(Character.isLetter(a))
      {
         int pos = cipher.indexOf(a);
         char b = alphabet.charAt(pos);
         decrypted.append(b);
      }
      else
      {
         decrypted.append(a);
      }
   
   }
   
   decrypted.delete(0,2);//removes first two char that contain key for spaces and periods
   
   String decry = decrypted.toString();
   

   return decry;
   
  
  }
  
  public static void main(String args[])
   throws IOException
  {
   TopSecret sophia = new TopSecret();
   
   StringBuffer s = new StringBuffer("Do not scorn pity that is the gift of a gentle heart, Eowyn.");
   StringBuffer n = new StringBuffer("abopaqpiajnpkqamwidaixciawjaixsaywziapzacaysqitsaxscki,aspfdqb");
  
   System.out.println(sophia.encrypt(s));
   System.out.println(sophia.decrypt(n));
  }
   
   
}
                          