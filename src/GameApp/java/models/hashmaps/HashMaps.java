package GameApp.java.models.hashmaps;

import GameApp.java.models.BitDepth;
import GameApp.java.models.FormFactor;

import java.util.HashMap;
import java.util.Map;

public class HashMaps {
    public static final Map<BitDepth, String> bitDepthStringMap = new HashMap<>(){{
        put(BitDepth.EIGHT, "8bit");
        put(BitDepth.SIXTEEN, "16bit");
        put(BitDepth.THIRTY_TWO, "32bit");
        put(BitDepth.SIXTY_FOUR, "64bit");
        put(BitDepth.HUNDRED_TWENTY_EIGHT, "128bit");
        put(BitDepth.TWO_HUNDRED_FIFTY_SIX, "256bit");
    }};//Hashmap for console bit types

    public static final Map<FormFactor, String> formFactorStringMap = new HashMap<>(){{
        put(FormFactor.STANDARD, "Standard");
        put(FormFactor.HANDHELD, "Handheld");
    }};//Hashmap for console formfactor types
}
