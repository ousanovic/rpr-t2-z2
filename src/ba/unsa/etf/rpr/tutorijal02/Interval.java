package ba.unsa.etf.rpr.tutorijal02;

public class Interval {

    private double leftValue, rightValue;
    private Boolean leftIncluded, rightIncluded;
    public Interval(double v, double v1, boolean b, boolean b1) {
        if(v1 < v) throw new IllegalArgumentException("Krajnja tačka ne može biti manja od početne!");
        leftValue = v;
        rightValue = v1;
        leftIncluded = b;
        rightIncluded = b1;
    }

    public Interval() {
        leftValue = 0;
        rightValue = 0;
        leftIncluded = false;
        rightIncluded = false;
    }

    public static Interval intersect(Interval i, Interval i2) {
        return new Interval(Math.max(i.leftValue, i2.leftValue), Math.min(i.rightValue, i2.rightValue),
                Math.max(i.leftValue, i2.leftValue) == i.leftValue ? i.leftIncluded : i2.leftIncluded,
                Math.min(i.rightValue, i2.rightValue) == i.rightValue ? i.rightIncluded : i2.rightIncluded);
    }

    public boolean isIn(double v) {
        return leftValue < v && v < rightValue || v == leftValue && leftIncluded || v == rightValue && rightIncluded;
    }

    public boolean isNull() {
        return leftValue == 0 && rightValue == 0 && !leftIncluded && !rightIncluded;
    }

    public Interval intersect(Interval interval) {
        this.leftValue = Math.max(this.leftValue, interval.leftValue);
        this.rightValue = Math.min(this.rightValue, interval.rightValue);
        this.leftIncluded = Math.max(this.leftValue, interval.leftValue) == this.leftValue ? this.leftIncluded : interval.leftIncluded;
        this.rightIncluded = Math.min(this.rightValue, interval.rightValue) == this.rightValue ? this.rightIncluded : interval.rightIncluded;
        return this;
    }
    @Override
    public String toString() {
        if(this.isNull()) return "()";
        return (leftIncluded ? "[" : "(") + leftValue + "," + rightValue + (rightIncluded ? "]" : ")");
    }
    @Override
    public boolean equals(Object obj) {
        Interval i = null;
        if(obj instanceof Interval)
            i = (Interval)obj;
        return i != null && this.leftValue == i.leftValue && this.leftIncluded == i.leftIncluded &&
                this.rightValue == i.rightValue && this.rightIncluded == i.rightIncluded;
    }

}
