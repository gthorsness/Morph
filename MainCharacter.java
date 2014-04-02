package morphgame;

import java.awt.Graphics;

public class MainCharacter{

    // Constants are Here
    final int GROUND = 382;
    
    private int jumpSpeed = 0;
    private int moveSpeed = 5;
    private int centerX = 100;
    private int centerY = GROUND;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private static Background bg1 = StartingClass.getBg1();                 
    private static Background bg2 = StartingClass.getBg2();

    private double speedX = 0;
    private int speedY = 1;
    
    public MainCharacter(){
    	
    }

    public void update() {

        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }
        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);

        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200){
            bg1.setSpeedX(-getMoveSpeed());
            bg2.setSpeedX(-getMoveSpeed());
        }

        // Updates Y Position
        centerY += speedY;
        if (centerY + speedY >= GROUND) {
            centerY = GROUND;
        }

        // Handles Jumping
        if (jumped == true) {
            speedY += 1;

            if (centerY + speedY >= GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
            }

        }

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
    }

    public void moveRight() {
        if (ducked == false) {
            speedX = getMoveSpeed();
            movingRight = true;
        }
    }

    public void moveLeft() {
        if (ducked == false) {
            speedX = -getMoveSpeed();
            movingLeft = true;
        }
    }

    public void stopRight() {
        setMovingRight(false);
        stop();
    }

    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }

    private void stop() {
        if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }

    }

    public void jump() {
        if (jumped == false) {
            speedY = getJumpSpeed();
            jumped = true;
        }

    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public double getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

	public int getGROUND() {
		return GROUND;
	}

	public int getJumpSpeed() {
		return jumpSpeed;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public static void setBg1(Background bg1) {
		MainCharacter.bg1 = bg1;
	}

	public static void setBg2(Background bg2) {
		MainCharacter.bg2 = bg2;
	}

}
