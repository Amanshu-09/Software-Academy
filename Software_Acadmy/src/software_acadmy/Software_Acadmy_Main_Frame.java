package software_acadmy;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;

public class Software_Acadmy_Main_Frame extends javax.swing.JFrame {

    public Software_Acadmy_Main_Frame() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd - MMM - yyyy");
        LocalDateTime now = LocalDateTime.now();
        lblcurrentdate.setText("Date : " + dtf.format(now));

        StartClock();

    }

    public void StartClock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar cal = new GregorianCalendar();

                        int hour = cal.get(Calendar.HOUR);
                        int minute = cal.get(Calendar.MINUTE);
                        int second = cal.get(Calendar.SECOND);
                        int ap = cal.get(Calendar.AM_PM);

                        if (String.valueOf(ap).compareTo("0") == 0) {
                            lblcurrenttime.setText("Time : " + hour + " : " + minute + " : " + second + " AM ");
                        } else if (String.valueOf(ap).compareTo("1") == 0) {
                            lblcurrenttime.setText("Time : " + hour + " : " + minute + " : " + second + " PM ");
                        }

                        if (String.valueOf(ap).compareTo("0") == 0 && hour == 0 == true) {
                            lblcurrenttime.setText("Time : 12 : " + minute + " : " + second + " AM ");
                        } else if (String.valueOf(ap).compareTo("1") == 0 && hour == 0 == true) {
                            lblcurrenttime.setText("Time : 12 : " + minute + " : " + second + " PM ");
                        }

                        sleep(1000);
                    }
                } catch (Exception e) {
                }
            }
        };
        clock.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("EzapiyaBackground.jpg"));
        Image img = icon.getImage();
        MyDesktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g)
            {
                g.drawImage(img,275,180,800,400,this);
            }
        };
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblcurrentdate = new javax.swing.JLabel();
        lblcurrenttime = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ezapiya Sofware Acadmy");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        MyDesktopPane.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MyDesktopPaneLayout = new javax.swing.GroupLayout(MyDesktopPane);
        MyDesktopPane.setLayout(MyDesktopPaneLayout);
        MyDesktopPaneLayout.setHorizontalGroup(
            MyDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1394, Short.MAX_VALUE)
        );
        MyDesktopPaneLayout.setVerticalGroup(
            MyDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );

        jPanel1.add(MyDesktopPane, java.awt.BorderLayout.CENTER);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true));
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Course.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Subject.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Batch.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Admission.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/Student.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblcurrentdate.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblcurrentdate.setText(".");

        lblcurrenttime.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblcurrenttime.setText(".");

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/dashboard.png"))); // NOI18N
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_acadmy/fee.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblcurrenttime, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblcurrentdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblcurrentdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblcurrenttime)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel2);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Add_Course ac = new frm_Add_Course();
        ac.setVisible(true);
        MyDesktopPane.add(ac);
        ac.setTitle("Courses");
        ac.setResizable(true);
        ac.setClosable(true);
        ac.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Add_Subject as = new frm_Add_Subject();
        as.setVisible(true);
        MyDesktopPane.add(as);
        as.setTitle("Subjects");
        as.setResizable(true);
        as.setClosable(true);
        as.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Batch fb = new frm_Batch();
        fb.setVisible(true);
        MyDesktopPane.add(fb);
        fb.setTitle("Batches");
        fb.setResizable(true);
        fb.setClosable(true);
        fb.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Admission fa = new frm_Admission();
        fa.setVisible(true);
        MyDesktopPane.add(fa);
        fa.setTitle("Addmissions");
        fa.setResizable(true);
        fa.setClosable(true);
        fa.requestFocus();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Student_Info si = new frm_Student_Info();
        si.setVisible(true);
        MyDesktopPane.add(si);
        si.setTitle("Student Information");
        si.setResizable(true);
        si.setClosable(true);
        si.requestFocus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();

        frm_Dashboard dab = new frm_Dashboard();
        MyDesktopPane.add(dab);
        
        if (jToggleButton1.isSelected() == true) {
            dab.setVisible(true);
        } else {
            dab.setVisible(false);
        }

        Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
        dab.setLocation(Screensize.width-405,-24);
        dab.setResizable(true);
        dab.requestFocus();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged

    }//GEN-LAST:event_jToggleButton1StateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        MyDesktopPane.removeAll();
        MyDesktopPane.repaint();
        
        frm_Fees ff = new frm_Fees();
        ff.setVisible(true);
        MyDesktopPane.add(ff);
        ff.setTitle("Fees Information");
        ff.setResizable(true);
        ff.setClosable(true);
        ff.requestFocus();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Software_Acadmy_Main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Software_Acadmy_Main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Software_Acadmy_Main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Software_Acadmy_Main_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>*/

 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Software_Acadmy_Main_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane MyDesktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblcurrentdate;
    private javax.swing.JLabel lblcurrenttime;
    // End of variables declaration//GEN-END:variables
}
