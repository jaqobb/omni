package dev.jaqobb.omni.random;

import java.util.Objects;

public class WeightedRandomChoiceWrapper<T> extends WeightedRandomChoice {
  public static <T> WeightedRandomChoiceWrapper<T> from(T wrapped, double weight) {
    if (wrapped == null) {
      throw new IllegalArgumentException("wrapped cannot be null");
    }
    if (Double.isNaN(weight)) {
      throw new IllegalArgumentException("weight must not be NaN");
    }
    if (Double.isInfinite(weight)) {
      throw new IllegalArgumentException("weight must not be infinite");
    }
    if (weight <= 0.0D) {
      throw new IllegalArgumentException("weight must be greater than 0");
    }
    return new WeightedRandomChoiceWrapper<>(wrapped, weight);
  }

  protected final T wrapped;

  protected WeightedRandomChoiceWrapper(T wrapped, double weight) {
    super(weight);
    this.wrapped = wrapped;
  }

  public T getWrapped() {
    return wrapped;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof WeightedRandomChoiceWrapper that)) {
      return false;
    }
    return Objects.equals(wrapped, that.wrapped) && Double.compare(weight, that.weight) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(wrapped, weight);
  }

  @Override
  public String toString() {
    return "WeightedRandomChoiceWrapper(" + "wrapped=" + wrapped + ", weight=" + weight + ")";
  }
}
