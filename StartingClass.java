package morphgame;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	private MainCharacter mainCharacter;
	private Square square;
	private Circle circle;
	private Image image, character,characterDown,characterJumped,characterForward,characterBack,currentSprite, background;
	private Graphics second;
	private URL base;
	private static Background bg1,bg2;
	
	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Morph");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		background = getImage(base, "data/backgroundMockUp1.png");
	}
	
	public void squareImages(){
		character = getImage(base, "data/square.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/squareJump.png");
		characterForward = getImage(base,"data/squareForward.png");
		characterBack = getImage(base,"data/squareBack.png");
		currentSprite = character;	
	}
	
	public void circleImages(){
		character = getImage(base, "data/circle.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/circleJump.png");
		characterForward = getImage(base,"data/circleForward.png");
		characterBack = getImage(base,"data/circleBack.png");
		currentSprite = character;
	}

	@Override
	public void start() {
		squareImages();
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		//character objects
		square = new Square();
		circle = new Circle();
		mainCharacter = square;
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (true) {
			mainCharacter.update();
			if(mainCharacter.isMovingLeft()){
				currentSprite = characterBack;
			}
			else if(mainCharacter.isMovingRight()){
				currentSprite = characterForward;
			}
			else if (mainCharacter.isJumped()){
				currentSprite = characterJumped;
			}else if (mainCharacter.isJumped() == false && mainCharacter.isDucked() == false){
				currentSprite = character;
			}
			bg1.update();
			bg2.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(currentSprite, mainCharacter.getCenterX() - 61, mainCharacter.getCenterY() - 63, this);
	}
	
	public void keyPressed(KeyEvent e) {
	
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Move up");
            break;

        case KeyEvent.VK_DOWN:
            currentSprite = characterDown;
            if (mainCharacter.isJumped() == false){
                mainCharacter.setDucked(true);
                mainCharacter.setSpeedX(0);
            }
            break;

        case KeyEvent.VK_LEFT:
            mainCharacter.moveLeft();
            mainCharacter.setMovingLeft(true);
            break;

        case KeyEvent.VK_RIGHT:
            mainCharacter.moveRight();
            mainCharacter.setMovingRight(true);
            break;

        case KeyEvent.VK_SPACE:
            mainCharacter.jump();
            break;
            
            
        case KeyEvent.VK_1:
            switchMorph();
            break;
        }

    }

	@Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Stop moving up");
            break;

        case KeyEvent.VK_DOWN:
            currentSprite = character;
            mainCharacter.setDucked(false);
            break;

        case KeyEvent.VK_LEFT:
            mainCharacter.stopLeft();
            break;

        case KeyEvent.VK_RIGHT:
            mainCharacter.stopRight();
            break;

        case KeyEvent.VK_SPACE:
            break;
            
        case KeyEvent.VK_1:
            break;

        }

    }
	
	private void switchMorph() {
		if (mainCharacter instanceof Square){
			mainCharacter = circle;
			circleImages();
			
		}else if (mainCharacter instanceof Circle){
			mainCharacter = square;
			squareImages();
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

}
