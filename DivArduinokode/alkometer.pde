int alkosensor = 0;
int led1 = 13;
int led2 = 12;
int led3 = 11;
int led4 = 10;
int led5 = 9;
int led6 = 8;

void setup()
{
  Serial.begin(115200);
  pinMode(alkosensor, INPUT);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
}

int fylleverdi = 0;
void loop()
{
  fylleverdi = analogRead(0);
  if(fylleverdi <= 10){
    ledOff(led1);
    ledOff(led2);
    ledOff(led3);
    ledOff(led4);
    ledOff(led5);
    ledOff(led6);
  }
  else if(fylleverdi > 10 && fylleverdi <20){
    ledOn(led1); 
  }
  else if(fylleverdi >=20 && fylleverdi< 30){
    ledOn(led1);
    ledOn(led2);
  }
  else if(fylleverdi >=30 && fylleverdi< 40){
    ledOn(led1);
    ledOn(led2);
    ledOn(led3);
  }
  else if(fylleverdi >= 40 && fylleverdi < 50){
    ledOn(led1);
    ledOn(led2);
    ledOn(led3);
    ledOn(led4);
  }
  else if(fylleverdi >= 50 && fylleverdi < 60){
    ledOn(led1);
    ledOn(led2);
    ledOn(led3);
    ledOn(led4);
    ledOn(led5);   
  }
  else{
    ledOn(led1);
    ledOn(led2);
    ledOn(led3);
    ledOn(led4);
    ledOn(led5);
    ledOn(led6);
  }
  Serial.print(fylleverdi);
  Serial.print(10,BYTE);

  delay(50);
      
}

void ledOn(int led){
   digitalWrite(led,HIGH);
}

void ledOff(int led){
  digitalWrite(led,LOW);
}
