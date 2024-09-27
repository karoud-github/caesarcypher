

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
     public static void main(String[] args) {
        // create a cypher

        Cypher cypher=new Cypher();
        BruteForce bruteForce=new BruteForce();
        StatisticalAnalyzer statisticalAnalyzer=new StatisticalAnalyzer();
        runApp(cypher, bruteForce,statisticalAnalyzer);
    }

    public static void  runApp(Cypher cypher,BruteForce bruteForce,StatisticalAnalyzer statisticalAnalyzer) {
        printMenu();
        try(Scanner keyboard=new Scanner(System.in)){
            outer : while(true){
                int ch=keyboard.nextInt();
                keyboard.nextLine();
                switch(ch){
                    case 1->{
                        option1_encrypt(cypher,keyboard);
                        printMenu();
                    }
                    case 2->{
                        option2_decrypt(cypher,keyboard);
                        printMenu();
                    }
                    case 3->{
                        option3_decryptByBruteForce(cypher,bruteForce);
                    }
                    case 4->{
                        option4_statisticallyAnalyze(cypher,statisticalAnalyzer);
                    }
                    case 5->{
                        option5_changeAlphabet(cypher);
                    }
                    case 6->{
                        option6_exit();
                        break outer;
                    }
                    default ->{
                        option_default();
                    }
                }

            }
        }

    }

    private static void option_default() {
        System.out.println("You entered a wrong character, please try again");
    }

    private static void option6_exit() {
        final String exit="You left the program, thank you\n".toUpperCase();
        System.out.println(exit);
    }

    private static void option5_changeAlphabet(Cypher cypher) {

    }

    private static void option4_statisticallyAnalyze(Cypher cypher,StatisticalAnalyzer statisticalAnalyzer) {
    }

    private static void option3_decryptByBruteForce(Cypher cypher,BruteForce bruteForce) {
    }

    private static void option2_decrypt(Cypher cypher, Scanner keyboard) {

        String pathOfFileToDecrypt,decryptedFile;
        try{
            outer : while(true){
                //C:\Users\gs\Documents\TP INF464
                System.out.println("Enter path of the file to decrypt  or 'exit' to leave the program: ");
                pathOfFileToDecrypt=keyboard.nextLine();
                if(pathOfFileToDecrypt.equals("exit")){
                    break;
                }
                if(Files.isRegularFile(Path.of(pathOfFileToDecrypt)) && Files.exists(Path.of(pathOfFileToDecrypt))){
                    System.out.println("Enter the key :");
                    int key;
                    if(keyboard.hasNextInt()){
                        key=keyboard.nextInt();
                        keyboard.nextLine();
                        decryptedFile= cypher.decryptFile(pathOfFileToDecrypt,key);
                        System.out.println("File was decrypted successfully");
                    }else{
                        System.out.println("The key you entered is not valid.");
                        keyboard.nextLine();
                    }
                }else{
                    System.out.println("The file doesn't exist");
                }
                System.out.println("Type 'exit' to leave.");
                String quit=keyboard.nextLine();
                if(quit.equals("exit")){
                    break;
                }
            }
        }catch(InputMismatchException ex){
            System.out.println("Please enter a digit instead!");
        }
    }

    private static void option1_encrypt(Cypher cypher,Scanner keyboard) {
        // encrypt a string or a file
        boolean stop=false;
        String pathOfFileToEncrypt,encryptedFile;
        try{
            outer : while(!stop){
                //C:\Users\gs\Documents\TP INF464
                System.out.println("Enter path of the file to encrypt  or 'exit' to leave the program: ");
                System.out.println("for example \"files\\\\mission.txt\" ");
                pathOfFileToEncrypt=keyboard.nextLine();
                if(pathOfFileToEncrypt.equals("exit")){
                    break;
                }
                if(Files.isRegularFile(Path.of(pathOfFileToEncrypt)) && Files.exists(Path.of(pathOfFileToEncrypt))){
                    System.out.println("Enter the key :");
                    int key;
                    if(keyboard.hasNextInt()){
                        key=keyboard.nextInt();
                        keyboard.nextLine();
                        encryptedFile= cypher.encryptFile(pathOfFileToEncrypt,key);
                        System.out.println("File was encrypted successfully");
                    }else{
                        System.out.println("The key you entered is not valid.");
                        keyboard.nextLine();
                    }
                }else{
                    System.out.println("The file doesn't exist");
                }
                System.out.println("Type 'exit' to leave.");
                String quit=keyboard.nextLine();
                if(quit.equals("exit")){
                   break;
                }
            }
        }catch(InputMismatchException ex){
            System.out.println("Please enter a digit instead!");
        }
    }

    private static void printMenu() {
        final String entry="THIS APP SIMULATES THE CAESAR CYPHER ALGORITHM\n";
        final String invite="Please type a character to make your choice\n".toUpperCase();
        final String option1="1) Encrypt a file".toUpperCase();
        final String option2="2) Decrypt an  encrypted file".toUpperCase();
        final String option3="3) Decrypt a file by brute force".toUpperCase();
        final String option4="4) Decrypt a file by statistical analysis".toUpperCase();
        final String option5="5) Change the alphabet".toUpperCase();
        final String option6="6) Exit the program".toUpperCase();
        System.out.print(entry + option1 +"\t\t" + option2 +"\t\t" + option3 +"\n" + option4 +"\t\t" + option5 +"\t\t" + option6 + "\n" +  invite) ;
    }
}
