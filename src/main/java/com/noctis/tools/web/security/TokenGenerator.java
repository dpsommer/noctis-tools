package com.noctis.tools.web.security;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class TokenGenerator {
  
  private static final SecureRandom RANDOM = new SecureRandom();

  public static String getNewToken() {
    return new BigInteger(130, RANDOM).toString(32);
  }
  
  public static byte[] getRandomBytes(int size) {
    byte[] rand = new byte[size];
    RANDOM.nextBytes(rand);
    return rand;
  }
  
}