

int cmd = 0;
int param1 = 0;
int param2 = 0;
long nextread = 0;


void cmdDigitalWrite() {
  if( param1 < 0 || param1 > 13 ) {
    err(01,1,0);
  } 
  else {
    if( param2 == 0 ) {
      digitalWrite(param1,LOW);
      ok();
    } 
    else if( param2 == 1 ) {
      digitalWrite(param1,HIGH);      
      ok();
    } 
    else {
      err(01,2,0);
    }
  }
}

void (*fp)() = cmdDigitalWrite;
class CRectangle {
public:
  int doit () {
    // send data only when you receive data:
    if (Serial.available() > 7) {

      cmd = (Serial.read()-48)*10;
      cmd = cmd+(Serial.read()-48);

      param1 = (Serial.read()-48)*10;
      param1 = param1+(Serial.read()-48);

      param2 = (Serial.read()-48)*1000;
      param2 = param2+(Serial.read()-48)*100;
      param2 = param2+(Serial.read()-48)*10;
      param2 = param2+(Serial.read()-48);

      if(cmd==1) {
        Serial.println("cmd 1");
        fp();
      } 
      else {
        err(00,cmd,0);
      }

      Serial.flush();

    }

  }


};

CRectangle rect;


void setup() {
  Serial.begin(115200);
  pinMode(0, OUTPUT);
  pinMode(1, INPUT);
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);

  long nextread = millis() + 1000;
}



void loop() {
  rect.doit();
}

void ok() {
  Serial.print("OK");
  Serial.println("");
}

void err(int code, int p1,int p2) {
  Serial.print("ER");
  Serial.print(p1/10);
  Serial.print(p1%10);
  Serial.print(p2/10);
  Serial.print(p2%10);
  Serial.println("");
}





















