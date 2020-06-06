#include <Servo.h>

//0 for ailerons down (clockwise), 180 for ailerons up (counterclockwise) - RIGHT WING
//180 for ailerons down (counterclockwise), 0 for ailerons up (clockwise) - LEFT WING

Servo rightAileronServo;
const int rightAileronServoPin = 6;

Servo leftAileronServo;
const int leftAileronServoPin = 7;

const int rightVal = 114;
const int leftVal = 108;

int pos = 0; //0 is both ailerons level, 2 is full right, -2 is full left

const int serialSize = 3;
char serial[serialSize];

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(10);
}

void loop() {
  if (Serial.available() > 0) {
    Serial.readBytes(serial, serialSize);
    updatePos(serial[0]);
  }
}

void updatePos(int aileronPos) {
  if (pos > aileronPos) {
    left(pos - aileronPos);
    Serial.print("shifted left ");
    Serial.println((pos - aileronPos));
  }
  else if (pos < aileronPos) {
    right(serial - pos);
    Serial.print("shifted right ");
    Serial.println((aileronPos - pos));
  }
  pos = aileronPos;
}

void right(int i) { //turn right - right aileron down, left aileron up - both servos 0
  rightAileronServo.attach(rightAileronServoPin);
  leftAileronServo.attach(leftAileronServoPin);
  leftAileronServo.write(0);
  rightAileronServo.write(0);
  delay(60 * i);
  rightAileronServo.detach();
  leftAileronServo.detach();
  Serial.println("RIGHT");
}

void left(int i) { //turn left - right aileron up, left aileron down - both servos 180
  rightAileronServo.attach(rightAileronServoPin);
  leftAileronServo.attach(leftAileronServoPin);
  leftAileronServo.write(180);
  rightAileronServo.write(180);
  delay(60 * i);
  rightAileronServo.detach();
  leftAileronServo.detach();
  Serial.println("LEFT");
}
