package morphgame;

public class Background{
private int bgX,bgY;
double speedX=0;
	
	public Background(int x,int y){
		bgX= x;
		bgY = y;
		speedX=0;
	}
	
	public void update() {
	   bgX += speedX;

	   if (bgX <= -2160){
	      bgX += 4320;
	   }
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(double d) {
		this.speedX = d;
	}
}
