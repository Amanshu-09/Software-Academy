package software_acadmy;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class frm_Student_Info extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm;

    public frm_Student_Info() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, Screensize.width, Screensize.height - 150);

        MainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        /*Student Id*/
        StudentId();

        /*Declaring Tables*/
        dtm = new DefaultTableModel();
        tableStudentData.setModel(dtm);
        dtm.addColumn("Student Id");
        dtm.addColumn("First Name");
        dtm.addColumn("Middle Name");
        dtm.addColumn("Last Name");
        dtm.addColumn("Guardian Name");
        dtm.addColumn("Student's Phone No.");
        dtm.addColumn("Guardian's Phone No.");
        dtm.addColumn("Date Of Birth");
        dtm.addColumn("Education Name");
        dtm.addColumn("Class/Year/Semester");
        dtm.addColumn("Collage Name");
        dtm.addColumn("Photo Link");

        tableStudentData.setDefaultEditor(Object.class, null);
        tableStudentData.getTableHeader().setBackground(Color.white);
        tableStudentData.getTableHeader().setForeground(Color.orange);
        tableStudentData.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        ResultSet res = db.run_select("Select * From Student Where Deleted = 'No' Order By StudentId");
        try {
            while (res.next()) {
                String si = "EZP_STD_" + res.getString("StudentId");
                String fn = res.getString("FirstName");
                String mn = res.getString("MiddleName");
                String ln = res.getString("LastName");
                String gn = res.getString("GuardianName");
                String sp = res.getString("StudentPhoneNo");
                String gp = res.getString("GuardianPhoneNo");
                String db = res.getString("DateOfBirth");
                String ed = res.getString("EducationName");
                String ys = res.getString("ClassYearSemester");
                String cn = res.getString("CollageName");
                String ph = res.getString("Photo");

                dtm.addRow(new Object[]{si, fn, mn, ln, gn, sp, gp, db, ed, ys, cn, ph});

            }
        } catch (Exception e) {
        }

        /*Setting Date*/
        LocalDate now = LocalDate.now();
        Date TD = java.sql.Date.valueOf(now);
        dcDOB.setDate(TD);

        /*Setting Values in Combobox cmbeducationname*/
        ResultSet res2 = db.run_select("Select EducationName From Student  GROUP by EducationName ORDER by EducationName");
        try {
            while (res2.next()) {
                String ed = res2.getString("EducationName");
                cmbeducationname.addItem(ed);
            }
        } catch (Exception e) {
        }

        /*SettingFocusTraversalKeysCollectionsEmpty*/
        txtFirstName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtMiddleName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtLastName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtGuardianName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        cmbeducationname.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtCollageName.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtGuardianPhone.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        btnage.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        btnattachphoto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());

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
        JpanelCurrentQualification = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtClassYearSem = new javax.swing.JTextField();
        txtEducationName = new javax.swing.JTextField();
        txtCollageName = new javax.swing.JTextField();
        cmbeducationname = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMiddleName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_Image = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtStudentPhone = new javax.swing.JTextField();
        txtGuardianPhone = new javax.swing.JTextField();
        txtStudentId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnage = new javax.swing.JButton();
        dcDOB = new com.toedter.calendar.JDateChooser();
        lblAge = new javax.swing.JLabel();
        btnattachphoto = new javax.swing.JButton();
        txtGuardianName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblimagepath = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableStudentData = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        MainScrollPane.setHorizontalScrollBar(null);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        JpanelCurrentQualification.setBackground(new java.awt.Color(0, 51, 102));
        JpanelCurrentQualification.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Add Student", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Current Qualification", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Class/Year/Semester");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Collage Name");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Education Name");

        txtClassYearSem.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtClassYearSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClassYearSemActionPerformed(evt);
            }
        });

        txtEducationName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtEducationName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEducationNameActionPerformed(evt);
            }
        });
        txtEducationName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEducationNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEducationNameKeyReleased(evt);
            }
        });

        txtCollageName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCollageName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCollageNameActionPerformed(evt);
            }
        });
        txtCollageName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCollageNameKeyPressed(evt);
            }
        });

        cmbeducationname.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbeducationname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbeducationname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbeducationnameItemStateChanged(evt);
            }
        });
        cmbeducationname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbeducationnameActionPerformed(evt);
            }
        });
        cmbeducationname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbeducationnameKeyPressed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 51, 102));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 51, 102));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClassYearSem, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addComponent(txtEducationName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbeducationname, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCollageName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEducationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbeducationname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtClassYearSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtCollageName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel11, jLabel12, jLabel13});

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Id");

        txtMiddleName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtMiddleName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMiddleNameKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("First Name");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date Of Birth");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Guardian's Phone No.");

        lbl_Image.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Student's Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        txtLastName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastNameKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Last Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Middle Name");

        txtFirstName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyPressed(evt);
            }
        });

        txtStudentPhone.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtGuardianPhone.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtGuardianPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGuardianPhoneKeyPressed(evt);
            }
        });

        txtStudentId.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student's Phone No.");

        btnage.setBackground(new java.awt.Color(0, 51, 102));
        btnage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnage.setForeground(new java.awt.Color(26, 238, 167));
        btnage.setText("Age ?");
        btnage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        btnage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnageActionPerformed(evt);
            }
        });
        btnage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnageKeyPressed(evt);
            }
        });

        dcDOB.setDateFormatString("dd, MMM yyyy");
        dcDOB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dcDOB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dcDOBKeyPressed(evt);
            }
        });

        lblAge.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblAge.setForeground(new java.awt.Color(255, 255, 255));

        btnattachphoto.setBackground(new java.awt.Color(0, 51, 102));
        btnattachphoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnattachphoto.setForeground(new java.awt.Color(26, 238, 167));
        btnattachphoto.setText("Attach photo");
        btnattachphoto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(26, 238, 167), 1, true));
        btnattachphoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnattachphotoActionPerformed(evt);
            }
        });
        btnattachphoto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnattachphotoKeyPressed(evt);
            }
        });

        txtGuardianName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtGuardianName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGuardianNameKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Guardian's Name");

        lblimagepath.setBackground(new java.awt.Color(0, 51, 102));
        lblimagepath.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblimagepath.setForeground(new java.awt.Color(0, 51, 102));

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 51, 102));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 51, 102));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 51, 102));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 51, 102));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 51, 102));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 51, 102));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpanelCurrentQualificationLayout = new javax.swing.GroupLayout(JpanelCurrentQualification);
        JpanelCurrentQualification.setLayout(JpanelCurrentQualificationLayout);
        JpanelCurrentQualificationLayout.setHorizontalGroup(
            JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(53, 53, 53)
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMiddleName)
                                    .addComponent(txtLastName)
                                    .addComponent(txtGuardianName)
                                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                        .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtFirstName)))
                            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(43, 43, 43)
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dcDOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtStudentPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtGuardianPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnage, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbl_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnattachphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblimagepath, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JpanelCurrentQualificationLayout.setVerticalGroup(
            JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtStudentPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtGuardianPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanelCurrentQualificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(btnage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addGroup(JpanelCurrentQualificationLayout.createSequentialGroup()
                        .addComponent(lbl_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(btnattachphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(lblimagepath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Student's Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        tableStudentData.setBackground(new java.awt.Color(0, 51, 102));
        tableStudentData.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tableStudentData.setForeground(new java.awt.Color(255, 255, 255));
        tableStudentData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableStudentData.setRowHeight(20);
        tableStudentData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStudentDataMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableStudentData);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1742, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Eat_Sleep_Code_Repeat.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(786, Short.MAX_VALUE))
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
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JpanelCurrentQualification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JpanelCurrentQualification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
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

    private void btnageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnageActionPerformed
        /*Calculating Age*/
        LocalDate now = LocalDate.now();
        Date dobtemp = dcDOB.getDate();
        LocalDate dob = dobtemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period diff = Period.between(dob, now);
        long ageyears = diff.getYears();
        long agedays = diff.getDays();
        long agemonths = diff.getMonths();

        //Showing Age in Years
        if (agedays < 0 || agemonths < 0 || ageyears < 0) {
            lblAge.setText("");
            JOptionPane.showMessageDialog(this, "You Can't Born In Future !!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            lblAge.setText(String.valueOf("Age is " + ageyears + " Years, " + agemonths + " Months And " + agedays + " Days"));
        }

    }//GEN-LAST:event_btnageActionPerformed

    private void txtCollageNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCollageNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCollageNameActionPerformed

    private void txtEducationNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEducationNameActionPerformed

    }//GEN-LAST:event_txtEducationNameActionPerformed

    private void txtClassYearSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClassYearSemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClassYearSemActionPerformed

    private void btnattachphotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnattachphotoActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose Students's Photo");
        fc.showOpenDialog(null);
        File image = fc.getSelectedFile();
        String imagepath = image.getAbsolutePath();
        lblimagepath.setText(imagepath);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagepath.toString()));
        } catch (IOException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        Image finalimage = img.getScaledInstance(lbl_Image.getWidth() - 8, lbl_Image.getHeight() - 20, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(finalimage);
        lbl_Image.setIcon(icon);
    }//GEN-LAST:event_btnattachphotoActionPerformed

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

    private void tableStudentDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStudentDataMouseClicked
        int row = tableStudentData.getSelectedRow();

        /*Setting Text Field*/
        txtStudentId.setText(dtm.getValueAt(row, 0).toString());
        txtFirstName.setText(dtm.getValueAt(row, 1).toString());
        txtMiddleName.setText(dtm.getValueAt(row, 2).toString());
        txtLastName.setText(dtm.getValueAt(row, 3).toString());
        txtGuardianName.setText(dtm.getValueAt(row, 4).toString());
        txtStudentPhone.setText(dtm.getValueAt(row, 5).toString());
        txtGuardianPhone.setText(dtm.getValueAt(row, 6).toString());
        txtEducationName.setText(dtm.getValueAt(row, 8).toString());
        cmbeducationname.setSelectedItem(dtm.getValueAt(row, 8).toString());
        txtClassYearSem.setText(dtm.getValueAt(row, 9).toString());
        txtCollageName.setText(dtm.getValueAt(row, 10).toString());

        /*Setting date Of Birth*/
        String tsi = dtm.getValueAt(row, 0).toString();
        String si = BasisLibrary.substringF(tsi, 9);
        
        ResultSet res = db.run_select("Select * From Student Where StudentId = " + si + " ");
        try {
            while (res.next()) {
                String dob = res.getString("DateOfBirth");
                Date bd = new SimpleDateFormat("dd-MMM-yyyy").parse(dob);
                dcDOB.setDate(bd);
            }
        } catch (Exception e) {
        }

        /*Calulating Age*/
        Age();

        /*loading Image*/

        ResultSet res2 = db.run_select("Select * From Student Where StudentId = '" + si + "'");
        try {
            while (res2.next()) {
                String ip = res2.getString("Photo");

                String imagepath = ip;
                lblimagepath.setText(imagepath);

                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(imagepath.toString()));
                } catch (IOException ex) {
                    Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
                }

                Image finalimage = img.getScaledInstance(lbl_Image.getWidth() - 8, lbl_Image.getHeight() - 20, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(finalimage);
                lbl_Image.setIcon(icon);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tableStudentDataMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtGuardianName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String fn = txtFirstName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where FirstName like '%" + fn + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "First Name Like " + fn + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where FirstName like '%" + fn + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cmbeducationnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbeducationnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbeducationnameActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String gn = txtGuardianName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where GuardianName like '%" + gn + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Guardian's Name Like " + gn + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where GuardianName like '%" + gn + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn1 = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn1, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtLastName.setText("");
        txtFirstName.setText("");
        txtGuardianName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String mn = txtMiddleName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where MiddleName like '%" + mn + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Middle Name Like " + mn + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where MiddleName like '%" + mn + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn1 = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn1, ln, gn, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtFirstName.setText("");
        txtGuardianName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String ln = txtLastName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where LastName like '%" + ln + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Last Name Like " + ln + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where LastName like '%" + ln + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln1 = res.getString("LastName");
                            String gn = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln1, gn, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtStudentPhone.setText("");
        txtGuardianName.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String en = txtEducationName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where EducationName like '%" + en + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Education Name Like " + en + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where EducationName like '%" + en + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn1 = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn1, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtGuardianName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String cn = txtCollageName.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where CollageName like '%" + cn + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Collage Name Like " + cn + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where CollageName like '%" + cn + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn1 = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn1 = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn1, sp, gp, db, ed, ys, cn1, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void cmbeducationnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbeducationnameItemStateChanged
        String si = cmbeducationname.getSelectedItem().toString();

        if (si.compareTo("Select") == 0) {
            txtEducationName.setText("");
        } else {
            txtEducationName.setText(si);
        }
    }//GEN-LAST:event_cmbeducationnameItemStateChanged

    private void txtEducationNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEducationNameKeyReleased
        /*Setting Selected Item in cmbeducationname*/
        String en = txtEducationName.getText().toString();
        ResultSet res = db.run_select("Select * From Student");
        try {
            while (res.next()) {
                String ed = res.getString("EducationName");
                if (en.toLowerCase().compareTo(ed.toLowerCase()) == 0) {
                    cmbeducationname.setSelectedItem(ed);
                } else if (en.compareTo("") == 0) {
                    cmbeducationname.setSelectedItem("Select");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEducationNameKeyReleased

    private void txtEducationNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEducationNameKeyPressed

    }//GEN-LAST:event_txtEducationNameKeyPressed

    private void btnattachphotoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnattachphotoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choose Students's Photo");
            fc.showOpenDialog(null);
            File image = fc.getSelectedFile();
            String imagepath = image.getAbsolutePath();
            lblimagepath.setText(imagepath);

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(imagepath.toString()));
            } catch (IOException ex) {
                Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image finalimage = img.getScaledInstance(lbl_Image.getWidth() - 8, lbl_Image.getHeight() - 20, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(finalimage);
            lbl_Image.setIcon(icon);
        }

        if (evt.getKeyCode() == evt.VK_TAB) {
            txtEducationName.requestFocus();
        }

    }//GEN-LAST:event_btnattachphotoKeyPressed

    private void btnageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnageKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            Age();
        }
        if (evt.getKeyCode() == evt.VK_TAB) {
            btnattachphoto.requestFocus();
        }
    }//GEN-LAST:event_btnageKeyPressed

    private void txtFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtMiddleName.requestFocus();
        }
    }//GEN-LAST:event_txtFirstNameKeyPressed

    private void txtMiddleNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMiddleNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtLastName.requestFocus();
        }
    }//GEN-LAST:event_txtMiddleNameKeyPressed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtGuardianName.requestFocus();
        }
    }//GEN-LAST:event_txtLastNameKeyPressed

    private void txtGuardianNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGuardianNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtStudentPhone.requestFocus();
        }
    }//GEN-LAST:event_txtGuardianNameKeyPressed

    private void cmbeducationnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbeducationnameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtClassYearSem.requestFocus();
        }
    }//GEN-LAST:event_cmbeducationnameKeyPressed

    private void txtCollageNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCollageNameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtFirstName.requestFocus();
        }
    }//GEN-LAST:event_txtCollageNameKeyPressed

    private void txtGuardianPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGuardianPhoneKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            dcDOB.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtGuardianPhoneKeyPressed

    private void dcDOBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcDOBKeyPressed

    }//GEN-LAST:event_dcDOBKeyPressed

    private void txtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyReleased
        /*Setting GuardianName*/
        String GN = "" + txtMiddleName.getText().toString() + "" + " " + "" + txtLastName.getText().toString() + "";
        txtGuardianName.setText(GN);

        if (txtMiddleName.getText().compareTo("") == 0) {
            txtGuardianName.setText("" + txtLastName.getText().toString() + "");
        }
    }//GEN-LAST:event_txtLastNameKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtStudentPhone.setText("");
        txtGuardianName.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String gn = txtGuardianPhone.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where GuardianPhoneNo like '%" + gn + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Guardian's Phone Number Like " + gn + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where GuardianPhoneNo like '%" + gn + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn1 = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn1, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        /*Refreshing Page*/
        StudentId();
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtGuardianName.setText("");
        txtGuardianPhone.setText("");
        lblAge.setText("");
        lblimagepath.setText("");
        lbl_Image.setIcon(null);
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        LocalDate tnow = LocalDate.now();
        Date t2now = java.sql.Date.valueOf(tnow);
        dcDOB.setDate(t2now);
        dtm.setRowCount(0);
        /*Searching Student's Data In Student Table*/

        String sp1 = txtStudentPhone.getText().toString();

        ResultSet res2 = db.run_select("SELECT Count(*) From Student where StudentPhoneNo like '%" + sp1 + "%' ");
        try {
            while (res2.next()) {
                String c = res2.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Student's Phone Number Like " + sp1 + " Not Found ! ", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res = db.run_select("SELECT * From Student where StudentPhoneNo like '%" + sp1 + "%' ");
                    try {
                        while (res.next()) {
                            String si = "EZP_STD_" + res.getString("StudentId");
                            String fn1 = res.getString("FirstName");
                            String mn = res.getString("MiddleName");
                            String ln = res.getString("LastName");
                            String gn1 = res.getString("GuardianName");
                            String sp = res.getString("StudentPhoneNo");
                            String gp = res.getString("GuardianPhoneNo");
                            String db = res.getString("DateOfBirth");
                            String ed = res.getString("EducationName");
                            String ys = res.getString("ClassYearSemester");
                            String cn = res.getString("CollageName");
                            String ph = res.getString("Photo");

                            dtm.addRow(new Object[]{si, fn1, mn, ln, gn1, sp, gp, db, ed, ys, cn, ph});
                        }
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton13ActionPerformed
    /*Refresh*/
    public void Refresh() {
        StudentId();
        txtFirstName.setText("");
        txtMiddleName.setText("");
        txtLastName.setText("");
        txtGuardianName.setText("");
        txtStudentPhone.setText("");
        txtGuardianPhone.setText("");
        txtEducationName.setText("");
        cmbeducationname.setSelectedItem("Select");
        txtClassYearSem.setText("");
        txtCollageName.setText("");

        dtm.setRowCount(0);

        lbl_Image.setIcon(null);
        lblAge.setText("");
        lblimagepath.setText("");

        LocalDate now = LocalDate.now();
        Date TD = java.sql.Date.valueOf(now);
        dcDOB.setDate(TD);

        ResultSet res = db.run_select("Select * From Student Where Deleted = 'No' Order By StudentId");
        try {
            while (res.next()) {
                String si = "EZP_STD_" + res.getString("StudentId");
                String fn = res.getString("FirstName");
                String mn = res.getString("MiddleName");
                String ln = res.getString("LastName");
                String gn = res.getString("GuardianName");
                String sp = res.getString("StudentPhoneNo");
                String gp = res.getString("GuardianPhoneNo");
                String db = res.getString("DateOfBirth");
                String ed = res.getString("EducationName");
                String ys = res.getString("ClassYearSemester");
                String cn = res.getString("CollageName");
                String ph = res.getString("Photo");

                dtm.addRow(new Object[]{si, fn, mn, ln, gn, sp, gp, db, ed, ys, cn, ph});
            }
        } catch (Exception e) {
        }

        ResultSet res2 = db.run_select("Select EducationName From Student  GROUP by EducationName ORDER by EducationName");
        try {
            while (res2.next()) {
                String ed = res2.getString("EducationName");
                cmbeducationname.addItem(ed);
            }
        } catch (Exception e) {
        }

        int ic = cmbeducationname.getItemCount() / 2 - 1;
        for (int i = ic; i > 0; i--) {
            cmbeducationname.removeItemAt(i);
        }
        cmbeducationname.removeItemAt(1);
    }

    /*StudentId*/
    public void StudentId() {
        txtStudentId.setEditable(false);
        ResultSet res = db.run_select("Select Count(*) From Student");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                int temp = Integer.valueOf(c) + 1;
                String SI = "EZP_STD_" + String.valueOf(temp) + "";
                txtStudentId.setText(SI);
            }
        } catch (Exception e) {
        }
    }

    /*Age*/
    public void Age() {

        LocalDate now = LocalDate.now();
        Date dobtemp = dcDOB.getDate();
        LocalDate dob = dobtemp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period diff = Period.between(dob, now);
        long ageyears = diff.getYears();
        long agedays = diff.getDays();
        long agemonths = diff.getMonths();

        if (agedays < 0 || agemonths < 0 || ageyears < 0) {
            lblAge.setText("");
            JOptionPane.showMessageDialog(this, "You Can't Born In Future !!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            lblAge.setText(String.valueOf("Age is " + ageyears + " Years, " + agemonths + " Months And " + agedays + " Days"));
        }
    }

    /*Save Function*/
    public void Save() {
        Date tdob = dcDOB.getDate();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        String dob = dateformat.format(tdob);

        String tempsi = txtStudentId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "insert into Student values(" + si + ", "
                + "'" + txtFirstName.getText().toString() + "', '" + txtMiddleName.getText().toString() + "', "
                + "'" + txtLastName.getText().toString() + "', '" + txtGuardianName.getText().toString() + "', "
                + "'" + txtStudentPhone.getText().toString() + "', '" + txtGuardianPhone.getText().toString() + "', "
                + "'" + dob + "', '" + txtEducationName.getText().toString() + "', "
                + "'" + txtClassYearSem.getText().toString() + "', '" + txtCollageName.getText().toString() + "', "
                + "'" + lblimagepath.getText().toString() + "','No')";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Saved");

        Refresh();
    }

    /*Function Update*/
    public void Update() {
        Date tdob = dcDOB.getDate();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        String dob = dateformat.format(tdob);

        String tempsi = txtStudentId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Student Set FirstName = '" + txtFirstName.getText().toString() + "', "
                + " MiddleName = '" + txtMiddleName.getText().toString() + "', "
                + " LastName = '" + txtLastName.getText().toString() + "', "
                + " GuardianName = '" + txtGuardianName.getText().toString() + "', "
                + " StudentPhoneNo = '" + txtStudentPhone.getText().toString() + "', "
                + " GuardianPhoneNo = '" + txtGuardianPhone.getText().toString() + "', "
                + " DateOfBirth = '" + dob + "', "
                + " EducationName = '" + txtEducationName.getText().toString() + "', "
                + " ClassYearSemester = '" + txtClassYearSem.getText().toString() + "', "
                + " CollageName = '" + txtCollageName.getText().toString() + "', "
                + " Photo = '" + lblimagepath.getText().toString() + "' Where StudentId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sn = "" + txtFirstName.getText() + " " + txtMiddleName.getText() + " " + txtLastName.getText() + "";

        String sql2 = "Update Admission set StudentName = '" + sn + "' Where StudentId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql4 = "Update Fees set StudentName = '" + sn + "' Where StudentId = " + si + " ";
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

        String tempsi = txtStudentId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Student Set Deleted = 'Yes' Where StudentId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql2 = "Update Admission Set Deleted = 'Yes' Where StudentId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Student_Info.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(null, "Deleted");

        Refresh();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpanelCurrentQualification;
    private javax.swing.JScrollPane MainScrollPane;
    private javax.swing.JButton btnage;
    private javax.swing.JButton btnattachphoto;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cmbeducationname;
    private com.toedter.calendar.JDateChooser dcDOB;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lbl_Image;
    private javax.swing.JLabel lblimagepath;
    private javax.swing.JTable tableStudentData;
    private javax.swing.JTextField txtClassYearSem;
    private javax.swing.JTextField txtCollageName;
    private javax.swing.JTextField txtEducationName;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGuardianName;
    private javax.swing.JTextField txtGuardianPhone;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddleName;
    private javax.swing.JTextField txtStudentId;
    private javax.swing.JTextField txtStudentPhone;
    // End of variables declaration//GEN-END:variables
}
