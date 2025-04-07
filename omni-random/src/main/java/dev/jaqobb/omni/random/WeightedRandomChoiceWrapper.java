package dev.jaqobb.omni.random;

import java.util.Objects;

public class WeightedRandomChoiceWrapper<T> extends WeightedRandomChoice {
  protected final T wrapped;

  public WeightedRandomChoiceWrapper(T wrapped, double weight) {
    super(weight);
    if (wrapped == null) {
      throw new IllegalArgumentException("wrapped cannot be null");
    }
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
