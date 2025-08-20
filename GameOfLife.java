public class GameOfLife {
    public static void main(String[] args) {
        Verden verden = new Verden(8, 12);
        System.out.println("Dette er verden!");
        verden.tegn();
        System.out.println("Antall levende: " + verden.rutenett.antallLevende());

        for (int i = 0; i < 3; ++i) {
            verden.oppdatering();
            System.out.println();
            System.out.println("Dette er generasjon: " + (i + 1));
            verden.tegn();
            System.out.println("Antall levende: " + verden.rutenett.antallLevende());
        }
    }
}
