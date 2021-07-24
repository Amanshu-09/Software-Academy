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
import java.text.ParseException;
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

public class frm_Admission extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm;

    public frm_Admission() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, Screensize.width, Screensize.height - 150);

        MainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        /*Setting Admission Id*/
        AdmissionId();

        /*Setting Values In ComboBoxes*/
 /*Combobox Student*/
        ResultSet res = db.run_select("Select * From Student Where Deleted = 'No' Order By FirstName");
        try {
            while (res.next()) {
                String fn = res.getString("FirstName");
                String mn = res.getString("MiddleName");
                String ln = res.getString("LastName");

                cmbStudentName.addItem("" + fn + " " + mn + " " + ln + "");
            }
        } catch (Exception e) {
        }

        /*ComboBox CourseSubject*/
        ResultSet res3 = db.run_select("Select * From Course Where Deleted = 'No' Order By CourseName");
        try {
            while (res3.next()) {
                String C = res3.getString("CourseName");
                cmbCourseSubject.addItem(C);
            }
        } catch (Exception e) {
        }

        /*Combobox DCourseSubject*/
        ResultSet res4 = db.run_select("Select * From Admission Where Deleted = 'No' Group By CourseSubject Order By CourseSubject");
        try {
            while (res4.next()) {
                String cs = res4.getString("CourseSubject");
                cmbDCourseSubject.addItem(cs);
            }
        } catch (Exception e) {
        }

        /*Declaring Table*/
        dtm = new DefaultTableModel();
        tableAdmissionData.setModel(dtm);
        dtm.addColumn("Admission Id");
        dtm.addColumn("Student Name");
        dtm.addColumn("CourseSubject");
        dtm.addColumn("Batch");
        dtm.addColumn("AdmissionDate");

        tableAdmissionData.setDefaultEditor(Object.class, null);
        tableAdmissionData.getTableHeader().setBackground(Color.white);
        tableAdmissionData.getTableHeader().setForeground(Color.orange);
        tableAdmissionData.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        ResultSet res1 = db.run_select("Select * From Admission Where Deleted = 'No' Order by AdmissionId ");
        try {
            while (res1.next()) {
                String ai = "EZP_ADM_" + res1.getString("AdmissionId");
                String s = res1.getString("StudentName");
                String cs = res1.getString("CourseSubject");
                String b = res1.getString("Batch");
                String ad = res1.getString("AdmissionDate");

                dtm.addRow(new Object[]{ai, s, cs, b, ad});
            }
        } catch (Exception e) {
        }

        /*SettingFocusTransversalCollectionsEmpty*/
        cmbDBatch.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());

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
                cmbDCourseSubject.removeItemAt(1);
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
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAdmissionData = new javax.swing.JTable();
        rbtnActive = new javax.swing.JRadioButton();
        cmbDBatch = new javax.swing.JComboBox<>();
        rbtnInactive = new javax.swing.JRadioButton();
        cmbDCourseSubject = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbCourseSubject = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cmbBatch = new javax.swing.JComboBox<>();
        dcAddmissiondate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbStudentName = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtAdMissionId = new javax.swing.JTextField();
        lbladdmissionid = new javax.swing.JLabel();
        txttemp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        MainScrollPane.setHorizontalScrollBar(null);

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Check Adimissions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        tableAdmissionData.setBackground(new java.awt.Color(0, 51, 102));
        tableAdmissionData.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tableAdmissionData.setForeground(new java.awt.Color(255, 255, 255));
        tableAdmissionData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableAdmissionData.setRowHeight(20);
        tableAdmissionData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAdmissionDataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableAdmissionData);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1377, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel3);

        rbtnActive.setBackground(new java.awt.Color(0, 51, 102));
        buttonGroup1.add(rbtnActive);
        rbtnActive.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnActive.setForeground(new java.awt.Color(255, 255, 255));
        rbtnActive.setText("Active");
        rbtnActive.setEnabled(false);

        cmbDBatch.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbDBatch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select  " }));
        cmbDBatch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDBatchItemStateChanged(evt);
            }
        });
        cmbDBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDBatchActionPerformed(evt);
            }
        });
        cmbDBatch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDBatchKeyPressed(evt);
            }
        });

        rbtnInactive.setBackground(new java.awt.Color(0, 51, 102));
        buttonGroup1.add(rbtnInactive);
        rbtnInactive.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnInactive.setForeground(new java.awt.Color(255, 255, 255));
        rbtnInactive.setText("Inactive");
        rbtnInactive.setEnabled(false);

        cmbDCourseSubject.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbDCourseSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "All" }));
        cmbDCourseSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDCourseSubjectItemStateChanged(evt);
            }
        });
        cmbDCourseSubject.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbDCourseSubjectFocusGained(evt);
            }
        });
        cmbDCourseSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDCourseSubjectActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Batch");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Couse/Subject");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24)
                        .addComponent(cmbDCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(cmbDBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnActive)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnInactive)
                        .addGap(0, 164, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmbDBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbtnActive)
                        .addComponent(rbtnInactive))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbDCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "New! Admission", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        cmbCourseSubject.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbCourseSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbCourseSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCourseSubjectItemStateChanged(evt);
            }
        });
        cmbCourseSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbCourseSubjectMouseClicked(evt);
            }
        });
        cmbCourseSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCourseSubjectActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Course");

        cmbBatch.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbBatch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbBatch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBatchItemStateChanged(evt);
            }
        });

        dcAddmissiondate.setDateFormatString("dd, MMM yyyy");
        dcAddmissiondate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dcAddmissiondate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dcAddmissiondateFocusLost(evt);
            }
        });
        dcAddmissiondate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dcAddmissiondateKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Addmission Date");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Student");

        cmbStudentName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbStudentName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbStudentName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStudentNameItemStateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Batch");

        txtAdMissionId.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lbladdmissionid.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbladdmissionid.setForeground(new java.awt.Color(255, 255, 255));
        lbladdmissionid.setText("Admission ID");
        lbladdmissionid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lbladdmissionidFocusGained(evt);
            }
        });

        txttemp.setPreferredSize(new java.awt.Dimension(6, 0));
        txttemp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txttempFocusGained(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1_1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(112, 112, 112))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(32, 32, 32)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dcAddmissiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttemp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                            .addComponent(cmbCourseSubject, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbBatch, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbladdmissionid))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAdMissionId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbStudentName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lbladdmissionid))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtAdMissionId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmbCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel16))
                            .addComponent(cmbBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(dcAddmissiondate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbStudentName, jButton1});

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Ezapiya_Quote.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(782, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainScrollPane.setViewportView(jPanel4);

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

    private void cmbCourseSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbCourseSubjectMouseClicked

    }//GEN-LAST:event_cmbCourseSubjectMouseClicked

    private void cmbCourseSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCourseSubjectActionPerformed

    }//GEN-LAST:event_cmbCourseSubjectActionPerformed

    private void cmbDBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDBatchActionPerformed

    }//GEN-LAST:event_cmbDBatchActionPerformed

    private void cmbDCourseSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDCourseSubjectActionPerformed

    }//GEN-LAST:event_cmbDCourseSubjectActionPerformed

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
        try {
            Refresh();
            cmbDCourseSubject.removeItemAt(1);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void cmbDBatchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDBatchItemStateChanged
        /*Setting Data In Admission Data Table*/
        dtm.setRowCount(0);

        // For All
        if (cmbDCourseSubject.getSelectedItem().toString().compareTo("All") == 0) {
            ResultSet res2 = db.run_select("Select * From Admission Where Batch = '" + cmbDBatch.getSelectedItem().toString() + "' ");
            try {
                while (res2.next()) {
                    String ai = "EZP_ADM_" + res2.getString("AdmissionId");
                    String s = res2.getString("StudentName");
                    String cs = res2.getString("CourseSubject");
                    String b = res2.getString("Batch");
                    String ad = res2.getString("AdmissionDate");

                    dtm.addRow(new Object[]{ai, s, cs, b, ad});
                }
            } catch (Exception e) {
            }

        }// For All

        ResultSet res2 = db.run_select("Select * From Admission Where Batch = '" + cmbDBatch.getSelectedItem().toString() + "' And CourseSubject = '" + cmbDCourseSubject.getSelectedItem().toString() + "' ");
        try {
            while (res2.next()) {
                String ai = "EZP_ADM_" + res2.getString("AdmissionId");
                String s = res2.getString("StudentName");
                String cs = res2.getString("CourseSubject");
                String b = res2.getString("Batch");
                String ad = res2.getString("AdmissionDate");

                dtm.addRow(new Object[]{ai, s, cs, b, ad});
            }
        } catch (Exception e) {
        }

        /*Setting Batch is Active or Not*/
        try {
            String st = cmbDBatch.getSelectedItem().toString().substring(0, 8);
            String et = cmbDBatch.getSelectedItem().toString().substring(11, 19);
            String sd = cmbDBatch.getSelectedItem().toString().substring(22, 33);
            String ed = cmbDBatch.getSelectedItem().toString().substring(36, 47);

            ResultSet res = db.run_select("Select * From Batch Where StartingTime like '%" + st + "%' AND Endingtime like '%" + et + "%' AND StartingDate like '%" + sd + "%' AND EndingDate like '%" + ed + "%' AND CourseSubject = '" + cmbDCourseSubject.getSelectedItem().toString() + "' ");
            try {
                while (res.next()) {
                    String ai = res.getString("ActiveInactive");

                    if (ai.compareTo("Active") == 0) {
                        rbtnActive.setSelected(true);
                    } else if (ai.compareTo("Inactive") == 0) {
                        rbtnInactive.setSelected(true);
                    }
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cmbDBatchItemStateChanged

    private void tableAdmissionDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdmissionDataMouseClicked
        int row = tableAdmissionData.getSelectedRow();

        txtAdMissionId.setText(dtm.getValueAt(row, 0).toString());
        cmbStudentName.setSelectedItem(dtm.getValueAt(row, 1).toString());
        cmbCourseSubject.setSelectedItem(dtm.getValueAt(row, 2).toString());
        cmbBatch.setSelectedItem(dtm.getValueAt(row, 3).toString());

        /*Setting AddimissionDate*/
        String tad = dtm.getValueAt(row, 4).toString();
        try {
            Date ad = new SimpleDateFormat("dd-MMM-yyyy").parse(tad);
            dcAddmissiondate.setDate(ad);
        } catch (ParseException ex) {
            Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Selecting Batch Is Active OR Inactive*/
        String st = dtm.getValueAt(row, 3).toString().substring(0, 8);
        String et = dtm.getValueAt(row, 3).toString().substring(12, 19);
        String sd = dtm.getValueAt(row, 3).toString().substring(22, 33);
        String ed = dtm.getValueAt(row, 3).toString().substring(36, 47);

        ResultSet res = db.run_select("Select * From Batch Where StartingTime like '%" + st + "%' AND Endingtime like '%" + et + "%' AND StartingDate like '%" + sd + "%' AND EndingDate like '%" + ed + "%' AND CourseSubject = '" + dtm.getValueAt(row, 2) + "' ");
        try {
            while (res.next()) {
                String ai = res.getString("ActiveInactive");

                if (ai.compareTo("Active") == 0) {
                    rbtnActive.setSelected(true);
                } else if (ai.compareTo("Inactive") == 0) {
                    rbtnInactive.setSelected(true);
                }
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_tableAdmissionDataMouseClicked

    private void cmbDCourseSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDCourseSubjectItemStateChanged
        /*Adding Items in cmbDBatch*/
        dtm.setRowCount(0);
        buttonGroup1.clearSelection();
        String cs = cmbDCourseSubject.getSelectedItem().toString();

        int ics = cmbDBatch.getItemCount() - 1;
        for (int i = ics; i > 0; i--) {
            cmbDBatch.removeItemAt(i);
        }

        //All Start
        if (cs.compareTo("All") == 0) {

            ResultSet res3 = db.run_select("Select * From Admission Order By AdmissionId");
            try {
                while (res3.next()) {
                    String ai = "EZP_ADM_" + res3.getString("AdmissionId");
                    String s = res3.getString("StudentName");
                    String cs2 = res3.getString("CourseSubject");
                    String b = res3.getString("Batch");
                    String ad = res3.getString("AdmissionDate");
                    dtm.addRow(new Object[]{ai, s, cs2, b, ad});
                }
            } catch (Exception e) {
            }

            ResultSet res5 = db.run_select("Select Count(*) From Admission");
            try {
                String C = res5.getString("Count(*)");
                if (Integer.valueOf(C) % 2 == 0) {
                    int ic = cmbDBatch.getItemCount();
                    for (int i = ic; i > 1; i--) {
                        cmbDBatch.removeItemAt(i);
                    }
                } else if (Integer.valueOf(C) % 2 == 1) {
                    int ic = cmbDBatch.getItemCount() - 1;
                    for (int i = ic; i > 1; i--) {
                        cmbDBatch.removeItemAt(i);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet res2 = db.run_select("Select * From Admission Group by Batch");
            try {
                while (res2.next()) {
                    String B = res2.getString("Batch");
                    cmbDBatch.addItem(B);
                }
            } catch (Exception e) {
            }
            //All End

            //Select Start 
        } else if (cs.compareTo("Select") == 0) {

            int ic = cmbDBatch.getItemCount() - 1;
            for (int i = ic; i > 0; i--) {
                cmbDBatch.removeItemAt(i);
            }
        }
        //Select End

        //CourseSubjects Start
        ResultSet res4 = db.run_select("Select * From Admission Where CourseSubject = '" + cs + "' ");
        try {
            while (res4.next()) {
                String csn = res4.getString("CourseSubject");
                if (cs.compareTo(csn) == 0) {

                    ResultSet res5 = db.run_select("Select Count(*) From Admission");
                    try {
                        String C = res5.getString("Count(*)");
                        /*if (Integer.valueOf(C) % 2 == 1) {
                            int ic = cmbDBatch.getItemCount();
                            for (int i = ic; i > 1; i--) {
                                cmbDBatch.removeItemAt(i);
                            }
                        }*/ if (Integer.valueOf(C) % 2 == 0) {
                            int ic = cmbDBatch.getItemCount() - 1;
                            for (int i = ic; i > 1; i--) {
                                cmbDBatch.removeItemAt(i);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String B = res4.getString("Batch");
                    cmbDBatch.addItem(B);

                    String ai = "EZP_ADM_" + res4.getString("AdmissionId");
                    String s = res4.getString("StudentName");
                    String cs2 = res4.getString("CourseSubject");
                    String b = res4.getString("Batch");
                    String ad = res4.getString("AdmissionDate");

                    dtm.addRow(new Object[]{ai, s, cs2, b, ad});

                }

            }
        } catch (Exception e) {
        }
        //CourseSubject End
    }//GEN-LAST:event_cmbDCourseSubjectItemStateChanged

    private void cmbStudentNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStudentNameItemStateChanged

    }//GEN-LAST:event_cmbStudentNameItemStateChanged

    private void dcAddmissiondateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcAddmissiondateKeyPressed

    }//GEN-LAST:event_dcAddmissiondateKeyPressed

    private void cmbDBatchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDBatchKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            cmbDCourseSubject.requestFocus();
        }
    }//GEN-LAST:event_cmbDBatchKeyPressed

    private void cmbDCourseSubjectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbDCourseSubjectFocusGained

    }//GEN-LAST:event_cmbDCourseSubjectFocusGained

    private void dcAddmissiondateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dcAddmissiondateFocusLost

    }//GEN-LAST:event_dcAddmissiondateFocusLost

    private void lbladdmissionidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lbladdmissionidFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lbladdmissionidFocusGained

    private void txttempFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttempFocusGained
        cmbStudentName.requestFocus();
    }//GEN-LAST:event_txttempFocusGained

    private void cmbBatchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBatchItemStateChanged
        try {
            String st = cmbBatch.getSelectedItem().toString().substring(0, 8);
            String et = cmbBatch.getSelectedItem().toString().substring(11, 19);
            String sd = cmbBatch.getSelectedItem().toString().substring(22, 33);
            String ed = cmbBatch.getSelectedItem().toString().substring(36, 47);

            ResultSet res = db.run_select("Select * From Batch Where StartingTime like '%" + st + "%' AND Endingtime like '%" + et + "%' AND StartingDate like '%" + sd + "%' AND EndingDate like '%" + ed + "%' AND CourseSubject = '" + cmbCourseSubject.getSelectedItem().toString() + "' ");
            try {
                while (res.next()) {
                    String ai = res.getString("ActiveInactive");

                    if (ai.compareTo("Active") == 0) {
                        rbtnActive.setSelected(true);
                    } else if (ai.compareTo("Inactive") == 0) {
                        rbtnInactive.setSelected(true);
                    }
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbBatchItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*Setting Admission Id*/
        AdmissionId();

        /*Reselecting Select In Comboboxes*/
        cmbCourseSubject.setSelectedItem("Select");
        cmbBatch.setSelectedItem("Select");

        /*Setting CurrentDate In dcAdmissiondate*/
        LocalDate tempnow = LocalDate.now();
        Date now = java.sql.Date.valueOf(tempnow);
        dcAddmissiondate.setDate(now);

        /*Clearing Radio Button Selection*/
        buttonGroup1.clearSelection();

        /*Clearing Data in Table*/
        dtm.setRowCount(0);

        /*If Select is Selected*/
        String si = cmbStudentName.getSelectedItem().toString();

        if (si.compareTo("Select") == 0) {
            ResultSet res = db.run_select("Select * From Admission Order by AdmissionId ");
            try {
                while (res.next()) {
                    String ai = res.getString("AdmissionId");
                    String s = res.getString("StudentName");
                    String cs = res.getString("CourseSubject");
                    String b = res.getString("Batch");
                    String ad = res.getString("AdmissionDate");

                    dtm.addRow(new Object[]{ai, s, cs, b, ad});
                }
            } catch (Exception e) {
            }
        } else {
            /*Setting Data in Table*/
            ResultSet res = db.run_select("Select * From Admission Where StudentName = '" + si + "' ");
            try {
                while (res.next()) {
                    String ai = res.getString("AdmissionId");
                    String s = res.getString("StudentName");
                    String cs = res.getString("CourseSubject");
                    String b = res.getString("Batch");
                    String ad = res.getString("AdmissionDate");

                    dtm.addRow(new Object[]{ai, s, cs, b, ad});
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*Setting Admission Id*/
        AdmissionId();

        /*Reselecting Select In Comboboxes*/
        cmbCourseSubject.setSelectedItem("Select");
        cmbBatch.setSelectedItem("Select");
        cmbStudentName.setSelectedItem("Select");

        /*Clearing Radio Button Selection*/
        buttonGroup1.clearSelection();

        /*Clearing Data in Table*/
        dtm.setRowCount(0);

        Date tempd = dcAddmissiondate.getDate();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String adate = df.format(tempd);

        /*Setting Data in Table*/
        ResultSet res = db.run_select("Select * From Admission Where AdmissionDate = '" + adate + "' ");
        try {
            while (res.next()) {
                String ai = res.getString("AdmissionId");
                String s = res.getString("StudentName");
                String cs = res.getString("CourseSubject");
                String b = res.getString("Batch");
                String ad = res.getString("AdmissionDate");

                dtm.addRow(new Object[]{ai, s, cs, b, ad});
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbCourseSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCourseSubjectItemStateChanged
        /*Adding Item In cmbBatch*/
        cmbBatch.removeAllItems();
        cmbBatch.addItem("Select");

        String cs = cmbCourseSubject.getSelectedItem().toString();
        ResultSet res4 = db.run_select("Select * From Batch Where CourseSubject = '" + cs + "' ");
        try {
            while (res4.next()) {
                String st = res4.getString("StartingTime");
                String et = res4.getString("EndingTime");
                String sd = res4.getString("StartingDate");
                String ed = res4.getString("EndingDate");

                cmbBatch.addItem("" + st + " - " + et + " , " + sd + " - " + ed + "");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbCourseSubjectItemStateChanged

    /*AdmissionId*/
    public void AdmissionId() {
        txtAdMissionId.setEditable(false);

        ResultSet res = db.run_select("Select Count(*) From Admission");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                int i = Integer.valueOf(c) + 1;
                String AI = "EZP_ADM_" + "" + String.valueOf(i) + "";
                txtAdMissionId.setText(AI);
            }
        } catch (Exception e) {
        }

    }

    /*Refreshing Page*/
    public void Refresh() {
        /*Re-setting Admission Id*/
        AdmissionId();

        /*Reselecting Select In Comboboxes*/
        cmbStudentName.setSelectedItem("Select");
        cmbCourseSubject.setSelectedItem("Select");
        cmbBatch.setSelectedItem("Select");

        /*Setting CurrentDate In dcAdmissiondate*/
        LocalDate tempnow = LocalDate.now();
        Date now = java.sql.Date.valueOf(tempnow);
        dcAddmissiondate.setDate(now);

        /*Clearing Radio button Selection*/
        buttonGroup1.clearSelection();

        /*Re-setting Table*/
        dtm.setRowCount(0);
        ResultSet res1 = db.run_select("Select * From Admission Where Deleted = 'No' Order by AdmissionId ");
        try {
            while (res1.next()) {
                String ai = "EZP_ADM_" + res1.getString("AdmissionId");
                String s = res1.getString("StudentName");
                String cs = res1.getString("CourseSubject");
                String b = res1.getString("Batch");
                String ad = res1.getString("AdmissionDate");

                dtm.addRow(new Object[]{ai, s, cs, b, ad});
            }
        } catch (Exception e) {
        }

        /*Re-setting Values in cmbDCourseSubject*/
        ResultSet res4 = db.run_select("Select * From Admission Where Deleted = 'No' Group By CourseSubject Order By CourseSubject");
        try {
            while (res4.next()) {
                String cs = res4.getString("CourseSubject");
                cmbDCourseSubject.addItem(cs);
            }
        } catch (Exception e) {
        }

        int ic = cmbDCourseSubject.getItemCount() / 2;

        for (int i = ic; i > 1; i--) {
            cmbDCourseSubject.removeItemAt(i);
        }

        cmbDCourseSubject.setSelectedItem("Select");

        /*Clearing cmbDaBatch*/
        int ic2 = cmbDBatch.getItemCount();
        for (int i = ic2; i > 0; i--) {
            cmbDBatch.removeItemAt(i);
        }
    }

    /*Save Function*/
    public void Save() {
        Date tad = dcAddmissiondate.getDate();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        String ad = dateformat.format(tad);

        String tempsi = txtAdMissionId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String ssn = cmbStudentName.getSelectedItem().toString();
        String sc = cmbCourseSubject.getSelectedItem().toString();
        String sb = cmbBatch.getSelectedItem().toString();

        ResultSet res1 = db.run_select("Select * From Course Where CourseName = '" + sc + "' ");
        try {
            while (res1.next()) {
                String ci = res1.getString("CourseId");
                String cn = res1.getString("CourseName");
                String cf = res1.getString("CourseFees");

                if (cn.compareTo(sc) == 0) {

                    String st1 = cmbBatch.getSelectedItem().toString().substring(0, 8);
                    String et1 = cmbBatch.getSelectedItem().toString().substring(11, 19);
                    String sd1 = cmbBatch.getSelectedItem().toString().substring(22, 33);
                    String ed1 = cmbBatch.getSelectedItem().toString().substring(36, 47);

                    ResultSet res2 = db.run_select("Select * From Batch Where CourseSubject = '" + sc + "' And StartingTime = '" + st1 + "' And EndingTime = '" + et1 + "' And StartingDate = '" + sd1 + "' And EndingDate = '" + ed1 + "' ");
                    try {
                        while (res2.next()) {
                            String bi = res2.getString("BatchId");
                            String st = res2.getString("StartingTime");
                            String et = res2.getString("EndingTime");
                            String sd = res2.getString("StartingDate");
                            String ed = res2.getString("EndingDate");

                            String n[] = ssn.split(" ");
                            String fn1 = n[0];
                            String mn1 = n[1];
                            String ln1 = n[2];

                            ResultSet res3 = db.run_select("Select * From Student Where FirstName = '" + fn1 + "' And MiddleName = '" + mn1 + "' And LastName = '" + ln1 + "' ");
                            try {
                                while (res3.next()) {
                                    String nsi = res3.getString("StudentId");
                                    String fn = res3.getString("FirstName");
                                    String mn = res3.getString("MiddleName");
                                    String ln = res3.getString("LastName");

                                    String sql = "insert into Admission values(" + si + ", " + ci + ", " + bi + ", " + nsi + ", "
                                            + "'" + ssn + "', '" + sc + "', "
                                            + "'" + sb + "', '" + ad + "','No')";
                                    try {
                                        db.execute_sql(sql);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    String sf = "insert into Fees Values(0," + bi + ", " + ci + ", " + nsi + ",'" + ssn + "'," + si + ",'" + sc + "','" + sb + "'," + cf + ",0,'Zero Rupees',0," + cf + ",'" + ad + "')";
                                    db.execute_sql(sf);

                                }
                            } catch (Exception e) {
                            }

                        }
                    } catch (Exception e) {
                    }

                }

            }
        } catch (Exception e) {
        }

        JOptionPane.showMessageDialog(null, "Saved");

        try {
            Refresh();
            cmbDCourseSubject.removeItemAt(1);
        } catch (Exception e) {
        }
    }

    /*Update Function*/
    public void Update() {

        Date tad = dcAddmissiondate.getDate();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        String ad = dateformat.format(tad);

        String tempsi = txtAdMissionId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From Fees Where AdmissionId = " + si + " ");
        try {
            while (res.next()) {

                String sn = cmbStudentName.getSelectedItem().toString();
                String n[] = sn.split(" ");
                String fn = n[0];
                String mn = n[1];
                String ln = n[2];

                ResultSet res1 = db.run_select("Select * From Student Where FirstName = '" + fn + "' And MiddleName = '" + mn + "' And LastName = '" + ln + "' ");
                try {
                    while (res1.next()) {
                        String nsi = res1.getString("StudentId");

                        ResultSet res2 = db.run_select("Select * From Course Where CourseName = '" + cmbCourseSubject.getSelectedItem().toString() + "' ");
                        try {
                            while (res2.next()) {
                                String ci = res2.getString("CourseId");
                                String cf = res2.getString("CourseFees");

                                String st1 = cmbBatch.getSelectedItem().toString().substring(0, 8);
                                String et1 = cmbBatch.getSelectedItem().toString().substring(11, 19);
                                String sd1 = cmbBatch.getSelectedItem().toString().substring(22, 33);
                                String ed1 = cmbBatch.getSelectedItem().toString().substring(36, 47);

                                ResultSet res3 = db.run_select("Select * From Batch Where CourseSubject = '" + cmbCourseSubject.getSelectedItem().toString() + "' And StartingTime = '" + st1 + "' And EndingTime = '" + et1 + "' And StartingDate = '" + sd1 + "' And EndingDate = '" + ed1 + "' ");
                                try {
                                    while (res3.next()) {
                                        String bi = res3.getString("BatchId");

                                        String sql = "Update Admission Set CourseId = " + ci + ", BatchId = " + bi + ", StudentId = " + nsi + ", "
                                                + "StudentName = '" + cmbStudentName.getSelectedItem().toString() + "', "
                                                + " CourseSubject = '" + cmbCourseSubject.getSelectedItem().toString() + "', "
                                                + " Batch = '" + cmbBatch.getSelectedItem().toString() + "', "
                                                + " AdmissionDate = '" + ad + "' Where AdmissionId = " + si + " ";
                                        try {
                                            db.execute_sql(sql);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        ResultSet res4 = db.run_select("Select * From Fees Where Admissionid = " + si + " ");
                                        try {
                                            while (res4.next()) {
                                                String pf = res.getString("PaidFee");

                                                int tbf = Integer.valueOf(cf) - Integer.valueOf(pf);

                                                String sql1 = "Update Fees Set StudentName = '" + sn + "', StudentId = " + nsi + ", "
                                                        + "CourseName = '" + cmbCourseSubject.getSelectedItem().toString() + "', "
                                                        + "CourseId = " + ci + ", Batch = '" + cmbBatch.getSelectedItem().toString() + "', "
                                                        + "BatchId = " + bi + ", Fee = " + cf + ", BalanceFee = " + tbf + " Where AdmissionId = " + si + " And PaidFee = " + pf + " ";
                                                db.execute_sql(sql1);

                                            }
                                        } catch (Exception e) {
                                        }

                                    }
                                } catch (Exception e) {
                                }

                            }
                        } catch (Exception e) {
                        }

                    }
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }

        JOptionPane.showMessageDialog(null, "Updated");

        Refresh();
    }

    /*Delete Function*/
    public void Delete() {

        String tempsi = txtAdMissionId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Admission Set Deleted = 'Yes' Where AdmissionId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Deleted");

        try {
            Refresh();
            cmbDCourseSubject.removeItemAt(1);
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MainScrollPane;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbBatch;
    private javax.swing.JComboBox<String> cmbCourseSubject;
    private javax.swing.JComboBox<String> cmbDBatch;
    private javax.swing.JComboBox<String> cmbDCourseSubject;
    private javax.swing.JComboBox<String> cmbStudentName;
    private com.toedter.calendar.JDateChooser dcAddmissiondate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lbladdmissionid;
    private javax.swing.JRadioButton rbtnActive;
    private javax.swing.JRadioButton rbtnInactive;
    private javax.swing.JTable tableAdmissionData;
    private javax.swing.JTextField txtAdMissionId;
    private javax.swing.JTextField txttemp;
    // End of variables declaration//GEN-END:variables
}
