package com.example.CISC4900.WeightliftingApp;

public class EstimateRepMaxPercent {

    public static final int BAECHLE = 0;
    public static final int BRZYCKI = 1;
    public static final int DOS_REMEDIOS = 2;

    public static final double ONE_REP_MAX_BAECHLE      =1.00;
    public static final double TWO_REP_MAX_BAECHLE      =.95;
    public static final double THREE_REP_MAX_BAECHLE    =.93;
    public static final double FOUR_REP_MAX_BAECHLE     =.90;
    public static final double FIVE_REP_MAX_BAECHLE     =.87;
    public static final double SIX_REP_MAX_BAECHLE      =.85;
    public static final double SEVEN_REP_MAX_BAECHLE    =.83;
    public static final double EIGHT_REP_MAX_BAECHLE    =.80;
    public static final double NINE_REP_MAX_BAECHLE     =.77;
    public static final double TEN_REP_MAX_BAECHLE      =.75;
    public static final double ELEVEN_REP_MAX_BAECHLE   =0;
    public static final double TWELVE_REP_MAX_BAECHLE   =.67;
    public static final double FIFTEEN_REP_MAX_BAECHLE  =.65;

    public static final double ONE_REP_MAX_BRZYCKI       =1.00;
    public static final double TWO_REP_MAX_BRZYCKI      =.95;
    public static final double THREE_REP_MAX_BRZYCKI    =.90;
    public static final double FOUR_REP_MAX_BRZYCKI     =.88;
    public static final double FIVE_REP_MAX_BRZYCKI     =.86;
    public static final double SIX_REP_MAX_BRZYCKI      =.83;
    public static final double SEVEN_REP_MAX_BRZYCKI    =.80;
    public static final double EIGHT_REP_MAX_BRZYCKI    =.78;
    public static final double NINE_REP_MAX_BRZYCKI     =.76;
    public static final double TEN_REP_MAX_BRZYCKI      =.75;
    public static final double ELEVEN_REP_MAX_BRZYCKI   =.72;
    public static final double TWELVE_REP_MAX_BRZYCKI   =.70;
    public static final double FIFTEEN_REP_MAX_BRZYCKI  =0;

    public static final double ONE_REP_MAX_DOS_REMEDIOS         =1.00;
    public static final double TWO_REP_MAX_DOS_REMEDIOS         =.92;
    public static final double THREE_REP_MAX_DOS_REMEDIOS       =.90;
    public static final double FOUR_REP_MAX_DOS_REMEDIOS        =.87;
    public static final double FIVE_REP_MAX_DOS_REMEDIOS        =.85;
    public static final double SIX_REP_MAX_DOS_REMEDIOS         =.82;
    public static final double SEVEN_REP_MAX_DOS_REMEDIOS       =0;
    public static final double EIGHT_REP_MAX_DOS_REMEDIOS       =.75;
    public static final double NINE_REP_MAX_DOS_REMEDIOS        =0;
    public static final double TEN_REP_MAX_DOS_REMEDIOS         =.70;
    public static final double ELEVEN_REP_MAX_DOS_REMEDIOS      =0;
    public static final double TWELVE_REP_MAX_DOS_REMEDIOS      =.65;
    public static final double FIFTEEN_REP_MAX_DOS_REMEDIOS     =.60;

    private double oneRepMax    = ONE_REP_MAX_BAECHLE    ;
    private double twoRepMax    = TWO_REP_MAX_BAECHLE    ;
    private double threeRepMax  = THREE_REP_MAX_BAECHLE  ;
    private double fourRepMax   = FOUR_REP_MAX_BAECHLE   ;
    private double fiveRepMax   = FIVE_REP_MAX_BAECHLE   ;
    private double sixRepMax    = SIX_REP_MAX_BAECHLE    ;
    private double sevenRepMax  = SEVEN_REP_MAX_BAECHLE  ;
    private double eightRepMax  = EIGHT_REP_MAX_BAECHLE  ;
    private double nineRepMax   = NINE_REP_MAX_BAECHLE   ;
    private double tenRepMax    = TEN_REP_MAX_BAECHLE    ;
    private double elevenRepMax = ELEVEN_REP_MAX_BAECHLE ;
    private double twelveRepMax = TWELVE_REP_MAX_BAECHLE ;
    private double fifteenRepMax= FIFTEEN_REP_MAX_BAECHLE;

    private double repMaxPercent = 1;

    public EstimateRepMaxPercent(){
    }

    public void setDataSet(int data){
        switch(data) {
            case BAECHLE:
                oneRepMax    =ONE_REP_MAX_BAECHLE    ;
                twoRepMax    =TWO_REP_MAX_BAECHLE    ;
                threeRepMax  =THREE_REP_MAX_BAECHLE  ;
                fourRepMax   =FOUR_REP_MAX_BAECHLE   ;
                fiveRepMax   =FIVE_REP_MAX_BAECHLE   ;
                sixRepMax    =SIX_REP_MAX_BAECHLE    ;
                sevenRepMax  =SEVEN_REP_MAX_BAECHLE  ;
                eightRepMax  =EIGHT_REP_MAX_BAECHLE  ;
                nineRepMax   =NINE_REP_MAX_BAECHLE   ;
                tenRepMax    =TEN_REP_MAX_BAECHLE    ;
                elevenRepMax =ELEVEN_REP_MAX_BAECHLE ;
                twelveRepMax =TWELVE_REP_MAX_BAECHLE ;
                fifteenRepMax=FIFTEEN_REP_MAX_BAECHLE;
                break;
            case BRZYCKI:
                oneRepMax    =ONE_REP_MAX_BRZYCKI    ;
                twoRepMax    =TWO_REP_MAX_BRZYCKI    ;
                threeRepMax  =THREE_REP_MAX_BRZYCKI  ;
                fourRepMax   =FOUR_REP_MAX_BRZYCKI   ;
                fiveRepMax   =FIVE_REP_MAX_BRZYCKI   ;
                sixRepMax    =SIX_REP_MAX_BRZYCKI    ;
                sevenRepMax  =SEVEN_REP_MAX_BRZYCKI  ;
                eightRepMax  =EIGHT_REP_MAX_BRZYCKI  ;
                nineRepMax   =NINE_REP_MAX_BRZYCKI   ;
                tenRepMax    =TEN_REP_MAX_BRZYCKI    ;
                elevenRepMax =ELEVEN_REP_MAX_BRZYCKI ;
                twelveRepMax =TWELVE_REP_MAX_BRZYCKI ;
                fifteenRepMax=FIFTEEN_REP_MAX_BRZYCKI;
                break;
            case DOS_REMEDIOS:
                oneRepMax    =ONE_REP_MAX_DOS_REMEDIOS    ;
                twoRepMax    =TWO_REP_MAX_DOS_REMEDIOS    ;
                threeRepMax  =THREE_REP_MAX_DOS_REMEDIOS  ;
                fourRepMax   =FOUR_REP_MAX_DOS_REMEDIOS   ;
                fiveRepMax   =FIVE_REP_MAX_DOS_REMEDIOS   ;
                sixRepMax    =SIX_REP_MAX_DOS_REMEDIOS    ;
                sevenRepMax  =SEVEN_REP_MAX_DOS_REMEDIOS  ;
                eightRepMax  =EIGHT_REP_MAX_DOS_REMEDIOS  ;
                nineRepMax   =NINE_REP_MAX_DOS_REMEDIOS   ;
                tenRepMax    =TEN_REP_MAX_DOS_REMEDIOS    ;
                elevenRepMax =ELEVEN_REP_MAX_DOS_REMEDIOS ;
                twelveRepMax =TWELVE_REP_MAX_DOS_REMEDIOS ;
                fifteenRepMax=FIFTEEN_REP_MAX_DOS_REMEDIOS;
                break;
             default:
                oneRepMax    =ONE_REP_MAX_BAECHLE    ;
                twoRepMax    =TWO_REP_MAX_BAECHLE    ;
                threeRepMax  =THREE_REP_MAX_BAECHLE  ;
                fourRepMax   =FOUR_REP_MAX_BAECHLE   ;
                fiveRepMax   =FIVE_REP_MAX_BAECHLE   ;
                sixRepMax    =SIX_REP_MAX_BAECHLE    ;
                sevenRepMax  =SEVEN_REP_MAX_BAECHLE  ;
                eightRepMax  =EIGHT_REP_MAX_BAECHLE  ;
                nineRepMax   =NINE_REP_MAX_BAECHLE   ;
                tenRepMax    =TEN_REP_MAX_BAECHLE    ;
                elevenRepMax =ELEVEN_REP_MAX_BAECHLE ;
                twelveRepMax =TWELVE_REP_MAX_BAECHLE ;
                fifteenRepMax=FIFTEEN_REP_MAX_BAECHLE;
                break;
        }
    }

    public void setRepMaxPercent(int numberOfReps) {
        switch (numberOfReps) {
            case 0:
                repMaxPercent = oneRepMax;
                break;
            case 1:
                repMaxPercent = twoRepMax;
                break;
            case 2:
                repMaxPercent = threeRepMax;
                break;
            case 3:
                repMaxPercent = fourRepMax;
                break;
            case 4:
                repMaxPercent = fiveRepMax;
                break;
            case 5:
                repMaxPercent = sixRepMax;
                break;
            case 6:
                repMaxPercent = sevenRepMax;
                break;
            case 7:
                repMaxPercent = eightRepMax;
                break;
            case 8:
                repMaxPercent = nineRepMax;
                break;
            case 9:
                repMaxPercent = tenRepMax;
                break;
            case 10:
                repMaxPercent = elevenRepMax;
                break;
            case 11:
                repMaxPercent = twelveRepMax;
                break;
            case 12:
                repMaxPercent = fifteenRepMax;
                break;
            default:
                repMaxPercent = oneRepMax;
        }
    }

    public double getRepMax(double oRM){
        return oRM * repMaxPercent;
    }
}
