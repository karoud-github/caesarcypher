
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cypher {
    private char[] alphabet;
     private static int numberOfFiles=0;

    public Cypher(){
        this.alphabet="abcdefghijklmnopqrstuvwxyz0123456789'-\".,;:!? ".toCharArray();
    }
    public void setAlphabet(char[] alphabet){
        this.alphabet=alphabet;
    }
    private String encryptString(String text, int shift){
        Map<Character,Character> map=new HashMap<>();
        text=text.toLowerCase();
        char[] textCharArray=text.toCharArray();
        String encryptedText="";
        int alphabetSize=alphabet.length;
        char[] shiftedAlphabet=new char[alphabetSize];
        for(int i=0;i<alphabetSize;i++){
            shiftedAlphabet[i]=alphabet[(i+ shift) % alphabetSize] ;
            map.put(alphabet[i],shiftedAlphabet[i]);
        }
        Character[] alphabetWrapper=new Character[alphabetSize];
        for(int i=0;i<alphabetSize;i++){
            alphabetWrapper[i]=alphabet[i];
        }
        List listOfChars=Arrays.asList(alphabetWrapper);
        for(int i=0;i<textCharArray.length;i++){
            if(listOfChars.contains(textCharArray[i])){
                encryptedText+=String.valueOf(map.get(textCharArray[i]));
            }else{
                encryptedText+=String.valueOf(textCharArray[i]);
            }
        }
        return encryptedText;
    }

    public String encryptFile(String file,int shift){
        //generate a unique file name
        String uniqueFileName=generateUniqueFileName();
        Path entryPath=Path.of(file);
        String entry=entryPath.getFileName().toString();
        int lastIndex=entry.lastIndexOf(".");
        String fileName=entry.substring(0,lastIndex);
        Path encryptedFile=Path.of("files\\encrypted_"+fileName+"_" +uniqueFileName +".txt");
        Path outPath= entryPath;
        try {
            //reading from file logic
            List<String> lines= Files.readAllLines(outPath);

            List<String> encryptedLines=new ArrayList<>();
            for(String line:lines){
                String encryptedLine=encryptString(line,shift);
                encryptedLines.add(encryptedLine);
            }

            //write to file logic
            for(String line:encryptedLines){
                Files.writeString(encryptedFile,line,StandardOpenOption.APPEND,StandardOpenOption.CREATE);
            }

        }catch(IOException e){
            System.out.print(e.toString());
        }

        return null;
    }

    public String decryptString(String encryptedText, int shift){
        Map<Character,Character> map=new HashMap<>();
        encryptedText=encryptedText.toLowerCase();
        char[] textCharArray=encryptedText.toCharArray();
        String decryptedText="";
        int alphabetSize=alphabet.length;
        char[] shiftedAlphabet=new char[alphabetSize];
        for(int i=0;i<alphabetSize;i++){
            int newIndex=i-shift;
            if(newIndex<0){
                newIndex+=alphabetSize;
            }
            shiftedAlphabet[i]=alphabet[newIndex] ;
            map.put(alphabet[i],shiftedAlphabet[i]);
        }
        Character[] alphabetWrapper=new Character[alphabetSize];
        for(int i=0;i<alphabetSize;i++){
            alphabetWrapper[i]=alphabet[i];
        }
        List listOfChars=Arrays.asList(alphabetWrapper);
        for(int i=0;i<textCharArray.length;i++){
            if(listOfChars.contains(textCharArray[i])){
                decryptedText+=String.valueOf(map.get(textCharArray[i]));
            }else{
                decryptedText+=String.valueOf(textCharArray[i]);
            }
        }
        return decryptedText;
    }

    public String decryptFile(String encryptedFile,int shift){
        //generate a unique file name
        String uniqueFileName=generateUniqueFileName();
        Path entryPath=Path.of(encryptedFile);
        String entry=entryPath.getFileName().toString();
        int lastIndex=entry.lastIndexOf(".");
        String fileName=entry.substring(0,lastIndex);
        Path decryptedFile=Path.of("files\\decrypted_" +uniqueFileName +".txt");
        Path outPath= entryPath;
        try {
            //reading from file logic
            List<String> lines= Files.readAllLines(outPath);

            List<String> decryptedLines=new ArrayList<>();
            for(String line:lines){
                String decryptedLine=decryptString(line,shift);
                decryptedLines.add(decryptedLine);
            }


            //write to file logic
            for(String line:decryptedLines){
                Files.writeString(decryptedFile,line,StandardOpenOption.APPEND,StandardOpenOption.CREATE);
            }

        }catch(IOException e){
            System.out.print(e.toString());
        }
        return null;
    }

    public String generateUniqueFileName(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        return timestamp + ".txt";
    }
}
