package software_acadmy;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class frm_Add_Subject extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm, dtm2;

    public frm_Add_Subject() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, Screensize.width, Screensize.height - 150);

        MainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        /*Declaring Tables*/
        /*Table 1*/
        dtm = new DefaultTableModel();
        tablesubjectintable.setModel(dtm);

        dtm.addColumn("Chapter Name");
        dtm.addColumn("Notes Link");
        dtm.addColumn("Questions Link");
        dtm.addColumn("Programs Link");

        tablesubjectintable.setDefaultEditor(Object.class, null);
        tablesubjectintable.getTableHeader().setBackground(Color.white);
        tablesubjectintable.getTableHeader().setForeground(Color.orange);
        tablesubjectintable.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        /*Table 2*/
        dtm2 = new DefaultTableModel();
        tableSubjectData.setModel(dtm2);

        dtm2.addColumn("Subject Id");
        dtm2.addColumn("Subject Name");
        dtm2.addColumn("Description");

        tableSubjectData.setDefaultEditor(Object.class, null);
        tableSubjectData.getTableHeader().setBackground(Color.white);
        tableSubjectData.getTableHeader().setForeground(Color.orange);
        tableSubjectData.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        ResultSet res2 = db.run_select("Select * From Subject Where Deleted = 'No' Order By SubjectId");
        try {
            while (res2.next()) {
                String si = "EZP_SUB_" + res2.getString("SubjectId");
                String sn = res2.getString("SubjectName");
                String de = res2.getString("Description");

                dtm2.addRow(new Object[]{si, sn, de});
            }
        } catch (Exception e) {
        }

        SubjectId();

        /*Setting Textfields Non Editable*/
        txtNoteslink.setEditable(false);
        txtQuestionLink.setEditable(false);
        txtProgramsLink.setEditable(false);

        /*SettingFocusTraversalKeysCollectionsEmpty*/
        txtSubjectName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtdescription.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtChapterName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtNoteslink.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtQuestionLink.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtProgramsLink.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        btnremoveall.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        btnadd.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        btnremove.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());

        /*Adding Shortcuts*/
        /*Save Shortcut*/
        Action saveaction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Save();
            }
        };
        String save = "Save";
        btnsave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), save);
        btnsave.getActionMap().put(save, saveaction);

        /*Update Shortcut*/
        Action updateaction = new AbstractAction("Update") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        };
        String update = "Update";
        btnupdate.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK), update);
        btnupdate.getActionMap().put(update, updateaction);

        /*Delete Shortcut*/
        Action deleteaction = new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete();
            }
        };
        String delete = "Delete";
        btndelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), delete);
        btndelete.getActionMap().put(delete, deleteaction);

        /*Refresh Shortcut*/
        Action refreshaction = new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Refresh();
            }
        };
        String refresh = "Refresh";
        btnrefresh.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), refresh);
        btnrefresh.getActionMap().put(refresh, refreshaction);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtSubjectName = new javax.swing.JTextField();
        txtSubjectId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtdescription = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableSubjectData = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtChapterName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtQuestionLink = new javax.swing.JTextField();
        txtProgramsLink = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNoteslink = new javax.swing.JTextField();
        btnadd = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();
        btnremoveall = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablesubjectintable = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        MainScrollPane.setHorizontalScrollBar(null);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Subject", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        txtSubjectName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtSubjectName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSubjectNameKeyPressed(evt);
            }
        });

        txtSubjectId.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtSubjectId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubjectIdActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Subject Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Description");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Subject ID");

        txtdescription.setColumns(20);
        txtdescription.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtdescription.setRows(5);
        txtdescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdescriptionKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(txtdescription);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubjectId)
                            .addComponent(txtSubjectName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subject's Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        tableSubjectData.setBackground(new java.awt.Color(0, 51, 102));
        tableSubjectData.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tableSubjectData.setForeground(new java.awt.Color(255, 255, 255));
        tableSubjectData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSubjectData.setRowHeight(20);
        tableSubjectData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSubjectDataMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableSubjectData);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Chapters To Subject", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chapters in This Subject");

        txtChapterName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtChapterName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtChapterNameMouseClicked(evt);
            }
        });
        txtChapterName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtChapterNameKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Programs Link");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Chapter Name ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Questions Link");

        txtQuestionLink.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtQuestionLink.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQuestionLinkFocusGained(evt);
            }
        });
        txtQuestionLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtQuestionLinkMouseClicked(evt);
            }
        });
        txtQuestionLink.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuestionLinkKeyPressed(evt);
            }
        });

        txtProgramsLink.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtProgramsLink.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProgramsLinkFocusGained(evt);
            }
        });
        txtProgramsLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProgramsLinkMouseClicked(evt);
            }
        });
        txtProgramsLink.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProgramsLinkKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Notes Link");

        txtNoteslink.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtNoteslink.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoteslinkFocusGained(evt);
            }
        });
        txtNoteslink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNoteslinkMouseClicked(evt);
            }
        });
        txtNoteslink.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoteslinkKeyPressed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(0, 51, 102));
        btnadd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnadd.setForeground(new java.awt.Color(26, 238, 167));
        btnadd.setText("Add");
        btnadd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        btnadd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnaddKeyPressed(evt);
            }
        });

        btnremove.setBackground(new java.awt.Color(0, 51, 102));
        btnremove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnremove.setForeground(new java.awt.Color(26, 238, 167));
        btnremove.setText("Remove");
        btnremove.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        btnremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveActionPerformed(evt);
            }
        });
        btnremove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnremoveKeyPressed(evt);
            }
        });

        btnremoveall.setBackground(new java.awt.Color(0, 51, 102));
        btnremoveall.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnremoveall.setForeground(new java.awt.Color(26, 238, 167));
        btnremoveall.setText("Remove All");
        btnremoveall.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        btnremoveall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveallActionPerformed(evt);
            }
        });
        btnremoveall.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnremoveallKeyPressed(evt);
            }
        });

        tablesubjectintable.setBackground(new java.awt.Color(0, 51, 102));
        tablesubjectintable.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tablesubjectintable.setForeground(new java.awt.Color(255, 255, 255));
        tablesubjectintable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablesubjectintable.setRowHeight(20);
        tablesubjectintable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesubjectintableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablesubjectintable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel7);

        jButton8.setBackground(new java.awt.Color(0, 51, 102));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(26, 238, 167));
        jButton8.setText("Go");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 51, 102));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(26, 238, 167));
        jButton9.setText("Go");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 51, 102));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(26, 238, 167));
        jButton11.setText("Go");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnremove, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnremoveall))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuestionLink)
                                    .addComponent(txtProgramsLink, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtChapterName)
                                    .addComponent(txtNoteslink, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton9)
                                    .addComponent(jButton11)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton11, jButton8, jButton9});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnadd, btnremove, btnremoveall});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtChapterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNoteslink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtQuestionLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtProgramsLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnremove, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnadd)
                        .addComponent(btnremoveall)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnadd, btnremove, btnremoveall});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton8, jButton9});

        jToolBar2.setRollover(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnsave.setBackground(new java.awt.Color(255, 255, 255));
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave.setForeground(new java.awt.Color(0, 51, 102));
        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Save.png"))); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(255, 255, 255));
        btndelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndelete.setForeground(new java.awt.Color(0, 51, 102));
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Delete.png"))); // NOI18N
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(255, 255, 255));
        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(0, 51, 102));
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Update.png"))); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnrefresh.setBackground(new java.awt.Color(255, 255, 255));
        btnrefresh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnrefresh.setForeground(new java.awt.Color(0, 51, 102));
        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Refresh.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnsave)
                .addGap(18, 18, 18)
                .addComponent(btnupdate)
                .addGap(18, 18, 18)
                .addComponent(btndelete)
                .addGap(18, 18, 18)
                .addComponent(btnrefresh)
                .addContainerGap(754, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btndelete, btnsave, btnupdate});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnupdate)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrefresh))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btndelete, btnsave, btnupdate});

        jToolBar2.add(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainScrollPane.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScrollPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScrollPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        int rc = dtm.getRowCount();
        String et = txtChapterName.getText().toString();

        try {
            for (int i = 0; i < rc; i++) {
                String tv = dtm.getValueAt(i, 0).toString();
                if (tv.compareTo(et) == 0) {
                    JOptionPane.showMessageDialog(this, "Chapter Already Exist !", "Warning", JOptionPane.WARNING_MESSAGE);
                    dtm.removeRow(i);
                }
            }
        } catch (Exception e) {
        }

        String cn = txtChapterName.getText().toString();
        String nl = txtNoteslink.getText().toString();
        String ql = txtQuestionLink.getText().toString();
        String pl = txtProgramsLink.getText().toString();
        dtm.addRow(new Object[]{cn, nl, ql, pl});

    }//GEN-LAST:event_btnaddActionPerformed

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
        int rc = tablesubjectintable.getRowCount();
        String et = txtChapterName.getText().toString();
        for (int i = 0; i < rc; i++) {
            if (dtm.getValueAt(i, 0).toString().compareTo(et) == 0) {
                dtm.removeRow(i);
            }
        }

        int sr = tablesubjectintable.getSelectedRow();
        dtm.removeRow(sr);
    }//GEN-LAST:event_btnremoveActionPerformed

    private void btnremoveallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveallActionPerformed
        dtm.setRowCount(0);
    }//GEN-LAST:event_btnremoveallActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        Update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void tableSubjectDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSubjectDataMouseClicked
        dtm.setRowCount(0);
        int row = tableSubjectData.getSelectedRow();

        txtSubjectId.setText(dtm2.getValueAt(row, 0).toString());
        txtSubjectName.setText(dtm2.getValueAt(row, 1).toString());
        txtdescription.setText(dtm2.getValueAt(row, 2).toString());

        txtChapterName.setText("");
        txtNoteslink.setText("");
        txtProgramsLink.setText("");
        txtQuestionLink.setText("");

        String tempsi = dtm2.getValueAt(row, 0).toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From SubjectData Where SubjectId = '" + si + "'");
        try {
            while (res.next()) {
                String cn = res.getString("ChapterName");
                String nl = res.getString("NotesLink");
                String ql = res.getString("QuestionsLink");
                String pl = res.getString("ProgramsLink");

                dtm.addRow(new Object[]{cn, nl, ql, pl});
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_tableSubjectDataMouseClicked

    private void tablesubjectintableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablesubjectintableMouseClicked
        int row = tablesubjectintable.getSelectedRow();

        txtChapterName.setText(dtm.getValueAt(row, 0).toString());
        txtNoteslink.setText(dtm.getValueAt(row, 1).toString());
        txtQuestionLink.setText(dtm.getValueAt(row, 2).toString());
        txtProgramsLink.setText(dtm.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tablesubjectintableMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        Delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        File file = new File(txtProgramsLink.getText().toString());
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        File file = new File(txtQuestionLink.getText().toString());
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        File file = new File(txtNoteslink.getText().toString());
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txtChapterNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtChapterNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChapterNameMouseClicked

    private void txtNoteslinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoteslinkMouseClicked
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Choose Notes Link");
        f.showOpenDialog(null);
        String Path = f.getSelectedFile().getAbsolutePath();
        txtNoteslink.setText(Path);
    }//GEN-LAST:event_txtNoteslinkMouseClicked

    private void txtQuestionLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQuestionLinkMouseClicked
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Choose Questions Link");
        f.showOpenDialog(null);
        String Path = f.getSelectedFile().getAbsolutePath();
        txtQuestionLink.setText(Path);
    }//GEN-LAST:event_txtQuestionLinkMouseClicked

    private void txtProgramsLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProgramsLinkMouseClicked
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Choose Programs Link");
        f.showOpenDialog(null);
        String Path = f.getSelectedFile().getAbsolutePath();
        txtProgramsLink.setText(Path);
    }//GEN-LAST:event_txtProgramsLinkMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        /*Searching Subject By Subject Name*/
        SubjectId();
        txtdescription.setText("");
        txtChapterName.setText("");
        txtNoteslink.setText("");
        txtQuestionLink.setText("");
        txtProgramsLink.setText("");
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        String input = txtSubjectName.getText().toString();

        ResultSet res = db.run_select("Select * From Subject where SubjectName like '%" + txtSubjectName.getText().toString() + "%' order by SubjectId ");
        try {
            while (res.next()) {
                String SI = res.getString("SubjectId");
                String SI2 = "EZP_SUB_" + res.getString("SubjectId");
                String SN = res.getString("SubjectName");
                String DE = res.getString("Description");
                dtm2.addRow(new Object[]{SI2, SN, DE});
                if (input.toLowerCase().compareTo(SN.toLowerCase()) == 0) {
                    txtSubjectId.setText(SI2);
                    txtSubjectName.setText(SN);
                    txtdescription.setText(DE);
                    ResultSet res2 = db.run_select("Select * From SubjectData Where SubjectId = " + SI + " ");
                    try {
                        while (res2.next()) {
                            String cn = res2.getString("ChapterName");
                            String nl = res2.getString("NotesLink");
                            String ql = res2.getString("QuestionsLink");
                            String pl = res2.getString("ProgramsLink");
                            dtm.addRow(new Object[]{cn, nl, ql, pl});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        /*Searching Subject By Subject Description*/
        SubjectId();
        txtSubjectName.setText("");
        txtChapterName.setText("");
        txtNoteslink.setText("");
        txtQuestionLink.setText("");
        txtProgramsLink.setText("");
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        String input = txtdescription.getText().toString();

        ResultSet res = db.run_select("Select * From Subject where Description like '%" + txtdescription.getText().toString() + "%' order by SubjectId ");
        try {
            while (res.next()) {
                String SI = res.getString("SubjectId");
                String SI2 = "EZP_SUB_" + res.getString("SubjectId");
                String SN = res.getString("SubjectName");
                String DE = res.getString("Description");
                dtm2.addRow(new Object[]{SI2, SN, DE});
                if (input.toLowerCase().compareTo(DE.toLowerCase()) == 0) {
                    txtSubjectId.setText(SI2);
                    txtSubjectName.setText(SN);
                    txtdescription.setText(DE);
                    ResultSet res2 = db.run_select("Select * From SubjectData Where SubjectId = " + SI + " ");
                    try {
                        while (res2.next()) {
                            String cn = res2.getString("ChapterName");
                            String nl = res2.getString("NotesLink");
                            String ql = res2.getString("QuestionsLink");
                            String pl = res2.getString("ProgramsLink");
                            dtm.addRow(new Object[]{cn, nl, ql, pl});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtSubjectIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubjectIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubjectIdActionPerformed

    private void txtSubjectNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubjectNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtdescription.requestFocus();
            txtdescription.setTabSize(0);
        }
    }//GEN-LAST:event_txtSubjectNameKeyPressed

    private void txtdescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescriptionKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtChapterName.requestFocus();
        }
    }//GEN-LAST:event_txtdescriptionKeyPressed

    private void txtChapterNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChapterNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtNoteslink.requestFocus();
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Choose Notes Link");
            f.showOpenDialog(null);
            String Path = f.getSelectedFile().getAbsolutePath();
            txtNoteslink.setText(Path);
        }
    }//GEN-LAST:event_txtChapterNameKeyPressed

    private void txtNoteslinkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteslinkFocusGained

    }//GEN-LAST:event_txtNoteslinkFocusGained

    private void txtQuestionLinkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuestionLinkFocusGained

    }//GEN-LAST:event_txtQuestionLinkFocusGained

    private void txtProgramsLinkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProgramsLinkFocusGained

    }//GEN-LAST:event_txtProgramsLinkFocusGained

    private void txtNoteslinkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoteslinkKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtQuestionLink.requestFocus();
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Choose Questions Link");
            f.showOpenDialog(null);
            String Path = f.getSelectedFile().getAbsolutePath();
            txtQuestionLink.setText(Path);
        }
    }//GEN-LAST:event_txtNoteslinkKeyPressed

    private void txtQuestionLinkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuestionLinkKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtProgramsLink.requestFocus();
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Choose Programs Link");
            f.showOpenDialog(null);
            String Path = f.getSelectedFile().getAbsolutePath();
            txtProgramsLink.setText(Path);
        }
    }//GEN-LAST:event_txtQuestionLinkKeyPressed

    private void txtProgramsLinkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProgramsLinkKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            btnadd.requestFocus();
        }
    }//GEN-LAST:event_txtProgramsLinkKeyPressed

    private void btnaddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnaddKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            btnremove.requestFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {

            int rc = dtm.getRowCount();
            String et = txtChapterName.getText().toString();

            try {
                for (int i = 0; i < rc; i++) {
                    String tv = dtm.getValueAt(i, 0).toString();
                    if (tv.compareTo(et) == 0) {
                        JOptionPane.showMessageDialog(this, "Chapter Already Exist !", "Warning", JOptionPane.WARNING_MESSAGE);
                        dtm.removeRow(i);
                    }
                }
            } catch (Exception e) {
            }

            String cn = txtChapterName.getText().toString();
            String nl = txtNoteslink.getText().toString();
            String ql = txtQuestionLink.getText().toString();
            String pl = txtProgramsLink.getText().toString();
            dtm.addRow(new Object[]{cn, nl, ql, pl});

            txtChapterName.requestFocus();
        }
    }//GEN-LAST:event_btnaddKeyPressed

    private void btnremoveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoveKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            btnremoveall.requestFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            int rc = tablesubjectintable.getRowCount();
            String et = txtChapterName.getText().toString();
            for (int i = 0; i < rc; i++) {
                if (dtm.getValueAt(i, 0).toString().compareTo(et) == 0) {
                    dtm.removeRow(i);
                }
            }
            int sr = tablesubjectintable.getSelectedRow();
            dtm.removeRow(sr);
        }
    }//GEN-LAST:event_btnremoveKeyPressed

    private void btnremoveallKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoveallKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtSubjectName.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            dtm.setRowCount(0);
        }
    }//GEN-LAST:event_btnremoveallKeyPressed
    /*Genrating Subject Id*/
    public void SubjectId() {
        txtSubjectId.setEditable(false);

        ResultSet si = db.run_select("Select Count(*) From Subject");

        try {
            while (si.next()) {
                String c = si.getString("Count(*)");
                int s = Integer.valueOf(c) + 1;
                String SubjectId = "EZP_SUB_" + "" + s + "";
                txtSubjectId.setText(SubjectId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Refeshing Page*/
    public void Refresh() {
        SubjectId();
        txtSubjectName.setText("");
        txtdescription.setText("");
        txtChapterName.setText("");
        txtNoteslink.setText("");
        txtQuestionLink.setText("");
        txtProgramsLink.setText("");
        dtm.setRowCount(0);
        dtm2.setRowCount(0);
        ResultSet res2 = db.run_select("Select * From Subject Where Deleted = 'No' Order By SubjectId");
        try {
            while (res2.next()) {
                String si = "EZP_SUB_" + res2.getString("SubjectId");
                String sn = res2.getString("SubjectName");
                String de = res2.getString("Description");

                dtm2.addRow(new Object[]{si, sn, de});
            }
        } catch (Exception e) {
        }
    }

    /*Save Function*/
    public void Save() {
        /*Storing Record To Subject Table*/
        String tempsi = txtSubjectId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "insert into Subject Values(" + si + ", "
                + " '" + txtSubjectName.getText().toString() + "', '" + txtdescription.getText().toString() + "', 'No')";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Storing Records To SubjectData Table*/
        int rc = dtm.getRowCount();
        for (int i = 0; i < rc; i++) {
            String cn = dtm.getValueAt(i, 0).toString();
            String nl = dtm.getValueAt(i, 1).toString();
            String ql = dtm.getValueAt(i, 2).toString();
            String pl = dtm.getValueAt(i, 3).toString();

            String sql2 = "insert into SubjectData Values(" + si + ", '" + cn + "', '" + nl + "', '" + ql + "', '" + pl + "', 'No')";

            try {
                db.execute_sql(sql2);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        JOptionPane.showMessageDialog(null, "Saved");

        Refresh();
    }

    /*Update Function*/
    public void Update() {
        /*Updating Record From Subject Table*/

        String tempsi = txtSubjectId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql3 = "Update Subject Set SubjectName = '" + txtSubjectName.getText().toString() + "', "
                + " Description = '" + txtdescription.getText().toString() + "' Where SubjectId = " + si + " ";
        try {
            db.execute_sql(sql3);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Deleting Records From SubjectData Table*/
        String sql = "Delete From SubjectData Where SubjectId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Re-Inserting Record To SubjectData Table*/
        int rc = dtm.getRowCount();
        for (int i = 0; i < rc; i++) {
            String cn = dtm.getValueAt(i, 0).toString();
            String nl = dtm.getValueAt(i, 1).toString();
            String ql = dtm.getValueAt(i, 2).toString();
            String pl = dtm.getValueAt(i, 3).toString();

            String sql2 = "insert into SubjectData Values(" + si + ", "
                    + "'" + cn + "', '" + nl + "', '" + ql + "', '" + pl + "', 'No')";
            try {
                db.execute_sql(sql2);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String sql1 = "Update CourseData Set SubjectName = '" + txtSubjectName.getText().toString() + "', Description = '" + txtdescription.getText().toString() + "' Where SubjectId = '" + si + "' ";
        try {
            db.execute_sql(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Updated");

        Refresh();
    }

    /*Delete Function*/
    public void Delete() {
        /*Deleting Rrecord From Subject Table*/

        String tempsi = txtSubjectId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Subject Set Deleted = 'Yes' Where SubjectId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Deleting Records From SubjectData Table*/
        String sql2 = "Update SubjectData Set Deleted = 'Yes' Where SubjectId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (Exception e) {
        }

        String sql3 = "Update CourseData Set Deleted = 'Yes' Where SubjectId = " + si + " ";
        try {
            db.execute_sql(sql3);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Subject.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Deleted");
        Refresh();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MainScrollPane;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnremove;
    private javax.swing.JButton btnremoveall;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable tableSubjectData;
    private javax.swing.JTable tablesubjectintable;
    private javax.swing.JTextField txtChapterName;
    private javax.swing.JTextField txtNoteslink;
    private javax.swing.JTextField txtProgramsLink;
    private javax.swing.JTextField txtQuestionLink;
    private javax.swing.JTextField txtSubjectId;
    private javax.swing.JTextField txtSubjectName;
    private javax.swing.JTextArea txtdescription;
    // End of variables declaration//GEN-END:variables
}
