public class Game {
  
  public Game () {}
  
  public int eydamer() {
    return 5;
  }
  
  public void run() {
    StdDraw.setCanvasSize(512, 512);
    StdDraw.setScale(0, 100);
    Bird flappy = new Bird();
    long upThen = System.currentTimeMillis()/10;
    int score = 0;
    
    boolean running = true;
    while (running) {
      // Þarf að bæta við pixlum efst í bakgrunninn
      StdDraw.show(5);
      StdDraw.picture(50, 30, "../Myndir/background.jpg", 110*1.6, 110);
      //////////////////////////////////////////
      
      flappy.draw();
      
      //////////////////
      // Takkaskipanir
      //////////////////
      
      // Hömlur á það hversu oft má ýta á up með vissu tímabili
      if (StdDraw.isKeyPressed(38)) flappy.up(System.currentTimeMillis()/10);   // Er verið að ýta á upp örvalykil?
      if (StdDraw.isKeyPressed(27)) close();                                    // Er verið að ýta á Escape?
      
      //////////////
      // Stigagjöf
      //////////////
      
      
      
      //////////////////////////////////////////
      StdDraw.show();
    }
  }
    
  public void close() {
    System.exit(0);
  }
    
}