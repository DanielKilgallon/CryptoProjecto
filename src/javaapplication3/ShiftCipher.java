package javaapplication3;

/**
 * The ShiftCipher class will be used to encode, decode, and break messages using a basic shift cipher
 */
public class ShiftCipher
{
    /**
     * Encodes a string using a basic shift cipher.
     * @param msg The message to encode.
     * @param shift The alphabetic shift key.
     * @return The encoded message.
     */
    public static String encode(String msg, char shift)
    {
        char[] english = msg.toCharArray();
        char[] crypto = new char[msg.length()];
        char difference = shift>='a'?'a':'A';

        for(int i=0;i<msg.length();i++)
        {
            if(!Character.isLetter(english[i]))//if not a letter, directly copy the character
                crypto[i]=english[i];
            else //otherwise, shift --- subtract
            {
                //crypto[i] = english[i] > 'Z' ? (char) (english[i] + shift - difference) : (char) (english[i] + shift - difference);
                if(english[i] >= 'a')//if lower case
                {
                    if(english[i]+shift-difference>'z')//if the shift puts the character outside of the alphabet
                        crypto[i]= (char) (english[i]+shift-difference-26);
                    else//if the shift keeps the character inside the alphabet
                        crypto[i]= (char) (english[i]+shift-difference);
                }

                else//if uppercase
                {
                    if(english[i]+shift-difference>'Z')//if the shift puts the character outside of the alphabet (of capital letters)
                        crypto[i]= (char) (english[i]+shift-difference-26);
                    else//if the shift keeps the character inside the alphabet
                        crypto[i]= (char) (english[i]+shift-difference);
                }

            }
        }

        return new String(crypto);
    }//end encode method

    public static String decode(String msg, char shift){
        char[] crypto = msg.toCharArray();
        char[] english = new char[msg.length()];
        char difference = shift>='a'?'a':'A';

        for(int i=0;i<msg.length();i++)
        {
            if(!Character.isLetter(crypto[i]))//if not a letter, directly copy the character
                english[i]=crypto[i];
            else //otherwise, shift --- subtract
            {
                //crypto[i] = english[i] > 'Z' ? (char) (english[i] + shift - difference) : (char) (english[i] + shift - difference);
                if(crypto[i] >= 'a')//if lower case
                {
                    if(crypto[i]-shift+difference<'a')//if the shift puts the character outside of the alphabet
                        english[i]= (char) (crypto[i]-shift+difference+26);
                    else//if the shift keeps the character inside the alphabet
                        english[i]= (char) (crypto[i]-shift+difference);
                }

                else//if uppercase
                {
                    if(crypto[i]-shift+difference<'A')//if the shift puts the character outside of the alphabet (of capital letters)
                        english[i]= (char) (crypto[i]-shift+difference+26);
                    else//if the shift keeps the character inside the alphabet
                        english[i]= (char) (crypto[i]-shift+difference);
                }

            }
        }

        return new String(english);
    }
    
    /**
     * This method will return an array of Strings with 26 elements. One element
     * for each alphabetic shift.
     * @param s The string to decode
     * @return The array of Strings containing the possible broken code
     */
    public static String[] breakShift(String s)
    {
        String[] possible = new String[26];
        for(int i='a', j=0; i<='z'; i++, j++)
            possible[j]=decode(s,(char)i);
            
        return possible;
    }

}//end class
