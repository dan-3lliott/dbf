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

int serial = 0;

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(10);
}

void loop() {
  if (Serial.available() > 0) {
    serial = Serial.parseInt();
    updatePos();
  }
}

void updatePos() {
  Serial.print("current pos is ");
  Serial.print(pos);
  Serial.print(", going to ");
  Serial.println(serial);
  if (pos > serial) {
    left(pos - serial);
    Serial.print("shifted left ");
    Serial.println((pos - serial));
  }
  else if (pos < serial) {
    right(serial - pos);
    Serial.print("shifted right ");
    Serial.println((serial - pos));
  }
  pos = serial;
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
