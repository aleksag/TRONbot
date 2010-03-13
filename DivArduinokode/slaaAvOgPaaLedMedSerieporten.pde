int ledPin = 13;

void setup() {
  Serial.begin(115200);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  char command = 0;
  if(Serial.available()){
    while(Serial.available()){
      command = Serial.read();
    }  
    if(command == '1'){
      digitalWrite(ledPin, HIGH);
      Serial.print("Slår led på");
      Serial.print(10,BYTE);
    }
    else if(command = '2'){
      digitalWrite(ledPin, LOW); 
      Serial.print("Slår led av");
      Serial.print(10,BYTE);  
    }
  }
}

