package software_acadmy;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class frm_Batch extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm;

    public frm_Batch() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screensize.width, screensize.height - 150);

        MainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        /*Setting Batch Id*/
        txtbatchid.setEditable(false);
        BatchId();

        /*Declaring Table*/
        dtm = new DefaultTableModel();
        tablebatchdata.setModel(dtm);
        dtm.addColumn("Batch Id");
        dtm.addColumn("Course / Subject");
        dtm.addColumn("Starting Time");
        dtm.addColumn("Ending Time");
        dtm.addColumn("Starting Date");
        dtm.addColumn("Ending Date");
        dtm.addColumn("No of Students");
        dtm.addColumn("Active / Inactive");

        tablebatchdata.setDefaultEditor(Object.class, null);
        tablebatchdata.getTableHeader().setBackground(Color.white);
        tablebatchdata.getTableHeader().setForeground(Color.orange);
        tablebatchdata.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        ResultSet res1 = db.run_select("Select * From Batch Where Deleted = 'No' Order By BatchId");
        try {
            while (res1.next()) {
                String bi = "EZP_BTC_" + res1.getString("BatchId");
                String cs = res1.getString("CourseSubject");
                String st = res1.getString("StartingTime");
                String et = res1.getString("EndingTime");
                String sd = res1.getString("StartingDate");
                String ed = res1.getString("EndingDate");
                String ns = res1.getString("NoOfStudents");
                String ai = res1.getString("ActiveInactive");

                dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
            }
        } catch (Exception e) {
        }

        /*Setting Dates*/
        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        dcendingdate.setDate(date);
        dcstartingdate.setDate(date);

        /*Setting Values In cmbCourseSubject*/
        ResultSet res2 = db.run_select("Select * From Course Where Deleted = 'No' Order By CourseName");
        try {
            while (res2.next()) {
                String cn = res2.getString("CourseName");
                cmbCourseSubject.addItem(cn);
            }
        } catch (Exception e) {
        }

        /*Setting values cmbstminute*/
        for (int i = 1; i < 60; i++) {
            if (i <= 9) {
                cmbstminute.addItem("0" + String.valueOf(i));
            } else {
                cmbstminute.addItem(String.valueOf(i));
            }
        }

        /*Setting values cmbetminute*/
        for (int i = 1; i < 60; i++) {
            if (i <= 9) {
                cmbetminute.addItem("0" + String.valueOf(i));
            } else {
                cmbetminute.addItem(String.valueOf(i));
            }
        }

        /*SettingFocusTraversalKeysCollectionsEmptySet*/
        rbtnactive.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        rbtninactive.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        cmbetap.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtNoofstudents.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        MainScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbtnactive = new javax.swing.JRadioButton();
        rbtninactive = new javax.swing.JRadioButton();
        txtbatchid = new javax.swing.JTextField();
        txtNoofstudents = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbCourseSubject = new javax.swing.JComboBox<>();
        dcstartingdate = new com.toedter.calendar.JDateChooser();
        dcendingdate = new com.toedter.calendar.JDateChooser();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        cmbsthour = new javax.swing.JComboBox<>();
        cmbstminute = new javax.swing.JComboBox<>();
        cmbstap = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cmbethour = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cmbetminute = new javax.swing.JComboBox<>();
        cmbetap = new javax.swing.JComboBox<>();
        jButton17 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablebatchdata = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        MainScrollPane.setHorizontalScrollBar(null);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Batch", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Batch Id");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Batch Duration ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No. Of Students");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Batch Time");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Course ");

        rbtnactive.setBackground(new java.awt.Color(0, 51, 102));
        buttonGroup1.add(rbtnactive);
        rbtnactive.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnactive.setForeground(new java.awt.Color(255, 255, 255));
        rbtnactive.setText("Active");
        rbtnactive.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtnactiveKeyPressed(evt);
            }
        });

        rbtninactive.setBackground(new java.awt.Color(0, 51, 102));
        buttonGroup1.add(rbtninactive);
        rbtninactive.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtninactive.setForeground(new java.awt.Color(255, 255, 255));
        rbtninactive.setText("Inactive");
        rbtninactive.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbtninactiveKeyPressed(evt);
            }
        });

        txtbatchid.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtNoofstudents.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtNoofstudents.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNoofstudentsKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ending Time");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Starting Time");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Starting Date");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ending Date");

        cmbCourseSubject.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbCourseSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbCourseSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCourseSubjectActionPerformed(evt);
            }
        });
        cmbCourseSubject.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCourseSubjectKeyPressed(evt);
            }
        });

        dcstartingdate.setDateFormatString("dd, MMM yyyy");
        dcstartingdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        dcendingdate.setDateFormatString("dd, MMM yyyy");
        dcendingdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dcendingdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dcendingdateKeyPressed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        cmbsthour.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbsthour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbstminute.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbstminute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00" }));

        cmbstap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbstap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(":");

        cmbethour.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbethour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText(":");

        cmbetminute.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbetminute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00" }));

        cmbetap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbetap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        cmbetap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbetapKeyPressed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton17FocusGained(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(103, 103, 103)
                        .addComponent(txtbatchid, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(44, 44, 44)
                        .addComponent(txtNoofstudents, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(110, 110, 110)
                            .addComponent(cmbCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rbtnactive)
                            .addGap(32, 32, 32)
                            .addComponent(rbtninactive)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(44, 44, 44)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(14, 14, 14)
                                    .addComponent(dcstartingdate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dcendingdate, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(79, 79, 79)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(cmbsthour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel8)
                                    .addGap(4, 4, 4)
                                    .addComponent(cmbstminute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(cmbstap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbethour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel15)
                                    .addGap(4, 4, 4)
                                    .addComponent(cmbetminute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbetap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbatchid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel1))
                                    .addComponent(rbtnactive)
                                    .addComponent(rbtninactive)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(cmbCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbsthour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbstminute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbstap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel4))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbethour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbetminute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))
                                    .addComponent(cmbetap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(dcendingdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcstartingdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(txtNoofstudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbetap, cmbethour, cmbetminute, cmbstap, cmbsthour, cmbstminute, jButton16, jLabel15, jLabel8});

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Batch Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setFocusTraversalPolicyProvider(true);

        tablebatchdata.setBackground(new java.awt.Color(0, 51, 102));
        tablebatchdata.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tablebatchdata.setForeground(new java.awt.Color(255, 255, 255));
        tablebatchdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablebatchdata.setRowHeight(20);
        tablebatchdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablebatchdataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablebatchdata);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1742, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setText("EZAPIYA");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Getting_Stonger.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Cool_Guy_On_Computer.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(32, 32, 32))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10))
        );

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
                .addContainerGap(778, Short.MAX_VALUE))
        );
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

        jToolBar2.add(jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmbCourseSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCourseSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCourseSubjectActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        Delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        Update();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void tablebatchdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablebatchdataMouseClicked
        int row = tablebatchdata.getSelectedRow();

        String st = dtm.getValueAt(row, 2).toString();
        String et = dtm.getValueAt(row, 3).toString();

        String sh = BasisLibrary.substringF(st, 1, 2);
        String sm = BasisLibrary.substringF(st, 4, 2);
        String sap = BasisLibrary.substringF(st, 7, 2);

        String eh = BasisLibrary.substringF(et, 1, 2);
        String em = BasisLibrary.substringF(et, 4, 2);
        String eap = BasisLibrary.substringF(et, 7, 2);

        /*Setting values In TextFields*/
        txtbatchid.setText(dtm.getValueAt(row, 0).toString());
        cmbCourseSubject.setSelectedItem(dtm.getValueAt(row, 1));

        cmbsthour.setSelectedItem(sh);
        cmbstminute.setSelectedItem(sm);
        cmbstap.setSelectedItem(sap);

        cmbethour.setSelectedItem(eh);
        cmbetminute.setSelectedItem(em);
        cmbetap.setSelectedItem(eap);

        txtNoofstudents.setText(dtm.getValueAt(row, 6).toString());

        /*Setting Dates In DateChoosers*/
        String tempsi = dtm.getValueAt(row, 0).toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From Batch Where BatchId = " + si + " ");
        try {
            while (res.next()) {
                String sd = res.getString("StartingDate");
                String ed = res.getString("EndingDate");

                Date sdate = new SimpleDateFormat("dd-MMM-yyyy").parse(sd);
                dcstartingdate.setDate(sdate);
                Date edate = new SimpleDateFormat("dd-MMM-yyyy").parse(ed);
                dcendingdate.setDate(edate);
            }
        } catch (Exception e) {
        }

        /*Setting Selected RadioButton*/
        if (dtm.getValueAt(row, 7).toString().compareTo("Active") == 0) {
            rbtninactive.setSelected(false);
            rbtnactive.setSelected(true);
        } else if (dtm.getValueAt(row, 7).toString().compareTo("Inactive") == 0) {
            rbtnactive.setSelected(false);
            rbtninactive.setSelected(true);
        }
    }//GEN-LAST:event_tablebatchdataMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        /*Refreshing page*/
        BatchId();

        cmbsthour.setSelectedItem("01");
        cmbstminute.setSelectedItem("00");
        cmbstap.setSelectedItem("AM");

        cmbethour.setSelectedItem("01");
        cmbetminute.setSelectedItem("00");
        cmbetap.setSelectedItem("AM");

        txtNoofstudents.setText("");

        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        dcendingdate.setDate(date);
        dcstartingdate.setDate(date);

        dtm.setRowCount(0);

        /*Searching Data*/
        String sb = cmbCourseSubject.getSelectedItem().toString();

        if (sb.compareTo("Select") == 0) {
            if (rbtnactive.isSelected() == true || rbtninactive.isSelected() == true) {
                if (rbtnactive.isSelected() == true) {
                    ResultSet res = db.run_select("Select Count(*) From batch Where ActiveInactive = 'Active' Order By BatchId");
                    try {
                        while (res.next()) {
                            String c = res.getString("Count(*)");
                            if (c.compareTo("0") == 0) {
                                JOptionPane.showMessageDialog(this, "Data Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                ResultSet res2 = db.run_select("Select * From batch Where ActiveInactive = 'Active' Order By BatchId");
                                try {
                                    while (res2.next()) {
                                        String bi = "EZP_BTC_" + res2.getString("BatchId");
                                        String cs = res2.getString("CourseSubject");
                                        String st = res2.getString("StartingTime");
                                        String et = res2.getString("EndingTime");
                                        String sd = res2.getString("StartingDate");
                                        String ed = res2.getString("EndingDate");
                                        String ns = res2.getString("NoOfStudents");
                                        String ai = res2.getString("ActiveInactive");

                                        dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }

                } else if (rbtninactive.isSelected() == true) {
                    ResultSet res = db.run_select("Select Count(*) From batch Where ActiveInactive = 'Inactive' Order By BatchId");
                    try {
                        while (res.next()) {
                            String c = res.getString("Count(*)");
                            if (c.compareTo("0") == 0) {
                                JOptionPane.showMessageDialog(this, "Data Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                ResultSet res2 = db.run_select("Select * From batch Where ActiveInactive = 'Inactive' Order By BatchId");
                                try {
                                    while (res2.next()) {
                                        String bi = "EZP_BTC_" + res2.getString("BatchId");
                                        String cs = res2.getString("CourseSubject");
                                        String st = res2.getString("StartingTime");
                                        String et = res2.getString("EndingTime");
                                        String sd = res2.getString("StartingDate");
                                        String ed = res2.getString("EndingDate");
                                        String ns = res2.getString("NoOfStudents");
                                        String ai = res2.getString("ActiveInactive");

                                        dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }

                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Select Course Status !(Active or Inactive)", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (rbtnactive.isSelected() == true || rbtninactive.isSelected() == true) {
                if (rbtnactive.isSelected() == true) {
                    ResultSet res = db.run_select("Select Count(*) From batch Where ActiveInactive = 'Active' and CourseSubject = '" + sb + "' Order By BatchId");
                    try {
                        while (res.next()) {
                            String c = res.getString("Count(*)");
                            if (c.compareTo("0") == 0) {
                                JOptionPane.showMessageDialog(this, "Data Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                ResultSet res2 = db.run_select("Select * From batch Where ActiveInactive = 'Active' and CourseSubject = '" + sb + "' Order By BatchId");
                                try {
                                    while (res2.next()) {
                                        String bi = "EZP_BTC_" + res2.getString("BatchId");
                                        String cs = res2.getString("CourseSubject");
                                        String st = res2.getString("StartingTime");
                                        String et = res2.getString("EndingTime");
                                        String sd = res2.getString("StartingDate");
                                        String ed = res2.getString("EndingDate");
                                        String ns = res2.getString("NoOfStudents");
                                        String ai = res2.getString("ActiveInactive");

                                        dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }

                } else if (rbtninactive.isSelected() == true) {
                    ResultSet res = db.run_select("Select Count(*) From batch Where ActiveInactive = 'Inactive' and CourseSubject = '" + sb + "' Order By BatchId");
                    try {
                        while (res.next()) {
                            String c = res.getString("Count(*)");
                            if (c.compareTo("0") == 0) {
                                JOptionPane.showMessageDialog(this, "Data Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                ResultSet res2 = db.run_select("Select * From batch Where ActiveInactive = 'Inactive' and CourseSubject = '" + sb + "' Order By BatchId");
                                try {
                                    while (res2.next()) {
                                        String bi = "EZP_BTC_" + res2.getString("BatchId");
                                        String cs = res2.getString("CourseSubject");
                                        String st = res2.getString("StartingTime");
                                        String et = res2.getString("EndingTime");
                                        String sd = res2.getString("StartingDate");
                                        String ed = res2.getString("EndingDate");
                                        String ns = res2.getString("NoOfStudents");
                                        String ai = res2.getString("ActiveInactive");

                                        dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }

                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Select Course Status !(Active or Inactive)", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        /*Refreshing page*/
        BatchId();

        txtNoofstudents.setText("");

        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        dcendingdate.setDate(date);
        dcstartingdate.setDate(date);

        dtm.setRowCount(0);

        /*Searching Data*/
        String st = cmbsthour.getSelectedItem().toString() + ":" + cmbstminute.getSelectedItem().toString() + " " + cmbstap.getSelectedItem().toString();
        String et = cmbethour.getSelectedItem().toString() + ":" + cmbetminute.getSelectedItem().toString() + " " + cmbetap.getSelectedItem().toString();

        ResultSet res = db.run_select("Select Count(*) From Batch Where StartingTime = '" + st + "' And EndingTime = '" + et + "' ");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "No Data Found !", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From Batch Where StartingTime = '" + st + "' And EndingTime = '" + et + "' ");
                    try {
                        while (res1.next()) {
                            String bi = "EZP_BTC_" + res1.getString("BatchId");
                            String cs = res1.getString("CourseSubject");
                            String st1 = res1.getString("StartingTime");
                            String et1 = res1.getString("EndingTime");
                            String sd = res1.getString("StartingDate");
                            String ed = res1.getString("EndingDate");
                            String ns = res1.getString("NoOfStudents");
                            String ai = res1.getString("ActiveInactive");

                            dtm.addRow(new Object[]{bi, cs, st1, et1, sd, ed, ns, ai});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        /*Refreshing page*/
        BatchId();

        txtNoofstudents.setText("");

        cmbsthour.setSelectedItem("01");
        cmbstminute.setSelectedItem("00");
        cmbstap.setSelectedItem("AM");

        cmbethour.setSelectedItem("01");
        cmbetminute.setSelectedItem("00");
        cmbetap.setSelectedItem("AM");

        dtm.setRowCount(0);

        /*Searching Data*/
        Date tsd = dcstartingdate.getDate();
        Date ted = dcendingdate.getDate();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String sd = dateFormat.format(tsd);
        String ed = dateFormat.format(ted);

        ResultSet res = db.run_select("Select Count(*) From batch where StartingDate = '" + sd + "' And EndingDate = '" + ed + "' ");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Data not Found !", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res2 = db.run_select("Select * From batch where StartingDate = '" + sd + "' And EndingDate = '" + ed + "' ");
                    try {
                        while (res2.next()) {
                            String bi = "EZP_BTC_" + res2.getString("BatchId");
                            String cs = res2.getString("CourseSubject");
                            String st = res2.getString("StartingTime");
                            String et = res2.getString("EndingTime");
                            String sd1 = res2.getString("StartingDate");
                            String ed1 = res2.getString("EndingDate");
                            String ns = res2.getString("NoOfStudents");
                            String ai = res2.getString("ActiveInactive");

                            dtm.addRow(new Object[]{bi, cs, st, et, sd1, ed1, ns, ai});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void cmbCourseSubjectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCourseSubjectKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCourseSubjectKeyPressed

    private void rbtnactiveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtnactiveKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            cmbsthour.requestFocus();
        }
    }//GEN-LAST:event_rbtnactiveKeyPressed

    private void rbtninactiveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbtninactiveKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            cmbsthour.requestFocus();
        }
    }//GEN-LAST:event_rbtninactiveKeyPressed

    private void cmbetapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbetapKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            dcstartingdate.requestFocusInWindow();
        }
    }//GEN-LAST:event_cmbetapKeyPressed

    private void dcendingdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcendingdateKeyPressed

    }//GEN-LAST:event_dcendingdateKeyPressed

    private void txtNoofstudentsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoofstudentsKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            cmbCourseSubject.requestFocus();
        }
    }//GEN-LAST:event_txtNoofstudentsKeyPressed

    private void jButton17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton17FocusGained
        txtNoofstudents.requestFocus();
    }//GEN-LAST:event_jButton17FocusGained
    public void BatchId() {
        ResultSet res = db.run_select("Select Count(*) From Batch");
        try {
            String c = res.getString("Count(*)");
            int i = Integer.valueOf(c) + 1;
            String BI = "EZP_BTC_" + "" + String.valueOf(i) + "";
            txtbatchid.setText(BI);
        } catch (Exception e) {
        }
    }

    /*Refresh*/
    public void Refresh() {
        BatchId();
        cmbCourseSubject.setSelectedItem("Select");

        cmbsthour.setSelectedItem("01");
        cmbstminute.setSelectedItem("00");
        cmbstap.setSelectedItem("AM");

        cmbethour.setSelectedItem("01");
        cmbetminute.setSelectedItem("00");
        cmbetap.setSelectedItem("AM");

        txtNoofstudents.setText("");

        LocalDate now = LocalDate.now();
        Date date = java.sql.Date.valueOf(now);
        dcendingdate.setDate(date);
        dcstartingdate.setDate(date);

        buttonGroup1.clearSelection();

        dtm.setRowCount(0);

        ResultSet res1 = db.run_select("Select * From Batch Where Deleted = 'No' Order By BatchId");
        try {
            while (res1.next()) {
                String bi = "EZP_BTC_" + res1.getString("BatchId");
                String cs = res1.getString("CourseSubject");
                String st = res1.getString("StartingTime");
                String et = res1.getString("EndingTime");
                String sd = res1.getString("StartingDate");
                String ed = res1.getString("EndingDate");
                String ns = res1.getString("NoOfStudents");
                String ai = res1.getString("ActiveInactive");

                dtm.addRow(new Object[]{bi, cs, st, et, sd, ed, ns, ai});
            }
        } catch (Exception e) {
        }
    }

    /*Save Function*/
    public void Save() {
        Date tsd = dcstartingdate.getDate();
        Date ted = dcendingdate.getDate();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String sd = dateFormat.format(tsd);
        String ed = dateFormat.format(ted);

        String st = cmbsthour.getSelectedItem().toString() + ":" + cmbstminute.getSelectedItem().toString() + " " + cmbstap.getSelectedItem().toString();
        String et = cmbethour.getSelectedItem().toString() + ":" + cmbetminute.getSelectedItem().toString() + " " + cmbetap.getSelectedItem().toString();

        /*Saving Record In Batch Table*/
        String tempsi = txtbatchid.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From Course Where Deleted = 'No' ");
        try {
            while (res.next()) {
                String ci = res.getString("CourseId");
                String cn = res.getString("CourseName");

                if (cn.compareTo(cmbCourseSubject.getSelectedItem().toString()) == 0) {

                    String rbtnAns;
                    if (rbtnactive.isSelected() == true) {
                        rbtnAns = "Active";

                        String sql = "insert into Batch values(" + si + ", " + ci + ", "
                                + "'" + cn + "', '" + st + "', "
                                + "'" + et + "', '" + sd + "', "
                                + "'" + ed + "', '" + txtNoofstudents.getText().toString() + "', "
                                + "'" + rbtnAns + "','No')";
                        try {
                            db.execute_sql(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (rbtninactive.isSelected() == true) {
                        rbtnAns = "Inactive";

                        String sql = "insert into Batch values(" + si + ", " + ci + ", "
                                + "'" + cn + "', '" + st + "', "
                                + "'" + et + "', '" + sd + "', "
                                + "'" + ed + "', '" + txtNoofstudents.getText().toString() + "', "
                                + "'" + rbtnAns + "','No')";
                        try {
                            db.execute_sql(sql);
                        } catch (SQLException ex) {
                            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        JOptionPane.showMessageDialog(null, "Saved");

        Refresh();
    }

    /*Update Function*/
    public void Update() {
        Date tsd = dcstartingdate.getDate();
        Date ted = dcendingdate.getDate();

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String sd = dateFormat.format(tsd);
        String ed = dateFormat.format(ted);

        String st = cmbsthour.getSelectedItem().toString() + ":" + cmbstminute.getSelectedItem().toString() + " " + cmbstap.getSelectedItem().toString();
        String et = cmbethour.getSelectedItem().toString() + ":" + cmbetminute.getSelectedItem().toString() + " " + cmbetap.getSelectedItem().toString();

        /*Updating Record In Batch Table*/
        String tempsi = txtbatchid.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From Course Where CourseName = '" + cmbCourseSubject.getSelectedItem().toString() + "' ");
        try {
            while (res.next()) {
                String ci = res.getString("CourseId");

                String rbtnAns;
                if (rbtnactive.isSelected() == true) {
                    rbtnAns = "Active";

                    String sql = "Update Batch set CourseId = " + ci + ", CourseSubject = '" + cmbCourseSubject.getSelectedItem().toString() + "', "
                            + "StartingTime = '" + st + "', "
                            + "EndingTime = '" + et + "', "
                            + "StartingDate = '" + sd + "', "
                            + "EndingDate = '" + ed + "', "
                            + "NoOfStudents = '" + txtNoofstudents.getText().toString() + "', "
                            + "ActiveInactive = '" + rbtnAns + "' "
                            + "Where BatchId = " + si + " ";

                    db.execute_sql(sql);

                } else if (rbtninactive.isSelected() == true) {
                    rbtnAns = "Inactive";

                    String sql = "Update Batch set CourseId = " + ci + ", CourseSubject = '" + cmbCourseSubject.getSelectedItem().toString() + "', "
                            + "StartingTime = '" + st + "', "
                            + "EndingTime = '" + et + "', "
                            + "StartingDate = '" + sd + "', "
                            + "EndingDate = '" + ed + "', "
                            + "NoOfStudents = '" + txtNoofstudents.getText().toString() + "', "
                            + "ActiveInactive = '" + rbtnAns + "' "
                            + "Where BatchId = " + si + " ";

                    db.execute_sql(sql);

                }

            }
        } catch (Exception e) {
        }

        String bb = "" + st + " - " + et + " , " + sd + " - " + ed + "";

        String sql2 = "Update Admission set Batch = '" + bb + "' Where BatchId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql4 = "Update Fees set Batch = '" + bb + "' Where BatchId = " + si + " ";
        try {
            db.execute_sql(sql4);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Updated");

        Refresh();
    }

    /*Delete Function*/
    public void Delete() {
        /*Deleting Record From Batch Table*/

        String tempsi = txtbatchid.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Batch Set Deleted = 'Yes' Where BatchId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql2 = "Update Admission Set Deleted = 'Yes' Where BatchId = "+si+" ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Refresh();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MainScrollPane;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbCourseSubject;
    private javax.swing.JComboBox<String> cmbetap;
    private javax.swing.JComboBox<String> cmbethour;
    private javax.swing.JComboBox<String> cmbetminute;
    private javax.swing.JComboBox<String> cmbstap;
    private javax.swing.JComboBox<String> cmbsthour;
    private javax.swing.JComboBox<String> cmbstminute;
    private com.toedter.calendar.JDateChooser dcendingdate;
    private com.toedter.calendar.JDateChooser dcstartingdate;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JRadioButton rbtnactive;
    private javax.swing.JRadioButton rbtninactive;
    private javax.swing.JTable tablebatchdata;
    private javax.swing.JTextField txtNoofstudents;
    private javax.swing.JTextField txtbatchid;
    // End of variables declaration//GEN-END:variables
}
