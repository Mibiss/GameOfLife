public class Celle {
    public boolean status;
    public boolean levende;
    public Celle naboer[];
    public int antNaboer;
    public int antLevendeNaboer = 0;

    public Celle() {
        this.levende = false;
        this.naboer = new Celle[8];
        this.antNaboer = 0;
    }

    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() {
        if (erLevende()) {
            return 'O';
        } else {
            return '.';
        }
        
    }

    public void leggTilNabo(Celle nyCelle) {
        if (nyCelle != null) {
            for (int i = 0; i < naboer.length; i++) {
                if (naboer[i] == null) {
                    naboer[i] = nyCelle;
                    break;
                }
            }
            antNaboer++;
        }
    }

    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;

        for (Celle celle : naboer) {
            if (celle != null) {
                if (celle.erLevende()) {
                    antLevendeNaboer++;
                }
            }
        }
    }

    public void oppdaterStatus() {
        if (levende) {
            if (antLevendeNaboer < 2) {
                settDoed();
            } else if (antLevendeNaboer == 2 || antLevendeNaboer == 3) {
                settLevende();
            } else if (antLevendeNaboer > 3) {
                settDoed();
            }
        } else if (!levende) {
            if (antLevendeNaboer == 3) {
                settLevende();
            } else {
                settDoed();
            }
        }
    }
}
