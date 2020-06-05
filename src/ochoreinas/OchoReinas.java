
package ochoreinas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class OchoReinas extends JFrame implements ActionListener{

    JButton botonCombinar = new JButton("Generar combinacion");
    
    JPanel tablero = new JPanel();
    
    String [][] matriz = new String [8][8];
    
    ArrayList reinas = new ArrayList();
    ArrayList posX = new ArrayList();
    ArrayList posY = new ArrayList();
    
    public static void main(String[] args) {
        
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
        c.add(tablero);
        
        botonCombinar.addActionListener(this);
        botonCombinar.setBounds(100,20,500,30);
        botonCombinar.setBackground(Color.ORANGE);
    
        tablero.setBounds(100, 100, 500, 500);
        tablero.setBackground(Color.WHITE);
        
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
        
        if(e.getSource() == botonCombinar){
        
            for(int i = 0; i<8; i++){
            
                reinas.add("R");
                
                for(int j=0; j<8; j++){
                
                    matriz[i][j]="0";
                    
                    System.out.print(matriz[i][j] + ", ");
                    
                }
                
                System.out.println(" ");
                
            }
            
            int x = 0, y = 0;
            
            while(reinas.size() != 0){
                
                if( y < 8 && matriz[x][y].equals("0") ){
                
                    System.out.println(" ");
                    
                    matriz[x][y] = "R";
                    
                    reinas.remove(reinas.size()-1);
                    
                    posX.add(x);
                    posY.add(y);
                    
                    setVerticales("1", x, y);
                    setHorizontales("1", x, y);
                    setDiagonales("1", x, y);
                    
                    x++;
                    y = 0;
                    
                    for(int i = 0; i<8; i++){

                        for(int j=0; j<8; j++){

                            System.out.print(matriz[i][j] + ", ");

                        }

                        System.out.println(" ");

                    }
                    
                } else {
                    
                    y++;
                    
                }
                     
                if(y >= 8){
                
                    System.out.println(" ");
                    
                    x = (int) posX.get(posX.size() - 1);
                    y = (int) posY.get(posY.size() - 1);
                    
                    posX.remove(posX.size() - 1);
                    posY.remove(posY.size() - 1);
                    
                    reinas.add("R");
                    
                    matriz[x][y] = "0";
                    
                    setVerticales("-1", x, y);
                    setHorizontales("-1", x, y);
                    setDiagonales("-1", x, y);
                    
                    y++;
                
                    for(int i = 0; i<8; i++){

                        for(int j=0; j<8; j++){

                            System.out.print(matriz[i][j] + ", ");

                        }

                        System.out.println(" ");

                    }
                    
                }
                
            }
            
//            System.out.println(" ");
//                
//            for(int i = 0; i<8; i++){
//
//                for(int j=0; j<8; j++){
//
//                    System.out.print(matriz[i][j] + ", ");
//
//                }
//
//                System.out.println(" ");
//
//            }
            
        }
        
    }
    
}
