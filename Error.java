/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MLpackage;

import java.io.BufferedReader;
import java.io.PrintWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 *
 * @author Ankit Modi
 * This is not a comment.
 */
public class Error 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       
        try{
           
        	 BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\Prediction_main.txt"));
	            
        	    double i =0;
        	    double error =0.0;
        	    //double rms_error = 0;
	            String line;
	            //int counter = 0;
	            //System.out.println("begin");
	            while ((line = br.readLine()) != null) {
	            	
	    
	            	String[] col = line.split("	");
	            	double actual = Double.parseDouble(col[2]);
	            	double predicted = Double.parseDouble(col[3]);
	            	double err;
	            	//double rms;
	            	if(actual > predicted){
	            		 err = actual - predicted;
	            	}
	            	else{
	            		 err = predicted - actual;
	            	}
	            	//rms = err * err;
	            	//rms_error += rms;
	            	error += err ;
	            	i++ ;
	            	
	            	//if(i>=90000)
	            		//break;
	            	//System.out.println(err);
	            	//System.out.println(error);
	            	//System.out.println(i);
	            	
	            	
	            }
	           
	            double mean_abs_error = error/i ;
	            //double mean_sqr = rms_error / i;
	            //double root_mean_sqr = Math.sqrt(mean_sqr);
	            
	            System.out.println("Mean Absolute Error:	" +  mean_abs_error);
	            br.close();
        }
        
        catch(Exception e)
        {
        System.out.println(e);
        }
        
       
        
    
}


    
}
