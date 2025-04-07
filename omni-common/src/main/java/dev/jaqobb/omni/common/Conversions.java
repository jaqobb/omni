package dev.jaqobb.omni.common;

import java.util.function.Function;

public final class Conversions {
  private Conversions() {}

  public static byte toByte(String string, byte defaultValue) {
    Byte value = toByte(string);
    return value != null ? value : defaultValue;
  }

  public static Byte toByte(String string) {
    return to(string, Byte::valueOf);
  }

  public static short toShort(String string, short defaultValue) {
    Short value = toShort(string);
    return value != null ? value : defaultValue;
  }

  public static Short toShort(String string) {
    return to(string, Short::valueOf);
  }

  public static int toInt(String string, int defaultValue) {
    Integer value = toInteger(string);
    return value != null ? value : defaultValue;
  }

  public static Integer toInteger(String string) {
    return to(string, Integer::valueOf);
  }

  public static long toLong(String string, long defaultValue) {
    Long value = toLong(string);
    return value != null ? value : defaultValue;
  }

  public static Long toLong(String string) {
    return to(string, Long::valueOf);
  }

  public static float toFloat(String string, float defaultValue) {
    Float value = toFloat(string);
    return value != null ? value : defaultValue;
  }

  public static Float toFloat(String string) {
    return to(string, Float::valueOf);
  }

  public static double toDouble(String string, double defaultValue) {
    Double value = toDouble(string);
    return value != null ? value : defaultValue;
  }

  public static Double toDouble(String string) {
    return to(string, Double::valueOf);
  }

  public static boolean toBoolean(String string, boolean defaultValue) {
    Boolean value = toBoolean(string);
    return value != null ? value : defaultValue;
  }

  public static Boolean toBoolean(String string) {
    return to(string, value -> {
      switch (value.toLowerCase()) {
        case "true", "1", "yes", "y":
          return Boolean.TRUE;
        case "false", "0", "no", "n":
          return Boolean.FALSE;
        default:
          return null;
      }
    });
  }

  private static <T> T to(String string, Function<String, T> function) {
    if (string == null) {
      throw new IllegalArgumentException("string cannot be null");
    }
    if (string.isEmpty()) {
      return null;
    }
    try {
      return function.apply(string);
    } catch (NumberFormatException exception) {
      return null;
    }
  }
}
