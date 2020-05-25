#include <Servo.h>

//0 for ailerons down (clockwise), 180 for ailerons up (counterclockwise) - RIGHT WING
//180 for ailerons down (counterclockwise), 0 for ailerons up (clockwise) - LEFT WING

Servo rightAileronServo;
const int rightAileronServoPin = 6;

Servo leftAileronServo;
const int leftAileronServoPin = 7;

const int rightVal = 114;
const int leftVal = 108;

int serial = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    serial = Serial.read();
    Serial.println(serial, DEC);
    switch (serial) {
      case rightVal: //turn right - right aileron down, left aileron up - both servos 0
        rightAileronServo.attach(rightAileronServoPin);
        leftAileronServo.attach(leftAileronServoPin);
        rightAileronServo.write(0);
        leftAileronServo.write(0);
        delay(80);
        rightAileronServo.detach();
        leftAileronServo.detach();
        Serial.println("RIGHT");
        break;
      case leftVal: //turn left - right aileron up, left aileron down - both servos 180
        rightAileronServo.attach(rightAileronServoPin);
        leftAileronServo.attach(leftAileronServoPin);
        rightAileronServo.write(180);
        leftAileronServo.write(180);
        delay(80);
        rightAileronServo.detach();
        leftAileronServo.detach();
        Serial.println("LEFT");
        break;
    }
  }
}
