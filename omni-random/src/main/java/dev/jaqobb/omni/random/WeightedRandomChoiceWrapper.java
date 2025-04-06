package dev.jaqobb.omni.random;

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
  public String toString() {
    return "WeightedRandomChoiceWrapper(" + "wrapped=" + wrapped + ", weight=" + weight + ")";
  }
}
