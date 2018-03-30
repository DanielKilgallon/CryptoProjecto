package sample;

public class ShiftTest
{
    public static void main(String[] a)
    {
        //String lMsg = "Hello. My name is Inigo Montoya. You killed my father. Prepare to die.";
        String lMsg = "UUUMMMMTHISISATASTYBURGERMINDIFIHAVESOMEOFYOURTASTBEVERAGETOWASHTHISDOWNWITH";
        String code = "XXXPPPPWKLVLVDWDVWBEXUJHUPLQGLILKDYHVRPHRIBRXUWDVWEHYHUDJHWRZDVKWKLVGRZQZLWK";

        /*System.out.println("English: " + lMsg);
        System.out.println("Cryptic: " + ShiftCipher.encode(lMsg,'D'));*/
        System.out.println("Code: " + code);
        System.out.println("English: " + ShiftCipher.decode(code, 'D'));

/*
        System.out.println("English: " + bMsg);
        System.out.println("Cryptic: " + ShiftCipher.encode(bMsg,'c'));
*/
    }
}
