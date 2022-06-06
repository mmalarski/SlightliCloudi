package kw.hk.mm.mr.slightlicloudi.weather.recommendations;

public enum Clothes {
    THERMAL_CLOTHING("thermal clothing"),
    WOOL_SOCKS("wool socks"),
    SCARF("scarf"),
    FACE_CREAM("face cream"),
    SUNGLASSES("sunglasses"),
    GLOVES("gloves"),
    HAT("hat"),
    JACKET("jacket"),
    COAT("coat"),
    SHOES("appropriate shoes"),
    T_SHIRT("t-shirt"),
    SPF("sunscreen"),
    AIRY_CLOTHES("airy clothes"),
    CAP("cap"),
    WATER("water"),
    TURTLENECK("turtleneck"),
    LONG_SLEEVE("long sleeve"),
    HAIRSPRAY("hair spray"),
    UMBRELLA("umbrella"),
    NO_UMBRELLA("no umbrella"),
    LAYERED_CLOTHING("layered clothing"),
    RAINCOAT("raincoat"),
    RAIN_BOOTS("rain boots"),
    RAIN_CAPE("rain cape"),
    WATERPROOF_CLOTHING("waterproof outer layer"),
    LIGHTNING_PRECAUTIONS("lightning strike precautions"),
    SPARE_CLOTHES("change of clothes"),
    WINTER_JACKET("winter jacket"),
    WINTER_MASK("winter mask"),
    WINTER_SHOES("winter shoes"),
    WINTER_HAT("winter hat"),
    WINTER_GLOVES("winter gloves"),
    EASTER_EGG("plastic see-through pants");

    private final String clothing;

    Clothes(String clothing) {
        this.clothing = clothing;
    }


    @Override
    public String toString() {
        return clothing;
    }
}
