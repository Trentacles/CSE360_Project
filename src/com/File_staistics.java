package text;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class File_staistics {

    String line;
    static int wordCount;
    static int lineCount;
    static int characterCount;
    static int avgWordsperLine;
    static int avgLineLength;
    static ArrayList<String> outputstring;

    File_staistics(String filepath){

        wordCount(filepath);
        lineCount(filepath);
        avgWordsperLine(filepath);
        avgLineLength(filepath);
        lineLimit(filepath);
    }

    //Returns the word count in file
    public int wordCount(String fp)
    {
        int wordcount1 = 0;
        int characterCount1 = 0;
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        try {
            while((line = reader.readLine()) != null)
            {
                if(!(line.equals("")))
                {

                    characterCount1 += line.length();

                    String[] wordList = line.split("\\s+");     // \\s+ is the space delimiter in java
                    wordcount1 += wordList.length;
                }
            }
            //System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        characterCount = characterCount1;
        wordCount = wordcount1;
        return wordCount;
    }


    public int lineCount(String fp)
    {
        int linecount1 = 0;
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        try {
            while((line = reader.readLine()) != null)
            {
                if(!(line.equals("")))
                {

                    linecount1++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        lineCount = linecount1;
        return lineCount;
    }


    public void lineLimit(String fp)
    {
        int wordcount1 = 0;
        int characterCount1 = 0;
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fileContent="";
        try {
            File f = new File(fp);
            FileInputStream inp = new FileInputStream(f);
            byte[] bf = new byte[(int)f.length()];
            inp.read(bf);
            fileContent = new String(bf, "UTF-8");

           String [] tokens = fileContent.split("\\s+");
           ArrayList<String> linesList = new ArrayList<String>();
           String line = "";
          
           for (int i = 0; i < tokens.length; i++) {
        	   		if (line.length() + tokens[i].length() >= 80) {
        	   			linesList.add(line.trim());
        	   			line = tokens[i];
        	   		} else {
        	   			line += tokens[i] + " ";
        	   		}
        	   		
        	   		if (i == tokens.length - 1)
        	   			linesList.add(line.trim());
           }
           
           
           outputstring = linesList;
           
  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns the average word count in file
    public int avgWordsperLine(String fp)
    {
        int wc = wordCount(fp);
        int l = lineCount(fp);

        avgWordsperLine = wc/l;
        return avgWordsperLine;
    }

    public int avgLineLength(String fp){
        int cc = characterCount;
        int l = lineCount(fp);

        avgLineLength = cc/l;
        return avgLineLength;
    }


}
