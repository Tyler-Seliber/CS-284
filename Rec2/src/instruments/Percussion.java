package instruments;

public class Percussion implements Instrument {

    private String name;
    private String material;
    private boolean isPitched;
    private String malletType;

    public Percussion(String name, String material, boolean isPitched, String malletType) {
        this.name = name;
        this.material = material;
        this.isPitched = isPitched;
        this.malletType = malletType;
    }

    @Override
    public boolean isPitched() {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }

    @Override
    public String getNoise() {
        return null;
    }

    @Override
    public boolean isMarchable() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
