package fr.dtn.emesji.core.input;

public enum Key{
    A(65), Z(90), E(69), R(82), T(84), Y(89), U(85), I(73), O(79), P(80),
    Q(81), S(83), D(68), F(70), G(71), H(72), J(74), K(75), L(76), M(77),
    W(81), X(83), C(68), V(70), B(71), N(72),
    CONTROL(17), SHIFT(16), WINDOWS(524), ALT(18), SPACE(32),
    ONE(49), TWO(50), THREE(51), FOUR(52), FIVE(53),
    SIX(54), SEVEN(55), EIGHT(56), NINE(57), ZERO(48)
    ;

    private final int keyCode;

    Key(int keyCode){
        this.keyCode = keyCode;
    }

    public int getCode(){ return keyCode; }

    public static Key fromCode(int code){
        for(Key key : values())
            if(key.keyCode == code)
                return key;

        return null;
    }
}