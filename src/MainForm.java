import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
        import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame{
    private JPanel panelMain;
    private JCheckBox checkBox1;
    private JButton addBt;
    private JButton zeSouboruBt;
    private JTextField textField1;
    private JTextField textField2;
    private List<Integer> seznamCisel = new ArrayList<>();
    private File selectedFile;
    public MainForm(){
        setContentPane(panelMain);
        setTitle("Test: Seznam čísel");
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        int soucetCisel = 0;
        for (int i : seznamCisel){
            soucetCisel += i;
        }
        textField2.setText(String.valueOf(soucetCisel));

        addBt.addActionListener(e->{pridejCiclo();});
        zeSouboruBt.addActionListener(e->{choooseFile();});

    }
    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu pridej = new JMenu("Přidej");
        menuBar.add(pridej);

        JMenuItem cislo1 = new JMenuItem("121");
        pridej.add(cislo1);
        cislo1.addActionListener(e->{seznamCisel.add(121);});

        JMenuItem cislo2 = new JMenuItem("1024");
        pridej.add(cislo2);
        cislo2.addActionListener(e->{seznamCisel.add(1024);});

        JMenuItem reset = new JMenuItem("Reset");
        pridej.add(reset);
        reset.addActionListener(e->{
            seznamCisel.clear();
            textField2.setText("0");
        });
    }
    public void pridejCiclo(){
        try {
            int cislo = Integer.parseInt(textField1.getText());
            seznamCisel.add(cislo);
            int soucetCisel = 0;
            for (int i : seznamCisel) {
                soucetCisel += i;
            }
            textField2.setText(String.valueOf(soucetCisel));
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(MainForm.this,"Nezadali jste číslo."+ ex.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
        }


    }
    public void choooseFile(){
        JFileChooser fc = new JFileChooser(".");
        int result = fc.showOpenDialog(this);
        if( result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            readFiel(selectedFile);
        }
    }
    public void readFiel(File selectedFile){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            int soucetCisel = 0;
            while (sc.hasNextLine()){
                String radek = sc.nextLine();
                Integer cislo = Integer.parseInt(radek);
                seznamCisel.add(cislo);
                soucetCisel += cislo;
            }
            textField2.setText(String.valueOf(soucetCisel));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor: "+selectedFile+" nebyl nalezen.");
        }
    }
}
