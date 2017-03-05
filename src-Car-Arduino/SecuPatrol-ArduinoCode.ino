#include <Servo.h> 

int pinLF=5;
int pinLB=7;
int pinRF=9;
int pinRB=10;
int inputPin=1;
int outputPin=2;
int frontDistance=0;
int q=0;
int w=1;
int s=2;
int a=3;
int d=4;

Servo servoX;


char val=' ';
char temp=' '; 
int state=0;
int angle =90;



void setup()
 {
  Serial.begin(9600);
  pinMode(pinLB,OUTPUT);
  pinMode(pinLF,OUTPUT);
  pinMode(pinRB,OUTPUT);
  pinMode(pinRF,OUTPUT);
  pinMode(inputPin,INPUT);
  pinMode(outputPin,OUTPUT);
  servoX.attach(2);
  state = q;
  
 }


void forward()
    {
     digitalWrite(pinRF,HIGH);
     digitalWrite(pinRB,LOW);
     digitalWrite(pinLF,HIGH);
     digitalWrite(pinLB,LOW);
    }

void right()
    {
    if(angle < 140){
        angle = angle + 10;
      }
	 servoX.write(angle);

    }
void left()
    {
   if(angle > 40){
      angle = angle - 10;
    }
	 servoX.write(angle);
    }
       
void stopp()
    {
     digitalWrite(pinRB,LOW);
     digitalWrite(pinRF,LOW);
     digitalWrite(pinLB,LOW);
     digitalWrite(pinLF,LOW);
    }
void back()
 {
     digitalWrite(pinRF,LOW);
     digitalWrite(pinRB,HIGH);
     digitalWrite(pinLF,LOW);
     digitalWrite(pinLB,HIGH);
}

void loop()
 {
    if(Serial.available()){

     val = Serial.read();
     

       if(val == 'W'){
         state = w;
       }else if(val == 'S'){
         state = s;
       }else if(val == 'A'){
         state = a;
       }else if(val == 'D'){
         state = d;
       }else if(val == 'Q'){
         state = q;
       }
    }
    
    if(state == a){
      left();
      state = q;
    }else if(state == d){
      right();
      state = q;
    }else if(state == w){
      forward();
    }else if(state == s){
      back();
    }else if(state == q){
      stopp();
    }
  
  Serial.flush();
 }
