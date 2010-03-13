/*
  Serial Call and Response
 Language: Wiring/Arduino
 
 This program sends an ASCII A (byte of value 65) on startup
 and repeats that until it gets some data in.
 Then it waits for a byte in the serial port, and 
 sends three sensor values whenever it gets a byte in.
 
 Thanks to Greg Shakar and Scott Fitzgerald for the improvements
 
 The circuit:
 * potentiometers attached to analog inputs 0 and 1 
 * pushbutton attached to digital I/O 2
 
 
 http://www.arduino.cc/en/Tutorial/SerialCallResponse
 
 Created 26 Sept. 2005
 by Tom Igoe
 Modified 14 April 2009
 by Tom Igoe and Scott Fitzgerald
 */

int firstSensor = 0;    // first analog sensor
int secondSensor = 0;   // second analog sensor
int thirdSensor = 0;    // digital sensor
int inByte = 0;         // incoming serial byte
int loopCount = 0;
int threshold = 0;

void setup()
{
  // start serial port at 9600 bps:
  Serial.begin(115200);
  pinMode(0, INPUT);   // digital sensor is on digital pin 2
  pinMode(13, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(10, OUTPUT);
    pinMode(9, OUTPUT);
  pinMode(8, OUTPUT);


}

void loop()
{
  // read second analog input, divide by 4 to make the range 0-255:
  firstSensor = analogRead(0);
  if(firstSensor <= 10){
    digitalWrite(13,LOW);
    digitalWrite(12,LOW);
    digitalWrite(11,LOW);
    digitalWrite(10,LOW); 
        digitalWrite(9,LOW);
    digitalWrite(8,LOW); 
  }
  else if(firstSensor > 10 && firstSensor <20){
    digitalWrite(13,HIGH); 
  }
  else if(firstSensor >=20 && firstSensor< 30){
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
  }
  else if(firstSensor >=30 && firstSensor< 40){
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
    digitalWrite(11,HIGH); 
  }
  else if(firstSensor >= 40 && firstSensor < 50){
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
    digitalWrite(11,HIGH);
    digitalWrite(10,HIGH);  
  }
  else if(firstSensor >= 50 && firstSensor < 60){
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
    digitalWrite(11,HIGH);
    digitalWrite(10,HIGH);
    digitalWrite(9,HIGH);   
  }
  else{
    digitalWrite(13,HIGH);
    digitalWrite(12,HIGH);
    digitalWrite(11,HIGH);
    digitalWrite(10,HIGH);
    digitalWrite(9,HIGH);
    digitalWrite(8,HIGH);

  }
  Serial.print(firstSensor);
  Serial.print(10,BYTE);

  delay(50);
      
}




