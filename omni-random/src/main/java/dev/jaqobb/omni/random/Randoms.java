package dev.jaqobb.omni.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
  private Randoms() {}

  public static int randomInt(int min, int max) {
    return randomInt(ThreadLocalRandom.current(), min, max);
  }

  public static int randomInt(Random random, int min, int max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return random.nextInt(max - min + 1) + min;
  }
}
