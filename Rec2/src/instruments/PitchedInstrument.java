package instruments;

public interface PitchedInstrument extends Instrument {
    public int[] getRange();

    public String getKey();
}
