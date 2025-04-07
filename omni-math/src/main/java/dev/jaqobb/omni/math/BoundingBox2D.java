package dev.jaqobb.omni.math;

import java.util.Objects;

public class BoundingBox2D {
  protected final double minX;
  protected final double minY;
  protected final double maxX;
  protected final double maxY;

  public BoundingBox2D(double minX, double minY, double maxX, double maxY) {
    if (minX > maxX) {
      throw new IllegalArgumentException("minX cannot be greater than maxX");
    }
    if (minY > maxY) {
      throw new IllegalArgumentException("minY cannot be greater than maxY");
    }
    this.minX = minX;
    this.minY = minY;
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public double getMinX() {
    return minX;
  }

  public double getMinY() {
    return minY;
  }

  public double getMaxX() {
    return maxX;
  }

  public double getMaxY() {
    return maxY;
  }

  public double getWidth() {
    return maxX - minX;
  }

  public double getHeight() {
    return maxY - minY;
  }

  public double getCenterX() {
    return (minX + maxX) / 2.0D;
  }

  public double getCenterY() {
    return (minY + maxY) / 2.0D;
  }

  public double getArea() {
    return getWidth() * getHeight();
  }

  public double getPerimeter() {
    return 2.0D * (getWidth() + getHeight());
  }

  public boolean contains(double x, double y) {
    return x >= minX && x <= maxX && y >= minY && y <= maxY;
  }

  public boolean contains(BoundingBox2D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    return minX <= other.minX && maxX >= other.maxX && minY <= other.minY && maxY >= other.maxY;
  }

  public boolean intersects(BoundingBox2D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    return maxX >= other.minX && minX <= other.maxX && maxY >= other.minY && minY <= other.maxY;
  }

  public BoundingBox2D intersection(BoundingBox2D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    if (!intersects(other)) {
      return null;
    }
    double newMinX = Math.max(minX, other.minX);
    double newMinY = Math.max(minY, other.minY);
    double newMaxX = Math.min(maxX, other.maxX);
    double newMaxY = Math.min(maxY, other.maxY);
    return new BoundingBox2D(newMinX, newMinY, newMaxX, newMaxY);
  }

  public BoundingBox2D union(BoundingBox2D other) {
    if (other == null) {
      throw new IllegalArgumentException("other cannot be null");
    }
    double newMinX = Math.min(minX, other.minX);
    double newMinY = Math.min(minY, other.minY);
    double newMaxX = Math.max(maxX, other.maxX);
    double newMaxY = Math.max(maxY, other.maxY);
    return new BoundingBox2D(newMinX, newMinY, newMaxX, newMaxY);
  }

  public BoundingBox2D expand(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("amount cannot be negative");
    }
    return new BoundingBox2D(minX - amount, minY - amount, maxX + amount, maxY + amount);
  }

  public BoundingBox2D shrink(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("amount cannot be negative");
    }
    return new BoundingBox2D(minX + amount, minY + amount, maxX - amount, maxY - amount);
  }

  public BoundingBox2D translate(double dx, double dy) {
    return new BoundingBox2D(minX + dx, minY + dy, maxX + dx, maxY + dy);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof BoundingBox2D that)) {
      return false;
    }
    return Double.compare(minX, that.minX) == 0 && Double.compare(minY, that.minY) == 0
        && Double.compare(maxX, that.maxX) == 0 && Double.compare(maxY, that.maxY) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(minX, minY, maxX, maxY);
  }

  @Override
  public String toString() {
    return String.format("BoundingBox2D(minX=%f, minY=%f, maxX=%f, maxY=%f)", minX, minY, maxX,
        maxY);
  }
}
