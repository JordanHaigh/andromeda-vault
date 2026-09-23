import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * public class RSA
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * This class creates an asymmetric key pair (and N value) using the RSA key generation operation
 */
public class RSA {

    private SecureRandom random;
    private BigInteger n;

   public RSA(SecureRandom random){
       this.random = random;
   }

    /**
     * Starting function to generate asymmetric keys
     * Creates two big integer primes p and q and multiplies them together
     * Note p and q are private. N = p*q which is public
     * Calculates lambdaN which is the lcm of p-1 and q-1
     * Finds e value within the restrictions that e and lambdaN are coprime and 1<e<lambdaN
     * Calculates d which is mod inverse.
     * @return Keypair containing public, private keys and N value
     */
    public KeyPair generateAsymmetricKeys(){
        //https://en.wikipedia.org/wiki/RSA_(cryptosystem)

        //Choose two distinct prime numbers p and q. For security purposes, the integers p and q should be chosen at random,
        // and should be similar in magnitude but differ in length by a few digits to make factoring harder.
        BigInteger p = generateBigIntegerPrime();
        BigInteger q = generateBigIntegerPrime();

        //Compute n = pq.
        //n is used as the modulus for both the public and private keys. Its length, usually expressed in bits, is the key length.
        n = p.multiply(q);

        //Compute lambda(n) = lcm(lambda(p), lambda(q)) = lcm(p − 1, q − 1)
        BigInteger lambdaN = lcmForBigIntegers(p.subtract(BigInteger.ONE),q.subtract(BigInteger.ONE));

        //Choose an integer e such that 1 < e < lambda(n) and gcd(e, lambda(n)) = 1; i.e., e and lambda(n) are coprime.
        BigInteger e = generateRandomBigInteger();

        while (!eIsWithinOneAndLambdaN(e, lambdaN) || !e.gcd(lambdaN).equals(BigInteger.ONE)) {
            //If e is not prime or it is not within 1 and lambda n keep looping;
            e = generateRandomBigInteger();
        }

        //Right. Now, e is a prime and is greater than 1 and less than lambdaN

        //Determine d as d ≡ e^−1 (mod lambda(n)); i.e., d is the modular multiplicative inverse of e modulo lamda(n).
        //This means: solve for d the equation d⋅e = 1 (mod lambda(n)).
        BigInteger d = e.modInverse(lambdaN);

        //WE HAVE PUBLIC AND PRIVATE KEYS!!!!!!!!!!!!!!!!

        return new KeyPair(d,e,n);
    }

    /**
     * Calculates the least common multiple for two big integers
     * @param a - Big Integer A
     * @param b - Big Integer B
     * @return - Least common multiple of A and B
     */
    private BigInteger lcmForBigIntegers(BigInteger a, BigInteger b){
        //https://en.wikipedia.org/wiki/Least_common_multiple#Computing_the_least_common_multiple
        BigInteger result = a.multiply(b);
        result = result.divide(a.gcd(b));
        return result;
    }

    /**
     * Generates a 1024 bit Big integer prime
     * @return - 1024 bit Big Integer prime
     */
    private BigInteger generateBigIntegerPrime(){
        BigInteger p;
        SecureRandom rnd1=new SecureRandom();
        p=BigInteger.probablePrime(1024, rnd1);
        return p;
    }

    /**
     * Generates a random big integer that is 1024 bit
     * @return - Random big integer 1024 bit
     */
    private BigInteger generateRandomBigInteger(){
        SecureRandom rnd = new SecureRandom();
        return new BigInteger(1024, rnd);
    }

    /**
     * Boolean check to determine is input parameter e is within 1 and lambda N
     * @param e - Value to check
     * @param lambdaN - Upper bound
     * @return - True or false if it is within range
     */
    private boolean eIsWithinOneAndLambdaN(BigInteger e, BigInteger lambdaN){
       return e.compareTo(BigInteger.ONE) > 0 && e.compareTo(lambdaN) < 0;
    }

    /**
     * Encrypts a message using the other persons public key and n value
     * @param message - Message to encrypt
     * @param otherPersonsPublicKey - Other persons public key to encrypt
     * @param otherPersonsNValue - Other persons n value to encrypt
     * @return - Encrypted byte array of the message
     */
    public byte[] encrypt(byte[] message, BigInteger otherPersonsPublicKey, BigInteger otherPersonsNValue){
        BigInteger messageToBigInt = new BigInteger(message);
        BigInteger encryptedMessage = messageToBigInt.modPow(otherPersonsPublicKey, otherPersonsNValue);
        return encryptedMessage.toByteArray();
    }

    /**
     * Decrypts a message using your private key and your N value
     * @param encrypted - encrypted message to decrypt
     * @param yourPrivateKey - your private key used to decrypt the message
     * @param yourNValue - your n value to decrypt the message
     * @return - Decrypted byte array message
     */
    public byte[] decrypt(byte[] encrypted, BigInteger yourPrivateKey, BigInteger yourNValue){
        BigInteger encryptedToByteArray = new BigInteger(encrypted);
        BigInteger decryptedMessage = encryptedToByteArray.modPow(yourPrivateKey, yourNValue);
        return decryptedMessage.toByteArray();
    }

    /**
     * "Unit Test" for testing encryption and decryption
     */
//    public void testEncryptAndDecrypt(){
//        RSA rsa = new RSA(random);
//
//        User alice = new User("alice", rsa, random);
//        User bob = new User("bob", rsa, random);
//
//        String message = "Hello World";
//        byte[] messageToBytes = message.getBytes();
//        byte[] encrypted = encrypt(messageToBytes, alice.getPrivateKey(), alice.getN());
//        byte[] decrypted = decrypt(encrypted, alice.getPublicKey(), alice.getN());
//        System.out.println(new String(decrypted));
//    }
}
