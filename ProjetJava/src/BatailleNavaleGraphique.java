import java.awt.*;
import java.awt.event.*;
import java.io.*;

  /* La classe contient une fonction principale           */
  /* d'utilisation en tant qu'application.                */
  /* Elle etend la classe Frame pour la creation          */
  /* d'une fenetre.                                       */
  /* Elle implemente les interfaces WindowListener,       */
  /* ActionListener et MouseListener pour autoriser       */
  /* la gestion des evenements fenetre, action et souris. */

public class BatailleNavaleGraphique extends Frame
                                     implements WindowListener,
                                          ActionListener,
                                          MouseListener {
  /* Objets menu                                          */
  private MenuBar mb = new MenuBar();
  private Menu m1 = new Menu("Fichier");
  private MenuItem mnj = new MenuItem("Nouveau jeu");
  private MenuItem mq = new MenuItem("Quitter");
  
  /* Chaine de caracteres affichee sur le plateau de jeu  */
  private String message ="";
  
  /* Position de la souris lors d'un clic                 */
  private int mousex = -1;
  private int mousey = -1;
  
  /* Description du plateau de jeu                        */
  private static int [][] tst;
  /* Description de la flote                              */
  private static boolean [][] fl;

  /* Constructeur                                         */

  public BatailleNavaleGraphique() {
  /* Appel au constructeur de la super-classe Frame       */
  /* pour donner un titre a la fenetre                    */
    super("Bataille navale");
  /* Ajout d'un ecouteur de fenetre a la fenetre          */
  /* pour gerer les evenements fenetre                    */
  /* L'ecouteur est l'objet BatailleNavaleGraphique       */
  /* lui-meme qui etend l'interface WindowListener        */
    addWindowListener(this);
  /* Ajout d'un ecouteur de souris a la fenetre           */
  /* pour gerer les evenements souris                     */
  /* L'ecouteur est l'objet BatailleNavaleGraphique       */
  /* lui-meme qui etend l'interface MouseListener         */
    addMouseListener(this);
  /* Ajout d'une barre de menu a la fenetre               */
    setMenuBar(mb);
  /* Ajout des menus a la barre de menu                   */
    mb.add(m1);
    m1.add(mnj);
    m1.addSeparator();
    m1.add(mq);
  /* Ajout d'un ecouteur d'action aux menus idoines       */
  /* pour gerer leurs evenements d'activation             */
  /* L'ecouteur est l'objet BatailleNavaleGraphique       */
  /* lui-meme qui etend l'interface ActionListener        */
    mq.addActionListener(this);
    mnj.addActionListener(this);
  /* Le blanc est la couleur de fond de la fenetre        */
    setBackground(Color.white);
  /* Ajustement de la taille de la fenetre en fonction    */
  /* de la taille du plateau de jeu                       */
    setSize(24*tst.length+8,24*tst.length+72);
  /* Activation de la fenetre                             */
    setVisible(true);
  }

  /* Test si un bateau est present en (x,y)               */

  public boolean bateauPresent(int x,int y) {
    if ( !dansTableau(fl,x,y) )
      return(false);
    return(fl[y][x]);
  }

  /* Test si le bateau present en (x,y) est coule         */

  public boolean bateauCoule(int x,int y) {
    boolean sens = true;
    int xi = x;
    int yi = y;
    while ( ( yi >= 0 ) && ( bateauPresent(xi,yi-1) ) ) {
      yi--;
      sens = true; }
    while ( ( xi >= 0 ) && ( bateauPresent(xi-1,yi) ) ) {
      xi--;
      sens = false; }
    int xf = xi;
    int yf = yi;
    while ( ( yf < fl.length ) && ( bateauPresent(xf,yf+1) ) ) {
      yf++;
      sens = true; }
    while ( ( xf < fl.length ) && ( bateauPresent(xf+1,yf) ) ) {
      xf++;
      sens = false; }
    if ( sens ) {
      for ( y = yi ; y <= yf ; y++ )
        if ( tst[y][xi] != 1 )
          return(false); }
      else {
      for ( x = xi ; x <= xf ; x++ ) {
        if ( tst[yi][x] != 1 )
          return(false); } }
    return(true);
  }

  /* Test de la fin du jeu                                */

  public boolean testJeuFiniBatailleNavale() {
    for ( int y = 0 ; y < fl.length ; y++ )
      for ( int x = 0 ; x < fl.length ; x++ )
        if ( ( fl[y][x] ) && ( tst[y][x] != 1 ) )
          return(false);
    return(true);
  }

  /* Calcul du carre de la distance entre deux positions  */

  public int distance2(int x1,int y1,int x2,int y2) {
    int dx = x2-x1;
    int dy = y2-y1;
    return(dx*dx+dy*dy);
  }

  /* Lecture d'une position jouee                         */

  public int [] positionEssai() {
    int [] pos = new int[2];
    boolean good = false;
    do {
      while ( ( mousex == -1 ) && ( mousey == -1 ) )
        try {
          Thread.sleep(200); }
        catch ( Exception e ) { };
      for ( int y = 0 ; y < tst.length ; y++ ) 
        for ( int x = 0 ; x < tst[y].length ; x++ ) {
          int px = 12+24*x;
          int py = 60+24*y;
          if ( distance2(px,py,mousex,mousey) < 100 ) {
            pos[0] = x;
            pos[1] = y;
            mousex = -1;
            mousey = -1;
            return(pos); } }
      mousex = -1;
      mousey = -1; }
    while ( !good );
    return(pos);
  }

  /* Fonction principale de gestion du jeu                */

  public void batailleNavale() {
    int nbt = 0;
    while ( !testJeuFiniBatailleNavale() ) {
      nbt++;
      repaint();
      int [] pos = positionEssai();
      if ( fl[pos[1]][pos[0]] ) {
        tst[pos[1]][pos[0]] = 1;
        message = "Bateau en ("+pos[0]+","+pos[1]+")";
        if ( bateauCoule(pos[0],pos[1]) )
          message = "Bateau coule!"; }
        else {
        tst[pos[1]][pos[0]] = 0;
        message = "Pas de bateau en ("+pos[0]+","+pos[1]+")"; } }
    message = "Toute la flote est coulee en "+nbt+" tours";
    repaint();
  }

  /* Affichage du plateau de jeu                          */

  public void paint(Graphics g) {
    for ( int y = 0 ; y < tst.length ; y++ ) {
      for ( int x = 0 ; x < tst.length ; x++ )
        switch ( tst[y][x] ) {
          case -1 : g.drawLine(12+24*x,60+24*y,12+24*x,60+24*y);
                    break;
          case 0  : g.drawRect(10+24*x,58+24*y,4,4);
                    break;
          case 1  : g.drawLine(10+24*x,58+24*y,14+24*x,62+24*y);
                    g.drawLine(10+24*x,62+24*y,14+24*x,58+24*y);
                    break; } }
    g.drawString(message,10,58+tst.length*24);
  }

  /* Reinitialisation du jeu                              */

  public void nouveauJeu() throws IOException {
  /* Creation d'une nouvelle flote                        */
    fl = initFlote();
  /* Creation d'un nouveau plateau                        */
    tst = initPlateau(fl);
  /* Effacement du message de la fenetre                  */
    message ="";
  /* Redimensionnement de la fenetre                      */
    setSize(24*tst.length+8,24*tst.length+72);
  /* Reaffichage du plateau de jeu                        */
    repaint();
  }

  /* Reactions aux evenements fenetre                     */

  public void windowActivated(WindowEvent e) {
  }
  
  public void windowClosed(WindowEvent e) {
  }
  
  public void windowClosing(WindowEvent e) {
  /* Interruption du programme                            */
    System.exit(0);
  }
  
  public void windowDeactivated(WindowEvent e) {
  }
  
  public void windowDeiconified(WindowEvent e) {
  }
  
  public void windowIconified(WindowEvent e) {
  }
  
  public void windowOpened(WindowEvent e) {
  }
    
  /* Reaction aux evenements action                       */

  public void actionPerformed(ActionEvent e) {
  /* Si l'entree de menu "Quitter" est utilisee           */
    if ( e.getSource() == mq ) {
  /* Interruption du programme                            */
      System.exit(0); }
  /* Si l'entree de menu "Nouveau jeu" est utilisee       */
    if ( e.getSource() == mnj ) {
  /* Reinitialisation du jeu via la fonction nouveauJeu   */
      try {
        nouveauJeu(); }
      catch (IOException ioe) {} }
  }

  /* Reaction aux evenements souris                       */

  /* Si la souris est cliquee                             */

  public void mouseClicked(MouseEvent e) {
  /* Affectation des variables mousex et mousey           */
  /* avec les coordonnees de la souris                    */
    mousex = e.getX();
    mousey = e.getY();
  }
  
  public void mouseEntered(MouseEvent e) {
  }
  
  public void mouseExited(MouseEvent e) {
  }
  
  public void mousePressed(MouseEvent e) {
  }
  
  public void mouseReleased(MouseEvent e) {
  }

  /* Fonctions de generation aleatoire d'une flotte       */
  /* de navires sur un plateau de jeu                     */

  static BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));

  public static boolean dansTableau(boolean [][] t,int xi,int yi) {
    if ( xi < 0 )
      return(false);
    if ( yi < 0 )
      return(false);
    if ( xi >= t.length )
      return(false);
    if ( yi >= t.length )
      return(false);
    return(true);
  }

  public static boolean placementPossible(boolean [][] t,int xi,int yi,int xf,int yf,int sens) {
    if ( !dansTableau(t,xi,yi) )
      return(false);
    if ( !dansTableau(t,xf,yf) )
      return(false);
    if ( sens == 0 ) {
      for ( int x = xi-1 ; x <= xf+1 ; x++ )
        for ( int y = yi-1 ; y <= yi+1 ; y++ )
          if ( dansTableau(t,x,y) )
            if ( t[y][x] )
              return(false); }
      else {
      for ( int x = xi-1 ; x <= xi+1 ; x++ )
        for ( int y = yi-1 ; y <= yf+1 ; y++ )
          if ( dansTableau(t,x,y) )
            if ( t[y][x] )
              return(false); }
    return(true);
  }

  public static void place(boolean [][] t,int xi,int yi,int xf,int yf,int sens) {
    if ( sens == 0 ) {
      for ( int x = xi ; x <= xf ; x++ )
        t[yi][x] = true; }
      else {
      for ( int y = yi ; y <= yf ; y++ )
        t[y][xi] = true; }
  }

  public static void placeBateau(int taille,boolean [][] t) {
    int xi,yi,xf = 0,yf = 0;
    int sens;
    do {
      xi =(int) (Math.random() * t.length);
      yi =(int) (Math.random() * t.length);
      sens =(int) (Math.random() * 4); 
      switch (sens) {
        case 2 : xf = xi;
                 yf = yi+taille-1;
                 break;
        case 3 : xf = xi;
                 yf = yi;
                 yi = yf-taille+1;
                 break;
        case 0 : yf = yi;
                 xf = xi+taille-1;
                 break;
        case 1 : yf = yi;
                 xf = xi;
                 xi = xf-taille+1;
                 break; } }
    while ( !placementPossible(t,xi,yi,xf,yf,sens/2) );
    place(t,xi,yi,xf,yf,sens/2);
  }

  public static boolean [][] genereFlote(int n,int n1,int n2,int n3,int n4, int n5) {
    boolean [][] t = new boolean[n][n];
    for ( int i = 0 ; i < n ; i++ )
      for ( int j = 0 ; j < n ; j++ )
        t[i][j] = false;
    for ( int i = 0 ; i < n5 ; i++ )
      placeBateau(5,t);
    for ( int i = 0 ; i < n4 ; i++ )
      placeBateau(4,t);
    for ( int i = 0 ; i < n3 ; i++ )
      placeBateau(3,t);
    for ( int i = 0 ; i < n2 ; i++ )
      placeBateau(2,t);
    for ( int i = 0 ; i < n1 ; i++ )
      placeBateau(1,t);
    return(t);
  }

  /* Fonction principale                                  */

  public static boolean [][] initFlote() throws IOException {
  /* Lecture au clavier de la taille du damier            */
    System.out.print("Taille du damier   : ");
    int n = Integer.valueOf(flux.readLine()).intValue();
  /* Lecture au clavier des nombres de bateaux            */
  /* de taille 1, 2, 3, 4 et 5                            */
    System.out.println("Nombres de bateaux : ");
    System.out.print("Taille 1 : ");
    int n1 = Integer.valueOf(flux.readLine()).intValue();
    System.out.print("Taille 2 : ");
    int n2 = Integer.valueOf(flux.readLine()).intValue();
    System.out.print("Taille 3 : ");
    int n3 = Integer.valueOf(flux.readLine()).intValue();
    System.out.print("Taille 4 : ");
    int n4 = Integer.valueOf(flux.readLine()).intValue();
    System.out.print("Taille 5 : ");
    int n5 = Integer.valueOf(flux.readLine()).intValue();
  /* Generation de la flote sur le damier                 */
    return(genereFlote(n,n1,n2,n3,n4,n5));
  }

  public static int [][] initPlateau(boolean [][] fl) throws IOException {
  /* Generation et initialisation du tableau              */
  /* des cases testees                                    */
    int [][] tst = new int[fl.length][fl[0].length];
    for ( int i = 0 ; i < fl.length ; i++ )
      for ( int j = 0 ; j < fl[0].length ; j++ )
        tst[i][j] = -1;
    return(tst);
  }

  public static void main(String [] args) throws IOException {
  /* Initialisation du jeu                                */
    fl = initFlote();
    tst = initPlateau(fl);
  /* Lancement du jeu                                     */
    BatailleNavaleGraphique bng = new BatailleNavaleGraphique();
    bng.batailleNavale();
  }
}