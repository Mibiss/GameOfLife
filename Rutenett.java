public class Rutenett {
    public int antRader;
    public int antKolonner;
    public Celle[][] rutene;

    public Rutenett(int r, int k) {
        this.antRader = r;
        this.antKolonner = k;
        rutene = new Celle[r][k];
    }

    public Celle[][] hentRutenett() {
        return rutene;
    }

    public Celle lagCelle() {
        Celle celle = new Celle();

        if (Math.random() <= 0.333) {
            celle.settLevende();
        }

        return celle;
    }

    public void fyllMedTilfeldigeCeller() {

        for (int rx = 0; rx < antRader; rx++) {
            for (int kx = 0; kx < antKolonner; kx++) {
                Celle celle = lagCelle();
                rutene[rx][kx] = celle;
            }
        }
    }

    public Celle hentCelle(int rx, int kx) {
        if (rx < 0 || rx >= antRader || kx < 0 || kx >= antKolonner) {
            return null;
        } else if (rx >= 0 && rx < antRader && kx >= 0 && kx < antKolonner) {
            if (rutene[rx][kx] != null) {
                return rutene[rx][kx];
            }
        }
        return null;
    }

    public void tegnRutenett() {
        for (int rx = 0; rx < antRader; rx++) {
            for (int kx = 0; kx < antKolonner; kx++) {
                System.out.print(rutene[rx][kx].hentStatusTegn() + " ");
            }
            System.out.println();
        }
    }

    public void settNaboer(int rx, int kx) {

        rutene[rx][kx].leggTilNabo(hentCelle(rx + 1, kx + 1));

        rutene[rx][kx].leggTilNabo(hentCelle(rx, kx + 1));
        rutene[rx][kx].leggTilNabo(hentCelle(rx - 1, kx - 1));
        rutene[rx][kx].leggTilNabo(hentCelle(rx - 1, kx));
        rutene[rx][kx].leggTilNabo(hentCelle(rx - 1, kx + 1));

        rutene[rx][kx].leggTilNabo(hentCelle(rx + 1, kx));
        rutene[rx][kx].leggTilNabo(hentCelle(rx + 1, kx - 1));
        rutene[rx][kx].leggTilNabo(hentCelle(rx, kx - 1));
    }

    public void kobleAlleCeller() {
        for (int rx = 0; rx < antRader; rx++) {
            for (int kx = 0; kx < antKolonner; kx++) {
                settNaboer(rx, kx);
            }
        }
    }

    public int antallLevende() {
        int antallLevende = 0;
        for (int rx = 0; rx < antRader; rx++) {
            for (int kx = 0; kx < antKolonner; kx++) {
                if (rutene[rx][kx].erLevende()) {
                    antallLevende++;
                }
            }
        }
        return antallLevende;
    }
}