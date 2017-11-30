package shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class World extends JPanel implements KeyListener{
	/**
	 * 主面板
	 */
	private static final long serialVersionUID = 1L;
	protected static final int WIDTH = 480;
	protected static final int HEIGHT = 700;
	
	private Sky sky;
	private Hero hero;
	private int score;
	private int generateDelay;
	private int gameRunTime;
	private boolean bossShow;
	
	private ArrayList<FlyingObject> flyingObjects;
	private ArrayList<Bullet> bullets;
	private int bulletIndex = 0;
	private int flyingobjIndex = 0;
	//暂停-继续
	private static BufferedImage pauseImg;
	private static BufferedImage gameOverImg;
	private boolean isPause = false;
	static {
		pauseImg = FlyingObject.loadImage("/shootImg/pause.png");
		gameOverImg = FlyingObject.loadImage("/shootImg/gameover.png");
	}
	
	public World() {
		sky = new Sky();
		hero = new Hero();
		bullets = new ArrayList<>();
		flyingObjects = new ArrayList<>();
		
		this.bossShow = false;
		this.score = 0;
		this.generateDelay = Config.GENERATE_DELAY;
		this.gameRunTime = 0;
		this.generate(true);
	}
	/**
	 * 绘图
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		this.gameRunTime++;
		if (this.hero.strongTime > 0) {
			this.hero.strongTime--;
		}
		
		//背景天空
		this.sky.draw(g);
		this.sky.step();
		//英雄机
		this.hero.draw(g);
		this.hero.step();
		//生成敌方飞机
		this.generate(false);
		//敌方飞机
		for (FlyingObject f : this.flyingObjects) {
			f.draw(g);
			f.step();
		}
		//生成子弹
		this.addBullet();
		//绘制子弹
		for (Bullet bullet : bullets) {
			bullet.draw(g);
			bullet.step();
		}
		//战斗
		this.fighting();
		//清理死亡或出界的元素
		this.removeObj();
		//游戏基本信息
		g.setFont(new Font("Microsoft YaHei", Font.ITALIC, 20));
		g.setColor(Color.white);
		g.drawString("Life：" + this.hero.life, 10, 20);
		g.drawString("Score：" + this.score, 10, 40);
		g.drawString(this.getGameTime(), (World.WIDTH/2)-50, 30);
		//暂停
		if (isPause) {
			g.drawImage(pauseImg, 0, 0, World.WIDTH, World.HEIGHT-20, null);
		}
		//游戏结束
		if (this.hero.isDead) {
			g.drawImage(gameOverImg, 0, 0, World.WIDTH, World.HEIGHT-20, null);
			this.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Program.entrance(false);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
	}
	/**
	 * 游戏时间
	 * @return
	 */
	public String getGameTime() {
		int s = this.gameRunTime/33;
		int m = s/60;
		s = s%60;
		String rel = "";
		if (m < 10) {
			rel += "0";
		}
		rel += "" + m + ":";
		if (s < 10) {
			rel += "0";
		}
		rel += "" + s;
		return rel;
	}
	/**
	 * 子弹击中敌机
	 */
	public void fighting() {
		//遍历飞行物
		for (FlyingObject f : this.flyingObjects) {
			//创建飞行物矩形
			Rectangle rf = new Rectangle(f.x,f.y,f.width,f.height);
			//遍历英雄子弹
			for (Bullet b : bullets) {
				//创建子弹矩形
				Rectangle bf = new Rectangle(b.x, b.y, b.width, b.height);
				//如果子弹击中飞行物
				if (rf.intersects(bf)) {
					//判断子弹是否已经爆炸
					if (!b.isBomb) {
						//没有爆炸就减飞行物生命值
						f.life--;
						//
						if (f.life == 0) {
							if (f instanceof DeBullet) {
								f.state = FlyingObject.REMOVE;
							} else if (f instanceof Bee) {
								this.hero.addBuff();
							} else {
								if (f instanceof AirPlane) {
									this.score++;
								} else {
									this.score+=2;
								}
							}
						}
					}
					b.isBomb = true;
				}
			}
			//英雄
			int w = this.hero.width;
			int h = this.hero.height;
			Rectangle heroR = new Rectangle(this.hero.x+(w/8), this.hero.y+(h/8), this.hero.width-(w/4), this.hero.height-(h/4));
			if (rf.intersects(heroR)) {
				if (this.hero.strongTime <= 0) {
					this.hero.life = --this.hero.life<0?0:this.hero.life;
					if (this.hero.life>0) {
						this.hero.strongTime = Config.HERO_STRONGTIME;
						this.hero.x = 200;
						this.hero.y = 550;
					}
				}
			}
		}
	}
	/**
	 * 子弹
	 */
	public void addBullet() {
		//每0.3秒新增子弹
		this.bulletIndex++;
		if (this.bulletIndex>this.hero.fireDelay && !this.hero.isDead) {
			int hx = this.hero.x;
			int hy = this.hero.y;
			switch (this.hero.firePower) {
			case 1:
				Bullet b1 = new Bullet(hx+26, hy-5);
				this.bullets.add(b1);
				break;
			case 2:
				Bullet b21 = new Bullet(hx+5, hy+15);
				this.bullets.add(b21);
				Bullet b22 = new Bullet(hx+45, hy+15);
				this.bullets.add(b22);
				break;
			case 3:
				Bullet b30 = new Bullet(hx+26, hy-5);
				this.bullets.add(b30);
				Bullet b31 = new Bullet(hx+5, hy+15);
				this.bullets.add(b31);
				Bullet b32 = new Bullet(hx+45, hy+15);
				this.bullets.add(b32);
				break;
			case 4:
				Bullet b40 = new Bullet(hx+18, hy-5);
				this.bullets.add(b40);
				Bullet b41 = new Bullet(hx+5, hy+15);
				this.bullets.add(b41);
				Bullet b42 = new Bullet(hx+45, hy+15);
				this.bullets.add(b42);
				Bullet b43 = new Bullet(hx+33, hy-5);
				this.bullets.add(b43);
				break;
			}
			this.bulletIndex = 0;
		}
		for (FlyingObject flyingObject : flyingObjects) {
			if (flyingObject instanceof AirPlane) {
				AirPlane a = (AirPlane)flyingObject;
				if (!a.isShoot) {
					DeBullet d = new DeBullet(a.x, a.y, hero.x+30, hero.y+40);
					this.flyingObjects.add(d);
					a.isShoot = true;
					break;
				}
			}
		}
	}
	/**
	 * 清理死亡或出界的元素
	 */
	public void removeObj() {
		for (FlyingObject t : this.flyingObjects) {
			if (t.state==FlyingObject.REMOVE) {
				this.flyingObjects.remove(t);
				if (t instanceof Boss) {
					this.bossShow = false;
					this.score += 10;
					this.hero.life++;
				}
				t = null;
				break;
			}
		}
		for (Bullet bullet : this.bullets) {
			if (bullet.state==FlyingObject.REMOVE) {
				this.bullets.remove(bullet);
				bullet = null;
				break;
			}
		}
	}
	/**
	 * 生成新元素
	 * @param bool
	 * @return
	 */
	public int generate(boolean bool) {
		if (!bool) {
			this.flyingobjIndex++;
			//30s
			if (this.gameRunTime > 1000) {
				this.generateDelay = 40;
				//60s
				if (this.gameRunTime > 2000) {
					this.generateDelay = 20;
				}
			}
			//每3秒新增一个新元素
			if (this.flyingobjIndex < this.generateDelay) {
				return 0;
			}
		}
		int n = (int)(Math.random()*100);
		FlyingObject f = null;
		if (n<60) {
			f = new AirPlane();
		} else if (n<85) {
			f = new BigAirPlane();
		} else {
			f = new Bee();
		}
		this.flyingObjects.add(f);
		if (this.gameRunTime%2000<=100&&!this.bossShow&&this.gameRunTime>101) {
			this.flyingObjects.add(new Boss());
			this.bossShow = true;
		}
		this.flyingobjIndex = 0;
		return 0;
	}
	/**
	 * 暂停/继续
	 */
	public void pause() {
		this.isPause = true;
	}
	public void continueGame() {
		this.isPause = false;
	}
	/**
	 * 键盘事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//键盘按下，放入键盘缓冲区
		KeyBuffer.addKey(e.getKeyCode());
		if (this.hero.isDead && e.getKeyCode() == 10) {
			Program.entrance(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//键盘释放，从缓冲区清除
		KeyBuffer.removeKey(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
