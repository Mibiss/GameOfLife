public class Verden {
    Rutenett rutenett;
    int genNr = 0;

    public Verden(int antRader, int antKolonner) {
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public Celle[][] hentRutenett() {
        return rutenett.hentRutenett();
    }

    public void tegn() {
        rutenett.tegnRutenett();
    }

    public void oppdatering() {

        for (int rx = 0; rx < rutenett.antRader; ++rx) {
            for (int kx = 0; kx < rutenett.antKolonner; ++kx) {

                rutenett.rutene[rx][kx].tellLevendeNaboer();
                // rutenett.rutene[rx][kx].oppdaterStatus();

            }
        }
        for (int rx = 0; rx < rutenett.antRader; ++rx) {
            for (int kx = 0; kx < rutenett.antKolonner; ++kx) {

                // rutenett.rutene[rx][kx].tellLevendeNaboer();
                rutenett.rutene[rx][kx].oppdaterStatus();

            }
        }
        genNr++;
    }
}
