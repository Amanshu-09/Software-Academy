package software_acadmy;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class frm_Dashboard extends javax.swing.JInternalFrame {

    dbConnection db;
    DefaultTableModel dtm;

    public frm_Dashboard() {
        initComponents();

        try {
            db = new dbConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frm_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(frm_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(404, Screensize.height - 128);

        dtm = new DefaultTableModel();
        tablestudentsjoinedthismonth.setModel(dtm);
        dtm.addColumn("StudentName");
        dtm.addColumn("CourseName");

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        LocalDate tempnow = LocalDate.now();
        //Date now = java.sql.Date.valueOf(tempnow);
        //String td = df.format(now);

        LocalDate tempthen = tempnow.minusMonths(1);
        //Date then = java.sql.Date.valueOf(tempthen);
        //String dbm = df.format(then);

        for (LocalDate date = tempthen; date.isBefore(tempnow); date.plusDays(1)) {
            
            Date sdate = java.sql.Date.valueOf(date);
            String pdate = df.format(sdate);
            
            ResultSet res = db.run_select("Select * From Admission Where AdmissionDate = '" + pdate + "' ");
            try {
                while (res.next()) {
                    String sn = res.getString("StudentName");
                    String ad = res.getString("CourseSubject");

                    dtm.addRow(new Object[]{sn, ad});
                }
            } catch (Exception e) {
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablestudentsjoinedthismonth = new javax.swing.JTable();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBar(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tablestudentsjoinedthismonth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablestudentsjoinedthismonth);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablestudentsjoinedthismonth;
    // End of variables declaration//GEN-END:variables
}
