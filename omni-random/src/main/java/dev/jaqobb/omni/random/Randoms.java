package dev.jaqobb.omni.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
  private Randoms() {}

  public static boolean randomBoolean() {
    return randomBoolean(ThreadLocalRandom.current());
  }

  public static boolean randomBoolean(Random random) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    return random.nextBoolean();
  }

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
    return random.nextInt(min, max + 1);
  }

  public static long randomLong(long min, long max) {
    return randomLong(ThreadLocalRandom.current(), min, max);
  }

  public static long randomLong(Random random, long min, long max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return random.nextLong(min, max + 1);
  }

  public static float randomFloat(float min, float max) {
    return randomFloat(ThreadLocalRandom.current(), min, max);
  }

  public static float randomFloat(Random random, float min, float max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return random.nextFloat(min, Math.nextUp(max));
  }

  public static double randomDouble(double min, double max) {
    return randomDouble(ThreadLocalRandom.current(), min, max);
  }

  public static double randomDouble(Random random, double min, double max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return random.nextDouble(min, Math.nextUp(max));
  }
}
