import javax.crypto.*;
import java.security.*;


/**
 * public class Program
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * Main entry point to the program
 */
public class Program {

    static SecureRandom random = new SecureRandom();
    static STS sts;
    static ThreeDESAndCounter threeDESAndCounter;



    public static void main(String[]args) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        //Rsa encryption and decryption works
        //SHA256 Hashing works
        //3DES Encryption and Decryption works
        //I have unit tests to make sure these all work

        RSA rsa = new RSA(random);

        User alice = new User("Alice", rsa, random);
        User bob = new User("Bob", rsa, random);

        sts = new STS(random);
        if(sts.authenticateTwoUsers(alice, bob)){
            //If we reach here, alice and bob are authenticated
            threeDESAndCounter = new ThreeDESAndCounter(alice.getSessionKey(),random);

            aliceSendsAMessageToBob();
        }
    }

    /**
     * Dummy method to show how alice would send a message to bob Triple DES and Counter Mode
     */
    private static void aliceSendsAMessageToBob() throws BadPaddingException, IllegalBlockSizeException {
        System.out.println("=======================================================================================");
        System.out.println("DEBUG:: Alice wants to send the following message to Bob:");

        String alicesMessage = "Hello Bob! I like SENG2250. It teaches me security. Thoughts? -A";
        System.out.println("DEBUG:: "+ alicesMessage);


        //Alice encrypts message using 3DES
        byte[] encryptedMessageFromAlice = threeDESAndCounter.encryptUsingCounterAnd3DES(alicesMessage.getBytes());

        //System.out.println("DEBUG:: Alice's encrypted message is junk at the moment: " + new String(encryptedMessageFromAlice));
        System.out.println("DEBUG:: Alice encrypts her message and sends it to Bob");

        //Alice sends encrypted message to bob
        bobRecievesEncryptedMessageFromAlice(encryptedMessageFromAlice);

    }

    /**
     * Dummy method to show how alice would send a message to bob Triple DES and Counter Mode
     */
    private static void bobRecievesEncryptedMessageFromAlice(byte[] encryptedMessageFromAlice) throws BadPaddingException, IllegalBlockSizeException {
        System.out.println("=======================================================================================");
        System.out.println("DEBUG:: Bob receives the encrypted message from Alice and decrypts message");

        byte[] decryptedMessage = threeDESAndCounter.decryptUsingCounterAnd3DES(encryptedMessageFromAlice);

        System.out.println("DEBUG:: Bob has decrypted the message. The message reads:");
        System.out.println("DEBUG:: " + new String(decryptedMessage));
        System.out.println("=======================================================================================");
        System.out.println("DEBUG:: Bob wants to send the following message to Alice:");
        String bobsMessage = "Hi Alice. I dont actually know you. Stop messaging me. Thanks -B";
        System.out.println("DEBUG:: " + bobsMessage);

        byte[] encryptedMessageFromBob = threeDESAndCounter.encryptUsingCounterAnd3DES(bobsMessage.getBytes());
        //System.out.println("DEBUG:: Bob's encrypted message is junk at the moment: " + new String(encryptedMessageFromBob));
        System.out.println("DEBUG:: Bob encrypts his message and sends it to Alice");

        bobSendsAMessageToAlice(encryptedMessageFromBob);
    }

    /**
     * Dummy method to show how alice would send a message to bob Triple DES and Counter Mode
     */
    private static void bobSendsAMessageToAlice(byte[] encryptedMessageFromBob) throws BadPaddingException, IllegalBlockSizeException {
        System.out.println("=======================================================================================");
        System.out.println("DEBUG:: Alice receives the encrypted message from Bob and decrypts message");

        byte[] decryptedMessage = threeDESAndCounter.decryptUsingCounterAnd3DES(encryptedMessageFromBob);

        System.out.println("DEBUG:: Alice has decrypted the message. The message reads:");
        System.out.println("DEBUG:: " + new String(decryptedMessage));

        //Alice is now very sad.
    }
}
