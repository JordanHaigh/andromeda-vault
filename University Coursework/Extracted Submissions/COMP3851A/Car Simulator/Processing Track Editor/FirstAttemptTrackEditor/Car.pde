class Car {

  int x;
  int y;
  int angle = 0;
  int carLength = 30;
  int carHeight = 50;


  public Car(int x, int y) {
    setXY(x, y);
  }
  public int x(){
  return x;
  }
  
  public int y(){
  return y;
  }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void rotateCar() {
    angle = (angle + 1) % 360;
  }

  public void render() {
    fill(255,0,0);
    rect(x, y, carLength, carHeight);
    rotate(angle);
    fill(0,0,0);
  }
}
