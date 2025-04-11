package dev.jaqobb.omni.math;

import java.util.Objects;

public class BoundingBox3D {
  public static BoundingBox3D from(double minX, double minY, double minZ, double maxX, double maxY,
      double maxZ) {
    if (minX > maxX) {
      throw new IllegalArgumentException("minX cannot be greater than maxX");
    }
    if (minY > maxY) {
      throw new IllegalArgumentException("minY cannot be greater than maxY");
    }
    if (minZ > maxZ) {
      throw new IllegalArgumentException("minZ cannot be greater than maxZ");
    }
    return new BoundingBox3D(minX, minY, minZ, maxX, maxY, maxZ);
  }

  public static BoundingBox3D fromCenter(double centerX, double centerY, double centerZ,
      double size) {
    if (size < 0) {
      throw new IllegalArgumentException("size cannot be negative");
    }
    return fromCenter(centerX, centerY, centerZ, size, size, size);
  }

  public static BoundingBox3D fromCenter(double centerX, double centerY, double centerZ,
      double width, double height, double depth) {
    if (width < 0) {
      throw new IllegalArgumentException("width cannot be negative");
    }
    if (height < 0) {
      throw new IllegalArgumentException("height cannot be negative");
    }
    if (depth < 0) {
      throw new IllegalArgumentException("depth cannot be negative");
    }
    double halfWidth = width / 2.0D;
    double halfHeight = height / 2.0D;
    double halfDepth = depth / 2.0D;
    double minX = centerX - halfWidth;
    double minY = centerY - halfHeight;
    double minZ = centerZ - halfDepth;
    double maxX = centerX + halfWidth;
    double maxY = centerY + halfHeight;
    double maxZ = centerZ + halfDepth;
    return new BoundingBox3D(minX, minY, minZ, maxX, maxY, maxZ);
  }

  protected final double minX;
  protected final double minY;
  protected final double minZ;
  protected final double maxX;
  protected final double maxY;
  protected final double maxZ;

  protected BoundingBox3D(double minX, double minY, double minZ, double maxX, double maxY,
      double maxZ) {
    this.minX = minX;
    this.minY = minY;
    this.minZ = minZ;
    this.maxX = maxX;
    this.maxY = maxY;
    this.maxZ = maxZ;
  }

  public double getMinX() {
    return minX;
  }

  public double getMinY() {
    return minY;
  }

  public double getMinZ() {
    return minZ;
  }

  public double getMaxX() {
    return maxX;
  }

  public double getMaxY() {
    return maxY;
  }

  public double getMaxZ() {
    return maxZ;
  }

  public double getWidth() {
    return maxX - minX;
  }

  public double getHeight() {
    return maxY - minY;
  }

  public double getDepth() {
    return maxZ - minZ;
  }

  public double getCenterX() {
    return (minX + maxX) / 2.0D;
  }

  public double getCenterY() {
    return (minY + maxY) / 2.0D;
  }

  public double getCenterZ() {
    return (minZ + maxZ) / 2.0D;
  }

  public double getVolume() {
    return getWidth() * getHeight() * getDepth();
  }

  public double getSurfaceArea() {
    return 2.0D * (getWidth() * getHeight() + getWidth() * getDepth() + getHeight() * getDepth());
  }

  public boolean contains(double x, double y, double z) {
    return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
  }

  public boolean contains(BoundingBox3D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    return this.minX <= other.minX && this.maxX >= other.maxX && this.minY <= other.minY
        && this.maxY >= other.maxY && this.minZ <= other.minZ && this.maxZ >= other.maxZ;
  }

  public boolean intersects(BoundingBox3D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    return this.maxX >= other.minX && this.minX <= other.maxX && this.maxY >= other.minY
        && this.minY <= other.maxY && this.maxZ >= other.minZ && this.minZ <= other.maxZ;
  }

  public BoundingBox3D intersection(BoundingBox3D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    if (!this.intersects(other)) {
      return null;
    }
    double newMinX = Math.max(this.minX, other.minX);
    double newMinY = Math.max(this.minY, other.minY);
    double newMinZ = Math.max(this.minZ, other.minZ);
    double newMaxX = Math.min(this.maxX, other.maxX);
    double newMaxY = Math.min(this.maxY, other.maxY);
    double newMaxZ = Math.min(this.maxZ, other.maxZ);
    return new BoundingBox3D(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
  }

  public BoundingBox3D union(BoundingBox3D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    double newMinX = Math.min(this.minX, other.minX);
    double newMinY = Math.min(this.minY, other.minY);
    double newMinZ = Math.min(this.minZ, other.minZ);
    double newMaxX = Math.max(this.maxX, other.maxX);
    double newMaxY = Math.max(this.maxY, other.maxY);
    double newMaxZ = Math.max(this.maxZ, other.maxZ);
    return new BoundingBox3D(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
  }

  public BoundingBox3D expand(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("amount cannot be negative");
    }
    return new BoundingBox3D(minX - amount, minY - amount, minZ - amount, maxX + amount,
        maxY + amount, maxZ + amount);
  }

  public BoundingBox3D shrink(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("amount cannot be negative");
    }
    return new BoundingBox3D(minX + amount, minY + amount, minZ + amount, maxX - amount,
        maxY - amount, maxZ - amount);
  }

  public BoundingBox3D translate(double dx, double dy, double dz) {
    return new BoundingBox3D(minX + dx, minY + dy, minZ + dz, maxX + dx, maxY + dy, maxZ + dz);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof BoundingBox3D that)) {
      return false;
    }
    return Double.compare(minX, that.minX) == 0 && Double.compare(minY, that.minY) == 0
        && Double.compare(minZ, that.minZ) == 0 && Double.compare(maxX, that.maxX) == 0
        && Double.compare(maxY, that.maxY) == 0 && Double.compare(maxZ, that.maxZ) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(minX, minY, minZ, maxX, maxY, maxZ);
  }

  @Override
  public String toString() {
    return String.format("BoundingBox3D(minX=%f, minY=%f, minZ=%f, maxX=%f, maxY=%f, maxZ=%f)",
        minX, minY, minZ, maxX, maxY, maxZ);
  }
}
