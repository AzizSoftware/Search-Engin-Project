package tokenizers;

public class FullOccucranceStat<A, B, C> {
	private final A first;
    private final B second;
    private final C third;

    public FullOccucranceStat(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

}
