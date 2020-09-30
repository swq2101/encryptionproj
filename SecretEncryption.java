import java.io.*;
import java.lang.*;

public class SecretEncryption

{
  
  private char common='0';
  
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
    }
      
      

   /**Writes String str into a file with pathname from String pathname*/
   
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

   /**Returns the most frequently occurring character in StringBuffer.*/
   
   public char mostFrequentChar(StringBuffer s)
   {
       int max=0;//counts number of times the most frequent char occurs
       String s2=s.toString();

       
       for(int a=32; a <= 126; a++)
       {
         char ch=(char) a;//converts ASCII value to corresponding char
         String s3=Character.toString(ch);
         
         if(Character.isLetter(ch))
         {
           
            int count = s2.length()-s2.replaceAll(s3,"").length();//counts number of times that given alphabetical char occurs
            if(count > max)
            {
               max=count;
               common=ch;
               
            }
            count=0;
         }
         
       
      }
         return common;
   
   }
   
   /**Method takes StringBuffer as parameter and returns encrypted StringBuffer.*/
   
   public StringBuffer encrypt(StringBuffer s)
   {
      String toencrypt = Character.toString(common);
      StringBuffer encrypted = new StringBuffer(toencrypt);
      int commie = (int) common; //ASCII value of most common char
      int letter = 0;//will take ASCII value of encrypted char
      
      for(int p = 0; p <= s.length()-1; p++)
      {
         
         int og = (int) s.charAt(p);
         
         if(og == 10 || og == 15)//preserves escape sequences
         {
            encrypted.append(s.charAt(p));
         }
         else
         {
           letter = commie + og;
         
           while(letter > 127)//wrap around
           {
             letter -= 95;
           }
           
           String hidden = Character.toString((char) letter);
           encrypted.append(hidden);
         }
     
         
      
      }
      
      
      return encrypted;
   
   }
   
   /**Method takes encrypted StringBuffer as parameter and returns unencrypted version"*/
   
   public StringBuffer decrypt(StringBuffer s)
   {
      StringBuffer decrypted = new StringBuffer("");
      int key = (int) s.charAt(0)-95;
      s.deleteCharAt(0);
      
      for(int d = 0; d <= s.length()-1; d++)
      {
         int real = (int) s.charAt(d);
         real -= key;
         
         while(real < 32)//wrap around
         {
            real += 95;
         }
         
         
         String decrypt = Character.toString((char) real);
         decrypted.append(decrypt);
      }
      
      
      
      return decrypted;
   
   }
      
   public static void main(String args[])
      throws IOException
   {
      SecretEncryption x=new SecretEncryption();
      
      StringBuffer s = new StringBuffer()
      
      
   
   }

}