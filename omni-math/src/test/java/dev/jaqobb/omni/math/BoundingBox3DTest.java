package dev.jaqobb.omni.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BoundingBox3DTest {
  @Test
  void testFromFactoryMethods() {
    BoundingBox3D box = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D);
    assertEquals(0.0D, box.getMinX());
    assertEquals(0.0D, box.getMinY());
    assertEquals(0.0D, box.getMinZ());
    assertEquals(10.0D, box.getMaxX());
    assertEquals(10.0D, box.getMaxY());
    assertEquals(10.0D, box.getMaxZ());
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(5.0D, 0.0D, 0.0D, 0.0D, 10.0D, 10.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(0.0D, 10.0D, 0.0D, 10.0D, 5.0D, 10.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(0.0D, 0.0D, 10.0D, 10.0D, 10.0D, 5.0D));
  }

  @Test
  void testFromCenterFactoryMethods() {
    BoundingBox3D box = BoundingBox3D.fromCenter(5.0D, 5.0D, 5.0D, 4.0D, 6.0D, 8.0D);
    assertEquals(3.0D, box.getMinX());
    assertEquals(2.0D, box.getMinY());
    assertEquals(1.0D, box.getMinZ());
    assertEquals(7.0D, box.getMaxX());
    assertEquals(8.0D, box.getMaxY());
    assertEquals(9.0D, box.getMaxZ());
    BoundingBox3D cube = BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 2.0D);
    assertEquals(-1.0D, cube.getMinX());
    assertEquals(-1.0D, cube.getMinY());
    assertEquals(-1.0D, cube.getMinZ());
    assertEquals(1.0D, cube.getMaxX());
    assertEquals(1.0D, cube.getMaxY());
    assertEquals(1.0D, cube.getMaxZ());
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, -1.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, -1.0D, 1.0D, 1.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 1.0D, -1.0D, 1.0D));
    assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D));
  }

  @Test
  void testAccessorsAndDerivedMetrics() {
    BoundingBox3D box = BoundingBox3D.from(1.0D, 2.0D, 3.0D, 5.0D, 6.0D, 7.0D);
    assertEquals(4.0D, box.getWidth());
    assertEquals(4.0D, box.getHeight());
    assertEquals(4.0D, box.getDepth());
    assertEquals(3.0D, box.getCenterX());
    assertEquals(4.0D, box.getCenterY());
    assertEquals(5.0D, box.getCenterZ());
    assertEquals(64.0D, box.getVolume());
    assertEquals(96.0D, box.getSurfaceArea());
  }

  @Test
  void testContainsPointAndBox() {
    BoundingBox3D box = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D);
    assertTrue(box.contains(5.0D, 5.0D, 5.0D));
    assertTrue(box.contains(0.0D, 0.0D, 0.0D));
    assertFalse(box.contains(11.0D, 5.0D, 5.0D));
    assertFalse(box.contains(5.0D, -1.0D, 5.0D));
    assertThrows(IllegalArgumentException.class, () -> box.contains(null));
    BoundingBox3D inside = BoundingBox3D.from(2.0D, 2.0D, 2.0D, 8.0D, 8.0D, 8.0D);
    assertTrue(box.contains(inside));
    BoundingBox3D overlap = BoundingBox3D.from(-1.0D, -1.0D, -1.0D, 5.0D, 5.0D, 5.0D);
    assertFalse(box.contains(overlap));
  }

  @Test
  void testIntersectionAndUnion() {
    BoundingBox3D a = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 5.0D, 5.0D, 5.0D);
    assertThrows(IllegalArgumentException.class, () -> a.union(null));
    assertThrows(IllegalArgumentException.class, () -> a.intersection(null));
    BoundingBox3D b = BoundingBox3D.from(3.0D, 3.0D, 3.0D, 6.0D, 6.0D, 6.0D);
    assertEquals(BoundingBox3D.from(3.0D, 3.0D, 3.0D, 5.0D, 5.0D, 5.0D), a.intersection(b));
    assertEquals(BoundingBox3D.from(0.0D, 0.0D, 0.0D, 6.0D, 6.0D, 6.0D), a.union(b));
    BoundingBox3D c = BoundingBox3D.from(6.1D, 6.1D, 6.1D, 7.0D, 7.0D, 7.0D);
    assertNull(a.intersection(c));
  }

  @Test
  void testIntersects() {
    BoundingBox3D a = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 5.0D, 5.0D, 5.0D);
    BoundingBox3D b = BoundingBox3D.from(4.0D, 4.0D, 4.0D, 10.0D, 10.0D, 10.0D);
    assertTrue(a.intersects(b));
    assertThrows(IllegalArgumentException.class, () -> a.intersects(null));
    BoundingBox3D c = BoundingBox3D.from(6.0D, 6.0D, 6.0D, 8.0D, 8.0D, 8.0D);
    assertFalse(a.intersects(c));
  }

  @Test
  void testExpandShrinkTranslate() {
    BoundingBox3D box = BoundingBox3D.from(1.0D, 1.0D, 1.0D, 3.0D, 3.0D, 3.0D);
    assertEquals(BoundingBox3D.from(0.0D, 0.0D, 0.0D, 4.0D, 4.0D, 4.0D), box.expand(1.0D));
    assertThrows(IllegalArgumentException.class, () -> box.expand(-1.0D));
    assertEquals(BoundingBox3D.from(2.0D, 2.0D, 2.0D, 2.0D, 2.0D, 2.0D), box.shrink(1.0D));
    assertThrows(IllegalArgumentException.class, () -> box.shrink(-1.0D));
    assertEquals(BoundingBox3D.from(3.0D, 0.0D, 2.0D, 5.0D, 2.0D, 4.0D),
        box.translate(2.0D, -1.0D, 1.0D));
  }
}
