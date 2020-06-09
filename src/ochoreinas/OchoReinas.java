
package ochoreinas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class OchoReinas extends JFrame implements Runnable, ActionListener{

    JButton botonCombinar = new JButton("Generar combinacion");
    JButton botonStop = new JButton("STOP");
    JButton botonPlay = new JButton("PLAY");
    
    Thread hilo;
    
    JPanel tablero = new JPanel();
    
    String [][] matriz = new String [8][8];
    
    ArrayList reinas = new ArrayList();
    ArrayList posX = new ArrayList();
    ArrayList posY = new ArrayList();
    
    JLabel [][] cuadros = new JLabel[8][8]; 
    JLabel texto = new JLabel("Ingrese la columna donde desea ubicar la primera reina en la primera fila (0 - 7) : ");
    
    JTextField valor = new JTextField("0");
    JTextField solucion = new JTextField("");
    
    int x, y;
    
    boolean activo = true;
    
    public static void main(String[] args) throws InterruptedException  {
        
        OchoReinas reinas = new OchoReinas();
        reinas.setSize(700, 700);
        reinas.setBounds(300, 0, 700, 700);
        reinas.setTitle("Problema de las 8 reinas");
        reinas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        reinas.setVisible(true); 
        
    }

    OchoReinas(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(botonCombinar);
        c.add(botonStop);
        c.add(botonPlay);
        c.add(tablero);
        c.add(texto);
        c.add(valor);
        c.add(solucion);
        
        texto.setBounds(100, 5, 500, 20);
        valor.setBounds(560, 5, 40, 20);
        
        botonCombinar.addActionListener(this);
        botonCombinar.setBounds(100,30,300,30);
        botonCombinar.setBackground(Color.ORANGE);
    
        botonStop.addActionListener(this);
        botonStop.setBounds(530,30,70,30);
        botonStop.setBackground(Color.ORANGE);
        
        botonPlay.addActionListener(this);
        botonPlay.setBounds(430,30,70,30);
        botonPlay.setBackground(Color.ORANGE);
        
        tablero.setBounds(70, 70, 560, 560);
        tablero.setBackground(Color.WHITE);
        
        solucion.setBounds(70, 635, 560, 20);
        
    }
    
    void setVerticales(String estado, int x, int y){   
        
        for(int j = 0; j<8 ; j++){
            
            if(j == x){
            
                continue;
                
            } else {
        
                matriz[j][y] = Integer.toString(Integer.parseInt(matriz[j][y]) + Integer.parseInt(estado));
                
            }
            
        }
        
    }
    
    void setHorizontales(String estado, int x, int y){
        
        for(int i = 0; i<8 ; i++){
            
            if(i == y){
            
                continue;
                
            } else {
        
                matriz[x][i] = Integer.toString(Integer.parseInt(matriz[x][i]) + Integer.parseInt(estado));
                
            }
            
        }
        
    }
    
    void setDiagonales(String estado, int x, int y){
    
        // diagonal superior izquierda
        
        int tempX = x, tempY = y;
        
        while(tempX >= 0 && tempY >= 0 ){
        
            tempX--;
            tempY--;
            
            if(tempX >= 0 && tempY >= 0){

                matriz[tempX][tempY] = Integer.toString(Integer.parseInt(matriz[tempX][tempY]) + Integer.parseInt(estado));
                
            }
            
        }
       
        // diagonal superior derecha
        
        tempX = x;
        tempY = y;
        
        while(tempX >= 0 && tempY < 8 ){
        
            tempX--;
            tempY++;
            
            if(tempX >= 0 && tempY  < 8){

                matriz[tempX][tempY] = Integer.toString(Integer.parseInt(matriz[tempX][tempY]) + Integer.parseInt(estado));
                
            }
            
        }
        
        // diagonal inferior izquierda
        
        tempX = x;
        tempY = y;
        
        while(tempX < 8 && tempY >= 0 ){
        
            tempX++;
            tempY--;
            
            if(tempX < 8 && tempY >= 0){
            
                matriz[tempX][tempY] = Integer.toString(Integer.parseInt(matriz[tempX][tempY]) + Integer.parseInt(estado));
                
            }
            
        }
       
        // diagonal inferior derecha
        
        tempX = x;
        tempY = y;
        
        while(tempX < 8  && tempY < 8 ){
        
            tempX++;
            tempY++;
            
            if(tempX < 8 && tempY  < 8){
            
                matriz[tempX][tempY] = Integer.toString(Integer.parseInt(matriz[tempX][tempY]) + Integer.parseInt(estado));
                
            }
            
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == botonCombinar && Integer.parseInt(valor.getText()) > -1 && Integer.parseInt(valor.getText()) < 8){
            
            tablero.removeAll();
            hilo = new Thread( this );
            hilo.start();  
            
        } else if(e.getSource() == botonStop){
        
            hilo.suspend();
            
        } else if(e.getSource() == botonPlay){
            
            hilo.resume();
            
        }
        
    }

    @Override
    public void run() {
        
        try{
            
            ImageIcon cafeClaro = new ImageIcon(getClass().getResource("claro.png"));
            ImageIcon cafeOscuro = new ImageIcon(getClass().getResource("oscuro.png"));
            ImageIcon reina = new ImageIcon(getClass().getResource("reina.jpg"));

            Icon iconoEscalado;
            Image imgEscalada;
            
            for(int i = 0; i<8; i++){
            
                for(int j = 0; j<8; j++){
                
                    cuadros[i][j] = new JLabel();
                    
                    if( (i + j)%2 == 0 ){
                    
                        imgEscalada = cafeClaro.getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH);
                        iconoEscalado = new ImageIcon(imgEscalada);
                        
                        
                    } else {
                        
                        imgEscalada = cafeOscuro.getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH);
                        iconoEscalado = new ImageIcon(imgEscalada);
                        
                        
                    }
                    
                    cuadros[i][j].setBounds(70*j , 70*i, 70, 70);
                    cuadros[i][j].setIcon(iconoEscalado);
                    
                    tablero.add(cuadros[i][j]);
                    
                }
                
            }
            
            tablero.repaint();

            for(int i = 0; i<8; i++){
            
                reinas.add("R");
                
                for(int j=0; j<8; j++){
                
                    matriz[i][j]="0";
                    
                }

            }
            
            x = 0;

            y = Integer.parseInt(valor.getText());
            
            while(!reinas.isEmpty()){
                
                if( y < 8 && matriz[x][y].equals("0") ){

                    matriz[x][y] = "R";

                    reinas.remove(reinas.size()-1);

                    posX.add(x);
                    posY.add(y);

                    setVerticales("1", x, y);
                    setHorizontales("1", x, y);
                    setDiagonales("1", x, y);

                    imgEscalada = reina.getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH);
                    iconoEscalado = new ImageIcon(imgEscalada);
                    cuadros[x][y].setBounds(70*y , 70*x, 70, 70);
                    cuadros[x][y].setIcon(iconoEscalado);

                    tablero.add(cuadros[x][y]);  

                    tablero.paintImmediately(0,0,560,560);   

                    try {
                        Thread.sleep(100);                            
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OchoReinas.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    x++;
                    y = 0;

                } else {

                    y++;

                }

                if(y >= 8){

                    x = (int) posX.get(posX.size() - 1);
                    y = (int) posY.get(posY.size() - 1);

                    posX.remove(posX.size() - 1);
                    posY.remove(posY.size() - 1);

                    reinas.add("R");

                    matriz[x][y] = "0";

                    if( (x + y)%2 == 0 ){

                        imgEscalada = cafeClaro.getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH);
                        iconoEscalado = new ImageIcon(imgEscalada);

                    } else {

                        imgEscalada = cafeOscuro.getImage().getScaledInstance(70,70, Image.SCALE_SMOOTH);
                        iconoEscalado = new ImageIcon(imgEscalada);


                    }

                    cuadros[x][y].setBounds(70*y , 70*x , 70, 70);
                    cuadros[x][y].setIcon(iconoEscalado);

                    tablero.add(cuadros[x][y]);
                    tablero.paintImmediately(0,0,560,560); 

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OchoReinas.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    setVerticales("-1", x, y);
                    setHorizontales("-1", x, y);
                    setDiagonales("-1", x, y);

                    y++;

                    
                
                }
                
            }
 
            tablero.repaint();
            
            String result="";
            
            for(int i=0;i<8;i++){
            
                for(int j=0; j<8; j++){
                
                    if(matriz[i][j].equals("R")){
                    
                        result = result + "( " + Integer.toString(i) +", "+ Integer.toString(j) + ")";
                        
                    }
                    
                    
                }
                
            }
            
            solucion.setText(result);

        }catch(Exception e){
        
            System.out.print("No se que poner aca :D");
            
        }
        
    }
    
}
