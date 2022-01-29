package instruments;

public class StringInstrument implements PitchedInstrument {
    private String type;
    private String name;
    private String material;
    private boolean isPitched;
    private boolean isMarchable;
    private int[] range;
    private String playType;
    private String key;

    public StringInstrument(String type, String name, String material, boolean isMarchable, int[] range, String mouthpiece, String key) {
        this.type = type;
        this.name = name;
        this.material = material;
        this.isPitched = true;
        this.isMarchable = isMarchable;
        this.range = range;
        this.playType = playType;
        this.key = key;
    }

    @Override
    public boolean isPitched() {
        return isPitched;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public String getNoise() {
        return null;
    }

    @Override
    public boolean isMarchable() {
        return isMarchable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int[] getRange() {
        return range;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getPlayType() {
        return playType;
    }
}

