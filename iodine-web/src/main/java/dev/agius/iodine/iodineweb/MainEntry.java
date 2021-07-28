package dev.agius.iodine.iodineweb;

/**
 * This is the Entrypoint for the program when it's run from the command line
 */
public class MainEntry {

    private IodineWeb iodineWeb;

    public static void main(String[] args) {
        MainEntry entry = new MainEntry();
        entry.iodineWeb = new IodineWeb();

        entry.iodineWeb.bootstrapWeb();
    }
}
