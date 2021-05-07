package de.idrinth.waraddonclient.gui;

import de.idrinth.waraddonclient.backup.Backup;
import de.idrinth.waraddonclient.implementation.list.Tag;
import de.idrinth.waraddonclient.interfaces.model.Addon;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import net.lingala.zip4j.exception.ZipException;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import de.idrinth.waraddonclient.configuration.Version;
import de.idrinth.waraddonclient.gui.tablefilter.TextCategory;
import de.idrinth.waraddonclient.implementation.model.AddonSettings;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends javax.swing.JFrame {

    private TableRowSorter sorter;

    private Addon activeAddon = new de.idrinth.waraddonclient.implementation.model.NoAddon();

    private String language = "en";

    private Tag tagList;

    private static final String BASE_TITLE = "Idrinth's WAR Addon Client";

    private final Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

    /**
     * Creates new form Wrapper
     */
    public Window() {
        ThemeManager.init(prefs);
        initComponents();
        ThemeManager.addTo(jMenu5, prefs);
        finishGuiBuilding();
        processPosition();
    }

    public JTable getAddonTable() {
        return AddonList;
    }

    /**
     * adjustments to the autogenerated content
     *
     * @todo make it possible to handle most of this via an addon-like object
     */
    private void finishGuiBuilding() {
        AddonList.getSelectionModel().addListSelectionListener(new tableListener());
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/logo.png")));
        sorter = new TableRowSorter(AddonList.getModel());
        AddonList.setRowSorter(sorter);
        Description.addHyperlinkListener(new hyperlinkListener());
        localVersion.setText(Version.getLocalVersion());
        tagList = new Tag(Tags);
        new java.lang.Thread(tagList).start();
        new java.lang.Thread(new Version()).start();
        (new tableListener()).updateUi();
    }

    public void newFilter() {
        try {
            RowFilter rf = new TextCategory(Search.getText(), tagList.getActiveTags());
            sorter.setRowFilter(rf);
        } catch (java.util.regex.PatternSyntaxException exception) {
            de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
        }
    }

    /**
     * checks if this program is in the correct place
     */
    private void processPosition() {
        if (new java.io.File("./WAR.exe").exists()) {
            return;
        }
        File config = new File("./WARAddonClient.cfg");
        String path = "./";
        if (!config.exists()) {
            JOptionPane.showMessageDialog(this, "No WAR.exe found, please select it");
            JFileChooser j = new javax.swing.JFileChooser();
            int r = j.showOpenDialog(this);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                path = j.getSelectedFile().getParent();
                try {
                    if (!config.createNewFile()) {
                        throw new IOException("Unable to create config file");
                    }
                    try ( FileWriter writer = new FileWriter(config.getAbsoluteFile())) {
                        writer.write("path=" + path);
                    }
                } catch (IOException exception) {
                    de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
                }
            }
        }
        if (config.exists()) {
            //load from file
            try {
                try ( Scanner scanner = new Scanner(config)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.startsWith("path")) {
                            path = line.split("=")[1];
                        }
                    }
                }
                System.setProperty("user.dir", path);
                return;
            } catch (FileNotFoundException exception) {
                de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
            }
        }
        if (config.exists() && !config.delete()) {
            de.idrinth.factory.Logger.build().log("Unable to delete config file", de.idrinth.Logger.levelError);
        }
        exitWithError("Unable to find WAR.exe");
    }

    /**
     * Exits after displaying an error message
     *
     * @param error
     */
    public void exitWithError(String error) {
        JOptionPane.showMessageDialog(this, error);
        this.dispose();
        Runtime.getRuntime().exit(0);
    }

    public javax.swing.JLabel getRemoteVersionLabel() {
        return remoteVersion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSplitPane2 = new javax.swing.JSplitPane();
        leftSide = new javax.swing.JPanel();
        Search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddonList = new javax.swing.JTable();
        DeleteSearch = new javax.swing.JButton();
        UpdateAll = new javax.swing.JButton();
        rightSide = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Description = new javax.swing.JEditorPane();
        InstallButton = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        localVersion = new javax.swing.JLabel();
        remoteVersion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CurTags = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        UploadReason = new javax.swing.JTextArea();
        UploadUrl = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        UploadEnable = new javax.swing.JCheckBox();
        label1 = new java.awt.Label();
        UploadFile = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();
        Tags = new javax.swing.JMenu();
        Tools = new javax.swing.JMenu();
        CreateBackup = new javax.swing.JMenuItem();
        RestoreBackup = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        English = new javax.swing.JRadioButtonMenuItem();
        Deutsch = new javax.swing.JRadioButtonMenuItem();
        Francais = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Refresh1 = new javax.swing.JCheckBoxMenuItem();
        Refresh2 = new javax.swing.JCheckBoxMenuItem();
        Refresh3 = new javax.swing.JCheckBoxMenuItem();
        Refresh4 = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        Links = new javax.swing.JMenu();
        Guilded = new javax.swing.JMenuItem();
        BuyMeACoffee = new javax.swing.JMenuItem();
        Source = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setToolTipText("");
        jSplitPane2.setMinimumSize(new java.awt.Dimension(300, 200));
        jSplitPane2.setName(""); // NOI18N

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        jScrollPane2.setMaximumSize(null);

        AddonList.setAutoCreateRowSorter(true);
        AddonList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Name", "Version", "Installed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AddonList.setColumnSelectionAllowed(true);
        AddonList.setMaximumSize(null);
        AddonList.setName(""); // NOI18N
        AddonList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(AddonList);
        AddonList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        DeleteSearch.setText("X");
        DeleteSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteSearchMouseClicked(evt);
            }
        });

        UpdateAll.setText("Update All");
        UpdateAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateAllMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout leftSideLayout = new javax.swing.GroupLayout(leftSide);
        leftSide.setLayout(leftSideLayout);
        leftSideLayout.setHorizontalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UpdateAll)
                .addGap(0, 11, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        leftSideLayout.setVerticalGroup(
            leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSideLayout.createSequentialGroup()
                .addGroup(leftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteSearch)
                    .addComponent(UpdateAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(leftSide);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(null);

        Description.setEditable(false);
        Description.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(Description);

        InstallButton.setText("(Re)Install");
        InstallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstallButtonActionPerformed(evt);
            }
        });

        RemoveButton.setText("Remove");
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveButtonActionPerformed(evt);
            }
        });

        Title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        localVersion.setText("0.0.0");
        localVersion.setToolTipText("Local Version");

        remoteVersion.setText("0.0.0");
        remoteVersion.setToolTipText("Remote Version");

        jLabel3.setText("/");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(InstallButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(RemoveButton)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(localVersion)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remoteVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CurTags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoveButton)
                    .addComponent(InstallButton)
                    .addComponent(Title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localVersion)
                    .addComponent(remoteVersion)
                    .addComponent(jLabel3)
                    .addComponent(CurTags))
                .addGap(6, 6, 6))
        );

        rightSide.addTab("Main", jPanel1);

        UploadReason.setEditable(false);
        UploadReason.setColumns(20);
        UploadReason.setRows(5);
        jScrollPane3.setViewportView(UploadReason);

        UploadUrl.setEditable(false);
        UploadUrl.setToolTipText("");

        jLabel1.setText("Upload URL");

        UploadEnable.setText("Allow Upload");
        UploadEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadEnableActionPerformed(evt);
            }
        });

        label1.setText("File to Upload");

        UploadFile.setEditable(false);
        UploadFile.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(UploadEnable)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(UploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UploadUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UploadEnable)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        rightSide.addTab("Settings", jPanel2);

        jSplitPane2.setRightComponent(rightSide);

        jMenu4.setText("File");

        About.setText("About");
        About.setToolTipText("");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        jMenu4.add(About);

        jMenuBar1.add(jMenu4);

        Tags.setText("Tags");
        jMenuBar1.add(Tags);

        Tools.setText("Tools");

        CreateBackup.setText("Create Backup");
        CreateBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBackupActionPerformed(evt);
            }
        });
        Tools.add(CreateBackup);

        RestoreBackup.setText("Restore Backup");
        RestoreBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestoreBackupActionPerformed(evt);
            }
        });
        Tools.add(RestoreBackup);

        jMenuBar1.add(Tools);

        jMenu3.setText("Settings");

        jMenu1.setText("Language");

        English.setSelected(true);
        English.setText("English");
        English.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnglishActionPerformed(evt);
            }
        });
        jMenu1.add(English);

        Deutsch.setText("Deutsch");
        Deutsch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeutschActionPerformed(evt);
            }
        });
        jMenu1.add(Deutsch);

        Francais.setText("Francais");
        Francais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrancaisActionPerformed(evt);
            }
        });
        jMenu1.add(Francais);

        jMenu3.add(jMenu1);

        jMenu2.setText("Auto-Refresh");

        Refresh1.setText("15min");
        Refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh1ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh1);

        Refresh2.setText("30min");
        Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh2ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh2);

        Refresh3.setText("1h");
        Refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh3ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh3);

        Refresh4.setSelected(true);
        Refresh4.setText("3h");
        Refresh4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh4ActionPerformed(evt);
            }
        });
        jMenu2.add(Refresh4);

        jMenu3.add(jMenu2);

        jMenu5.setText("Theme");
        jMenu3.add(jMenu5);

        jMenuBar1.add(jMenu3);

        Links.setText("Links");

        Guilded.setText("Guilded: Idrinth's Addons");
        Guilded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuildedActionPerformed(evt);
            }
        });
        Links.add(Guilded);

        BuyMeACoffee.setText("BuyMeACoffee");
        BuyMeACoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyMeACoffeeActionPerformed(evt);
            }
        });
        Links.add(BuyMeACoffee);

        Source.setText("GitHub:WARAddonClient");
        Source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SourceActionPerformed(evt);
            }
        });
        Links.add(Source);

        jMenuBar1.add(Links);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The button install was clicked
     *
     * @param evt
     */
    private void InstallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstallButtonActionPerformed
        try {
            activeAddon.install();
            updateList();
            JOptionPane.showMessageDialog(this, "The requested Addon was installed.");
        } catch (java.lang.Exception exception) {
            de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
            JOptionPane.showMessageDialog(this, "Sadly Installing failed, check if the folder is writeable.");
        }
    }//GEN-LAST:event_InstallButtonActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        newFilter();
    }//GEN-LAST:event_SearchActionPerformed

    /**
     * the searchtext was changed
     *
     * @param evt
     */
    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        newFilter();
    }//GEN-LAST:event_SearchKeyReleased

    /**
     * delete addon was clicked
     *
     * @param evt
     */
    private void RemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveButtonActionPerformed
        try {
            activeAddon.uninstall();
        } catch (Exception exception) {
            de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
        }
        updateList();
    }//GEN-LAST:event_RemoveButtonActionPerformed

    /**
     *
     * @param evt
     */
    private void UploadEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploadEnableActionPerformed
        activeAddon.getUploadData().setEnabled(UploadEnable.isSelected());
    }//GEN-LAST:event_UploadEnableActionPerformed

    /**
     * changes language to english
     *
     * @param evt
     */
    private void EnglishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnglishActionPerformed
        changeLanguageTo("en");
    }//GEN-LAST:event_EnglishActionPerformed

    /**
     * @todo implement
     * @param evt
     */
    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this, "This software is provided for free by Björn Büttner.\n"
                + "If you have ideas or bugs please add them to the issues at GitHub:WARAddonClient.\n"
                + "If you want to buy me a coffee you can do so at Buy me a coffee.\n"
                + "If you need support, go to Guilded:Idrinth's Addons", "About", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutActionPerformed

    /**
     * changes language to german
     *
     * @param evt
     */
    private void DeutschActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeutschActionPerformed
        changeLanguageTo("de");
    }//GEN-LAST:event_DeutschActionPerformed

    /**
     * changes language to french
     *
     * @param evt
     */
    private void FrancaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrancaisActionPerformed
        changeLanguageTo("fr");
    }//GEN-LAST:event_FrancaisActionPerformed

    /**
     * changes refresh duration
     *
     * @param evt
     */
    private void Refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh1ActionPerformed
        changeRefreshTo(15);
    }//GEN-LAST:event_Refresh1ActionPerformed

    /**
     * changes refresh duration
     *
     * @param evt
     */
    private void Refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh2ActionPerformed
        changeRefreshTo(30);
    }//GEN-LAST:event_Refresh2ActionPerformed

    /**
     * changes refresh duration
     *
     * @param evt
     */
    private void Refresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh3ActionPerformed
        changeRefreshTo(60);
    }//GEN-LAST:event_Refresh3ActionPerformed

    /**
     * changes refresh duration
     *
     * @param evt
     */
    private void Refresh4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh4ActionPerformed
        changeRefreshTo(180);
    }//GEN-LAST:event_Refresh4ActionPerformed

    private void DeleteSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteSearchMouseClicked
        Search.setText("");
        SearchActionPerformed(new java.awt.event.ActionEvent(evt.getSource(), 1001, "reset"));
    }//GEN-LAST:event_DeleteSearchMouseClicked

    private void UpdateAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateAllMouseClicked
        de.idrinth.waraddonclient.implementation.list.Addon addonList = de.idrinth.waraddonclient.factory.AddonList.build();
        int errors = 0;
        int count = 0;
        for (int i = 0; i < addonList.size(); i++) {
            Addon addon = addonList.get(i);
            if (addon.getStatus().equals("X")) {
                count++;
                try {
                    addon.install();
                } catch (Exception ex) {
                    errors++;
                    de.idrinth.factory.Logger.build().log(ex, de.idrinth.Logger.levelError);
                }
            }
        }
        updateList();
        if (count == 0) {
            JOptionPane.showMessageDialog(this, "No Add-ons to update.");
        } else if (errors == 0) {
            JOptionPane.showMessageDialog(this, "All " + count + " Add-ons were updated.");
        } else {
            JOptionPane.showMessageDialog(this, "Updating " + errors + " out of " + count + " Add-ons failed.");
        }
    }//GEN-LAST:event_UpdateAllMouseClicked

    private void CreateBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBackupActionPerformed
        try {
            Backup.create();
            JOptionPane.showMessageDialog(this, "Saved your profile and addons in backups.");
        } catch (ZipException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Failed to save your profile and addons.");
        }
    }//GEN-LAST:event_CreateBackupActionPerformed

    private void SourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SourceActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://github.com/Idrinth/WARAddonClient/"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SourceActionPerformed

    private void BuyMeACoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyMeACoffeeActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://buymeacoffee.com/idrinth"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuyMeACoffeeActionPerformed

    private void GuildedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuildedActionPerformed
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://guilded.gg/Idrinths-Addons/"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GuildedActionPerformed

    private void RestoreBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestoreBackupActionPerformed
        FileDialog dialog = new java.awt.FileDialog(this, "Select backup", java.awt.FileDialog.LOAD);
        dialog.setVisible(true);
        if (dialog.getFile() != null) {
            if (!dialog.getFile().endsWith(".zip")) {
                JOptionPane.showMessageDialog(this, "Backup has to be a zip-File.");
                return;
            }
            try {
                Backup.restore(new java.io.File(dialog.getDirectory() + "/" + dialog.getFile()));
                JOptionPane.showMessageDialog(this, "Backup restored.");
            } catch (ZipException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Couldn't restore Backup.");
            }
        }
    }//GEN-LAST:event_RestoreBackupActionPerformed

    /**
     * handles actual changing of languages
     *
     * @param lang
     */
    private void changeLanguageTo(String lang) {
        if (lang.equals(language)) {
            return;
        }
        English.setSelected("en".equals(lang));
        Deutsch.setSelected("de".equals(lang));
        Francais.setSelected("fr".equals(lang));
        language = lang;
        if (activeAddon != null) {
            Description.setText(activeAddon.getDescription(language));
        }
    }

    /**
     * changes actual refresh settings
     *
     * @param dur
     */
    private void changeRefreshTo(int dur) {
        Refresh1.setSelected(dur == 15);
        Refresh2.setSelected(dur == 30);
        Refresh3.setSelected(dur == 60);
        Refresh4.setSelected(dur == 180);
        de.idrinth.waraddonclient.factory.AddonList.build().setDuration(dur);
    }

    /**
     * updates addon list
     */
    private void updateList() {
        de.idrinth.waraddonclient.implementation.list.Addon addons = de.idrinth.waraddonclient.factory.AddonList.build();
        for (int position = 0; position < AddonList.getRowCount(); position++) {
            de.idrinth.waraddonclient.implementation.model.Addon addon = addons.get(AddonList.convertRowIndexToModel(position));
            AddonList.setValueAt(addon.getInstalled(), position, 3);
            AddonList.setValueAt(addon.getStatus(), position, 0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JTable AddonList;
    private javax.swing.JMenuItem BuyMeACoffee;
    private javax.swing.JMenuItem CreateBackup;
    private javax.swing.JLabel CurTags;
    private javax.swing.JButton DeleteSearch;
    private javax.swing.JEditorPane Description;
    private javax.swing.JRadioButtonMenuItem Deutsch;
    private javax.swing.JRadioButtonMenuItem English;
    private javax.swing.JRadioButtonMenuItem Francais;
    private javax.swing.JMenuItem Guilded;
    private javax.swing.JButton InstallButton;
    private javax.swing.JMenu Links;
    private javax.swing.JCheckBoxMenuItem Refresh1;
    private javax.swing.JCheckBoxMenuItem Refresh2;
    private javax.swing.JCheckBoxMenuItem Refresh3;
    private javax.swing.JCheckBoxMenuItem Refresh4;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JMenuItem RestoreBackup;
    private javax.swing.JTextField Search;
    private javax.swing.JMenuItem Source;
    private javax.swing.JMenu Tags;
    private javax.swing.JLabel Title;
    private javax.swing.JMenu Tools;
    private javax.swing.JButton UpdateAll;
    private javax.swing.JCheckBox UploadEnable;
    private javax.swing.JTextField UploadFile;
    private javax.swing.JTextArea UploadReason;
    private javax.swing.JTextField UploadUrl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane2;
    private java.awt.Label label1;
    private javax.swing.JPanel leftSide;
    private javax.swing.JLabel localVersion;
    private javax.swing.JLabel remoteVersion;
    private javax.swing.JTabbedPane rightSide;
    // End of variables declaration//GEN-END:variables

    class hyperlinkListener implements HyperlinkListener {

        @Override
        public void hyperlinkUpdate(HyperlinkEvent event) {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(event.getEventType())) {
                try {
                    Desktop.getDesktop().browse(event.getURL().toURI());
                } catch (java.net.URISyntaxException | java.io.IOException exception) {
                    de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
                }
            }

        }
    }

    class tableListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            try {
                activeAddon = de.idrinth.waraddonclient.factory.AddonList.build().get(AddonList.convertRowIndexToModel(AddonList.getSelectedRow()));
            } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
                de.idrinth.factory.Logger.build().log(exception, de.idrinth.Logger.levelError);
                return;
            }
            if (activeAddon == null) {
                return;
            }
            updateUi();
        }

        /**
         * Updates the ui to show the current addon's data
         */
        public void updateUi() {
            boolean isAnAddon = !"".equals(activeAddon.getVersion());
            Description.setText(activeAddon.getDescription(language));
            Title.setText(activeAddon.getName());
            InstallButton.setEnabled(isAnAddon);
            RemoveButton.setEnabled(isAnAddon);
            setTitle(activeAddon.getName() + " - " + BASE_TITLE + " " + Version.getLocalVersion());
            if (!isAnAddon) {
                return;
            }
            AddonSettings settings = activeAddon.getUploadData();
            rightSide.setEnabledAt(1, settings.showSettings());
            UploadReason.setText(settings.getReason());
            UploadUrl.setText(settings.getUrl());
            UploadFile.setText(settings.getFile());
            UploadEnable.setSelected(settings.isEnabled());
            String taglist = "Tagged: ";
            taglist = activeAddon.getTags().stream().map((tagname) -> tagname + ", ").reduce(taglist, String::concat);
            CurTags.setText(taglist.substring(0, taglist.length() - 2));
        }
    }
}
