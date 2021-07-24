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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class frm_Add_Course extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm;
    DefaultListModel dlm;

    public frm_Add_Course() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, Screensize.width, Screensize.height - 150);

        MainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        /*Setting Values In cmbSelectSubjectsinCourse*/
        ResultSet res = db.run_select("Select * From Subject Where Deleted = 'No' Order By SubjectName");
        try {
            while (res.next()) {
                String sn = res.getString("SubjectName");
                cmbselectsubjectsincourse.addItem(sn);
            }
        } catch (Exception e) {
        }

        /*Declaring Table*/
        dtm = new DefaultTableModel();
        tableCourseData.setModel(dtm);

        dtm.addColumn("Course Id");
        dtm.addColumn("Course Name");
        dtm.addColumn("Subject Name");
        dtm.addColumn("Subject Description");
        tableCourseData.setDefaultEditor(Object.class, null);
        tableCourseData.getTableHeader().setBackground(Color.white);
        tableCourseData.getTableHeader().setForeground(Color.orange);
        tableCourseData.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        ResultSet res3 = db.run_select("Select * From Course Where Deleted = 'No' Order By CourseId");
        try {
            while (res3.next()) {
                String ci = res3.getString("CourseId");
                String ci2 = "EZP_CRS_" + res3.getString("CourseId");
                String cn = res3.getString("CourseName");

                ResultSet res4 = db.run_select("Select * from CourseData Where CourseId = " + ci + " And Deleted = 'No' ");
                try {
                    while (res4.next()) {
                        String sn = res4.getString("SubjectName");
                        String de = res4.getString("Description");
                        dtm.addRow(new Object[]{ci2, cn, sn, de});
                    }
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }

        /*Setting CourseId*/
        CourseId();

        /*Setting listModel*/
        dlm = new DefaultListModel();
        listsubsincourse.setModel(dlm);

        /*SettingFocusTraversalKeysCollectionsEmptySet*/
        txtCoursename.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        cmbselectsubjectsincourse.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtCourseDuration.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtCoursefees.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        txtDescription.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCourseId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCoursename = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listsubsincourse = new javax.swing.JList<>();
        cmbselectsubjectsincourse = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();
        btnremoveall = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCoursefees = new javax.swing.JTextField();
        txtCourseDuration = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCourseData = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jPanel6 = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();

        MainScrollPane.setHorizontalScrollBar(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Add Course", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Course Name");

        txtCourseId.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCourseId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCourseIdActionPerformed(evt);
            }
        });
        txtCourseId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCourseIdKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Course ID");

        txtCoursename.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCoursename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCoursenameKeyPressed(evt);
            }
        });

        listsubsincourse.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        listsubsincourse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listsubsincourseKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(listsubsincourse);

        cmbselectsubjectsincourse.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbselectsubjectsincourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbselectsubjectsincourse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbselectsubjectsincourseKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Select Subjects ");

        btnadd.setBackground(new java.awt.Color(0, 51, 102));
        btnadd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        btnremove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        btnremoveall.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Description");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDescription.setRows(5);
        txtDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescriptionKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(txtDescription);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course Duration");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Course Fees");

        txtCoursefees.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCoursefees.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCoursefeesKeyPressed(evt);
            }
        });

        txtCourseDuration.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtCourseDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCourseDurationActionPerformed(evt);
            }
        });
        txtCourseDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCourseDurationKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("in Course");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Search_1.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(txtCoursename, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbselectsubjectsincourse, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(71, 71, 71)
                                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(btnremove, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnremoveall)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(68, 68, 68)
                        .addComponent(txtCourseId)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCourseDuration, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                            .addComponent(txtCoursefees))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnadd, btnremove, btnremoveall});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCourseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtCourseDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCoursename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCoursefees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(cmbselectsubjectsincourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnremove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addComponent(btnremoveall, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Course Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        tableCourseData.setBackground(new java.awt.Color(0, 51, 102));
        tableCourseData.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tableCourseData.setForeground(new java.awt.Color(255, 255, 255));
        tableCourseData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableCourseData.setRowHeight(20);
        tableCourseData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCourseDataMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableCourseData);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(769, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainScrollPane.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        /*Adding Subject To Course*/
        String si = cmbselectsubjectsincourse.getSelectedItem().toString();

        for (int i = 0; i < dlm.getSize(); i++) {
            String li = dlm.getElementAt(i).toString();
            if (si.compareTo(li) == 0) {
                JOptionPane.showMessageDialog(this, "This Subject Already Available in This Course ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                dlm.removeElement(si);
            }
        }

        dlm.addElement(si);

        if (si.compareTo("Select") == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Subject To Add in Course ! ", "Warning", JOptionPane.WARNING_MESSAGE);
            dlm.removeElement("Select");
        }
    }//GEN-LAST:event_btnaddActionPerformed

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

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
        String lr = listsubsincourse.getSelectedValue();
        dlm.removeElement(lr);

        int rc = dlm.getSize();
        String et = cmbselectsubjectsincourse.getSelectedItem().toString();
        for (int i = 0; i < rc; i++) {
            if (dlm.getElementAt(i).toString().compareTo(et) == 0) {
                dlm.removeElementAt(i);
            }
        }
    }//GEN-LAST:event_btnremoveActionPerformed

    private void btnremoveallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveallActionPerformed
        dlm.removeAllElements();
    }//GEN-LAST:event_btnremoveallActionPerformed

    private void tableCourseDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCourseDataMouseClicked
        /*Setting values*/
        int row = tableCourseData.getSelectedRow();

        txtCourseId.setText(dtm.getValueAt(row, 0).toString());
        txtCoursename.setText(dtm.getValueAt(row, 1).toString());

        String tempsi = dtm.getValueAt(row, 0).toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        ResultSet res = db.run_select("Select * From Course Where CourseId = " + si + " ");
        try {
            while (res.next()) {
                String de = res.getString("Description");
                String cd = res.getString("CourseDuration");
                String cf = res.getString("CourseFees");

                txtDescription.setText(de);
                txtCourseDuration.setText(cd);
                txtCoursefees.setText(cf);
            }
        } catch (Exception e) {
        }

        dlm.removeAllElements();
        ResultSet res2 = db.run_select("Select * From CourseData Where CourseId = " + si + " ");
        try {
            while (res2.next()) {
                String sn = res2.getString("SubjectName");
                dlm.addElement(sn);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tableCourseDataMouseClicked

    private void txtCourseDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCourseDurationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseDurationActionPerformed

    private void txtCourseIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCourseIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseIdActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        /*Searching Course*/
        CourseId();
        cmbselectsubjectsincourse.setSelectedItem("Select");
        dlm.removeAllElements();
        txtDescription.setText("");
        txtCourseDuration.setText("");
        txtCoursefees.setText("");
        dtm.setRowCount(0);

        String si = txtCoursename.getText().toString();

        ResultSet res = db.run_select("Select Count(*) from Course where CourseName like '%" + si + "%'");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Subject Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From Course Where CourseName like '%" + si + "%' order by CourseId ");
                    try {
                        while (res1.next()) {
                            String ci = res1.getString("CourseId");
                            String ci2 = "EZP_CRS_" + res1.getString("CourseId");
                            String cn = res1.getString("CourseName");
                            String de = res1.getString("Description");
                            String cd = res1.getString("CourseDuration");
                            String cf = res1.getString("CourseFees");

                            if (si.toLowerCase().compareTo(cn.toLowerCase()) == 0) {
                                txtCourseId.setText(ci2);
                                txtCoursename.setText(cn);
                                txtDescription.setText(de);
                                txtCourseDuration.setText(cd);
                                txtCoursefees.setText(cf);
                                ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                                try {
                                    while (res2.next()) {
                                        String sn = res2.getString("SubjectName");
                                        String de2 = res2.getString("Description");

                                        dlm.addElement(sn);
                                    }
                                } catch (Exception e) {
                                }

                            }

                            ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                            try {
                                while (res2.next()) {
                                    String sn = res2.getString("SubjectName");
                                    String de2 = res2.getString("Description");

                                    dtm.addRow(new Object[]{ci2, cn, sn, de2});
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
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        /*Searching Course Duration*/
        CourseId();
        cmbselectsubjectsincourse.setSelectedItem("Select");
        dlm.removeAllElements();
        txtCoursename.setText("");
        txtDescription.setText("");
        txtCoursefees.setText("");
        dtm.setRowCount(0);

        String si = txtCourseDuration.getText().toString();

        ResultSet res = db.run_select("Select Count(*) from Course where CourseDuration like '%" + si + "%'");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Course Duration Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From Course Where CourseDuration like '%" + si + "%' order by CourseId ");
                    try {
                        while (res1.next()) {
                            String ci = res1.getString("CourseId");
                            String ci2 = "EZP_CRS_" + res1.getString("CourseId");
                            String cn = res1.getString("CourseName");
                            String de = res1.getString("Description");
                            String cd = res1.getString("CourseDuration");
                            String cf = res1.getString("CourseFees");

                            ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                            try {
                                while (res2.next()) {
                                    String sn = res2.getString("SubjectName");
                                    String de2 = res2.getString("Description");

                                    dtm.addRow(new Object[]{ci2, cn, sn, de2});
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
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        /*Searching Course Fees*/
        CourseId();
        cmbselectsubjectsincourse.setSelectedItem("Select");
        dlm.removeAllElements();
        txtCoursename.setText("");
        txtDescription.setText("");
        txtCourseDuration.setText("");
        dtm.setRowCount(0);

        String si = txtCoursefees.getText().toString();

        ResultSet res = db.run_select("Select Count(*) from Course where CourseFees = '" + si + "'");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Course Fees Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From Course Where CourseFees = '" + si + "' order by CourseId ");
                    try {
                        while (res1.next()) {
                            String ci = res1.getString("CourseId");
                            String ci2 = "EZP_CRS_" + res1.getString("CourseId");
                            String cn = res1.getString("CourseName");
                            String de = res1.getString("Description");
                            String cd = res1.getString("CourseDuration");
                            String cf = res1.getString("CourseFees");

                            ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                            try {
                                while (res2.next()) {
                                    String sn = res2.getString("SubjectName");
                                    String de2 = res2.getString("Description");

                                    dtm.addRow(new Object[]{ci2, cn, sn, de2});
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
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        /*Searching Description*/
        CourseId();
        cmbselectsubjectsincourse.setSelectedItem("Select");
        dlm.removeAllElements();
        txtCoursename.setText("");
        txtCourseDuration.setText("");
        txtCoursefees.setText("");
        dtm.setRowCount(0);

        String si = txtDescription.getText().toString();

        ResultSet res = db.run_select("Select Count(*) from Course where Description like '%" + si + "%'");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Description Not Found!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From Course Where Description like '%" + si + "%' order by CourseId ");
                    try {
                        while (res1.next()) {
                            String ci = res1.getString("CourseId");
                            String ci2 = "EZP_CRS_" + res1.getString("CourseId");
                            String cn = res1.getString("CourseName");
                            String de = res1.getString("Description");
                            String cd = res1.getString("CourseDuration");
                            String cf = res1.getString("CourseFees");

                            if (si.toLowerCase().compareTo(de.toLowerCase()) == 0) {
                                txtCourseId.setText(ci2);
                                txtCoursename.setText(cn);
                                txtDescription.setText(de);
                                txtCourseDuration.setText(cd);
                                txtCoursefees.setText(cf);
                                ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                                try {
                                    while (res2.next()) {
                                        String sn = res2.getString("SubjectName");
                                        String de2 = res2.getString("Description");

                                        dlm.addElement(sn);
                                    }
                                } catch (Exception e) {
                                }
                            }

                            ResultSet res2 = db.run_select("Select * from CourseData Where CourseId = " + ci + " ");
                            try {
                                while (res2.next()) {
                                    String sn = res2.getString("SubjectName");
                                    String de2 = res2.getString("Description");

                                    dtm.addRow(new Object[]{ci2, cn, sn, de2});
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
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        /*Searching Course*/
        CourseId();
        txtCoursename.setText("");
        dlm.removeAllElements();
        txtDescription.setText("");
        txtCourseDuration.setText("");
        txtCoursefees.setText("");
        dtm.setRowCount(0);

        String si = cmbselectsubjectsincourse.getSelectedItem().toString();

        ResultSet res = db.run_select("Select Count(*) from CourseData where SubjectName like '%" + si + "%'");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                if (c.compareTo("0") == 0) {
                    JOptionPane.showMessageDialog(this, "Subject " + si + " Not Found in Any Course!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ResultSet res1 = db.run_select("Select * From CourseData Where SubjectName like '%" + si + "%' order by CourseId ");
                    try {
                        while (res1.next()) {
                            String ci = res1.getString("CourseId");
                            String sn = res1.getString("SubjectName");
                            String de2 = res1.getString("Description");

                            if (si.compareTo(sn) == 0) {
                                ResultSet res2 = db.run_select("Select * from Course where CourseId = " + ci + " ");
                                try {
                                    String ci2 = "EZP_CRS_" + res1.getString("CourseId");
                                    String cn = res2.getString("CourseName");
                                    String de = res2.getString("Description");
                                    String cd = res2.getString("CourseDuration");
                                    String cf = res2.getString("CourseFees");

                                    dtm.addRow(new Object[]{ci2, cn, sn, de2});
                                } catch (Exception e) {
                                }
                            }

                        }
                    } catch (Exception e) {
                    }
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txtCourseIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseIdKeyPressed

    }//GEN-LAST:event_txtCourseIdKeyPressed

    private void txtCoursenameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoursenameKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            cmbselectsubjectsincourse.requestFocus();
        }
    }//GEN-LAST:event_txtCoursenameKeyPressed

    private void cmbselectsubjectsincourseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbselectsubjectsincourseKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtCourseDuration.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnadd.requestFocus();
        }
    }//GEN-LAST:event_cmbselectsubjectsincourseKeyPressed

    private void btnaddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnaddKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnremove.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            /*Adding Subject To Course*/
            String si = cmbselectsubjectsincourse.getSelectedItem().toString();

            for (int i = 0; i < dlm.getSize(); i++) {
                String li = dlm.getElementAt(i).toString();
                if (si.compareTo(li) == 0) {
                    JOptionPane.showMessageDialog(this, "This Subject Already Available in This Course ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                    dlm.removeElement(si);
                }
            }

            dlm.addElement(si);

            if (si.compareTo("Select") == 0) {
                JOptionPane.showMessageDialog(this, "Please Select Subject To Add in Course ! ", "Warning", JOptionPane.WARNING_MESSAGE);
                dlm.removeElement("Select");
            }

            cmbselectsubjectsincourse.requestFocus();
        }
    }//GEN-LAST:event_btnaddKeyPressed

    private void btnremoveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoveKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            btnremoveall.requestFocus();
        }

        if (evt.getKeyCode() == evt.VK_ENTER) {
            String lr = listsubsincourse.getSelectedValue();
            dlm.removeElement(lr);

            int rc = dlm.getSize();
            String et = cmbselectsubjectsincourse.getSelectedItem().toString();
            for (int i = 0; i < rc; i++) {
                if (dlm.getElementAt(i).toString().compareTo(et) == 0) {
                    dlm.removeElementAt(i);
                }
            }
        }
    }//GEN-LAST:event_btnremoveKeyPressed

    private void btnremoveallKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnremoveallKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCourseDuration.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dlm.removeAllElements();
        }
    }//GEN-LAST:event_btnremoveallKeyPressed

    private void listsubsincourseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listsubsincourseKeyPressed

    }//GEN-LAST:event_listsubsincourseKeyPressed

    private void txtCourseDurationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCourseDurationKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtCoursefees.requestFocus();
        }
    }//GEN-LAST:event_txtCourseDurationKeyPressed

    private void txtCoursefeesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoursefeesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtDescription.requestFocus();
            txtDescription.setTabSize(0);
        }
    }//GEN-LAST:event_txtCoursefeesKeyPressed

    private void txtDescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescriptionKeyPressed
        if (evt.getKeyCode() == evt.VK_TAB) {
            txtCoursename.requestFocus();
        }
    }//GEN-LAST:event_txtDescriptionKeyPressed

    /*Refresh*/
    public void Refresh() {
        CourseId();
        txtCoursename.setText("");
        cmbselectsubjectsincourse.setSelectedItem("Select");
        dlm.removeAllElements();
        txtDescription.setText("");
        txtCourseDuration.setText("");
        txtCoursefees.setText("");
        dtm.setRowCount(0);

        ResultSet res3 = db.run_select("Select * From Course Where Deleted = 'No' Order By CourseId");
        try {
            while (res3.next()) {
                String ci = res3.getString("CourseId");
                String ci2 = "EZP_CRS_" + res3.getString("CourseId");
                String cn = res3.getString("CourseName");

                ResultSet res4 = db.run_select("Select * from CourseData Where CourseId = '" + ci + "' And Deleted = 'No' ");
                try {
                    while (res4.next()) {
                        String sn = res4.getString("SubjectName");
                        String de = res4.getString("Description");
                        dtm.addRow(new Object[]{ci2, cn, sn, de});
                    }
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }
    }

    /*Setting CourseId*/
    public void CourseId() {
        txtCourseId.setEditable(false);
        ResultSet res = db.run_select("Select Count(*) From Course");
        try {
            while (res.next()) {
                String c = res.getString("Count(*)");
                int i = Integer.valueOf(c) + 1;
                String ci = "EZP_CRS_" + i + "";
                txtCourseId.setText(ci);
            }
        } catch (Exception e) {
        }
    }

    /*Save Function*/
    public void Save() {
        int lc2 = dlm.getSize();
        if (lc2 >= 1 == true) {
            /*Saving Record In Course Table*/

            String tempsi = txtCourseId.getText().toString();
            String si = BasisLibrary.substringF(tempsi, 9);

            String sql = "insert into Course values(" + si + ", "
                    + "'" + txtCoursename.getText().toString() + "', '" + txtDescription.getText().toString() + "', "
                    + "'" + txtCourseDuration.getText().toString() + "', '" + txtCoursefees.getText().toString() + "', 'No')";
            try {
                db.execute_sql(sql);
            } catch (SQLException ex) {
                Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*Saving Records In CourseData Table*/
            int lc = dlm.getSize();
            for (int i = 0; i < lc; i++) {

                String sn = dlm.getElementAt(i).toString();

                ResultSet res = db.run_select("Select * From Subject");
                try {
                    while (res.next()) {
                        String asi = res.getString("SubjectId");
                        String n = res.getString("SubjectName");
                        String de = res.getString("Description");

                        if (sn.compareTo(n) == 0) {
                            String sql2 = "insert into CourseData values(" + si + ", " + asi + ", '" + sn + "', '" + de + "', 'No')";
                            db.execute_sql(sql2);
                        } else {
                        }
                    }
                } catch (Exception e) {
                }
            }
            JOptionPane.showMessageDialog(null, "Saved");

            Refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Please Enter Add Atleast One Subject In A Course !", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*Update Function*/
    public void Update() {
        /*Updating Record In Course Table*/
        String tempsi = txtCourseId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "Update Course Set CourseName = '" + txtCoursename.getText().toString() + "', "
                + " Description = '" + txtDescription.getText().toString() + "', "
                + " CourseDuration = '" + txtCourseDuration.getText().toString() + "', "
                + " CourseFees = '" + txtCoursefees.getText().toString() + "' "
                + " Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Deleting Records From CourseData*/
        String sql3 = "Delete From CourseData Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql3);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Re-inserting Records In CourseData Table*/
        int lc = dlm.getSize();
        for (int i = 0; i < lc; i++) {

            String sn = dlm.getElementAt(i).toString();

            ResultSet res = db.run_select("Select * From Subject");
            try {
                while (res.next()) {
                    String asi = res.getString("SubjectId");
                    String n = res.getString("SubjectName");
                    String de = res.getString("Description");

                    if (sn.compareTo(n) == 0) {
                        String sql2 = "insert into CourseData values(" + si + ", " + asi + ", '" + sn + "', '" + de + "', 'No')";
                        db.execute_sql(sql2);
                    } else {
                    }
                }
            } catch (Exception e) {
            }
        }

        String sql1 = "update Batch set CourseSubject = '" + txtCoursename.getText().toString() + "' Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql2 = "Update Admission set CourseSubject = '" + txtCoursename.getText().toString() + "' Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql4 = "Update Fees set CourseName = '" + txtCoursename.getText().toString() + "' Where CourseId = " + si + " ";
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
        /*Deleting Record From Course Table*/
        String tempsi = txtCourseId.getText().toString();
        String si = BasisLibrary.substringF(tempsi, 9);

        String sql = "update Course Set Deleted = 'Yes' Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*Deleting Record From CourseData Table*/
        String sql2 = "Update CourseData Set Deleted = 'Yes' Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql1 = "update Batch set Deleted = 'Yes' Where CourseId = " + si + " ";
        try {
            db.execute_sql(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Add_Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql4 = "Update Admission Set Deleted = 'Yes' Where CourseId = "+si+" ";
        try {
            db.execute_sql(sql4);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Batch.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> cmbselectsubjectsincourse;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JList<String> listsubsincourse;
    private javax.swing.JTable tableCourseData;
    private javax.swing.JTextField txtCourseDuration;
    private javax.swing.JTextField txtCourseId;
    private javax.swing.JTextField txtCoursefees;
    private javax.swing.JTextField txtCoursename;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables
}
