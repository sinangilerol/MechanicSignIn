
void setup() {
  pinMode(2,INPUT_PULLUP);
  pinMode(3,INPUT_PULLUP);
  pinMode(4,INPUT_PULLUP);
  pinMode(5,INPUT_PULLUP);
  
  Serial.begin(9600);
  
}

void loop() {
  int sonuc=tara();

  if(sonuc > 0){
  
   
    Serial.print(sonuc);
    delay(6000);
    
    }
  
}

int tara(){
  int sonuc=0;
  if(digitalRead(2) == LOW){
    sonuc=1;
    
    }
  if(digitalRead(3) == LOW){
    sonuc=2;
    
    }
    if(digitalRead(4) == LOW){
    sonuc=3;
     
    }
  if(digitalRead(5) == LOW){
    sonuc=4;
     
    }

    return sonuc;
      
  }
