
import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class DOMTreeFrame extends JFrame {

    DocumentBuilder builder;

    public DOMTreeFrame() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem(("Open"));
        openItem.addActionListener(event -> openFile());

        JMenuItem closeItem = new JMenuItem(("Exit"));
        closeItem.addActionListener(event -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(closeItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("dom"));
        chooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));

        int r = chooser.showOpenDialog(this);
        if (r!= JFileChooser.APPROVE_OPTION) return;

        final File file = chooser.getSelectedFile();

        new SwingWorker<Document, Void>() {
            protected Document doInBackground() throws Exception {
                if (builder == null) {
                    builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                }
                return builder.parse(file);
            }

            protected void done() {
                try {
                    Document doc = get();
                    JTree tree = new JTree((new DOMTreeModel(doc)));
                    tree.setCellRenderer(new DOMTreeCellRenderer());

                    setContentPane(new JScrollPane(tree));
                    validate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(DOMTreeFrame.this, e);
                }
            }
        }.execute();


    }
}
