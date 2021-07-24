package software_acadmy;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import net.sf.jasperreports.view.JasperViewer;

public class frm_Fees extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm, dtm2;

    public frm_Fees() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Screensize.width, Screensize.height - 150);

        MainScollPane.getVerticalScrollBar().setUnitIncrement(16);

        LocalDate tempnow = LocalDate.now();
        Date dnow = java.sql.Date.valueOf(tempnow);
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String now = df.format(dnow);
        lblDate.setText(now);

        FeesId();
        
        ResultSet res2 = db.run_select("Select * From Course Where Deleted = 'No' order by CourseName");
        try {
            while (res2.next()) {
                String cn = res2.getString("CourseName");
                cmbCourseSubject.addItem(cn);
            }
        } catch (Exception e) {
        }

        ResultSet res3 = db.run_select("Select * From Student order by FirstName");
        try {
            while (res3.next()) {
                String fn = res3.getString("FirstName");
                String mn = res3.getString("MiddleName");
                String ln = res3.getString("LastName");

                String sname = "" + fn + " " + mn + " " + ln + "";
                cmbstudentname.addItem(sname);
            }
        } catch (Exception e) {
        }

        dtm = new DefaultTableModel();
        tablefeeshistory.setModel(dtm);
        tablefeeshistory.setDefaultEditor(Object.class, null);

        tablefeeshistory.getTableHeader().setBackground(Color.white);
        tablefeeshistory.getTableHeader().setForeground(Color.orange);
        tablefeeshistory.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        dtm.addColumn("Admission Id");
        dtm.addColumn("Course Name");
        dtm.addColumn("Batch");
        dtm.addColumn("Fee");
        dtm.addColumn("Paid Fee");
        dtm.addColumn("Balance Fee");
        dtm.addColumn("Date");

        dtm2 = new DefaultTableModel();
        tableAddedInFollowingCourse.setModel(dtm2);
        tableAddedInFollowingCourse.setDefaultEditor(Object.class, null);

        tableAddedInFollowingCourse.getTableHeader().setBackground(Color.white);
        tableAddedInFollowingCourse.getTableHeader().setForeground(Color.orange);
        tableAddedInFollowingCourse.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));

        dtm2.addColumn("Admission Id");
        dtm2.addColumn("Course Name");
        dtm2.addColumn("Batch");
        dtm2.addColumn("Fee");
        dtm2.addColumn("Paid Fee");
        dtm2.addColumn("Balance Fee");
        dtm2.addColumn("Active/Inactive");
        dtm2.addColumn("Admission Date");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainScollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablefeeshistory = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableAddedInFollowingCourse = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblstudentid = new javax.swing.JLabel();
        lblstudentname = new javax.swing.JLabel();
        cmbCourseSubject = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbBatch = new javax.swing.JComboBox<>();
        cmbstudentname = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblpaidamount = new javax.swing.JLabel();
        lblbalanceamount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPayment = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblTotalamount = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtfeesid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtpayamountinwords = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 255, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Paid Fees History");

        tablefeeshistory.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tablefeeshistory.setForeground(new java.awt.Color(0, 51, 102));
        tablefeeshistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablefeeshistory.setShowHorizontalLines(false);
        tablefeeshistory.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tablefeeshistory);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel2);

        tableAddedInFollowingCourse.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tableAddedInFollowingCourse.setForeground(new java.awt.Color(0, 51, 102));
        tableAddedInFollowingCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableAddedInFollowingCourse.setShowHorizontalLines(false);
        tableAddedInFollowingCourse.setShowVerticalLines(false);
        jScrollPane5.setViewportView(tableAddedInFollowingCourse);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel3);

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 255, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Added In Following Courses");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(java.awt.Color.darkGray);
        jLabel1.setText("Student Id :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(java.awt.Color.darkGray);
        jLabel11.setText("Student Name :");

        lblstudentid.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        lblstudentname.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblstudentname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblstudentid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblstudentid, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(lblstudentname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbCourseSubject.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbCourseSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbCourseSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCourseSubjectItemStateChanged(evt);
            }
        });
        cmbCourseSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCourseSubjectActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Batch");

        cmbBatch.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbBatch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbBatch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBatchItemStateChanged(evt);
            }
        });

        cmbstudentname.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbstudentname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbstudentname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbstudentnameItemStateChanged(evt);
            }
        });
        cmbstudentname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbstudentnameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Paid Amount");

        lblpaidamount.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblpaidamount.setForeground(new java.awt.Color(255, 255, 255));

        lblbalanceamount.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblbalanceamount.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Balance Amount");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Date");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pay Amount ");

        txtPayment.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Payment.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 202, 137), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblTotalamount.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTotalamount.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Amount");

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtfeesid.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtfeesid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfeesidActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fees Id");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pay Amount in words");

        txtpayamountinwords.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtpayamountinwords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayamountinwordsActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblpaidamount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblbalanceamount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(lblTotalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtfeesid)
                                    .addComponent(cmbCourseSubject, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbstudentname, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbBatch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpayamountinwords))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmbstudentname, txtPayment});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfeesid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbCourseSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbBatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbstudentname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtpayamountinwords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblpaidamount)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblbalanceamount)
                            .addComponent(jLabel6))
                        .addGap(57, 57, 57)
                        .addComponent(jButton2))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblDate, lblTotalamount, lblbalanceamount, lblpaidamount, txtPayment});

        MainScollPane.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1321, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainScollPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCourseSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCourseSubjectItemStateChanged
        /*Adding Items in cmbDBatch*/
        String cs = cmbCourseSubject.getSelectedItem().toString();

        int ics = cmbBatch.getItemCount() - 1;
        for (int i = ics; i > 0; i--) {
            cmbBatch.removeItemAt(i);
        }

        //Select Start
        if (cs.compareTo("Select") == 0) {

            int ic = cmbBatch.getItemCount() - 1;
            for (int i = ic; i > 0; i--) {
                cmbBatch.removeItemAt(i);
            }
            cmbBatch.setSelectedItem("Select");

            ResultSet res3 = db.run_select("Select * From Student order by FirstName");
            try {
                while (res3.next()) {
                    String fn = res3.getString("FirstName");
                    String mn = res3.getString("MiddleName");
                    String ln = res3.getString("LastName");

                    String sname = "" + fn + " " + mn + " " + ln + "";
                    cmbstudentname.addItem(sname);
                }
            } catch (Exception e) {
            }
            cmbstudentname.setSelectedItem("Select");

            txtPayment.setText("");
            lblTotalamount.setText("");
            lblpaidamount.setText("");
            lblbalanceamount.setText("");
            lblstudentid.setText("");
            lblstudentname.setText("");
        }
        //Select End

        //CourseSubjects Start
        ResultSet res4 = db.run_select("Select * From Admission Where CourseSubject = '" + cs + "' And Deleted = 'No' ");
        try {
            while (res4.next()) {
                String csn = res4.getString("CourseSubject");
                if (cs.compareTo(csn) == 0) {

                    ResultSet res5 = db.run_select("Select Count(*) From Admission");
                    try {
                        String C = res5.getString("Count(*)");
                        if (Integer.valueOf(C) % 2 == 0) {
                            int ic = cmbBatch.getItemCount() - 1;
                            for (int i = ic; i > 1; i--) {
                                cmbBatch.removeItemAt(i);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(frm_Admission.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String B = res4.getString("Batch");
                    cmbBatch.addItem(B);

                }

            }
        } catch (Exception e) {
        }
        //CourseSubject End
    }//GEN-LAST:event_cmbCourseSubjectItemStateChanged

    private void cmbBatchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBatchItemStateChanged
        String ci = cmbCourseSubject.getSelectedItem().toString();

        int ic = cmbstudentname.getItemCount() - 1;
        for (int i = ic; i > 0; i--) {
            cmbstudentname.removeItemAt(i);
        }

        ResultSet res2 = db.run_select("Select * From Admission Where Batch = '" + cmbBatch.getSelectedItem().toString() + "' And CourseSubject = '" + ci + "' And Deleted = 'No' ");
        try {
            while (res2.next()) {
                String ai = "EZP_ADM_" + res2.getString("AdmissionId");
                String s = res2.getString("StudentName");
                String cs = res2.getString("CourseSubject");
                String b = res2.getString("Batch");
                String ad = res2.getString("AdmissionDate");

                cmbstudentname.addItem(s);
            }
        } catch (Exception e) {
        }

        String bi = cmbBatch.getSelectedItem().toString();
        if (bi.compareTo("Select") == 0) {
            cmbstudentname.setSelectedItem("Select");

            txtPayment.setText("");
            lblTotalamount.setText("");
            lblpaidamount.setText("");
            lblbalanceamount.setText("");
            lblstudentid.setText("");
            lblstudentname.setText("");
        }

    }//GEN-LAST:event_cmbBatchItemStateChanged

    private void txtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaymentActionPerformed

    private void cmbstudentnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbstudentnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbstudentnameActionPerformed

    private void cmbstudentnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbstudentnameItemStateChanged
        dtm.setRowCount(0);
        dtm2.setRowCount(0);

        String ss = cmbstudentname.getSelectedItem().toString();

        if (ss.compareTo("Select") == 0) {
            txtPayment.setText("");
            lblTotalamount.setText("");
            lblpaidamount.setText("");
            lblbalanceamount.setText("");
            lblstudentid.setText("");
            lblstudentname.setText("");
        }

        /*Setting records in table1*/
        ResultSet res = db.run_select("Select * From Fees Where StudentName = '" + ss + "' ");
        try {
            while (res.next()) {
                String si = res.getString("StudentId");
                String si2 = "EZP_STD_" + res.getString("StudentId");
                String sn = res.getString("StudentName");
                String ai = res.getString("AdmissionId");
                String ai2 = "EZP_ADM_" + res.getString("AdmissionId");
                String cn = res.getString("CourseName");
                String b = res.getString("Batch");
                String f = res.getString("Fee");
                String pf = res.getString("PaidFee");
                String bf = res.getString("BalanceFee");
                String ad = res.getString("Date");

                lblstudentid.setText(si2);
                lblstudentname.setText(sn);

                dtm.addRow(new Object[]{ai2, cn, b, f, pf, bf, ad});

            }
        } catch (Exception e) {
        }

        /*Setting records in table2*/
        ResultSet res6 = db.run_select("Select *,max(PaidFee) From Fees Where StudentName = '" + ss + "' Group by AdmissionId");
        try {
            while (res6.next()) {
                String si = res6.getString("StudentId");
                String sn = res6.getString("StudentName");
                String ai = res6.getString("AdmissionId");
                String ai2 = "EZP_ADM_" + res6.getString("AdmissionId");
                String cn = res6.getString("CourseName");
                String b = res6.getString("Batch");
                String f = res6.getString("Fee");
                String pf = res6.getString("PaidFee");
                String bf = res6.getString("BalanceFee");

                ResultSet res7 = db.run_select("Select * from Admission Where AdmissionId = " + ai + " ");
                try {
                    while (res7.next()) {
                        String ad = res7.getString("AdmissionDate");

                        String st = b.substring(0, 8);
                        String et = b.substring(11, 19);
                        String sd = b.substring(22, 33);
                        String ed = b.substring(36, 47);

                        ResultSet res8 = db.run_select("Select * From Batch Where CourseSubject = '" + cn + "' And StartingTime = '" + st + "' And EndingTime = '" + et + "' And StartingDate = '" + sd + "' And EndingDate = '" + ed + "' ");
                        try {
                            while (res8.next()) {
                                String acic = res8.getString("ActiveInactive");
                                dtm2.addRow(new Object[]{ai2, cn, b, f, pf, bf, acic, ad});
                            }
                        } catch (Exception e) {
                        }

                    }
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
        }

        /*Setting Values in Overall fee, paid fee and balance fee*/
        int rc = dtm2.getRowCount();
        int of = 0;
        int opf = 0;
        int obf = 0;
        for (int i = 0; i < rc; i++) {
            String tfee = dtm2.getValueAt(i, 3).toString();
            String tpfee = dtm2.getValueAt(i, 4).toString();
            String tbfee = dtm2.getValueAt(i, 5).toString();

            of = of + Integer.valueOf(tfee);
            opf = opf + Integer.valueOf(tpfee);
            obf = obf + Integer.valueOf(tbfee);

            lblTotalamount.setText(String.valueOf(of));
            lblpaidamount.setText(String.valueOf(opf));
            lblbalanceamount.setText(String.valueOf(obf));
        }
    }//GEN-LAST:event_cmbstudentnameItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tfi = txtfeesid.getText();
        String fi = BasisLibrary.substringF(tfi, 9);
        String sc = cmbCourseSubject.getSelectedItem().toString();
        String sb = cmbBatch.getSelectedItem().toString();
        String p = txtPayment.getText();
        String pd = lblDate.getText();
        int ptp = Integer.valueOf(p);
        String pa = txtPayment.getText();
        String paiw = txtpayamountinwords.getText();
        String sn = cmbstudentname.getSelectedItem().toString();
        String n[] = sn.split(" ");
        String fn1 = n[0];
        String mn1 = n[1];
        String ln1 = n[2];

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

                            ResultSet res3 = db.run_select("Select * From Student Where FirstName = '" + fn1 + "' And MiddleName = '" + mn1 + "' And LastName = '" + ln1 + "' ");
                            try {
                                while (res3.next()) {
                                    String nsi = res3.getString("StudentId");
                                    String fn = res3.getString("FirstName");
                                    String mn = res3.getString("MiddleName");
                                    String ln = res3.getString("LastName");

                                    ResultSet res4 = db.run_select("Select * from Admission where CourseId = " + ci + " And BatchId = " + bi + " And StudentId = " + nsi + " ");
                                    try {
                                        while (res4.next()) {
                                            String ai = res4.getString("AdmissionId");
                                            String ad = res4.getString("AdmissionDate");

                                            ResultSet res5 = db.run_select("Select Max(PaidFee),BalanceFee From Fees Where AdmissionId = " + ai + " And CourseId = " + ci + " And BatchId = " + bi + " And StudentId = " + nsi + " ");
                                            try {
                                                while (res5.next()) {
                                                    String pp = res5.getString("Max(PaidFee)");
                                                    String bp = res5.getString("BalanceFee");

                                                    int tpp = Integer.valueOf(pp) + ptp;
                                                    int tpb = Integer.valueOf(bp) - ptp;

                                                    String sf = "insert into Fees Values("+fi+"," + bi + ", " + ci + ", " + nsi + ",'" + sn + "'," + ai + ",'" + sc + "','" + sb + "'," + cf + ","+pa+",'"+paiw+"'," + tpp + "," + tpb + ",'" + pd + "')";
                                                    db.execute_sql(sf);
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

            }
        } catch (Exception e) {
        }
        JOptionPane.showMessageDialog(this, "Payment Done Successfully !", "Information", JOptionPane.HEIGHT);

        /*Print*/
        try {
            Connection con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\HP\\Documents\\NetBeansProjects\\Software_Acadmy\\src\\SoftwareAcadmy.db");
            
            HashMap h = new HashMap();
            h.put("FeesId", fi);

            net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport("C:\\Users\\HP\\JaspersoftWorkspace\\MyReports\\Software_Acadmy\\Fees_Receipt.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(report, h, con);
            JasperViewer.viewReport(jp, true);

        } catch (SQLException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Refresh
        cmbCourseSubject.setSelectedItem("Select");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbCourseSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCourseSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCourseSubjectActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Connection con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\HP\\Documents\\NetBeansProjects\\Software_Acadmy\\src\\SoftwareAcadmy.db");
            
            HashMap h = new HashMap();
            String fi = BasisLibrary.substringF(txtfeesid.getText().toString(), 9);
            h.put("FeesId", fi);
            
            net.sf.jasperreports.engine.JasperReport report = JasperCompileManager.compileReport("C:\\Users\\HP\\JaspersoftWorkspace\\MyReports\\Software_Acadmy\\Fees_Receipt.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(report, h, con);
            JasperViewer.viewReport(jp, true);

        } catch (SQLException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(frm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }catch (JRFontNotFoundException ex){
            
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtfeesidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfeesidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfeesidActionPerformed

    private void txtpayamountinwordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayamountinwordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayamountinwordsActionPerformed
    public void FeesId(){
        txtfeesid.setEditable(false);
        ResultSet res = db.run_select("Select Count(*) From Fees");
        try {
            while(res.next()){
                String c1 = res.getString("Count(*)");
                ResultSet res1 = db.run_select("Select Count(*) From Fees Where FeesId = 0");
                try {
                    while(res1.next()){
                        String c2 = res1.getString("Count(*)");
                        int co1 = Integer.valueOf(c1);
                        int co2 = Integer.valueOf(c2);

                        int oc = co1-co2+1;

                        String fi = "EZP_FEE_"+String.valueOf(oc);
                        txtfeesid.setText(fi);
                    }
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MainScollPane;
    private javax.swing.JComboBox<String> cmbBatch;
    private javax.swing.JComboBox<String> cmbCourseSubject;
    private javax.swing.JComboBox<String> cmbstudentname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTotalamount;
    private javax.swing.JLabel lblbalanceamount;
    private javax.swing.JLabel lblpaidamount;
    private javax.swing.JLabel lblstudentid;
    private javax.swing.JLabel lblstudentname;
    private javax.swing.JTable tableAddedInFollowingCourse;
    private javax.swing.JTable tablefeeshistory;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextField txtfeesid;
    private javax.swing.JTextField txtpayamountinwords;
    // End of variables declaration//GEN-END:variables
}
