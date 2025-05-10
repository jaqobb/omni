package dev.jaqobb.omni.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BoundingBox2DTest {
  @Test
  void testFromFactoryMethods() {
    BoundingBox2D box = BoundingBox2D.from(0.0D, 0.0D, 10.0D, 10.0D);
    assertEquals(0.0D, box.getMinX());
    assertEquals(0.0D, box.getMinY());
    assertEquals(10.0D, box.getMaxX());
    assertEquals(10.0D, box.getMaxY());
    assertThrows(IllegalArgumentException.class, () -> BoundingBox2D.from(5.0D, 0.0D, 0.0D, 10.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.from(0.0D, 10.0D, 10.0D, 5.0D));
  }

  @Test
  void testFromCenterFactoryMethods() {
    BoundingBox2D box = BoundingBox2D.fromCenter(5.0D, 5.0D, 4.0D, 6.0D);
    assertEquals(3.0D, box.getMinX());
    assertEquals(2.0D, box.getMinY());
    assertEquals(7.0D, box.getMaxX());
    assertEquals(8.0D, box.getMaxY());
    BoundingBox2D square = BoundingBox2D.fromCenter(0.0D, 0.0D, 2.0D);
    assertEquals(-1.0D, square.getMinX());
    assertEquals(-1.0D, square.getMinY());
    assertEquals(1.0D, square.getMaxX());
    assertEquals(1.0D, square.getMaxY());
    assertThrows(IllegalArgumentException.class, () -> BoundingBox2D.fromCenter(0.0D, 0.0D, -1.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.fromCenter(0.0D, 0.0D, -1.0D, 1.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.fromCenter(0.0D, 0.0D, 1.0D, -1.0D));
  }

  @Test
  void testAccessorsAndDerivedMetrics() {
    BoundingBox2D box = BoundingBox2D.from(2.0D, 3.0D, 8.0D, 7.0D);
    assertEquals(6.0D, box.getWidth());
    assertEquals(4.0D, box.getHeight());
    assertEquals(5.0D, box.getCenterX());
    assertEquals(5.0D, box.getCenterY());
    assertEquals(24.0D, box.getArea());
    assertEquals(20.0D, box.getPerimeter());
  }

  @Test
  void testContainsPointAndBox() {
    BoundingBox2D box = BoundingBox2D.from(0.0D, 0.0D, 10.0D, 10.0D);
    assertTrue(box.contains(5.0D, 5.0D));
    assertTrue(box.contains(0.0D, 0.0D));
    assertFalse(box.contains(10.1D, 5.0D));
    assertFalse(box.contains(5.0D, -0.1D));
    assertThrows(IllegalArgumentException.class, () -> box.contains(null));
    BoundingBox2D inside = BoundingBox2D.from(2.0D, 2.0D, 8.0D, 8.0D);
    assertTrue(box.contains(inside));
    BoundingBox2D overlap = BoundingBox2D.from(-1.0D, -1.0D, 5.0D, 5.0D);
    assertFalse(box.contains(overlap));
  }

  @Test
  void testIntersectionAndUnion() {
    BoundingBox2D a = BoundingBox2D.from(0.0D, 0.0D, 5.0D, 5.0D);
    assertThrows(IllegalArgumentException.class, () -> a.union(null));
    assertThrows(IllegalArgumentException.class, () -> a.intersection(null));
    BoundingBox2D b = BoundingBox2D.from(3.0D, 3.0D, 6.0D, 6.0D);
    assertEquals(BoundingBox2D.from(3.0D, 3.0D, 5.0D, 5.0D), a.intersection(b));
    assertEquals(BoundingBox2D.from(0.0D, 0.0D, 6.0D, 6.0D), a.union(b));
    BoundingBox2D c = BoundingBox2D.from(6.0D, 6.0D, 7.0D, 7.0D);
    assertNull(a.intersection(c));
  }

  @Test
  void testIntersects() {
    BoundingBox2D a = BoundingBox2D.from(0.0D, 0.0D, 5.0D, 5.0D);
    BoundingBox2D b = BoundingBox2D.from(4.0D, 4.0D, 10.0D, 10.0D);
    assertTrue(a.intersects(b));
    assertThrows(IllegalArgumentException.class, () -> a.intersects(null));
    BoundingBox2D c = BoundingBox2D.from(6.0D, 6.0D, 8.0D, 8.0D);
    assertFalse(a.intersects(c));
  }

  @Test
  void testExpandShrinkTranslate() {
    BoundingBox2D box = BoundingBox2D.from(2.0D, 2.0D, 4.0D, 4.0D);
    assertEquals(BoundingBox2D.from(1.0D, 1.0D, 5.0D, 5.0D), box.expand(1.0D));
    assertThrows(IllegalArgumentException.class, () -> box.expand(-1.0D));
    assertEquals(BoundingBox2D.from(3.0D, 3.0D, 3.0D, 3.0D), box.shrink(1.0D));
    assertThrows(IllegalArgumentException.class, () -> box.shrink(-1.0D));
    assertEquals(BoundingBox2D.from(4.0D, 1.0D, 6.0D, 3.0D), box.translate(2.0D, -1.0D));
  }
}
