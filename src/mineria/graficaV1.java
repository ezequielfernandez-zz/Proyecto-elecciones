/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;


/**
 *
 * @author Ezequiel Fernandez, Santiago Schustik, Damian Fuentes
 */
public class graficaV1 extends javax.swing.JFrame {

    /**
     * Creates new form graficaV1
     */
    
    
    //Mis variables
    
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    
    //Barras de diputados
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    
    //Labels de numeros arriba de barras
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    
    
    
    public graficaV1() {
        initComponents();
        this.setTitle("Elecciones Legislativas 2017");
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        this.agregarBarraMassa();
        this.agregarBarraPitrola();
        this.agregarBarraRandazzo();
        this.agregarBarraCristina();
        this.agregarBarraMacri();
        this.mostrarImagenMassa(false);
        this.mostrarImagenPitrola(false);
        this.mostrarImagenRandazzo(false);
        this.mostrarImagenCristina(false);
        this.mostrarImagenMacri(false);
        this.agregarBarrasDiputados();
        this.agregarBarrasSenadores();
        this.setVisible(true);
        this.setBackground(new java.awt.Color(0,153,153));
    }
    
    private void agregarBarrasDiputados(){    
        //Barra Cambiemos
        jLabel55 = new javax.swing.JLabel();
        jLabel55.setBackground(new java.awt.Color(255,252,0));
        jLabel55.setOpaque(true);
        jLabel55.setBounds(60, 122, 20, 92);
        jPanel4.add(jLabel55);
        jPanel4.setComponentZOrder(jLabel55, 0);
        //Barra FPV
        jLabel56 = new javax.swing.JLabel();
        jLabel56.setBackground(new java.awt.Color(0,126,255));
        jLabel56.setOpaque(true);
        jLabel56.setBounds(90, 134, 20, 80);
        jPanel4.add(jLabel56);
        jPanel4.setComponentZOrder(jLabel56, 0);
        //Barra Union Ciudadana
        jLabel57 = new javax.swing.JLabel();
        jLabel57.setBackground(new java.awt.Color(78,195,0));
        jLabel57.setOpaque(true);
        jLabel57.setBounds(120, 213, 20, 1);
        jPanel4.add(jLabel57);
        jPanel4.setComponentZOrder(jLabel57, 0);
        //Barra Frente Renovador
        jLabel58 = new javax.swing.JLabel();
        jLabel58.setBackground(new java.awt.Color(3,0,147));
        jLabel58.setOpaque(true);
        jLabel58.setBounds(150, 180, 20, 34);
        jPanel4.add(jLabel58);
        jPanel4.setComponentZOrder(jLabel58, 0);
        //Barra Frente de Izquierda
        jLabel59 = new javax.swing.JLabel();
        jLabel59.setBackground(new java.awt.Color(147,0,0));
        jLabel59.setOpaque(true);
        jLabel59.setBounds(180, 210, 20, 4);
        jPanel4.add(jLabel59);
        jPanel4.setComponentZOrder(jLabel59, 0);
        //Barra Otros
        jLabel60 = new javax.swing.JLabel();
        jLabel60.setBackground(new java.awt.Color(188,188,188));
        jLabel60.setOpaque(true);
        jLabel60.setBounds(210, 176, 20, 38);
        jPanel4.add(jLabel60);
        jPanel4.setComponentZOrder(jLabel60, 0);
        
        
        //Agregados por provincia de Buenos Aires
        //Barra Cambiemos
        jLabel66 = new javax.swing.JLabel();
        jLabel66.setBackground(new java.awt.Color(118,117,0));
        jLabel66.setOpaque(true);
        jLabel66.setBounds(60, 122-10, 20, 10);
        jPanel4.add(jLabel66);
        jPanel4.setComponentZOrder(jLabel66, 0);
        //Barra FPV
        jLabel65 = new javax.swing.JLabel();
        jLabel65.setBackground(new java.awt.Color(0,88,179));
        jLabel65.setOpaque(true);
        jLabel65.setBounds(90, 134-10, 20, 10);
        jPanel4.add(jLabel65);
        jPanel4.setComponentZOrder(jLabel65, 0);
        //Barra Union Ciudadana
        jLabel64 = new javax.swing.JLabel();
        jLabel64.setBackground(new java.awt.Color(43,108,0));
        jLabel64.setOpaque(true);
        jLabel64.setBounds(120, 213-30, 20, 30);
        jPanel4.add(jLabel64);
        jPanel4.setComponentZOrder(jLabel64, 0);
        //Barra Frente Renovador
        jLabel63 = new javax.swing.JLabel();
        jLabel63.setBackground(new java.awt.Color(2,1,68));
        jLabel63.setOpaque(true);
        jLabel63.setBounds(150, 180-15, 20, 15);
        jPanel4.add(jLabel63);
        jPanel4.setComponentZOrder(jLabel63, 0);
        //Barra Frente de Izquierda
        jLabel62 = new javax.swing.JLabel();
        jLabel62.setBackground(new java.awt.Color(65,0,0));
        jLabel62.setOpaque(true);
        jLabel62.setBounds(180, 210-20, 20, 20);
        jPanel4.add(jLabel62);
        jPanel4.setComponentZOrder(jLabel62, 0);
        //Barra Otros
        jLabel61 = new javax.swing.JLabel();
        jLabel61.setBackground(new java.awt.Color(107,107,107));
        jLabel61.setOpaque(true);
        jLabel61.setBounds(210, 176, 20, 0);
        jPanel4.add(jLabel61);
        jPanel4.setComponentZOrder(jLabel61, 0);
    }
    
    private void agregarBarrasSenadores(){    
        //Barra Cambiemos
        jLabel67 = new javax.swing.JLabel();
        jLabel67.setBackground(new java.awt.Color(255,252,0));
        jLabel67.setOpaque(true);
        jLabel67.setBounds(480, 154, 20, 60);
        jPanel4.add(jLabel67);
        jPanel4.setComponentZOrder(jLabel67, 0);
        //Barra FPV
        jLabel68 = new javax.swing.JLabel();
        jLabel68.setBackground(new java.awt.Color(0,126,255));
        jLabel68.setOpaque(true);
        jLabel68.setBounds(510, 114, 20, 100);
        jPanel4.add(jLabel68);
        jPanel4.setComponentZOrder(jLabel68, 0);
        //Barra Union Ciudadana
        jLabel69 = new javax.swing.JLabel();
        jLabel69.setBackground(new java.awt.Color(78,195,0));
        jLabel69.setOpaque(true);
        jLabel69.setBounds(540, 213, 20, 1);
        jPanel4.add(jLabel69);
        jPanel4.setComponentZOrder(jLabel69, 0);
        //Barra Frente Renovador
        jLabel70 = new javax.swing.JLabel();
        jLabel70.setBackground(new java.awt.Color(3,0,147));
        jLabel70.setOpaque(true);
        jLabel70.setBounds(570, 206, 20, 8);
        jPanel4.add(jLabel70);
        jPanel4.setComponentZOrder(jLabel70, 0);
        //Barra Frente Justicialista
        jLabel71 = new javax.swing.JLabel();
        jLabel71.setBackground(new java.awt.Color(210,2,208));
        jLabel71.setOpaque(true);
        jLabel71.setBounds(600, 213, 20, 1);
        jPanel4.add(jLabel71);
        jPanel4.setComponentZOrder(jLabel71, 0);
        //Barra Otros
        jLabel72 = new javax.swing.JLabel();
        jLabel72.setBackground(new java.awt.Color(188,188,188));
        jLabel72.setOpaque(true);
        jLabel72.setBounds(630, 174, 20, 40);
        jPanel4.add(jLabel72);
        jPanel4.setComponentZOrder(jLabel72, 0);
        
        
        //Agregados por provincia de Buenos Aires
        //Barra Cambiemos
        jLabel73 = new javax.swing.JLabel();
        jLabel73.setBackground(new java.awt.Color(118,117,0));
        jLabel73.setOpaque(true);
        jLabel73.setBounds(480, 134, 20, 20);
        jPanel4.add(jLabel73);
        jPanel4.setComponentZOrder(jLabel73, 0);
        //Barra FPV
        jLabel74 = new javax.swing.JLabel();
        jLabel74.setBackground(new java.awt.Color(0,88,179));
        jLabel74.setOpaque(true);
        jLabel74.setBounds(510, 94, 20, 20);
        jPanel4.add(jLabel74);
        jPanel4.setComponentZOrder(jLabel74, 0);
        //Barra Union Ciudadana
        jLabel75 = new javax.swing.JLabel();
        jLabel75.setBackground(new java.awt.Color(43,108,0));
        jLabel75.setOpaque(true);
        jLabel75.setBounds(540, 193, 20, 20);
        jPanel4.add(jLabel75);
        jPanel4.setComponentZOrder(jLabel75, 0);
        //Barra Frente Renovador
        jLabel76 = new javax.swing.JLabel();
        jLabel76.setBackground(new java.awt.Color(2,1,68));
        jLabel76.setOpaque(true);
        jLabel76.setBounds(570, 186, 20, 20);
        jPanel4.add(jLabel76);
        jPanel4.setComponentZOrder(jLabel76, 0);
        //Barra Frente Justicialista
        jLabel77 = new javax.swing.JLabel();
        jLabel77.setBackground(new java.awt.Color(108,0,108));
        jLabel77.setOpaque(true);
        jLabel77.setBounds(600, 193, 20, 20);
        jPanel4.add(jLabel77);
        jPanel4.setComponentZOrder(jLabel77, 0);
        //Barra Otros
        jLabel78 = new javax.swing.JLabel();
        jLabel78.setBackground(new java.awt.Color(107,107,107));
        jLabel78.setOpaque(true);
        jLabel78.setBounds(630, 174, 20, 0);
        jPanel4.add(jLabel78);
        jPanel4.setComponentZOrder(jLabel78, 0);
    }
    
    private void agregarBarraMassa(){
        jLabel45 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/medidor.png"))); // NOI18N
        jLabel45.setBounds(670, 65, 30, 12);
        jPanel1.add(jLabel45);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/barraimagen.png"))); // NOI18N
        jLabel42.setBounds(670, 40, 30, 150);
        jPanel1.add(jLabel42);
    }
    
    private void agregarBarraPitrola(){
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/medidor.png"))); // NOI18N
        jLabel47.setBounds(540, 90, 30, 12);
        jPanel1.add(jLabel47);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/barraimagen.png"))); // NOI18N
        jLabel48.setBounds(540, 40, 30, 150);
        jPanel1.add(jLabel48);
    }
    
    private void agregarBarraRandazzo(){
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/medidor.png"))); // NOI18N
        jLabel49.setBounds(400, 120, 30, 12);
        jPanel1.add(jLabel49);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/barraimagen.png"))); // NOI18N
        jLabel50.setBounds(400, 40, 30, 150);
        jPanel1.add(jLabel50);
        jPanel1.setComponentZOrder(jLabel49, 0);
        jPanel1.setComponentZOrder(jLabel50, 1);
    }
    
     private void agregarBarraCristina(){
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/medidor.png"))); // NOI18N
        jLabel51.setBounds(260, 65, 30, 12);
        jPanel1.add(jLabel51);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/barraimagen.png"))); // NOI18N
        jLabel52.setBounds(260, 40, 30, 150);
        jPanel1.add(jLabel52);
        jPanel1.setComponentZOrder(jLabel51, 0);
        jPanel1.setComponentZOrder(jLabel52, 1);
    }
    
    private void agregarBarraMacri(){
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/medidor.png"))); // NOI18N
        jLabel53.setBounds(130, 107, 30, 12);
        jPanel1.add(jLabel53);
        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/barraimagen.png"))); // NOI18N
        jLabel54.setBounds(130, 40, 30, 150);
        jPanel1.add(jLabel54);
    }
    
    private void mostrarImagenMassa(boolean estado){
        jLabel42.setVisible(estado);
        jLabel45.setVisible(estado);
    }

    private void mostrarImagenPitrola(boolean estado){
        jLabel47.setVisible(estado);
        jLabel48.setVisible(estado);
    }
    
    private void mostrarImagenRandazzo(boolean estado){
        jLabel49.setVisible(estado);
        jLabel50.setVisible(estado);
    }
    
    private void mostrarImagenCristina(boolean estado){
        jLabel51.setVisible(estado);
        jLabel52.setVisible(estado);
    }
    
    private void mostrarImagenMacri(boolean estado){
        jLabel53.setVisible(estado);
        jLabel54.setVisible(estado);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel26.setFont(new java.awt.Font("Franklin Gothic Book", 1, 24)); // NOI18N
        jLabel26.setText("Procesando...");
        jPanel5.add(jLabel26);
        jLabel26.setBounds(270, 90, 160, 30);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/procesando.gif"))); // NOI18N
        jPanel5.add(jLabel27);
        jLabel27.setBounds(210, 150, 256, 236);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(710, 220));
        jPanel1.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/bullrich.png"))); // NOI18N
        jLabel3.setName("bullrich"); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                macriArriba(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                macriAfuera(evt);
            }
        });
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 60, 100, 98);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bullrich / Ocaña");
        jLabel2.setName("tbullrich"); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 160, 74, 14);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("25%");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setName("pbullrich"); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 20, 120, 47);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/cristina.png"))); // NOI18N
        jLabel6.setName("cristina"); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cristinaArriba(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cristinaAfuera(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(160, 60, 100, 98);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kirchner / Taiana");
        jLabel7.setName("tcristina"); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(160, 160, 81, 14);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("25%");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setName("pcristina"); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(150, 20, 120, 47);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/randazzo.png"))); // NOI18N
        jLabel9.setName("randazzo"); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                randazzoArriba(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                randazzoAfuera(evt);
            }
        });
        jPanel1.add(jLabel9);
        jLabel9.setBounds(300, 60, 100, 98);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("25%");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setName("prandazzo"); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(290, 20, 120, 47);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Randazzo / Casamiquela");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(280, 160, 117, 14);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/pitrola.png"))); // NOI18N
        jLabel12.setName("pitrola"); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pitrolaArriba(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pitrolaAfuera(evt);
            }
        });
        jPanel1.add(jLabel12);
        jLabel12.setBounds(440, 60, 100, 98);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("25%");
        jLabel13.setToolTipText("");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setName("ppitrola"); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(430, 20, 120, 47);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pitrola / Del Caño");
        jLabel14.setName("tpitrola"); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(440, 160, 83, 14);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/massa.png"))); // NOI18N
        jLabel15.setName("massa"); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                massaArriba(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                massaAfuera(evt);
            }
        });
        jPanel1.add(jLabel15);
        jLabel15.setBounds(570, 60, 100, 98);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Massa / Stolbizer");
        jLabel16.setName("tmassa"); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(570, 160, 81, 14);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("0%");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel17.setName("pmassa"); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(560, 20, 120, 47);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setForeground(new java.awt.Color(0, 153, 153));
        jPanel4.setPreferredSize(new java.awt.Dimension(360, 243));
        jPanel4.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/escudo.png"))); // NOI18N
        jLabel5.setName("escudo"); // NOI18N
        jPanel4.add(jLabel5);
        jLabel5.setBounds(320, 0, 60, 91);

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Senadores");
        jLabel20.setName("tsenado"); // NOI18N
        jPanel4.add(jLabel20);
        jLabel20.setBounds(500, 20, 122, 32);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Diputados");
        jLabel21.setName("tdiputados"); // NOI18N
        jPanel4.add(jLabel21);
        jLabel21.setBounds(90, 20, 118, 32);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/etiquetas.png"))); // NOI18N
        jPanel4.add(jLabel19);
        jLabel19.setBounds(290, 90, 130, 130);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/fondoBarras.png"))); // NOI18N
        jPanel4.add(jLabel22);
        jLabel22.setBounds(50, 90, 200, 129);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mineria/fondoBarrasSenadores.png"))); // NOI18N
        jPanel4.add(jLabel18);
        jLabel18.setBounds(470, 90, 200, 129);

        jLabel23.setText("*Datos puramente estadísticos obtenidos de twitter");
        jPanel4.add(jLabel23);
        jLabel23.setBounds(20, 230, 690, 14);

        jLabel24.setText("35 diputados en juego para Bs.As.");
        jPanel4.add(jLabel24);
        jLabel24.setBounds(50, 60, 200, 14);

        jLabel25.setText("3 senadores en juego para Bs.As.");
        jPanel4.add(jLabel25);
        jLabel25.setBounds(470, 60, 200, 14);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elecciones Legislativas 2017");
        jLabel1.setName("titulo"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void massaArriba(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massaArriba
        mostrarImagenMassa(true);
    }//GEN-LAST:event_massaArriba

    private void massaAfuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_massaAfuera
        mostrarImagenMassa(false);
    }//GEN-LAST:event_massaAfuera

    private void pitrolaArriba(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pitrolaArriba
        mostrarImagenPitrola(true);
    }//GEN-LAST:event_pitrolaArriba

    private void pitrolaAfuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pitrolaAfuera
        mostrarImagenPitrola(false);
    }//GEN-LAST:event_pitrolaAfuera

    private void randazzoArriba(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_randazzoArriba
        mostrarImagenRandazzo(true);
    }//GEN-LAST:event_randazzoArriba

    private void randazzoAfuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_randazzoAfuera
        mostrarImagenRandazzo(false);
    }//GEN-LAST:event_randazzoAfuera

    private void cristinaArriba(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cristinaArriba
        mostrarImagenCristina(true);
    }//GEN-LAST:event_cristinaArriba

    private void cristinaAfuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cristinaAfuera
        mostrarImagenCristina(false);
    }//GEN-LAST:event_cristinaAfuera

    private void macriArriba(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_macriArriba
        mostrarImagenMacri(true);
    }//GEN-LAST:event_macriArriba

    private void macriAfuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_macriAfuera
        mostrarImagenMacri(false);
    }//GEN-LAST:event_macriAfuera

    public void setPBullrich(float p){
        jLabel4.setText(Float.toString(p)+"%");
    }
    
    public void setPRandazzo(float p){
        jLabel10.setText(Float.toString(p)+"%");
    }
    
    public void setPKirchner(float p){
        jLabel8.setText(Float.toString(p)+"%");
    }
    
    public void setPPitrola(float p){
        jLabel13.setText(Float.toString(p)+"%");
    }
    
    public void setPMassa(float p){
        jLabel17.setText(Float.toString(p)+"%");
    }
    
    //107 es el medio y tiene 70 para arriba y para abajo
    public void setIBullrich(float p){
        int y=(int) (107-(p*70));
        jLabel53.setBounds(jLabel53.getBounds().x, y, jLabel53.getBounds().width, jLabel53.getBounds().height);
    }
    
    public void setIRandazzo(float p){
        int y=(int) (107-(p*70));
        jLabel49.setBounds(jLabel49.getBounds().x, y, jLabel49.getBounds().width, jLabel49.getBounds().height);
    }
    
    public void setIKirchner(float p){
        int y=(int) (107-(p*70));
        jLabel51.setBounds(jLabel51.getBounds().x, y, jLabel51.getBounds().width, jLabel51.getBounds().height);
    }
    
    public void setIPitrola(float p){
        int y=(int) (107-(p*70));
        jLabel47.setBounds(jLabel47.getBounds().x, y, jLabel47.getBounds().width, jLabel47.getBounds().height);
    }
    
    public void setIMassa(float p){
        int y=(int) (107-(p*70));
        jLabel45.setBounds(jLabel45.getBounds().x, y, jLabel45.getBounds().width, jLabel45.getBounds().height);
    }
    
    public void setDBullrich(int p){
        //Label de provinciales para bullrich
        jLabel66.setBounds(60, 122-(p*2), 20, p*2);
        if (p>0){
            jLabel80 = new javax.swing.JLabel();
            jLabel80.setText("+"+p);
            jLabel80.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel80.setForeground(new java.awt.Color(0, 0, 0));
            jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel80.setBounds(60, 122-(p*2)-15, 15, 20);
            jPanel4.add(jLabel80);
            jPanel4.setComponentZOrder(jLabel80,0);
        }    
    }
    
    public void setDRandazzo(int p){
        //Label de provinciales para randazzo
        jLabel65.setBounds(90, 134-(p*2), 20, p*2);
        if (p>0){
            jLabel81 = new javax.swing.JLabel();
            jLabel81.setText("+"+p);
            jLabel81.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel81.setForeground(new java.awt.Color(0, 0, 0));
            jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel81.setBounds(90, 134-(p*2)-15, 15, 20);
            jPanel4.add(jLabel81);
            jPanel4.setComponentZOrder(jLabel81,0);
        }    
    }
    
    public void setDKirchner(int p){
        //Label de provinciales para kirchner
        jLabel64.setBounds(120, 213-(p*2), 20, p*2);
        if (p>0){
            jLabel82 = new javax.swing.JLabel();
            jLabel82.setText("+"+p);
            jLabel82.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel82.setForeground(new java.awt.Color(0, 0, 0));
            jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel82.setBounds(120, 213-(p*2)-15, 15, 20);
            jPanel4.add(jLabel82);
            jPanel4.setComponentZOrder(jLabel82,0);
        }    
    }
    
    public void setDMassa(int p){
        //Label de provinciales para massa
        jLabel63.setBounds(150, 180-(p*2), 20, p*2);
        if (p>0){
            jLabel83 = new javax.swing.JLabel();
            jLabel83.setText("+"+p);
            jLabel83.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel83.setForeground(new java.awt.Color(0, 0, 0));
            jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel83.setBounds(150, 180-(p*2)-15, 15, 20);
            jPanel4.add(jLabel83);
            jPanel4.setComponentZOrder(jLabel83,0);
        }    
    }
    
    public void setDPitrola(int p){
        //Label de provinciales para pitrola
        jLabel62.setBounds(180, 210-(p*2), 20, p*2);
        if (p>0){
            jLabel84 = new javax.swing.JLabel();
            jLabel84.setText("+"+p);
            jLabel84.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel84.setForeground(new java.awt.Color(0, 0, 0));
            jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel84.setBounds(180, 210-(p*2)-15, 15, 20);
            jPanel4.add(jLabel84);
            jPanel4.setComponentZOrder(jLabel84,0);
        }    
    }
    
    public void setDOtros(int p){
        //Label de provinciales para otros
        jLabel61.setBounds(210, 176-(p*2), 20, p*2);
        if (p>0){
            jLabel85 = new javax.swing.JLabel();
            jLabel85.setText("+"+p);
            jLabel85.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel85.setForeground(new java.awt.Color(0, 0, 0));
            jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel85.setBounds(210, 176-(p*2)-15, 15, 20);
            jPanel4.add(jLabel85);
            jPanel4.setComponentZOrder(jLabel85,0);
        }    
    }
    
    public void setSCambiemos(int p){
        //Label de provinciales para bullrich
        jLabel73.setBounds(480, 154-(p*4), 20, p*4);
        if (p>0){
            jLabel86 = new javax.swing.JLabel();
            jLabel86.setText("+"+p);
            jLabel86.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel86.setForeground(new java.awt.Color(0, 0, 0));
            jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel86.setBounds(480, 154-(p*4)-15, 15, 20);
            jPanel4.add(jLabel86);
            jPanel4.setComponentZOrder(jLabel86,0);
        }    
    }
    
    public void setSFPV(int p){
        //Label de provinciales para randazzo
        jLabel74.setBounds(510, 114-(p*4), 20, p*4);
        if (p>0){
            jLabel87 = new javax.swing.JLabel();
            jLabel87.setText("+"+p);
            jLabel87.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel87.setForeground(new java.awt.Color(0, 0, 0));
            jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel87.setBounds(510, 114-(p*4)-15, 15, 20);
            jPanel4.add(jLabel87);
            jPanel4.setComponentZOrder(jLabel87,0);
        }    
    }
    
    public void setSCiudadana(int p){
        //Label de provinciales para kirchner
        jLabel75.setBounds(540, 213-(p*4), 20, p*4);
        if (p>0){
            jLabel88 = new javax.swing.JLabel();
            jLabel88.setText("+"+p);
            jLabel88.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel88.setForeground(new java.awt.Color(0, 0, 0));
            jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel88.setBounds(540, 213-(p*4)-15, 15, 20);
            jPanel4.add(jLabel88);
            jPanel4.setComponentZOrder(jLabel88,0);
        }    
    }
    
    public void setSRenovador(int p){
        //Label de provinciales para massa
        jLabel76.setBounds(570, 206-(p*4), 20, p*4);
        if (p>0){
            jLabel89 = new javax.swing.JLabel();
            jLabel89.setText("+"+p);
            jLabel89.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel89.setForeground(new java.awt.Color(0, 0, 0));
            jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel89.setBounds(570, 206-(p*4)-15, 15, 20);
            jPanel4.add(jLabel89);
            jPanel4.setComponentZOrder(jLabel89,0);
        }    
    }
    
    public void setSJusticialista(int p){
        //Label de provinciales para pitrola
        jLabel77.setBounds(600, 213-(p*4), 20, p*4);
        if (p>0){
            jLabel90 = new javax.swing.JLabel();
            jLabel90.setText("+"+p);
            jLabel90.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel90.setForeground(new java.awt.Color(0, 0, 0));
            jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel90.setBounds(600, 213-(p*4)-15, 15, 20);
            jPanel4.add(jLabel90);
            jPanel4.setComponentZOrder(jLabel90,0);
        }    
    }
    
    public void setSOtros(int p){
        //Label de provinciales para otros
        jLabel78.setBounds(630, 174-(p*4), 20, p*4);
        if (p>0){
            jLabel91 = new javax.swing.JLabel();
            jLabel91.setText("+"+p);
            jLabel91.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
            jLabel91.setForeground(new java.awt.Color(0, 0, 0));
            jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel91.setBounds(630, 174-(p*4)-15, 15, 20);
            jPanel4.add(jLabel91);
            jPanel4.setComponentZOrder(jLabel91,0);
        }    
    }
    
    public void mostrarResultados(){
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
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        jPanel1.setVisible(true);
        jPanel4.setVisible(true);
        jPanel5.setVisible(false);
    }
    
    public void inicial(){
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
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(graficaV1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
        jPanel4.setVisible(false);
        jPanel5.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
