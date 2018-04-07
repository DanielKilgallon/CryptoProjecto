package sample;

public class CipherTest {
    public static void main(String[] a) {
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

        //Atbash Testing
        String toEncodeAtbash = "This is an atbash test message."; // encoded answer: Gsrh rh zm zgyzhs gvhg nvhhztv
        String toDecodeAtbash = "Vgszm hglk ivzwrmt gsrh."; // decoded answer: Ethan stop reading this.
        //String toBreakAtbash = ""; // broken answer:

        System.out.println("Atbash encoding: " + AtbashCipher.encode(toEncodeAtbash));
        System.out.println("Atbash decoding: " + AtbashCipher.decode(toDecodeAtbash));
        //AtbashCipher.breakCode(toBreakAtbash);
    }
}
