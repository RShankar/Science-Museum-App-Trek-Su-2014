package processing.test.floormaps;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ketai.ui.*; 
import android.view.MotionEvent; 
import ketai.sensors.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FloorMaps extends PApplet {





//gestures
KetaiGesture gesture;

//if view on first floor, on startup 1= true
boolean onFloor1=true;
boolean onFloorMaps=true;
//if menu is available
boolean onMenu=false;
//if menu set to Person
boolean onPerson=true;
//Map person options
boolean onChildren=false;
boolean onStudents=false;
boolean onAdults=false;
boolean onEveryone=false;
//String
String statusUpdate="Tap item to select, tap \n again to deselect.";

//image of floor maps
PImage floorMap;
PImage menuButton;

//places options
boolean onExhibits=false;
boolean onFood=false;
boolean onPlacesToSit=false;
boolean onRestrooms=false;
boolean onSimulations=false;
boolean onTheater=false;

String path="";

//locations
double longitude, latitude;
KetaiLocation location;
Location mods;
double xVal=0;
double yVal=0;
public void setup(){
  background(255);
  
  gesture = new KetaiGesture(this);
  mods = new Location("mods");
  mods.setLatitude(26.1216f);
  mods.setLongitude(-80.1483f);
  orientation(PORTRAIT);
}
//DRAW
public void draw(){
if(onFloorMaps){
  background(255);
  //menu button
    if(onFloor1){                        //first floor
      //buttons
      fill(0,204,205);
      rect(0,0,width/2,height/20);
      fill(255);
      rect(width/2,0,width/2,height/20);
      fill(0);
      textSize(32);
      text("Floor 1", width/5, height/22);
      text("Floor 2", 17*width/25, height/22);
      floorMap = loadImage("floor1.gif"); 

      /**if (location.getProvider() == "none") {
        println("working");
        textSize(22);
        text("Location data is unavailable.\n Please check your location settings.", 400, 20);
      }
      else*/ if((latitude>26.1206f && latitude<26.1216f) && (longitude>-80.1483f && longitude<-80.1476f)){
          xVal = convertLongToX(longitude);
          yVal = convertLatToY(latitude);
          stroke(10);
          fill(204,204,0);
          ellipse((float)xVal,(float)yVal,25,25);
        }
        else{
          fill(0);
          rect(565,1150,180,40);
          textSize(32);
          fill(255,0,0);
          text("Not in range.", 565, 1180);
        }
      }
    else{                              //second floor
      //buttons
      fill(255);                        //unselected
      rect(0,0,width/2,height/20);
      fill(0,204,205);                  //selected tab color
      rect(width/2,0,width/2,height/20);
      fill(0);
      textSize(32);
      text("Floor 1", width/5, height/22);
      text("Floor 2", 17*width/25, height/22);
      floorMap = loadImage("floor2.gif");
            /**if (location.getProvider() == "none") {
        println("working");
        textSize(22);
        text("Location data is unavailable.\n Please check your location settings.", 400, 20);
      }
      else*/ if((latitude>26.1206f && latitude<26.1216f) && (longitude>-80.1483f && longitude<-80.1476f)){
          xVal = convertLongToX2(longitude);
          yVal = convertLatToY2(latitude);
          stroke(10);
          fill(204,204,0);
          ellipse((float)xVal,(float)yVal,25,25);
        }
        else{
          fill(0);
          rect(565,1150,180,40);
          textSize(32);
          fill(255,0,0);
          text("Not in range.", 565, 1180);
        }
    } 
  image(floorMap,0,height/20,width,19*height/20);
  
    displayPlaces();
    menuButton=loadImage("menu.gif");
  if(!onMenu){
    image(menuButton, 750,90,50,50 );
  }
  else{
    if(onPerson){
      textSize(28);
      
      rect(width/2,90, width/2,height/2);
      image(menuButton, width/2-50,90,50,50 );
      
      fill(0,204,205);
      rect(width/2,90,width/4,height/40);
      
      fill(0);
      text("By Person", width/2, 115);   
      
      fill(255);
      rect(3*width/4,90,width/4,height/40);
      
      fill(0);
      text("By Location", 3*width/4, 115);
      
      fill(255);
      
      //OPTIONS for people
      if(!onChildren){
        fill(255);
        rect(width/2,90+height/40,width/2,height/25);
        fill(0);
        text("Children", width/2, 90+height/40+40);
      }
      else if(onChildren){
        fill(0,204,205); 
        rect(width/2,90+height/40,width/2,height/25);
        fill(0);
        text("Children", width/2, 90+height/40+40);
      }
      if(!onStudents){
        fill(255);
        rect(width/2,90+height/40+height/25,width/2,height/25);
        fill(0);
        text("Students", width/2, 90+height/40+height/25+40);
      }
      else if(onStudents){
        fill(0,204,205);
        rect(width/2,90+height/40+height/25,width/2,height/25);
        fill(0);
        text("Students", width/2, 90+height/40+height/25+40);
      }
      if(!onAdults){
        fill(255);
        rect(width/2,90+height/40+2*height/25,width/2,height/25);
        fill(0);
        text("Adults", width/2, 90+height/40+2*height/25+40);
      }
      else if(onAdults){
        fill(0,204,205);
        rect(width/2,90+height/40+2*height/25,width/2,height/25);
        fill(0);
        text("Adults", width/2, 90+height/40+2*height/25+40);
      }
       if(!onEveryone){
        fill(255);
        rect(width/2,90+height/40+3*height/25,width/2,height/25);
        fill(0);
        text("For the Whole Family", width/2, 90+height/40+3*height/25+40);
      }
       else if(onEveryone){
        fill(0,204,205);
        rect(width/2,90+height/40+3*height/25,width/2,height/25);
        fill(0);
        text("For the Whole Family", width/2, 90+height/40+3*height/25+40);
      }
      fill(255);
      statusUpdate="Person View can only be seen \none at a time. \nTap item to select, tap \nagain to deselect.";
      fill(255);
      text(statusUpdate, width/2, height/2-30);
    }
    else{ 
      //BY LOCATION
      textSize(28);
      
      rect(width/2,90, width/2,height/2);
      image(menuButton, width/2-50,90,50,50 );
      
      fill(255);
      rect(width/2,90,width/4,height/40);
      
      fill(0);
      text("By Person", width/2, 115);   
      
      fill(0,204,205);
      rect(3*width/4,90,width/4,height/40);
      
      fill(0);
      text("By Location", 3*width/4, 115);
      
      //OPTIONS for Location
      if(!onExhibits){
        fill(255);
        rect(width/2,90+height/40,width/2,height/25);
        fill(0);
        text("Exhibits", width/2, 90+height/40+40);
      }
      else if(onExhibits){
        fill(0,204,205); 
        rect(width/2,90+height/40,width/2,height/25);
        fill(0);
        text("Exhibits", width/2, 90+height/40+40);
      }
      if(!onFood){
        fill(255);
        rect(width/2,90+height/40+height/25,width/2,height/25);
        fill(0);
        text("Food", width/2, 90+height/40+height/25+40);
      }
      else if(onFood){
        fill(0,204,205);
        rect(width/2,90+height/40+height/25,width/2,height/25);
        fill(0);
        text("Food", width/2, 90+height/40+height/25+40);
      }
      if(!onPlacesToSit){
        fill(255);
        rect(width/2,90+height/40+2*height/25,width/2,height/25);
        fill(0);
        text("Places To Sit", width/2, 90+height/40+2*height/25+40);
      }
      else if(onPlacesToSit){
        fill(0,204,205);
        rect(width/2,90+height/40+2*height/25,width/2,height/25);
        fill(0);
        text("Places To Sit", width/2, 90+height/40+2*height/25+40);
      }
       if(!onRestrooms){
        fill(255);
        rect(width/2,90+height/40+3*height/25,width/2,height/25);
        fill(0);
        text("Restrooms", width/2, 90+height/40+3*height/25+40);
      }
       else if(onRestrooms){
        fill(0,204,205);
        rect(width/2,90+height/40+3*height/25,width/2,height/25);
        fill(0);
        text("Restrooms", width/2, 90+height/40+3*height/25+40);
      }
        if(!onSimulations){
        fill(255);
        rect(width/2,90+height/40+4*height/25,width/2,height/25);
        fill(0);
        text("Simulations & Hands-On", width/2, 90+height/40+4*height/25+40);
      }
       else if(onSimulations){
        fill(0,204,205);
        rect(width/2,90+height/40+4*height/25,width/2,height/25);
        fill(0);
        text("Simulations & Hands-On", width/2, 90+height/40+4*height/25+40);
      }
        if(!onTheater){
        fill(255);
        rect(width/2,90+height/40+5*height/25,width/2,height/25);
        fill(0);
        text("Theaters", width/2, 90+height/40+5*height/25+40);
      }
       else if(onTheater){
        fill(0,204,205);
        rect(width/2,90+height/40+5*height/25,width/2,height/25);
        fill(0);
        text("Theaters", width/2, 90+height/40+5*height/25+40);
      }
      fill(255);
      String statusUpdate="Tap item to select, tap \n again to deselect.";
      text(statusUpdate, width/2, height/2+50); 
    }
  }
}
else{
  background(255);
  specificPage= loadImage(path + ".jpg");
  image(specificPage,0,0,width,height);
  fill(255);
  rect(0,1150,200,50);
  fill(0);
  textSize(32);
  text("Back to Maps",0,1190);
}
}


public void onTap(float x, float y){
  if(x>width/2 && y<height/20){
    if(onFloor1){
      onFloor1=false;
    }
  }
  else if(x<width/2 && y<height/20){
    if(!onFloor1){
      onFloor1=true;
    }
  }
  
  if(!onMenu){
    if(x>750 && y>90 && y<140){
    onMenu=true;
    }
  }
  else if(onMenu){
    if(x>(350) && x<400 && y>90 && y<140){ //close menu
      onMenu=false;
    }
    if(onPerson){
      if(x>3*width/4 && y>90 && y<height/40+90){
        onPerson=false;
      }
      if(x>width/2 && y>90+height/40 && y<90+height/40+height/25){
      //rect(width/2,90+height/40,width/2,height/25);
        if(onChildren)
          onChildren=false;
        else
          onChildren=true;
      }
      if(x>width/2 && y>90+height/40+height/25 && y<90+height/40+2*height/25){
      //rect(width/2,90+height/40+height/25,width/2,height/25);
        if(onStudents)
          onStudents=false;
        else
          onStudents=true;
      }
       if(x>width/2 && y>90+height/40+2*height/25 && y<90+height/40+3*height/25){
        if(onAdults)
          onAdults=false;
        else
          onAdults=true;
      }
      if(x>width/2 && y>90+height/40+3*height/25 && y<90+height/40+4*height/25){
      //rect(width/2,90+height/40+height/25,width/2,height/25);
        if(onEveryone)
          onEveryone=false;
        else
          onEveryone=true;
      }  
    }
    else if(!onPerson){
       if(x>width/2 && x<3*width/4 && y>90 && y<height/40+90 ){
         onPerson=true;
       }
       if(x>width/2 && y>90+height/40 && y<90+height/40+height/25){
        //rect(width/2,90+height/40,width/2,height/25);
        if(onExhibits)
          onExhibits=false;
        else
          onExhibits=true;
      }
      if(x>width/2 && y>90+height/40+height/25 && y<90+height/40+2*height/25){
      //rect(width/2,90+height/40+height/25,width/2,height/25);
        if(onFood)
          onFood=false;
        else
          onFood=true;
      }
       if(x>width/2 && y>90+height/40+2*height/25 && y<90+height/40+3*height/25){
        if(onPlacesToSit)
          onPlacesToSit=false;
        else
          onPlacesToSit=true;
      }
      if(x>width/2 && y>90+height/40+3*height/25 && y<90+height/40+4*height/25){
        if(onRestrooms)
          onRestrooms=false;
        else
          onRestrooms=true;
        }  
       if(x>width/2 && y>90+height/40+4*height/25 && y<90+height/40+5*height/25){
        if(onSimulations)
          onSimulations=false;
        else
          onSimulations=true;
      }
      if(x>width/2 && y>90+height/40+5*height/25 && y<90+height/40+6*height/25){
        if(onTheater)
          onTheater=false;
        else
          onTheater=true;
       }
     }
    }
       //ICON FUNCTIONALITY SPACE ROOM
       //exhibits
       if(onExhibits){
         //image(exhibits,120,900,40,40);     //radial engine
         //image(exhibits,80,900,40,40);     //pratt whitney engine
         if(onFloorMaps && x>120 && x<160 && y<940 && y>900){//radial
           path="pandwr985";
           onFloorMaps=false;
         }
         if(onFloorMaps && x>80 && x<840 && y<940 && y>900){//prattwhit
           path="pandwf401";
           onFloorMaps=false;
         }
         else if(!onFloorMaps){
           if(x<200 && y>1150 && y<1190){
             onFloorMaps=true;
           }
         }
       }
       //simulators
       if(onSimulations){
        //image(simulations,75,820,40,40);      //simulator
        //image(simulations,200,650,40,40);     //escape velocity simulator
        //image(simulations,150,980,40,40);     //plane simulators
         if(onFloorMaps && x>75 && x<115 && y>820 && y<860){
           path="cockpit";
           onFloorMaps=false;
         }
         if(onFloorMaps && x>200 && x<240 && y>650 && y<690){
           path="escapevel";
           onFloorMaps=false;
         }
         if(onFloorMaps && x>150 && x<190 && y>980 && y<1020){
           path="zivkoedge540";
           onFloorMaps=false;
         }
         else if(!onFloorMaps){
           if(x<200 && y>1150 && y<1190){
             onFloorMaps=true;
           }
         }
         
       }
       
  println("SINGLE: "+ x + ", " + y);
}



public double convertLongToX(double l){
  return (abs((float)(l+80.1483f))*1000000)+90;
}
public double convertLatToY(double lat){
  return (abs((float)(lat-26.1216f)))*1000000+130;
}
public double convertLongToX2(double l){
  return (abs((float)(l+80.1483f))*1000000)+75;
}
public double convertLatToY2(double lat){
  return (abs((float)(lat-26.1216f)))*1000000+130;
}

public void onLocationEvent(Location _location) {
  //print out the location object
  println("onLocation event: " + _location.toString());
  longitude = _location.getLongitude();
  latitude = _location.getLatitude();
  
}

public boolean surfaceTouchEvent(MotionEvent event) {
  //call to keep mouseX and mouseY constants updated
  super.surfaceTouchEvent(event);
  //forward events
  return gesture.surfaceTouchEvent(event);
}

PImage food;
PImage restroom;
PImage theater;
PImage bench;
PImage exhibits;
PImage simulations;
PImage specificPage;

public void displayPlaces(){
  if(onFood){
    food = loadImage("food.png");
    if(onFloor1){
      image(food, 540, 820, 40,40);     //snack bar
      image(food, 520, 1070, 40,40);    //auntie annes
      image(food, 560, 1030, 40,40);    //subway
    }
  }
   if(onRestrooms){
    restroom = loadImage("restroom.png");
    if(onFloor1){
      image(restroom, 540, 790, 40,40);
      image(restroom, 520, 330, 40,40);
    }
    else if(!onFloor1){
      image(restroom, 480, 370, 40,40);
      image(restroom, 480, 730, 40,40);
    }
  }
  if(onTheater){
    theater = loadImage("theater.png");
    if(onFloor1){  
      image(theater, 700, 840,40,40); //imax
    }
    else if(!onFloor1){
      image(theater, 83, 1070, 40,40); //other
    }
  }
  if(onPlacesToSit){
    bench = loadImage("rest.png");
    if(onFloor1){  
      image(bench, 350, 900,40,40);
      image(bench, 200, 450,40,40);
      image(bench, 400, 320,40,40);
      image(bench, 470, 160,40,40);
    }
  }
  if(onExhibits){
    exhibits = loadImage("exhibit.png");
    if(onFloor1){
    image(exhibits,450,550,40,40);      //animals
    }
    if(!onFloor1){
      image(exhibits,120,900,40,40);     //radial engine
      image(exhibits,80,900,40,40);     //pratt whitney engine
    }
  }
  if(onSimulations){
    simulations = loadImage("simulator.png");
    if(onFloor1){
      image(simulations,200,700,40,40);      //play area
      image(simulations,320,180,40,40);      //fossil digging
      image(simulations,450,350,40,40);      //water
    }
    if(!onFloor1){
      image(simulations,75,820,40,40);      //simulator
      image(simulations,200,650,40,40);     //escape velocity simulator
      image(simulations,150,980,40,40);     //plane simulators
    }
  }
  if(onChildren){
     onStudents=false;
     onAdults=false;
     onEveryone=false;
     onExhibits=false;
     onFood=false;
     onPlacesToSit=false;
     onRestrooms=false;
     onSimulations=false;
     onTheater=false;
    simulations = loadImage("simulator.png");
    if(onFloor1){
      image(simulations,200,700,40,40);      //play area
      image(simulations,320,180,40,40);      //fossil digging
    }
  }
    if(onStudents){
     onChildren=false;
     onAdults=false;
     onEveryone=false;
     onExhibits=false;
     onFood=false;
     onPlacesToSit=false;
     onRestrooms=false;
     onSimulations=false;
     onTheater=false;
    simulations = loadImage("simulator.png");
    exhibits = loadImage("exhibit.png");
    if(onFloor1){
      image(exhibits,450,550,40,40);      //animals
      image(simulations,450,350,40,40);      //water
    }
  }
    if(onAdults){
     onChildren=false;
     onStudents=false;
     onEveryone=false;
     onExhibits=false;
     onFood=false;
     onPlacesToSit=false;
     onRestrooms=false;
     onSimulations=false;
     onTheater=false;
    theater = loadImage("theater.png");
    if(onFloor1){
      image(theater, 700, 840,40,40); //imax
    }
  }
    if(onEveryone){
     onChildren=false;
     onStudents=false;
     onAdults=false;
     onExhibits=false;
     onFood=false;
     onPlacesToSit=false;
     onRestrooms=false;
     onSimulations=false;
     onTheater=false;
    simulations = loadImage("simulator.png");
    exhibits = loadImage("exhibit.png");
    if(onFloor1){
      image(exhibits,450,200,40,40);      //otter
      image(simulations,120,200,40,40);      //everglades
    }
  }
}


}
