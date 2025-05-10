package dev.jaqobb.omni.random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RandomsTest {
  final Random fixedRandom = new Random(38L);

  @Test
  void testRandomInteger() {
    assertEquals(9, Randoms.randomInteger(fixedRandom, 1, 10));
    assertEquals(7, Randoms.randomInteger(fixedRandom, 7, 7));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomInteger(null, 1, 2));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomInteger(fixedRandom, 5, 4));
  }

  @Test
  void testRandomLong() {
    assertEquals(6L, Randoms.randomLong(fixedRandom, 1L, 10L));
    assertEquals(8L, Randoms.randomLong(fixedRandom, 8, 8));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomLong(null, 1, 2));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomLong(fixedRandom, 10, 5));
  }

  @Test
  void testRandomFloat() {
    assertEquals(1.7279222F, Randoms.randomFloat(fixedRandom, 1.0F, 2.0F), 1.0E-6F);
    assertEquals(3.0f, Randoms.randomFloat(fixedRandom, 3.0F, 3.0F));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomFloat(null, 1.0F, 2.0F));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomFloat(fixedRandom, 5.0F, 4.0F));
  }

  @Test
  void testRandomDouble() {
    assertEquals(1.7279220220228495D, Randoms.randomDouble(fixedRandom, 1.0F, 2.0F), 1.0E-9D);
    assertEquals(5.0D, Randoms.randomDouble(fixedRandom, 5.0D, 5.0D));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomDouble(null, 1.0D, 2.0D));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomDouble(fixedRandom, 4.0D, 3.0));
  }

  @Test
  void testRandomBoolean() {
    assertTrue(Randoms.randomBoolean(fixedRandom));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomBoolean(null));
  }

  @Test
  void testRandomElementArray() {
    String[] array = {"a", "b", "c"};
    assertEquals("a", Randoms.randomElement(fixedRandom, array));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomElement(null, array));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (String[]) null));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, new String[0]));
    assertEquals("x", Randoms.randomElement(fixedRandom, new String[] {"x"}));
  }

  @Test
  void testRandomElementList() {
    List<Integer> list = Arrays.asList(10, 20, 30);
    assertEquals(10, Randoms.randomElement(fixedRandom, list));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomElement(null, list));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (List<Integer>) null));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, List.of()));
    assertEquals(99, Randoms.randomElement(fixedRandom, List.of(99)));
  }

  @Test
  void testRandomElementCollection() {
    Collection<String> collection = new LinkedHashSet<>(Arrays.asList("x", "y", "z"));
    assertEquals("x", Randoms.randomElement(fixedRandom, collection));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomElement(null, collection));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (Collection<String>) null));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, Set.of()));
    assertEquals("only", Randoms.randomElement(fixedRandom, Set.of("only")));
  }

  @Test
  void testRandomWeightedChoice() {
    List<WeightedRandomChoice> choices = Arrays.asList(WeightedRandomChoice.from(1.0D),
        WeightedRandomChoice.from(2.0D), WeightedRandomChoice.from(3.0D));
    WeightedRandomChoice choice = Randoms.randomWeightedChoice(fixedRandom, choices);
    assertEquals(3.0D, choice.weight);
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomWeightedChoice(null, choices));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomWeightedChoice(fixedRandom, (List<WeightedRandomChoice>) null));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomWeightedChoice(fixedRandom, List.of()));
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomWeightedChoice(fixedRandom,
        Arrays.asList(WeightedRandomChoice.from(1.0D), null)));
  }

  @Test
  void testTotalWeight() {
    List<WeightedRandomChoice> list =
        Arrays.asList(WeightedRandomChoice.from(2.5D), WeightedRandomChoice.from(3.5D));
    assertEquals(6.0D, Randoms.totalWeight(list));
    assertThrows(IllegalArgumentException.class, () -> Randoms.totalWeight(null));
    assertThrows(IllegalArgumentException.class,
        () -> Randoms.totalWeight(Arrays.asList(WeightedRandomChoice.from(1.0D), null)));
  }

  @Test
  void testChance1() {
    assertTrue(Randoms.chance1(fixedRandom, 1.0D));
    assertFalse(Randoms.chance1(fixedRandom, 0.0D));
    assertFalse(Randoms.chance1(fixedRandom, 0.5D));
    assertThrows(IllegalArgumentException.class, () -> Randoms.chance1(null, 0.5D));
  }

  @Test
  void testRandomChance1() {
    assertEquals(0.7279220220228493D, Randoms.randomChance1(fixedRandom), 1.0E-9D);
    assertThrows(IllegalArgumentException.class, () -> Randoms.randomChance1(null));
  }

  @Test
  void testChance100() {
    assertTrue(Randoms.chance100(fixedRandom, 100.0D));
    assertFalse(Randoms.chance100(fixedRandom, 0.0D));
    assertFalse(Randoms.chance100(fixedRandom, 50.0D));
    assertThrows(IllegalArgumentException.class, () -> Randoms.chance100(null, 50.0D));
  }
}
