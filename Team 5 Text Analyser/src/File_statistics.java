import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File_statistics {

    String line;
    static int wordCount;
    static int lineCount;
    static int characterCount;
    static int avgWordsperLine;
    static int avgLineLength;
    static int characterMax;
    static ArrayList<String> outputstring;
    static ArrayList<String> outputstringfull;

    File_statistics(String filepath){

        wordCount(filepath);
        lineCount(filepath);
        avgWordsperLine(filepath);
        avgLineLength(filepath);
        lineLimit(filepath,characterMax);
        fullJustify(characterMax);
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


    public void lineLimit(String fp, int maxWidth)
    {
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String fileContent;
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
                if (line.length() + tokens[i].length() >= maxWidth) {
                    linesList.add(line.trim());
                    line = tokens[i]+ " ";
                } else {
                    line += tokens[i] + " ";
                }

                if (i == tokens.length - 1)
                    linesList.add(line.trim());
            }


            outputstring = linesList;

            //System.out.println(outputstring);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fullJustify(int maxWidth) {

        String[] words;
        List<String> result = new ArrayList<String>();

        for (String ignored : words = outputstring.toArray(new String[0])) {
        }

        if(words.length == 0){
            outputstringfull = (ArrayList<String>) result;
        }


        int count=0;
        int last=0;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<words.length; i++){
            count = count + words[i].length();

            if(count+i-last>maxWidth){
                int wordsLen = count-words[i].length();
                int spaceLen = maxWidth-wordsLen;
                int eachLen = 1;
                int extraLen = 0;

                if(i-last-1>0){
                    eachLen = spaceLen/(i-last-1);
                    extraLen = spaceLen%(i-last-1);
                }

                StringBuilder sb = new StringBuilder();

                for(int k=last; k<i-1; k++){
                    sb.append(words[k]);

                    int ce = 0;
                    while(ce<eachLen){
                        sb.append(" ");
                        ce++;
                    }

                    if(extraLen>0){
                        sb.append(" ");
                        extraLen--;
                    }
                }

                sb.append(words[i-1]);//last words in the line
                //if only one word in this line, need to fill left with space
                while(sb.length()<maxWidth){
                    sb.append(" ");
                }

                result.add(sb.toString());

                last = i;
                count=words[i].length();
            }
        }

        int lastLen = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=last; i<words.length-1; i++){
            count = count+words[i].length();
            sb.append(words[i]+" ");
        }

        sb.append(words[words.length-1]);
        int d=0;
        while(sb.length()<maxWidth){
            sb.append(" ");
        }
        result.add(sb.toString());

        //System.out.println(result);
        outputstringfull = (ArrayList<String>) result;
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