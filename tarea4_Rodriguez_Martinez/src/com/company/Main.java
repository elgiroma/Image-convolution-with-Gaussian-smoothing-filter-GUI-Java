package com.company;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

//----------------------------------------------------------------+
//                  Eliud Gilberto Rodríguez Martínez             |
//                Laboratorio de visión y procesamiento           |
//                              Tarea 4                           |
//----------------------------------------------------------------+

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 *
 * @author eliud
 */

public class Main extends javax.swing.JFrame {

    // Creates new form Main

    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        label_image = new javax.swing.JLabel();
        label_image1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField("10");
        jTextField2 = new javax.swing.JTextField("30");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Choose image button
        jButton1.setText("Choose image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        // --------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------

        // Text fields
        jLabel1.setText("Enter sigma value:");
        jLabel2.setText("Enter kernel size:");

        // --------------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------------

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(39, 39, 39)
                                                .addComponent(label_image, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label_image1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                                .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label_image1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(125, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        // JFileChooser object
        JFileChooser jFileChooser = new JFileChooser();
        String Path;

        // Filtering file extensions
        FileNameExtensionFilter file_extension_filter = new FileNameExtensionFilter("JGP, PNG & GIF", "jpg", "png", "gif");
        jFileChooser.setFileFilter(file_extension_filter);

        int choice = jFileChooser.showOpenDialog(this);

        // Choosing file
        if (choice == JFileChooser.APPROVE_OPTION) {
            Path = jFileChooser.getSelectedFile().getPath();

            // Showing selected image
            Image mImage = new ImageIcon(Path).getImage();
            ImageIcon mIcon = new ImageIcon(mImage.getScaledInstance(label_image.getWidth(), label_image.getHeight(), Image.SCALE_SMOOTH));
            label_image.setIcon(mIcon);

            // Creating convolution object
            Convolution image_conv = new Convolution();

            // Buffered image
            BufferedImage buffered_image = null;
            try{
                buffered_image = ImageIO.read(new File(Path));
            }
            catch(Exception e){
                System.out.print(e.toString());
                return;
            }

            // Entered size and sigma values
            int size_value = Integer.parseInt(jTextField1.getText());
            double sigma_value = Double.parseDouble(jTextField2.getText());

            // Calling convolution method
            buffered_image = image_conv.convolution(buffered_image, size_value, sigma_value);

            // Showing convolutionized image
            Image mImage1 = (Image) buffered_image;
            ImageIcon mIcon1 = new ImageIcon(mImage1.getScaledInstance(label_image1.getWidth(), label_image1.getHeight(), Image.SCALE_SMOOTH));
            label_image1.setIcon(mIcon1);
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt){
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt){
    }

    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel label_image;
    private javax.swing.JLabel label_image1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration
}