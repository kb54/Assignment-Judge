/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjudge;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Sunil
 */
public class create_cases extends javax.swing.JFrame {

    /**
     * Creates new form Check_Assignment
     */
    public create_cases() {
        initComponents();
        backgroundlbl.setFocusable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        testcasesarea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        expectedoutputarea = new javax.swing.JTextArea();
        casebtn = new javax.swing.JButton();
        exiticn = new javax.swing.JLabel();
        backgroundlbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Assignment Judge");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 840, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Test Generator");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 180, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Test Cases");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 100, 29));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Expected Output");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, -1, 29));

        testcasesarea.setColumns(20);
        testcasesarea.setForeground(new java.awt.Color(153, 153, 153));
        testcasesarea.setRows(5);
        testcasesarea.setText("Enter number of Test Cases\n#Case 1\n#Case 2\n#Case 3\n#Case 4\n.......\n.......\n.......\n.......\n#Case N\n\n\n");
        testcasesarea.setWrapStyleWord(true);
        testcasesarea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                testcasesareaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                testcasesareaFocusLost(evt);
            }
        });
        testcasesarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testcasesareaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(testcasesarea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 350, 355));

        expectedoutputarea.setColumns(20);
        expectedoutputarea.setForeground(new java.awt.Color(153, 153, 153));
        expectedoutputarea.setRows(5);
        expectedoutputarea.setText("#Output 1\n#Output 2\n#Output 3\n#Output 4\n.........\n.........\n.........\n.........\n.........\n#Output N");
        expectedoutputarea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                expectedoutputareaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                expectedoutputareaFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(expectedoutputarea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 150, 350, 355));

        casebtn.setBackground(new java.awt.Color(0, 153, 153));
        casebtn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        casebtn.setForeground(new java.awt.Color(255, 255, 255));
        casebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/click_evaluate.png"))); // NOI18N
        casebtn.setText("Generate Cases File");
        casebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casebtnActionPerformed(evt);
            }
        });
        getContentPane().add(casebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 510, 250, 50));

        exiticn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close_1.png"))); // NOI18N
        exiticn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exiticnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exiticnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exiticnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exiticnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exiticnMouseReleased(evt);
            }
        });
        getContentPane().add(exiticn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, -1, -1));

        backgroundlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/background(bg3).png"))); // NOI18N
        getContentPane().add(backgroundlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void casebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casebtnActionPerformed
        // revoking the rights from the user to input the file names(making file names default)
        if(testcasesarea.getText().equals("Enter number of Test Cases\n#Case 1\n#Case 2"
            + "\n#Case 3\n#Case 4\n.......\n.......\n.......\n.......\n#Case N\n\n\n")){
            JOptionPane.showMessageDialog(null, "Please Enter Test Cases!");
        }
        else if(expectedoutputarea.getText().equals("#Output 1\n#Output 2\n#Output 3\n#Output 4\n........."
            + "\n.........\n.........\n.........\n.........\n#Output N")){
            JOptionPane.showMessageDialog(null, "Please Enter Respective Expected Outputs!");
        }
        else{
            BuildTestCaseFile buildFiles = new BuildTestCaseFile();
            boolean result = buildFiles.createFiles("testCaseFile.txt", "expectedOutputFile.txt", "programOutputFile.txt", "resultCSV.csv",testcasesarea.getText(), expectedoutputarea.getText());
            if(result){
                JOptionPane.showMessageDialog(null, "Test Cases file Builded Successfully!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error in building files!");
            }
        }
    }//GEN-LAST:event_casebtnActionPerformed

    private void expectedoutputareaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_expectedoutputareaFocusLost
        if(expectedoutputarea.getText().trim().equals(""))
        {
            expectedoutputarea.setText("#Output 1\n#Output 2\n#Output 3\n#Output 4\n........."
                + "\n.........\n.........\n.........\n.........\n#Output N");
            expectedoutputarea.setForeground(new java.awt.Color(153,153,153));
        }
    }//GEN-LAST:event_expectedoutputareaFocusLost

    private void expectedoutputareaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_expectedoutputareaFocusGained
        if(expectedoutputarea.getText().equals("#Output 1\n#Output 2\n#Output 3\n#Output 4\n........."
            + "\n.........\n.........\n.........\n.........\n#Output N"))
    {
        expectedoutputarea.setText("");
        expectedoutputarea.setForeground(java.awt.Color.BLACK);
        }
    }//GEN-LAST:event_expectedoutputareaFocusGained

    private void testcasesareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testcasesareaMouseClicked
        
    }//GEN-LAST:event_testcasesareaMouseClicked

    private void testcasesareaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testcasesareaFocusLost
        if(testcasesarea.getText().trim().equals(""))
        {
            testcasesarea.setText("Enter number of Test Cases\n#Case 1\n#Case 2"
                + "\n#Case 3\n#Case 4\n.......\n.......\n.......\n.......\n#Case N\n\n\n");
            testcasesarea.setForeground(new java.awt.Color(153,153,153));
        }
    }//GEN-LAST:event_testcasesareaFocusLost

    private void testcasesareaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testcasesareaFocusGained
        if(testcasesarea.getText().equals("Enter number of Test Cases\n#Case 1\n#Case 2"
            + "\n#Case 3\n#Case 4\n.......\n.......\n.......\n.......\n#Case N\n\n\n"))
    {
        testcasesarea.setText("");
        testcasesarea.setForeground(java.awt.Color.BLACK);
    }
    }//GEN-LAST:event_testcasesareaFocusGained

    private void exiticnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiticnMouseClicked
        this.dispose();
    }//GEN-LAST:event_exiticnMouseClicked

    private void exiticnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiticnMouseEntered
        ImageIcon icn = new ImageIcon(getClass().getResource("/resources/closeho.png"));
        exiticn.setIcon(icn);
    }//GEN-LAST:event_exiticnMouseEntered

    private void exiticnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiticnMouseExited
        ImageIcon icn = new ImageIcon(getClass().getResource("/resources/close_1.png"));
        exiticn.setIcon(icn);
    }//GEN-LAST:event_exiticnMouseExited

    private void exiticnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiticnMousePressed
        ImageIcon icn = new ImageIcon(getClass().getResource("/resources/closec.png"));
        exiticn.setIcon(icn);
    }//GEN-LAST:event_exiticnMousePressed

    private void exiticnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exiticnMouseReleased

    }//GEN-LAST:event_exiticnMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
      
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new create_cases().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundlbl;
    private javax.swing.JButton casebtn;
    private javax.swing.JLabel exiticn;
    private javax.swing.JTextArea expectedoutputarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea testcasesarea;
    // End of variables declaration//GEN-END:variables

}