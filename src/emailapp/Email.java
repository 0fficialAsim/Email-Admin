package emailapp;

import java.util.Random;
import java.util.Scanner;

public class Email {
    private static final int MAX = 3; 
    private static final int MIN = 1; 

    private String firstname;
    private String lastname;
    private String password;
    private String department;
    private String company;
    private int mailboxCapacity;
    private String alternateEmail;
    private String email; 
    private String companySuffix = "company.com"; 

    // Constructor to create email based of off credentials.

    public Email(String firstname, String lastname) {
        System.out.println("EMAIL CREATED: " + firstname.toUpperCase() + " " + lastname.toUpperCase());
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = getPassword(); 
        this.department = setDepartment(); 
        this.mailboxCapacity = 1500; 
        
        //combine elements to generate email 
        email = firstname.toLowerCase() + "." + lastname.toLowerCase() + "@" + department +"."+ companySuffix; 
    }

    // Ask for the deparment
    private String setDepartment(){
        System.out.println("Enter the Department Name: ");
        Scanner in =  new Scanner(System.in); 
        String response = in.nextLine(); 
        in.close();
        return response; 
    }
    
    // Generate a random password
    private String getPassword(){ 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your Password will be randomly generated");
        System.out.println("How long would you like your password to be?");

        int lengthOfPassword;
        // Error Proofing...Looping user input until they enter a number greater then 8
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Please enter a number. Password must be 8+ characters long");
        }
        lengthOfPassword = scanner.nextInt();
        return createPassword(lengthOfPassword); 
         
    }

    private String createPassword(int lengthOfPassword) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] letters = alphabet.toCharArray();
        int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        String[] specialCharacters = { "@", "%", "+", "\\", "/", "'", "!", "#", "$", "^", "?", ":", ",", "(", ")", "[",
                "]", "{", "}", "~", "-", "_", "." };
       
        String password = ""; 
        int count = 0;
        while (count <= lengthOfPassword) {
            int random = (int) (Math.random() *  (MAX - MIN + 1) + MIN);
            if (random == 1) {
                // Getting random letter through char array
                Random randomChar = new Random();
                int c = randomChar.nextInt(25);
                char character = letters[c];

                // Randomizing capital or lowercase letters
                int loC = (int) (Math.random() * (2 - 1 + 1) + 1);

                if (loC == 2) {
                    password = password + character;
                } else if (loC == 1) {
                    password = password + Character.toString(character).toUpperCase();

                }
                count++;

            } else if (random == 2) {
                // Getting random out of num array
                Random randomNum = new Random();
                int n = randomNum.nextInt(9);
                int num = numbers[n];

                password = password + num;

                count++;
            } else if (random == 3) {
                Random randomSpecialChar = new Random();
                int sC = randomSpecialChar.nextInt(22);
                String spChar = specialCharacters[sC];

                password = password + spChar;

                count++;
            }
        }

        return password; 
    }

    // Set mailbox capacity
    private void  setMailCapacity(int capacity) {
        this.mailboxCapacity = capacity;       
    }

    // Set the alternate email address
    public void setAltEmail(String altMail) {
        this.alternateEmail = altMail; 
    }


    public void changePassword(String password){
        this.password = password; 
    }



    public String getAltEmail(){return alternateEmail;}
    public int getMailboxCapacity(){return mailboxCapacity; }
    public String getCurrentPassword(){return password; }

    public String ShowInfo(){
        return "DISPLAY NAME: " + firstname + " " + lastname  +
                "\nCOMPANY EMAIL: " + email + 
                "\nPASSWORD: "  + password + 
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb"; 
    }

}
