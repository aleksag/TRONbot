

#include <AFMotor.h>
AF_DCMotor motor(1, MOTOR12_64KHZ);
AF_DCMotor motor2(2, MOTOR12_64KHZ);
AF_DCMotor motor3(3, MOTOR12_64KHZ);
AF_DCMotor motor4(4, MOTOR12_64KHZ);// create motor #2, 64KHz pwm

void setup() {
  motor.setSpeed(200);     // set the speed to 200/255
  motor2.setSpeed(200);     // set the speed to 200/255
  motor3.setSpeed(200);     // set the speed to 200/255
  motor4.setSpeed(200);     // set the speed to 200/255
  Serial.begin(115200);

}
char command = 0;
void loop() {

  if(Serial.available()){
    while(Serial.available()){
      command = Serial.read();
    }
    if(command == '1'){//left
      motor.run(FORWARD);      // turn it on going forward
      motor2.run(BACKWARD);
      motor3.run(FORWARD);
      motor4.run(BACKWARD);

    }
    else if(command == '2'){//right
      motor.run(BACKWARD);      // turn it on going forward
      motor2.run(FORWARD);
      motor3.run(BACKWARD);
      motor4.run(FORWARD);

    }
    else if(command == '3'){//backwardleft

    }
    else if(command == '4'){//backwardright

    }
    else if(command == '5'){//forward
      motor.run(FORWARD);      // turn it on going forward
      motor2.run(FORWARD);
      motor3.run(FORWARD);
      motor4.run(FORWARD);

    }
    else if(command == '6'){//backward
      motor.run(BACKWARD);     // the other way
      motor2.run(BACKWARD);     // the other way
      motor3.run(BACKWARD);     // the other way
      motor4.run(BACKWARD);     // the other way

    }
    else if(command == '7'){//stop
      motor.run(RELEASE);      // stopped
      motor2.run(RELEASE);      // stopped
      motor3.run(RELEASE);      // stopped
      motor4.run(RELEASE);      // stopped

    }

  }
}









