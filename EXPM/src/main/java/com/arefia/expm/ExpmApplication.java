package com.arefia.expm;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ExpmApplication {
    private static JFrame expf;
    private static JProgressBar expb;
    
	public static void main(String[] args) {
//		SpringApplication.run(ExpmApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ExpmApplication.class);
        builder.headless(false).run(args);
		
		expf = new JFrame("ProgressBar demo"); 

        JPanel expp = new JPanel(); 
  
        expb = new JProgressBar(); 
  
        expb.setValue(0);   
        expb.setStringPainted(true); 
  
        expp.add(expb);
        
        JButton btn = new JButton("push me");
        
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//            }
//        });
  
        expp.add(btn);
        
        expf.add(expp); 
  
        expf.setSize(500, 500); 
        expf.setVisible(true); 
  
//        fill(); 
	}

	public static void fill() { 
        int i = 0;
        
        try { 
            while (i <= 100) {
                expb.setValue(i + 10); 
  
                Thread.sleep(1000); 
                i += 20; 
            } 
        } 
        catch (Exception e) {
        	System.out.println(e.getLocalizedMessage());
        } 
    } 
}
