package com.appiron.appleservices.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author hpg
 */
public class ByPasscodeUtil {


    private static final int MC_BYPASS_CODE_BUFFER_LENGTH = 31; // Including terminating null.

    private static final char[] K_SYMBOLS = "0123456789ACDEFGHJKLMNPQRTUVWXYZ".toCharArray();


    private ByPasscodeUtil() {
    }

    public static String createEscrowKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return PBKDF2Util.createActiveCode(password.toCharArray());
    }

    public static String generateRandomPassword(int len) {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String createCode(String password) {

        int[] kDashPositions = {5, 10, 14, 18, 22};
        int outputCharacterCount = 0;
        byte[] inputCursor = password.getBytes(StandardCharsets.UTF_8);
        char[] outputCursor = new char[MC_BYPASS_CODE_BUFFER_LENGTH];

        // Generate output one symbol at a time.
        final int inputBits = 128;
        final int bitsPerByte = 8;
        final int bitsPerSymbol = 5;

        int bitsProcessed = 0;
        int bitOffsetIntoByte = 0;

        int inputCursorIndex = 0;
        int outputCursorIndex = 0;
        int nextDashPositionIndex = 0;

        while (bitsProcessed <= (inputBits - bitsPerSymbol)) {

            int bitsThisByte = (bitOffsetIntoByte < bitsPerByte - bitsPerSymbol ? bitsPerSymbol : bitsPerByte - bitOffsetIntoByte);
            int bitsNextByte = (bitsThisByte < bitsPerSymbol ? bitsPerSymbol - bitsThisByte : 0);

            int value = (((inputCursor[inputCursorIndex] << bitOffsetIntoByte) & 0xff) >> (bitsPerByte - bitsThisByte));

            bitOffsetIntoByte += bitsPerSymbol;

            if (bitOffsetIntoByte >= bitsPerByte) {
                bitOffsetIntoByte -= bitsPerByte;
                inputCursorIndex++;
            }

            if (bitsNextByte != 0) {
                value <<= bitsNextByte;
                value |= (inputCursor[inputCursorIndex] >> (bitsPerByte - bitsNextByte));
            }

            outputCursor[outputCursorIndex++] = K_SYMBOLS[value];
            ++outputCharacterCount;
            if (nextDashPositionIndex < kDashPositions.length && outputCharacterCount == kDashPositions[nextDashPositionIndex]) {
                nextDashPositionIndex++;
                outputCursor[outputCursorIndex++] = '-';
            }

            bitsProcessed += bitsPerSymbol;
        }

        // Process remaining bits.
        int bitsRemaining = inputBits - bitsProcessed;
        if (bitsRemaining != 0) {
            int value = (((inputCursor[inputCursorIndex] << bitOffsetIntoByte) & 0xff) >> (bitsPerByte - bitsRemaining));
            outputCursor[outputCursorIndex] = K_SYMBOLS[value];
        }
        return new String(outputCursor);
    }

}
