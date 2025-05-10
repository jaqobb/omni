package dev.jaqobb.omni.random;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
  private Randoms() {}

  public static byte randomByte(byte min, byte max) {
    return randomByte(ThreadLocalRandom.current(), min, max);
  }

  public static byte randomByte(Random random, byte min, byte max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return (byte) random.nextInt(min, max + 1);
  }

  public static short randomShort(short min, short max) {
    return randomShort(ThreadLocalRandom.current(), min, max);
  }

  public static short randomShort(Random random, short min, short max) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (min > max) {
      throw new IllegalArgumentException("min must be less than or equal to max");
    }
    if (min == max) {
      return min;
    }
    return (short) random.nextInt(min, max + 1);
  }

  public static int randomInteger(int min, int max) {
    return randomInteger(ThreadLocalRandom.current(), min, max);
  }

  public static int randomInteger(Random random, int min, int max) {
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

  public static boolean randomBoolean() {
    return randomBoolean(ThreadLocalRandom.current());
  }

  public static boolean randomBoolean(Random random) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    return random.nextBoolean();
  }

  public static <T> T randomElement(T[] array) {
    return randomElement(ThreadLocalRandom.current(), array);
  }

  public static <T> T randomElement(Random random, T[] array) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (array == null) {
      throw new IllegalArgumentException("array cannot be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("array cannot be empty");
    }
    if (array.length == 1) {
      return array[0];
    }
    return array[random.nextInt(array.length)];
  }

  public static <T> T randomElement(List<T> list) {
    return randomElement(ThreadLocalRandom.current(), list);
  }

  public static <T> T randomElement(Random random, List<T> list) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (list == null) {
      throw new IllegalArgumentException("list cannot be null");
    }
    if (list.isEmpty()) {
      throw new IllegalArgumentException("list cannot be empty");
    }
    if (list.size() == 1) {
      return list.get(0);
    }
    return list.get(random.nextInt(list.size()));
  }

  public static <T> T randomElement(Collection<T> collection) {
    return randomElement(ThreadLocalRandom.current(), collection);
  }

  public static <T> T randomElement(Random random, Collection<T> collection) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (collection == null) {
      throw new IllegalArgumentException("collection cannot be null");
    }
    if (collection.isEmpty()) {
      throw new IllegalArgumentException("collection cannot be empty");
    }
    if (collection.size() == 1) {
      return collection.iterator().next();
    }
    int chosenIndex = random.nextInt(collection.size());
    if (collection instanceof List) {
      return ((List<T>) collection).get(chosenIndex);
    }
    Iterator<T> iterator = collection.iterator();
    for (int currentIndex = 0; currentIndex < chosenIndex; currentIndex += 1) {
      iterator.next();
    }
    return iterator.next();
  }

  public static <T extends WeightedRandomChoice> T randomWeightedChoice(
      Iterable<? extends T> choices) {
    return randomWeightedChoice(ThreadLocalRandom.current(), choices);
  }

  public static <T extends WeightedRandomChoice> T randomWeightedChoice(Random random,
      Iterable<? extends T> choices) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (choices == null) {
      throw new IllegalArgumentException("choices cannot be null");
    }
    Iterator<? extends T> iterator = choices.iterator();
    if (!iterator.hasNext()) {
      throw new IllegalArgumentException("choices cannot be empty");
    }
    double totalWeight = totalWeight(choices);
    double randomWeight = randomDouble(random, 0.0D, totalWeight);
    while (iterator.hasNext()) {
      T choice = iterator.next();
      if (choice == null) {
        throw new IllegalArgumentException("choices cannot contain null elements");
      }
      randomWeight -= choice.getWeight();
      if (randomWeight <= 0.0D) {
        return choice;
      }
    }
    // This should never happen.
    return null;
  }

  public static double totalWeight(Iterable<? extends WeightedRandomChoice> choices) {
    if (choices == null) {
      throw new IllegalArgumentException("choices cannot be null");
    }
    double totalWeight = 0.0D;
    for (WeightedRandomChoice choice : choices) {
      if (choice == null) {
        throw new IllegalArgumentException("choices cannot contain null elements");
      }
      totalWeight += choice.getWeight();
    }
    return totalWeight;
  }

  public static boolean chance1(double chance) {
    return chance1(ThreadLocalRandom.current(), chance);
  }

  public static boolean chance1(Random random, double chance) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (chance <= 0.0D) {
      return false;
    }
    if (chance >= 1.0D) {
      return true;
    }
    return chance >= randomChance1(random);
  }

  public static double randomChance1() {
    return randomChance1(ThreadLocalRandom.current());
  }

  public static double randomChance1(Random random) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    return randomDouble(random, 0.0D, 1.0D);
  }

  public static boolean chance100(double chance) {
    return chance100(ThreadLocalRandom.current(), chance);
  }

  public static boolean chance100(Random random, double chance) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    if (chance <= 0.0D) {
      return false;
    }
    if (chance >= 100.0D) {
      return true;
    }
    return chance >= randomChance100(random);
  }

  public static double randomChance100() {
    return randomChance100(ThreadLocalRandom.current());
  }

  public static double randomChance100(Random random) {
    if (random == null) {
      throw new IllegalArgumentException("random cannot be null");
    }
    return randomDouble(random, 0.0D, 100.0D);
  }
}
