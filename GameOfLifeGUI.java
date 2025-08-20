import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOfLifeGUI {

    static int antRader = 8;
    static int antKolonner = 8;
    static int antl;

    public static JPanel hovedPanel;
    public static JPanel headPanel;
    public static JPanel bodyPanel;
    public static JLabel label;
    public static Rutenett rutenett;
    public static JPanel panelRutenett;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        lagRutenett();
        Viewer();
    }

    public static void Viewer() {
        JFrame vindu = new JFrame("Game of Life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setLayout(new BorderLayout());
        vindu.pack();
        vindu.setSize(400, 315);

        label = new JLabel("Antall levende:   " + antl);

        vindu.add(hovedPanel);
        hovedPanel.add(headPanel);
        hovedPanel.add(bodyPanel);

        headPanel.setPreferredSize(new Dimension(375, 36));
        bodyPanel.setPreferredSize(new Dimension(352, 218));

        headPanel.setBackground(Color.white);
        bodyPanel.setBackground(Color.gray);

        headPanel.setLayout(new GridLayout(1, 3));

        headPanel.add(label);
        class StoppKnapp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        class StartKnapp extends JButton {

            public StartKnapp() {
                super("start");
                super.addActionListener(new StartKnappBehandler());
            }

            class StartKnappBehandler implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        return;
                    }

                    Component[] comp = panelRutenett.getComponents();
                    ArrayList<Celle> lst = new ArrayList<>();

                    for (int rad = 0; rad < antRader; rad++) {
                        for (int kol = 0; kol < antKolonner; kol++) {
                            Celle celle = rutenett.rutene[rad][kol];
                            celle.tellLevendeNaboer();
                            celle.oppdaterStatus();
                            lst.add(celle);

                        }
                    }
                    for (int i = 0; i < comp.length; i++) {
                        ((JButton) comp[i]).setText(String.valueOf(lst.get(i).hentStatusTegn()));
                    }
                    label.setText("Antall levende:   " + rutenett.antallLevende());
                }
            }
        }

        StartKnapp nStartKnapp = new StartKnapp();
        headPanel.add(nStartKnapp);

        JButton avsluttBtn = new JButton("Avslutt");
        avsluttBtn.addActionListener(new StoppKnapp());
        headPanel.add(avsluttBtn);

        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }

    public static void lagRutenett() {
        hovedPanel = new JPanel();
        headPanel = new JPanel();
        bodyPanel = new JPanel();

        panelRutenett = new JPanel();
        panelRutenett.setLayout(new GridLayout(antRader, antKolonner));

        class GameOfLifeKnapp extends JButton {
            private Celle celle;

            public GameOfLifeKnapp(Celle celle) {
                super(String.valueOf(celle.hentStatusTegn()));
                this.celle = celle;
                super.addActionListener(new KnappBehandler());
            }

            class KnappBehandler implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (celle.erLevende()) {
                        celle.settDoed();
                        setText(String.valueOf(celle.hentStatusTegn()));
                        label.setText("Antall levende:   " + rutenett.antallLevende());
                    } else {
                        celle.settLevende();
                        setText(String.valueOf(celle.hentStatusTegn()));
                        label.setText("Antall levende:   " + rutenett.antallLevende());
                    }

                }
            }
        }

        for (int rad = 0; rad < antRader; rad++) {
            for (int kol = 0; kol < antKolonner; kol++) {
                Celle celle = rutenett.rutene[rad][kol];
                GameOfLifeKnapp rutenettKnapp = new GameOfLifeKnapp(celle);
                panelRutenett.add(rutenettKnapp);
            }
        }
        antl = rutenett.antallLevende();

        bodyPanel.add(panelRutenett, BorderLayout.CENTER);
    }
}
