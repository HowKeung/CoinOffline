package com.jungel.coinoffline.eth;

import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.MnemonicUtils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import static org.web3j.crypto.Hash.sha256;

public class AndroidWalletUtils extends org.web3j.crypto.WalletUtils {

    private static final SecureRandom secureRandom = SecureRandomUtils.secureRandom();
    /**
     * Generates a BIP-39 compatible Ethereum wallet. The private key for the wallet can
     * be calculated using following algorithm:
     * <pre>
     *     Key = SHA-256(BIP_39_SEED(mnemonic, password))
     * </pre>
     *
     * @param password             Will be used for both wallet encryption and passphrase for BIP-39 seed
     * @param destinationDirectory The directory containing the wallet
     * @return A BIP-39 compatible Ethereum wallet
     * @throws CipherException if the underlying cipher is not available
     * @throws IOException     if the destination cannot be written to
     */
    public static Bip39Wallet generateBip39Wallet(String password, File destinationDirectory)
            throws CipherException, IOException {
        byte[] initialEntropy = new byte[16];
        secureRandom.nextBytes(initialEntropy);

        String mnemonic = AndroidMnemonicUtils.generateMnemonic(initialEntropy);
        byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
        ECKeyPair privateKey = ECKeyPair.create(sha256(seed));

        String walletFile = generateWalletFile(password, privateKey, destinationDirectory, false);

        return new Bip39Wallet(walletFile, mnemonic);
    }
}
