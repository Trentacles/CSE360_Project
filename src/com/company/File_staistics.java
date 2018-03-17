package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class File_staistics {

    String line;
    static int wordCount;
    static int lineCount;
    static int characterCount;
    static int avgWordsperLine;
    static int avgLineLength;
    static String outputstring;

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
