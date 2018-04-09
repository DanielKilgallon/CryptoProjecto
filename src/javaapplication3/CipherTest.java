package javaapplication3;

public class CipherTest {

    //this could help in breaking the shift cipher: http://practicalcryptography.com/cryptanalysis/stochastic-searching/cryptanalysis-caesar-cipher/

    public static void main(String[] a) {
        //String lMsg = "Hello. My name is Inigo Montoya. You killed my father. Prepare to die.";
        //String lMsg = "UUUMMMMTHISISATASTYBURGERMINDIFIHAVESOMEOFYOURTASTBEVERAGETOWASHTHISDOWNWITH";
        //String lMsg = "efgfoe uif fbtu xbmm pg uif dbtumf";
        //String lMsg = "fyyfhp fy ifbs";
        //String lMsg = "xxxppppwklvlvdwdvwbexujhuplqglilkdyhvrphr";
        //String code = "XXXPPPPWKLVLVDWDVWBEXUJHUPLQGLILKDYHVRPHRIBRXUWDVWEHYHUDJHWRZDVKWKLVGRZQZLWK";
        String code1 = "ZEWAFQDQWH";
        String code2 = "AUeCPKHBNW";
        String code3 = "ZBGCCISODF";
        String code4 = "CRZDPBUKIV";
        String code5 = "RPQRDLPKHW";

        /*System.out.println("English: " + lMsg);
        System.out.println("Cryptic: " + ShiftCipher.encode(lMsg,'D'));*/
        //System.out.println("Code: " + lMsg);
        //System.out.println("English: " + ShiftCipher.decode(lMsg, 'f'));
        System.out.println(ShiftCipher.decode(code1, 'S'));
        System.out.println(ShiftCipher.decode(code2, 'W'));
        System.out.println(ShiftCipher.decode(code3, 'O'));
        System.out.println(ShiftCipher.decode(code4, 'R'));
        System.out.println(ShiftCipher.decode(code5, 'D'));
        /*
        System.out.println("English: " + bMsg);
        System.out.println("Cryptic: " + ShiftCipher.encode(bMsg,'c'));

        */
        //testing the break method in the ShiftCipher class
        String cryptic = "SPWWZXJYLXPTDTYTRZXZYEZJLJZFVTWWPOXJQLESPCACPALCPEZOTP";
        String[] p = ShiftCipher.breakShift(cryptic);
        for (String s : p)
            System.out.println(s);


        //Atbash Testing
        String toEncodeAtbash = "This is an atbash test message."; // encoded answer: Gsrh rh zm zgyzhs gvhg nvhhztv
        String toDecodeAtbash = "Vgszm hglk ivzwrmt gsrh."; // decoded answer: Ethan stop reading this.
        //String toBreakAtbash = ""; // broken answer:

        System.out.println("Atbash encoding: " + AtbashCipher.encode(toEncodeAtbash));
        System.out.println("Atbash decoding: " + AtbashCipher.decode(toDecodeAtbash));
        //AtbashCipher.breakCode(toBreakAtbash);
    }
}
