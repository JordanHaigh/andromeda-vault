import javax.crypto.*;
import java.security.*;
import java.math.BigInteger;

/**
 * public class User
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * User class contains all information about a user and what attributes they have.
 * This is great when considering information hiding between two users.
 * Only select private variables have getters (to simulate that they are public knowledge)
 *
 */
public class User {

    private String name;
    private SecureRandom random;
    private RSA rsa;

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger n;

    private BigInteger randomValue;
    private BigInteger generatorPowRandom;
    private BigInteger sharedSecretKey;

    private BigInteger otherPersonsGeneratorPowRandom;
    private byte[] cipherTextFromOtherPerson;

    private BigInteger messagePersonIsExpectingFromOtherPerson;

    private ThreeDESAndCounter threeDESAndCounter;

    private byte[] signedText;
    private byte[] myCipherTextToSendToOtherPerson;

    private String sessionKey;

    /**
     * Constructor for User
     * @param name - Name to be assigned
     * @param rsa - RSA to be assigned
     * @param random - random to be assigned
     */
    public User(String name, RSA rsa, SecureRandom random) {
        this.name = name;
        this.rsa = rsa;
        KeyPair pair = rsa.generateAsymmetricKeys();

        this.privateKey = pair.getPrivateKey();
        this.publicKey = pair.getPublicKey();
        this.n = pair.getN();

        this.random = random;
    }

    /**
     * Gets the name of user
     * @return - Name
     */
    public String getName() {return name; }

    /**
     * Get the public key of user
     * @return - public key
     */
    public BigInteger getPublicKey() { return publicKey; }

    /**
     * Get generator^random of user (once made public)
     * @return - generator^random
     */
    public BigInteger getGeneratorPowRandom() { return generatorPowRandom; }

    /**
     * Get N value associated with user and their RSA key generation
     * @return - N value
     */
    public BigInteger getN() { return n; }

    /**
     * Get the Ciphertext that is going to be sent to the other person
     * @return - Ciphertext to be sent to the other person
     */
    public byte[] getMyCipherTextToSendToOtherPerson() { return myCipherTextToSendToOtherPerson; }

    /**
     * Gets the session key associated for a user
     * @return - Session key
     */
    public String getSessionKey() {return sessionKey; }


    /**
     * Concatenates two byte arrays together
     * @param a - Array A
     * @param b - Array B
     * @return - Concatenated Byte Array in format A||B
     */
    private byte[] concatenateByteArrays(byte[] a, byte[] b){
        byte[] outputByteArray = new byte[a.length + b.length];
        System.arraycopy(a, 0, outputByteArray, 0, a.length);
        System.arraycopy(b, 0, outputByteArray, a.length, b.length);
        return outputByteArray;
    }

    /**
     * Hashing function for SHA-256
     * @param input - String to be hashed
     * @return - Hashed String
     */
    public String SHA256(String input) {
        //Modified from https://stackoverflow.com/a/11009612
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    /**
     * Generates a random value x and generates g^x
     * @param generator - Generator from STS
     * @param prime - Prime from STS
     */
    public void generateRandomValueAndExponential(BigInteger generator, BigInteger prime){

        //Generates a random number x and computes exponential gx to send to other person.
        BigInteger x = new BigInteger(""+random.nextInt());
        BigInteger gx = generator.modPow(x, prime);
        this.randomValue = x;
        this.generatorPowRandom = gx;

    }

    /**
     * Receives g^x from other person and sets that in their private variables
     *  @param exponential - Exponential from other perosn
     */
    public void receiveExponentialFromOtherPerson(BigInteger exponential){
        this.otherPersonsGeneratorPowRandom = exponential;
    }

    /**
     * Creates a shared key using the other persons g^x and your random value
     * @param prime - Prime from STS
     */
    public void computeSharedKeyK(BigInteger prime){
        sharedSecretKey = otherPersonsGeneratorPowRandom.modPow(randomValue, prime);
    }

    /**
     * Creates concatenation of your generator^random || generator^otherPersonRandom
     * Hashes this and encrypts it
     */
    public void concatenatesExponentialsHashesAndSigns(){
        //Person concatenates the exponentials (my exponential, other persons exponential) (order is important),

        byte[] exponentialsConcatenatedByteArray = concatenateByteArrays(generatorPowRandom.toByteArray(), otherPersonsGeneratorPowRandom.toByteArray());

        String hashedExponentials = SHA256(new String(exponentialsConcatenatedByteArray));

        // signs them using their asymmetric (private) key B,
        //"Signing isn't just the message itself, it just a hash that's encrypted with your private key so
        // someone can confirm that you made the hash."

        signedText = rsa.encrypt(hashedExponentials.getBytes(), privateKey, n);
    }


    /**
     * Generates the session key by creating a hash and substring-ing this to 24 characters (192 bits for 3DES)
     * Creates 3DES instance for user
     * @throws Exception - Throws exceptions if something is wrong with the 3DES encryption
     */
    public void generateSessionKeyAndStart3DES() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        sessionKey = SHA256(new String(sharedSecretKey.toByteArray())).substring(0,24);
        threeDESAndCounter = new ThreeDESAndCounter(sessionKey, random);

        //Trim session key down to 192 bit or 24 bytes
    }

    /**
     * Sets the users ciphertext to send to another person
     * @throws Exception - Exceptions if something is wrong with encryption with 3DES
     */
    public void encryptSignedMessageUsingThreeDES() throws BadPaddingException, IllegalBlockSizeException {
        myCipherTextToSendToOtherPerson = threeDESAndCounter.encryptUsingCounterAnd3DES(signedText);

    }

    /**
     * User receives Ciphertext from the other person and their generator^random value. Sets these values
     * @param cipherTextFromOtherPerson - Cipher text from other person
     * @param otherPersonsGeneratorPowRandom - Generator^random from other person
     */
    public void receiveCipherTextAndGeneratorPowRandomFromOtherPerson(byte[] cipherTextFromOtherPerson, BigInteger otherPersonsGeneratorPowRandom){
        receiveCipherTextFromOtherPerson(cipherTextFromOtherPerson);
        receiveExponentialFromOtherPerson(otherPersonsGeneratorPowRandom);
    }

    /**
     * Sets the Ciphertext from other person variable in user class
     * @param cipherTextFromOtherPerson - Cipher text from other person
     */
    public void receiveCipherTextFromOtherPerson(byte[] cipherTextFromOtherPerson){
        this.cipherTextFromOtherPerson = cipherTextFromOtherPerson;
    }

    /**
     * Using a users own g^x and other users g^x, we create the message that we are expecting from the other used
     * This helps prevent MITM attacks as we will know that the message DID come from the other user
     */
    public void createOwnVersionOfOtherPersonsHashMessage(){
        byte[] expectedMessageFromOtherPerson = concatenateByteArrays(otherPersonsGeneratorPowRandom.toByteArray(),generatorPowRandom.toByteArray());
        byte[] hashedExpectedMessageFromOtherPerson = SHA256(new String(expectedMessageFromOtherPerson)).getBytes();
        messagePersonIsExpectingFromOtherPerson = new BigInteger(hashedExpectedMessageFromOtherPerson);

    }

    /**
     * Final step in verifying person.
     * Decrypts the ciphertext using the threedesandcounter decryption
     * Further decrypts with the other persons rsa
     * @param otherPerson - Other user to verify
     */
    public void decryptAndVerifyOtherPerson(User otherPerson) throws BadPaddingException, IllegalBlockSizeException {
        byte[] signedTextFromOtherPerson = threeDESAndCounter.decryptUsingCounterAnd3DES(cipherTextFromOtherPerson);
        byte[] decryptedSignedTextFromOtherPerson = rsa.decrypt(signedTextFromOtherPerson, otherPerson.getPublicKey(), otherPerson.getN());

        BigInteger supposedMessageFromOtherPerson= new BigInteger(decryptedSignedTextFromOtherPerson);

        if(messagePersonIsExpectingFromOtherPerson.compareTo(supposedMessageFromOtherPerson) != 0){
            throw new RuntimeException(otherPerson.getName() + "'s signature was not verified.");
        }
        System.out.println(otherPerson.getName()+"'s signature was verified!");
    }

//    public void testSHA256(){ //it works
//        String input = "test";
//
//        String shaOutput = SHA256(input);
//        System.out.println(new String(shaOutput));
//
//
//    }

}
