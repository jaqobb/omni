package dev.jaqobb.omni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import dev.jaqobb.omni.common.Conversions;

class ConversionsTest {
  @Test
  void testValidConversions() {
    assertEquals((byte) 100, Conversions.toByte("100"));
    assertEquals((short) 1000, Conversions.toShort("1000"));
    assertEquals(10_000, Conversions.toInteger("10000"));
    assertEquals(100_000L, Conversions.toLong("100000"));
    assertEquals(10.5F, Conversions.toFloat("10.5"));
    assertEquals(10.5D, Conversions.toDouble("10.5"));
    assertTrue(Conversions.toBoolean("true"));
    assertTrue(Conversions.toBoolean("1"));
    assertTrue(Conversions.toBoolean("yes"));
    assertTrue(Conversions.toBoolean("y"));
    assertFalse(Conversions.toBoolean("false"));
    assertFalse(Conversions.toBoolean("0"));
    assertFalse(Conversions.toBoolean("no"));
    assertFalse(Conversions.toBoolean("n"));
  }

  @Test
  void testInvalidConversions() {
    assertNull(Conversions.toByte("invalid"));
    assertNull(Conversions.toShort("invalid"));
    assertNull(Conversions.toInteger("invalid"));
    assertNull(Conversions.toLong("invalid"));
    assertNull(Conversions.toFloat("invalid"));
    assertNull(Conversions.toDouble("invalid"));
    assertNull(Conversions.toBoolean("invalid"));
  }

  @Test
  void testConversionsWithDefault() {
    assertEquals((byte) 42, Conversions.toByte("invalid", (byte) 42));
    assertEquals((short) 42, Conversions.toShort("invalid", (short) 42));
    assertEquals(42, Conversions.toInteger("invalid", 42));
    assertEquals(42L, Conversions.toLong("invalid", 42L));
    assertEquals(42.0F, Conversions.toFloat("invalid", 42.0F));
    assertEquals(42.0D, Conversions.toDouble("invalid", 42.0D));
    assertTrue(Conversions.toBoolean("invalid", true));
    assertFalse(Conversions.toBoolean("invalid", false));
  }
}
