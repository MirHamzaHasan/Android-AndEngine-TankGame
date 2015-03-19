package com.project.battlefield;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IDrawHandler;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;
import android.view.MotionEvent;






public class MainActivity extends BaseGameActivity{
 
	public MainActivity(){
		rot=0.0f;
	}
	public boolean isLeftDown;
	public boolean isRightDown;
	public boolean isBoostDown;
	
	float rot;
	
	boolean isFired;
	
    private  Camera mCamera;
	BitmapTextureAtlas playerTexture;
	ITextureRegion playerTextureRegion;
	
	
	ButtonSprite fireb;
	BitmapTextureAtlas firebTexture;
	ITextureRegion firebTextureRegion;
	
	BitmapTextureAtlas bulletTexture;
	ITextureRegion bulletTextureRegion;
	
	
	ButtonSprite leftb;
	BitmapTextureAtlas leftbTexture;
	ITextureRegion leftbTextureRegion;
	
	
	ButtonSprite rightb;
	BitmapTextureAtlas rightbTexture;
	ITextureRegion rightbTextureRegion;
	
	
	ButtonSprite boostb;
	BitmapTextureAtlas boosttbTexture;
	ITextureRegion boosttbTextureRegion;
	
	Scene scene;
	Sprite s;
	Sprite bullet;
	
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		this.mCamera=new Camera(0,0,800,480);
		EngineOptions option=new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(800,480), mCamera);
		//isLeftDown=false;
		return option;
	}
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		// TODO Auto-generated method stub
		// load textures;;;
		
		
		
	
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//myPlayer
		playerTexture =new BitmapTextureAtlas(getTextureManager(), 64, 32);
		playerTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(playerTexture, this, "tank.png", 0, 0);
		playerTexture.load();
		s=new Sprite(10,100,playerTextureRegion,this.mEngine.getVertexBufferObjectManager());//end
		
		bulletTexture =new BitmapTextureAtlas(getTextureManager(),16, 16);
		bulletTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(bulletTexture, this, "bullet.png", 0, 0);
		bulletTexture.load();
		bullet=new Sprite(0,0,bulletTextureRegion,this.mEngine.getVertexBufferObjectManager());
		
		
		leftbTexture =new BitmapTextureAtlas(getTextureManager(), 64, 32);
		leftbTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(leftbTexture, this, "button_left.png", 0, 0);
		leftbTexture.load();
		
		rightbTexture =new BitmapTextureAtlas(getTextureManager(), 64, 32);
		rightbTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(rightbTexture, this, "button_right.png", 0, 0);
		rightbTexture.load();
		
		boosttbTexture =new BitmapTextureAtlas(getTextureManager(), 64, 32);
		boosttbTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(boosttbTexture, this, "Boost.png", 0, 0);
		boosttbTexture.load();
		
		firebTexture =new BitmapTextureAtlas(getTextureManager(), 64, 64);
		firebTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(firebTexture, this, "fire.png", 0, 0);
		firebTexture.load();
		
         this.getEngine().registerDrawHandler(new IDrawHandler() {
			
			@Override
			public void onDraw(GLState pGLState, Camera pCamera) {
				// TODO Auto-generated method stub
				
			}
		});
		this.getEngine().registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				if(isLeftDown){
				s.setRotation(s.getRotation()-1.0f);
				}
				
				if(isRightDown){
					s.setRotation(s.getRotation()+1.0f);
				}
				if(isBoostDown){
				s.setX(s.getX()-(float)(1.0f*Math.cos((float)(Math.toRadians(s.getRotation())))));
				s.setY(s.getY()-(float)(1.0f*Math.sin((float)(Math.toRadians(s.getRotation())))));
				if(s.getX()>770){
					s.setX(770);
				}
				if(s.getX()<0){
					s.setX(0);
				}
				if(s.getY()>460){
					s.setY(460);
				}
				if(s.getY()<0){
					s.setY(0);
				}
				}
		        if((bullet.getX()>800 || bullet.getX()<0) ||(bullet.getY()>480 || bullet.getY()<0)) {
				if(isFired){
					bullet.setX(s.getX());
					bullet.setY(s.getY());
					rot=s.getRotation();
					isFired=false;
				}
				}
				
				bullet.setX(bullet.getX()-(float)(4.0f*Math.cos((float)(Math.toRadians(rot)))));
				bullet.setY(bullet.getY()-(float)(4.0f*Math.sin((float)(Math.toRadians(rot)))));
				
			}
		});

		
		
		pOnCreateResourcesCallback.onCreateResourcesFinished();
		
	}
	
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		scene=new Scene();
		scene.setBackground(new Background(0,0,255));
        
		fireb= new ButtonSprite(740,420,this.firebTextureRegion,this.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent ptc,float ptalx,float ptaly){
				if(ptc.getAction()==MotionEvent.ACTION_DOWN){
					isFired=true;
					return true;
				}
				return true;
			}
		};

		leftb=new ButtonSprite(0,420,this.leftbTextureRegion,this.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent ptc,float ptalx,float ptaly){
				if(ptc.getAction()==MotionEvent.ACTION_DOWN){
					
					isLeftDown=true;

					return true;
				}
				if(ptc.getAction()==MotionEvent.ACTION_UP){
					
				
					isLeftDown=false;

					return true;
				}
				
				return false;
				
			}
		};
		
		
		rightb=new ButtonSprite(128,420,this.rightbTextureRegion,this.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent ptc,float ptalx,float ptaly){
				if(ptc.getAction()==MotionEvent.ACTION_DOWN){
			
					
					isRightDown=true;
					return true;
				}
				if(ptc.getAction()==MotionEvent.ACTION_UP){
			
					isRightDown=false;
					
					return true;
				}
				return true;
				
			}
		};
		
		
		boostb=new ButtonSprite(64,420,this.boosttbTextureRegion,this.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent ptc,float ptalx,float ptaly){
				if(ptc.getAction()==MotionEvent.ACTION_DOWN){
					isBoostDown=true;
					//s.setRotation(s.getRotation()+5.0f);
					return true;
				}
				if(ptc.getAction()==MotionEvent.ACTION_UP){
					isBoostDown=false;
					//s.setRotation(s.getRotation()+5.0f);
					return true;
				}
				return false;
			}
		};
	    
		scene.registerTouchArea(leftb);
		scene.registerTouchArea(rightb);
		scene.registerTouchArea(boostb);
		scene.registerTouchArea(fireb);
		
		
		pOnCreateSceneCallback.onCreateSceneFinished(scene);
	}
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		this.scene.attachChild(s);
		this.scene.attachChild(leftb);
		this.scene.attachChild(rightb);
		this.scene.attachChild(boostb);
		this.scene.attachChild(fireb);
		this.scene.attachChild(bullet);
		
		
		
		
		
		
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	
 

	

	

	
	

	
}
