import java.math.BigInteger;


/**
 * public class KeyPair
 * Jordan Haigh c33256730 - SENG2250A3
 *
 * This class is a package class containing the private and public keys as well as the n value generated
 * in the RSA class.
 * Used for initialising the user class
 */
public class KeyPair {
    BigInteger privateKey;
    BigInteger publicKey;
    BigInteger n;

    /**
     * Overloaded constructor for Keypair. Takes in privatekey, publickey and n values to be set
     */
    public KeyPair(BigInteger privateKey, BigInteger publicKey, BigInteger n) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.n = n;
    }

    /**
     * Get Private Key Value
     * @return - Private Key Value
     */
    public BigInteger getPrivateKey() {
        return privateKey;
    }

    /**
     * Get Public Key Value
     * @return - Public Key Value
     */
    public BigInteger getPublicKey() {
        return publicKey;
    }

    /**
     * Get N Value
     * @return - N Value
     */
    public BigInteger getN() { return n; }
}
