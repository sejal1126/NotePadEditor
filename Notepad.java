package NotepadEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Notepad implements ActionListener {
    JFrame jf,replaceFrame;
    File filee;
    JFileChooser filechooser;

    String tittle="Untitled - Notepad";
    JMenuBar mb;
    JMenu fileMenu ,EditMenu,FormatMenu,HelpMenu;
    JMenuItem newItem, openItem, saveItem, saveAsItem, pageSetupItem, printItem, exitItem,EditItemCut,EditItemCopy;
    JMenuItem EditItempaste,Editreplace,Editdatetime,Formatfont,Formatfontcolor,FormatTextAreaColor;
    JTextArea ta;
    JTextField ts1,ts;
    JButton jb;
    public Notepad() {
        try {
            // Set Windows look and feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            filechooser = new JFileChooser();
            jf = new JFrame(tittle);
            jf.setSize(500, 500);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Menu setup
            mb = new JMenuBar();
            fileMenu = new JMenu("File");

            EditMenu = new JMenu("Edit");

            FormatMenu = new JMenu("Format");

            HelpMenu = new JMenu("Help");



            // Create menu items
            newItem = new JMenuItem("New");
            newItem.addActionListener(this);
            openItem = new JMenuItem("Open");
            openItem.addActionListener(this);
            saveItem = new JMenuItem("Save");
            saveItem.addActionListener(this);
            saveAsItem = new JMenuItem("Save As");
            saveAsItem.addActionListener(this);
            pageSetupItem = new JMenuItem("Page Setup");
            pageSetupItem.addActionListener(this);
            printItem = new JMenuItem("Print");
            printItem.addActionListener(this);
            exitItem = new JMenuItem("Exit");
            exitItem.addActionListener(this);

            EditItemCut = new JMenuItem("Cut");
            EditItemCut.addActionListener(this);
            EditItemCopy = new JMenuItem("Copy");
            EditItemCopy.addActionListener(this);
            EditItempaste = new JMenuItem("Paste");
            EditItempaste.addActionListener(this);
            Editreplace = new JMenuItem("Replace");
            Editreplace.addActionListener(this);
            Editdatetime = new JMenuItem("DateTime");
            Editdatetime.addActionListener(this);

            Formatfont = new JMenuItem("Font");
            Formatfont.addActionListener(this);
            Formatfontcolor = new JMenuItem("Fontcolor");
            Formatfontcolor.addActionListener(this);
            FormatTextAreaColor = new JMenuItem ("Textarea Color");
            FormatTextAreaColor.addActionListener(this);




            // Add keyboard shortcuts (accelerators)
            newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
            openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
            saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
            saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
            printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
            exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

            // Add items to File menu
            fileMenu.add(newItem);
            fileMenu.add(openItem);
            fileMenu.add(saveItem);
            fileMenu.add(saveAsItem);
            fileMenu.addSeparator();
            fileMenu.add(printItem);
            fileMenu.add(pageSetupItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);

            EditMenu.add(EditItemCut);
            EditMenu.add(EditItemCopy);
            EditMenu.add(EditItempaste);
            EditMenu.add(Editreplace);
            EditMenu.add(Editdatetime);

            FormatMenu.add(Formatfont);
            FormatMenu.add(Formatfontcolor);
            FormatMenu.add(FormatTextAreaColor);


            mb.add(fileMenu);
            mb.add(EditMenu);
            mb.add(FormatMenu);
            mb.add(HelpMenu);
            jf.setJMenuBar(mb);

            // Text area setup
            ta = new JTextArea();
            JScrollPane scroll = new JScrollPane(ta);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jf.add(scroll);

            // Set window icon (ensure image is in correct path in resources)
            URL iconURL = getClass().getResource("/NotepadEditor/notepad.png"); // Path from resources root
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                jf.setIconImage(icon.getImage());
            } else {
                System.err.println("Icon not found.");
            }

            jf.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace(); // Print error for debugging
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newItem)
        {
            newnotepad();
        }

        if(e.getSource()==exitItem)
        {
            System.exit(0);
        }
        if(e.getSource()==saveAsItem)
        {
            saveAsItem();
        }

        if(e.getSource()==openItem)
        {
            OpenItem();
        }
        if(e.getSource()==saveItem)
        {
            saveItem();
        }

        if(e.getSource()==pageSetupItem)
        {
            printsetup();
        }
        if(e.getSource()==printItem)
        {
            printitem();
        }
        if(e.getSource()==EditItemCut)
        {
            ta.cut();
        }
        if(e.getSource()==EditItemCopy) {
            ta.copy();
        }

        if(e.getSource()==EditItempaste)
        {
            ta.paste();
        }

        if(e.getSource()==Editreplace)
        {
            replaceframe();
        }
        if(e.getSource()==jb)
        {
            replace();
        }
        if(e.getSource()==Editdatetime)
        {
            setdatetime();
        }
        if(e.getSource()==Formatfontcolor)
        {
            fontcolorchange();
        }

        if(e.getSource()==FormatTextAreaColor)
        {
            setTextColor();
        }
    }

    public void fontcolorchange()
    {
           Color c= JColorChooser.showDialog(jf,"Select Font color",Color.black);
           ta.setForeground(c);
    }

    public void setTextColor()
    {
        Color c= JColorChooser.showDialog(jf,"Select Text color",Color.white);
        ta.setBackground(c);
    }

    public void setdatetime()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

        String formattedDateTime = now.format(formatter);
        ta.append(formattedDateTime);

    }

    public void replace()
    {
        String find_what = ts.getText();
        String replace_with=ts1.getText();
        String text = ta.getText();
        String new_text= text.replace(find_what,replace_with);
        ta.setText(new_text);
        replaceFrame.setVisible(false);





    }
    public void replaceframe()
    {
         replaceFrame = new JFrame();
        replaceFrame.setSize(500,300);
        replaceFrame.setLayout(null);

        JLabel jl = new JLabel("Find What : ");
        jl.setBounds(50,50,80,40);
        replaceFrame.add(jl);

         ts = new JTextField();
        ts.setBounds(150,50,200,40);
        replaceFrame.add(ts);

        JLabel jl1 = new JLabel("Replace with : ");
        jl1.setBounds(50,100,80,40);
        replaceFrame.add(jl1);

         ts1 = new JTextField();
        ts1.setBounds(150,100,200,40);
        replaceFrame.add(ts1);

        jb = new JButton("Replace");
        jb.addActionListener(this);
        jb.setBounds(170,150,80,40);
        replaceFrame.add(jb);

        replaceFrame.setVisible(true);
    }

    public void newnotepad()
    {
        String str = ta.getText();

        if(!(str.equals(" ")))
        {
            int i = JOptionPane.showConfirmDialog(jf,"Do you want to save the file?");

            if(i==0)
            {
                saveAsItem();

                if(!(jf.getTitle().equals(tittle)))
                {   settittle(tittle);
                    ta.getText();
                }

            }
            else if(i==1)
            {
                ta.setText("");
            }

        }

    }

    public void saveAsItem()
    {

        int i=filechooser.showSaveDialog(jf);
        if(i==0)
        {
            try{
                String str= ta.getText();
                File file = filechooser.getSelectedFile();
                FileOutputStream fs = new FileOutputStream(file);
                fs.write(str.getBytes());
                fs.close();
                settittle(file.getName());
            }
            catch (Exception e)
            {
                System.out.println();
            }



        }

    }

    public void saveItem()
    {
        if(jf.getTitle().equals(tittle))
        {
            saveAsItem();
        }
        else
        {
            try{

                String str = ta.getText();
                File file = filechooser.getSelectedFile();

                FileOutputStream fs = new FileOutputStream(file);

                fs.write(str.getBytes());
                fs.close();
            }
            catch (Exception e)
            {

            }

        }
    }

    public void printitem()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();

        if (pj.printDialog()) {
            try {pj.print();}
            catch (PrinterException exc) {
                System.out.println(exc);
            }
        }

    }
    public void settittle(String tittle)
    {
        jf.setTitle(tittle);
    }
    public void OpenItem()
    {
        int ii;

        int i=filechooser.showOpenDialog(jf);
        if(i==0)
        {
            try{
                 ta.setText("");
                File file = filechooser.getSelectedFile();
                FileInputStream fs = new FileInputStream(file);
                while((ii= fs.read())!=-1)
                {
                   ta.append(String.valueOf((char) ii));
                }
                settittle(filechooser.getSelectedFile().getName());
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }

    }

    public void printsetup()
    {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
    }
    public static void main(String[] args) {
        new Notepad();
    }
}
