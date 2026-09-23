import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * public class ThreeDesAndCounter
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * ThreeDes (Triple Des) class used for encryption of signed values in STS and used for basis of communication between
 * two users once authenticated and a session key is established.
 *
 */
public class ThreeDESAndCounter {

    private SecretKey key;
    private Cipher cipher;
    private SecureRandom random;

    /**
     * Return the cipher associated with the class
     * @return - Cipher associated with the class
     */

    /**
     * @param sessionKeyString - String of session key - converted to byte array
     * @param random - Secure random in the event Nonce is added to the program
     * @throws Exception - Exceptions thrown for Triple Des
     */
    public ThreeDESAndCounter(String sessionKeyString, SecureRandom random) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] sessionKey = sessionKeyString.getBytes();
        key = new SecretKeySpec(sessionKey, "DESede");
        cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        this.random = random;
    }

    /**
     * Encrypts a message using the cipher for threeDes
     * @param message - Message to encrypt
     * @return - Encrypted message
     */
    private byte[] encrypt3DES(byte[] message) throws BadPaddingException, IllegalBlockSizeException {
        return cipher.doFinal(message);
    }

    /**
     * Encrypts message using three Des and Counter mode of operation
     * encrypts the counters and xors that with the plaintext to generate the ciphertext
     * @param message - message to be encrypted
     * @return - encrypted message
     */
    public byte[] encryptUsingCounterAnd3DES(byte[] message) throws BadPaddingException, IllegalBlockSizeException {

        //IMPORTANT NOTE -  Normally you would include a nonce value as well as the counter string,
        //The lectures use a simplified version, where IT IS ONLY THE COUNTER VALUES BEING ENCRYPTED

        int numberOfCounters = message.length / 8; //its meant to be a good number
        if(message.length % 8 != 0){ //but we will still check for padding issues
            numberOfCounters++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numberOfCounters; i++){
            sb.append(generateCounterString(i));
        }

        byte[] countersToByteArray = sb.toString().getBytes();

        byte[] encryptedCounters = encrypt3DES(countersToByteArray);
        byte[] cipherText = new byte[message.length];
        for(int i = 0; i < message.length;i++){
            cipherText[i] = XOR(message[i], encryptedCounters[i]);
        }

        return cipherText;
    }

    /**
     * Decrypts Ciphertext using counter and three des
     * calculates  Ciphertext of counters concatenated and XORs that with the Ciphertext
     * @param cipherText - Ciphertext to be decrypted
     * @return - decrypted message
     */
    public byte[] decryptUsingCounterAnd3DES(byte[] cipherText) throws BadPaddingException, IllegalBlockSizeException {
        //Similar process as encrypting, only difference is XOR with cipher text to get plaintext
        int numberOfCounters = cipherText.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numberOfCounters;i++){
            sb.append(generateCounterString(i));
        }

        byte[] countersToByteArray = sb.toString().getBytes();
        byte[] encryptedCounters = encrypt3DES(countersToByteArray);
        //Create plaintext array ready to be added with xor operations
        byte[] plainText = new byte[cipherText.length];

        for(int i = 0; i < cipherText.length;i++){
            plainText[i] = XOR(cipherText[i],encryptedCounters[i]);
        }

        return plainText;

    }

    /**
     * Generates a single counter string 64 bits longs
     * @param countValue - value that needs padding at the start (its not a binary)
     * @return - Counter string with 0's padded at front
     */
    private String generateCounterString(int countValue){
        StringBuilder output = new StringBuilder("" + countValue);

        while(output.length() < 8 ){ //64 bits - 8 byte chunks
            output.insert(0, "0");
        }

        return output.toString();
    }

    /**
     * XOR two bytes together
     * @param a - Byte A
     * @param b - Byte B
     * @return - XORed Byte
     */
    private byte XOR(byte a, byte b){
        return (byte) (a ^ b);
    }


    /**
     * "Unit Test" checking to see if three des encryption works
     * @param message - message to be encrypted
     */
//    public void testThreeDesEncryptAndDecrypt(String message) throws BadPaddingException, IllegalBlockSizeException {
//        byte[] messageToBytes = message.getBytes();
//
//        //ENCRYPT
//        byte[] cipherText = encryptUsingCounterAnd3DES(messageToBytes);
//        System.out.println(new String(cipherText));
//
//        //DECRYPT
//        byte[] decryptedMessage = decryptUsingCounterAnd3DES(cipherText);
//        System.out.println(new String(decryptedMessage));
//
//    }


}
