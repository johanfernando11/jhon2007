package momento3;

public class HachaGuerra extends Arma {
    public static final HachaGuerra HACHA_GUERRA = new HachaGuerra();
    private HachaGuerra() {
        super("Hacha de Guerra", 25, 50);
    }
}