import javax.crypto.*;
import java.security.*;
import java.math.BigInteger;

/**
 * public class STS
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * This class is the basis for the Station to Station Protocol. Here, two users are passed to the main function,
 * where it runs through the steps for STS.
 * Prime and Generator have been hard coded as per instruction of the Lecturer
 * "It would be fine if you use hard-coded prime and generator, but their length should be reasonably large, for example 512bits, 1024bits…"
 */
public class STS {

    //https://www.ietf.org/rfc/rfc3526.txt
    //2048 bit prime
    private static BigInteger prime = new BigInteger("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63" +
                "B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B" +
                "0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA" +
                "8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE" +
                "3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728" +
                "E5A8AACAA68FFFFFFFFFFFFFFFF",16);
    private static BigInteger generator = new BigInteger("2");
    static SecureRandom random;


    public STS(SecureRandom random) {
        this.random = random;
    }

    /**
     * Runs the authentication protocol between two users. Returns true if successful authentication
     * @param alice - User Alice used in authentication
     * @param bob - User Bob used in Authentication
     * @throws Exception - Exceptions can be thrown when using 3Des
     * @return - True if users are sucessfully authenticated. Throws exception if error along the way through encryption
     */
    public boolean authenticateTwoUsers(User alice, User bob) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        //1. Alice generates a random number x and computes and sends the exponential gx to Bob.
        alice.generateRandomValueAndExponential(generator, prime);

        //2. Bob generates a random number y and computes the exponential gy
        bob.receiveExponentialFromOtherPerson(alice.getGeneratorPowRandom());
        bob.generateRandomValueAndExponential(generator,prime);
        //3. Bob computes the shared secret key K = (gx)y.
        bob.computeSharedKeyK(prime);
        //4. Bob concatenates the exponentials (gy, gx) (order is important),
        // signs them using his asymmetric (private) key B, and then encrypts the signature with K.
        // He sends the ciphertext along with his own exponential gy to Alice.
        bob.concatenatesExponentialsHashesAndSigns();
        bob.generateSessionKeyAndStart3DES();
        bob.encryptSignedMessageUsingThreeDES();


        alice.receiveCipherTextAndGeneratorPowRandomFromOtherPerson(bob.getMyCipherTextToSendToOtherPerson(), bob.getGeneratorPowRandom());
        //5. Alice computes the shared secret key K = (gy)x.
        alice.computeSharedKeyK(prime);
        //6. Alice decrypts and verifies Bob's signature using his asymmetric public key.
        alice.generateSessionKeyAndStart3DES();
        alice.createOwnVersionOfOtherPersonsHashMessage();
        alice.decryptAndVerifyOtherPerson(bob);
        //Bob should now be verified
        //7. Alice concatenates the exponentials (gx, gy) (order is important),
        // signs them using her asymmetric (private) key A, and then encrypts the signature with K.
        // She sends the ciphertext to Bob.
        alice.concatenatesExponentialsHashesAndSigns();
        alice.encryptSignedMessageUsingThreeDES();


        //8. Bob decrypts and verifies Alice's signature using her asymmetric public key.
        bob.receiveCipherTextFromOtherPerson(alice.getMyCipherTextToSendToOtherPerson());
        bob.createOwnVersionOfOtherPersonsHashMessage();
        bob.decryptAndVerifyOtherPerson(alice);

        //Alice and Bob are now mutually authenticated and have a shared secret.
        // This secret, K, can then be used to encrypt further communication.

        return true; //Success!
    }
}
