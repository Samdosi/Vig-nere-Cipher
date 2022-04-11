/*=============================================================================
| Assignment: pa01 - Encrypting a plaintext file using the Vigenere cipher
|
| Author: Mohammad Ali Dosi
| Language: Java
|
| To Compile: javac pa01.java
| gcc -o pa01 pa01.c
| g++ -o pa01 pa01.cpp
|
| To Execute: java -> java pa01 kX.txt pX.txt
| or c++ -> ./pa01 kX.txt pX.txt
| or c -> ./pa01 kX.txt pX.txt
| where kX.txt is the keytext file
| and pX.txt is plaintext file
|
| Note: All input files are simple 8 bit ASCII input
|
| Class: CIS3360 - Security in Computing - Spring 2022
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class pa01
{
    private String key ;
    private String plain_text ;
    public pa01()
    {
        key = new String();
        plain_text = new String();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        // making pa01 object type
        pa01 obj= new pa01();
        // reading the key file
        File file_k= new File(args[0]);
        // reading the text file
        File file_T= new File(args[1]);
        Scanner scan = new Scanner(file_k);
        // store the text in a key string
        while(scan.hasNextLine()) {
            String line= scan.nextLine();
            obj.key= obj.key + line ;

        }
        scan = new Scanner(file_T);
        int i=0;
        // store the text in a plain text string
        while(scan.hasNextLine()) {
            String line2= scan.nextLine();
            obj.plain_text = obj.plain_text + line2;
        }
        System.out.println();
        System.out.println();
        // removing the space from the text file
        obj.key = obj.key.replaceAll("\\s+", "");
        // removing all the characters that are not letters
        if(!obj.key.matches("[a-zA-Z]"))
            obj.key= obj.key.replaceAll("[^a-zA-Z]","");
        // make everything a lower case
        obj.key = obj.key.toLowerCase(Locale.ROOT);
        Character [] key_char = new Character[512];
        Character K;
        // storing everything inside the char array
        for(i=0; i< obj.key.length(); i++){
            // make the character lower case
            K = Character.toLowerCase(obj.key.charAt(i));
            // check to see if the character is the letter
            if( K >= 'a' &&  K<= 'z')
                key_char[i] = K;
        }
        System.out.println("Vigenere Key:");
        for(i=0; i<512; i++) {
            // print 80 letters per line
            if( i % 80 == 0){
                System.out.println();
            }
            // if there are no key letters to print break
            if( key_char[i] == null) {
                break;
            }
            // print the key letters
            System.out.print(key_char[i]);
        }
        // making a char array to store the plain text
        Character [] plain_text_char = new Character[512];
        // remove the space
        obj.plain_text = obj.plain_text.replaceAll("\\s+", "");
        // remove anything that is not a character
        if(!obj.plain_text.matches("[a-zA-Z]"))
            obj.plain_text= obj.plain_text.replaceAll("[^a-zA-Z]","");
        Character c;
        for( i=0; i<obj.plain_text.length(); i++)
        {
            // make the character lower case
            c= Character.toLowerCase(obj.plain_text.charAt(i));
            // make sure it is a character
            if( c>= 'a' &&  c<= 'z')
                plain_text_char[i]= c;
            // break if we go out of bounds
            if(i == 511)
                break;
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Plaintext:");
        for(int j=0; j<512; j++)
        {
            if( j % 80 == 0){
                System.out.println();
            }
            // if there is no more character replace it with 'x'
            if( plain_text_char[j] == null) {
                plain_text_char[j] = 'x';
            }
            // print the characters
            if( plain_text_char[j] != null)
                System.out.print(plain_text_char[j]);

        }
        // create a new array of int to store the ascii value of the letters
        int [] Cipher_Text = new int[512];
        int k=0;
        while(k != 512)
        {
            for(i=0; i< obj.key.length(); i++)
            {
                // calculate the cipher text and store them into the array
                Cipher_Text[k] = ( plain_text_char[k]-97 +  obj.key.charAt(i)-97) % 26;
                k++;
                // break if it equals to 512
                if(k==512) break;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Ciphertext:");
        for(int j=0; j<512; j++)
        {
            if( j % 80 == 0)
            {
                System.out.println();
            }
            // print out the cipher text
            System.out.printf("%c", Cipher_Text[j]+97);
        }
        System.out.println();
        // close the file stream
        scan.close();
    }
}
/*=============================================================================
| I [Mohammad Ali Dosi] ([mo346779]) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/