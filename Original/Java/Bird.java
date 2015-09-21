public class Bird {
  
  private double X;
  private double Y;
  private double YSpeed;
  private double rotation;
  private double rotSpeed;
  private long lastUp;
  /* X og Y eru á bilinu [0;100] og eru hnit Flappy
   * YSpeed [-0.9; 0.6] og er hraði Flappy í y-stefnu
   * rotation er á bilinu [-20°;20°] og er stefna Flappy
   * rotSpeed er sá hraði sem Flappy er að snúast um
   * lastUp er tími þegar up() var síðast virkjað
   */
  
  public Bird () {
    X = 50;
    Y = 50;
    YSpeed = 0;
    rotation = 0;
    rotSpeed = 0;
    lastUp = System.currentTimeMillis()/10;
  }
  
  public void up(long timi) {
    //////////////////
    // YSpeed Flappy
    // Kemur í veg fyrir að hægt sé að halda inni upp örvalykli:
    if (!(System.currentTimeMillis()/10 - lastUp < 8) && (YSpeed < 0.6)) {
      // Þegar ýtt er á uppörvalykil án þess að halda honum inni:  
      YSpeed = 0.45; // Hröðun upp á við
      Y += 1;
      rotSpeed = 10;
    }
    lastUp = timi; // Fyrir næsta skipti sem kallað er á up
  }
  
  public void draw() {
    // Þyngdarhröðun jarðar hefur áhrif
    if (YSpeed > -1.4) YSpeed -= 0.04; // Hröðun niður á við
    Y += YSpeed;
    //rotSpeed
    if (rotSpeed > -4) rotSpeed -= 0.2;
    if (-90 < rotation || rotSpeed > 0) rotation += rotSpeed;
    if (rotation > 40) {rotation = 40; rotSpeed = 0;}
    
    StdDraw.picture(X, Y, "../Myndir/flappyBird.png", 10, 10, rotation);
  }
}