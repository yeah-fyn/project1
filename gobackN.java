import java.util.Random;

class Sender{
  int window_size;
  int no_frames;
  int next_send;
  int next_recieve=0;
  int sent = 0;
  boolean[] ack ;
  
  public Sender(int window_size,int no_frames){
    this.window_size = window_size;
    this.no_frames = no_frames;
    next_send = 0;
    ack  = new boolean[no_frames];
  }
  public void send_frame(){
    if(next_send>=no_frames){
      recieve_frame();
      return;
    }
    Random rand = new Random();
    System.out.println("Frame "+next_send+" sent..");
    sent++;
    try{
      if(sent<window_size)
      Thread.sleep(400);
      else
      Thread.sleep(1000);
    }catch(InterruptedException e){

    }
    if(rand.nextInt(10)<2){
      ack[next_send]=false;
    }else{
      ack[next_send]=true;
    }
    next_send++;
    if(sent>=window_size){
      recieve_frame();
    }

  }
  public void recieve_frame(){
    if(ack[next_recieve]==false){
      System.out.println("Acknowledge not recieved from "+next_recieve);
      next_send=next_recieve;
      sent=0;
      for(int i=0;i<window_size && (next_send+i)<no_frames;i++){
        ack[i+next_send]=false;
      }
    }
    else{
      System.out.println("Acknowledge recieved from "+next_recieve);
      next_recieve++;
      sent--;
    }
  }
  public void strat_sending(){
    while(next_recieve<no_frames){
      send_frame();
    }
  }

}
public class gobackN {
  public static void main(String args[]){
    Sender sender = new Sender(3,6);
    sender.strat_sending();
  }
}