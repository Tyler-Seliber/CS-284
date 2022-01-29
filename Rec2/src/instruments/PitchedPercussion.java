package instruments;

public class PitchedPercussion extends Percussion implements PitchedInstrument {

    private int[] range;
    private String key;

    public PitchedPercussion(String name, String material, String malletType, int[] range, String key) {
        super(name, material, true, malletType);
        this.range = range;
        this.key = key;
    }

    @Override
    public int[] getRange() {
        return range;
    }

    @Override
    public String getKey() {
        return key;
    }
}
