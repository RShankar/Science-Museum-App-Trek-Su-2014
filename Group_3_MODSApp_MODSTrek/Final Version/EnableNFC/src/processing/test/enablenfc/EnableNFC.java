package processing.test.enablenfc;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.app.PendingIntent; 
import android.content.Intent; 
import android.os.Bundle; 
import ketai.net.nfc.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class EnableNFC extends PApplet {

PendingIntent mPendingIntent;
public void onCreate(Bundle savedInstanceState) {
ketaiNFC = new KetaiNFC(this);
super.onCreate(savedInstanceState);
mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
}
public void onNewIntent(Intent intent) { if (ketaiNFC != null)
    ketaiNFC.handleIntent(intent);
}
  
 
String tagText="";
Boolean pageView = false;
PImage back;
PImage page;
KetaiNFC ketaiNFC;
public void setup() {
ketaiNFC = new KetaiNFC(this); orientation(PORTRAIT); textSize(36); textAlign(CENTER, CENTER);
back=loadImage("background.png");
}
public void draw() {

if(!pageView){
  image(back,0,0,width,height);
  text("Read Tag to go to Exhibit Page:\n"+ tagText, width/2, height/2);

}

else if(pageView){
  page = loadImage(tagText + ".jpg");
  image(page,0,0,width,height);
}

}
 public void onNFCEvent(String txt)
{
  tagText=trim(txt);
  pageView =true;
}

}
