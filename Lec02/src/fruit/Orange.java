package fruit;

import color.Colorable;

public class Orange extends Fruit implements Colorable {
    private double acidity;

    public Orange(double acidity, boolean seeds, double weight) {
        super(seeds, weight);
        this.acidity = acidity;
    }

    public double getAcidity() {
        return acidity;
    }

    @Override
    public String getColor() {
        return "orange";
    }
}
