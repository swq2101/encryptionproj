import java.io.*;

public class ReadWrite
{
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
                            
   public static void main(String args[])
      throws IOException
   {
     String s=loadfile("Karel.txt").toString();
     
     s += " because he is so cool.";
     
     writeFile("newfile.txt" ,s);
     
     
   
   
   
   }




}