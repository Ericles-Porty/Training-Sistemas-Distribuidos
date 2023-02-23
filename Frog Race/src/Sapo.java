class Sapo extends Thread{
    String name;
    int jumps;
    int distance;
    static final int MAX_JUMP = 10;
    static final int MAX_DISTANCE = 100;
    
    public Sapo(String name){
        this.name = name;
        this.jumps = 0;
        this.distance = 0;
    }
    
    public void run(){
        while (this.distance < MAX_DISTANCE){
            try {
                jump();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Main.posicoes.add(this.name);
        System.out.println("=-= O sapo " + name + " chegou em " + (Main.posicoes.indexOf(this.name) + 1) + "º lugar! =-=");
    }

    public void jump () throws InterruptedException{  
        int d = (int)(Math.random() * MAX_JUMP);    
        this.distance += d;
        this.jumps++;
        System.out.println("O sapo " + name + " pulou " + d + " metros |" + " Distância Atual: " + this.distance + " metros!");
        Thread.sleep(10);
    }
}
