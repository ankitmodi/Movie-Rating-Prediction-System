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
 * 
 * @author Ankit Modi
 * This is not a comment.
 */
public class MovieLens 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       
        try{
           
        	PrintWriter writer = new PrintWriter("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\Pred.txt");
        	double alpha_user = 1.0;
        	double beta_movie = 1-alpha_user;
        	int idea=0;
        	double sim_thresh = 0.85;
        	double msim_thresh = 0.85;
        	double rating_thresh = 0.0;
        	BufferedReader modi = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\test1.txt"));
            String liner;
            while ((liner = modi.readLine()) != null) {
            
            	idea++;
            	System.out.print(idea);
            	
            	String[] mstr = liner.split("	");
	        	//Scanner input = new Scanner(System.in);
	            //System.out.print("User's name: ");
	            //String uname = input.nextLine();
	        	//String uname = "Ankit";
            	String uname = "modi";
	            
	            
	            //System.out.print("User's age: ");
	            //String age = input.nextLine();
            	String age = mstr[1];
	            double uage = Double.parseDouble(age);
	            
	            double uclass;
	            if(uage>=18 && uage<25) {
	            	uclass = 2;
	            }
	            else if(uage>=25 && uage<30) {
	            	uclass = 3;
	            }
	            else if(uage>=30 && uage<35) {
	            	uclass = 4;
	            }
	            else if(uage>=35 && uage<50) {
	            	uclass = 5;
	            }
	            else if(uage>=50) {
	            	uclass = 6;
	            }
	            else  {
	            	uclass = 1;
	            }
	            
	            double gender_factor = 4;
	            double ugender;
	            //System.out.print("User's gender (M or F): ");
	            //String gender = input.nextLine();
	            String gender = mstr[2];
	            if(gender.equals("M")) {
	            	ugender = 1;
	            }
	            else {
	            	ugender = 0;
	            }
	            
	            //System.out.print("User's occupation: ");
	            //String uoccupation = input.nextLine();
	            String uoccupation = mstr[3];
	            //String uoccupation = "technician";
	            //double occuweight = 4;
	            double occuweight = 1;
	            double uoccu = occuweight;
	            
	            //System.out.print("User's pincode: ");
	            //String pincode = input.nextLine();
	            String pincode = mstr[4];
	            String upincode = pincode.substring(0,1);
	            //System.out.print(upincode);
	            //double pinweight = 4;
	            double pinweight = 1;
	            double upin = pinweight;
	           
	            
	            //Vector test_user = new Vector();
	            double[] test_user = new double[4];
	            //test_user.addElement(new Double(uage));
	            //test_user.addElement(new Double(ugender));
	            //test_user[0] = uclass;
	            test_user[0] = 1;
	            //test_user[1] = gender_factor*ugender;
	            test_user[1] = ugender;
	            test_user[2] = uoccu;
	            test_user[3] = upin;
	            
	            Vector train_uid = new Vector();
	            Vector train_usim = new Vector();
	            
	            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\newuser.txt"));
	            
	            String line;
	            //int counter = 0;
	            //System.out.println("begin");
	            while ((line = br.readLine()) != null) {
	            	
	            	//Vector train_user = new Vector();
	            	double[] train_user = new double[4];
	            	String[] col = line.split("	");
	            	//System.out.println(col[0]);
	            	//System.out.println("begin");
	            	
	            	//double tid = Integer.parseInt(col[0]);
	            	//double tage = Double.parseDouble(col[1]);
	            	double tclass = Double.parseDouble(col[3]);
	                double tgender = Double.parseDouble(col[2]);
	                
	                double toccu = 0;
	                String toccupation = col[4];
	                if(uoccupation.equals(toccupation)) {
	                	toccu = occuweight;
	                }
	                //System.out.println("begin");
	                double tpin = 0;
	                String tpincode = col[5];
	                if(upincode.equals(tpincode)) {
	                	//System.out.println("begin");
	                	tpin = pinweight;
	                }
	                
	                //System.out.println("begin");
	                    
	                //train_user.addElement(new Double(tage));
	                //train_user.addElement(new Double(tgender));
	                if(uclass==tclass){
	                	train_user[0]=1;
	                }
	                else{
	                	train_user[0]=0;
	                }
	                //train_user[0] = tclass;
	                //train_user[1] = gender_factor * tgender;
	                train_user[1] = tgender;
	                train_user[2] = toccu;
	                train_user[3] = tpin;
	                
	                //System.out.println("begin");
	            	double sim = cosineSimilarity(test_user, train_user);
	            	//System.out.println(sim);
	            		
	            	if (sim > sim_thresh) {
	            			
	            		train_uid.addElement(new String(col[0]));
	            		train_usim.addElement(new Double(sim));
	            		//counter++ ;
	            		
	            	}
	            	            	
	            }
	            /*
	            int size = train_uid.size();
	        	for(int i=0; i<size;i++) {
	        		
	        		System.out.println(train_uid.get(i) + "	" + train_usim.get(i));
	        	}
	        	System.out.println(counter);
	        	*/
	            br.close();
	            
	            double yearweight = 1;
	            double[] test_movie = new double[20];
	            String umname="";
	        	String umyear="";
	        	String uyear="";
	        	
	        	//System.out.println(" ");
	        	//System.out.println(" ");
	            //System.out.println("Enter movie_id: ");
	            //String umov = input.nextLine();
	        	String umov = mstr[5];
	            //input.close();
	            
	            BufferedReader br5 = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\newitem.txt"));
	            String line_input;
	            while ((line_input = br5.readLine()) != null){
	            	String[] str = line_input.split("	");
	            	
	            	if(umov.equals(str[0])) {
	            		umname = str[1];
	            		umyear = str[2];
	            		uyear  = umyear.substring(0,3);
	            		test_movie[0] = yearweight;
	                    test_movie[1] = Double.parseDouble(str[3]);
	                    test_movie[2] = Double.parseDouble(str[4]);
	                    test_movie[3] = Double.parseDouble(str[5]);
	                    test_movie[4] = Double.parseDouble(str[6]);
	                    test_movie[5] = Double.parseDouble(str[7]);
	                    test_movie[6] = Double.parseDouble(str[8]);
	                    test_movie[7] = Double.parseDouble(str[9]);
	                    test_movie[8] = Double.parseDouble(str[10]);
	                    test_movie[9] = Double.parseDouble(str[11]);
	                    test_movie[10] = Double.parseDouble(str[12]);
	                    test_movie[11] = Double.parseDouble(str[13]);
	                    test_movie[12] = Double.parseDouble(str[14]);
	                    test_movie[13] = Double.parseDouble(str[15]);
	                    test_movie[14] = Double.parseDouble(str[16]);
	                    test_movie[15] = Double.parseDouble(str[17]);
	                    test_movie[16] = Double.parseDouble(str[18]);
	                    test_movie[17] = Double.parseDouble(str[19]);
	                    test_movie[18] = Double.parseDouble(str[20]);
	                    test_movie[19] = Double.parseDouble(str[21]);
	                    
	                    break;
	            	}
	            	
	            	
	            }
	            br5.close();
	            if(umname.equals("")) {
	            	
	           		 System.out.println("Given movie_id not found in the dataset :( ");
	            }
	            
	            
	            Vector train_mid = new Vector();
	            Vector train_msim = new Vector();
	            BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\newitem.txt"));
	            String line2;
	            //int counter2 = 0;
	            while ((line2 = br2.readLine()) != null){
	            	
	            	double[] train_movie = new double[20];
	            	String[] coz = line2.split("	");
	            	
	            	train_movie[0]=0;
	            	if(coz[2].substring(0,3).equals(uyear)) {
	            	
	            		train_movie[0]=1;
	            	
	            	}
	            	//System.out.println(coz[2]);
	            	//System.out.println(coz[2].substring(0,3));
	            	//System.out.println(train_movie[0]);
	            	train_movie[1]=Double.parseDouble(coz[3]);train_movie[2]=Double.parseDouble(coz[4]);
	            	train_movie[3]=Double.parseDouble(coz[5]);train_movie[4]=Double.parseDouble(coz[6]);
	            	train_movie[5]=Double.parseDouble(coz[7]);train_movie[6]=Double.parseDouble(coz[8]);
	            	train_movie[7]=Double.parseDouble(coz[9]);train_movie[8]=Double.parseDouble(coz[10]);
	            	train_movie[9]=Double.parseDouble(coz[11]);train_movie[10]=Double.parseDouble(coz[12]);
	            	train_movie[11]=Double.parseDouble(coz[13]);train_movie[12]=Double.parseDouble(coz[14]);
	            	train_movie[13]=Double.parseDouble(coz[15]);train_movie[14]=Double.parseDouble(coz[16]);
	            	train_movie[15]=Double.parseDouble(coz[17]);train_movie[16]=Double.parseDouble(coz[18]);
	            	train_movie[17]=Double.parseDouble(coz[19]);train_movie[18]=Double.parseDouble(coz[20]);
	            	train_movie[19]=Double.parseDouble(coz[21]);
	            	
	            	double msim = cosineSimilarity(test_movie, train_movie);
	            	//System.out.println(msim);
	            	if (msim > msim_thresh) {
	        			
	            		train_mid.addElement(new String(coz[0]));
	            		train_msim.addElement(new Double(msim));
	            		//counter2++ ;
	            		
	            	}
	            	
	            	/*
	            	int size2 = train_mid.size();
	            	for(int i=0; i<size2;i++) {
	            		
	            		System.out.println(train_mid.get(i) + "	" + train_msim.get(i));
	            	}
	            	System.out.println(counter2);
	            	*/
	                
	            	
	            }
	            br2.close();
	            
	            
	            
	            
	            BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\Ankit\\workspace\\ML\\src\\MLpackage\\u.data"));
	            String line3;
	            //int counter3 = 0;
	            
	            double rating_num = 0;
	            double rating_den = 0;
	            while ((line3 = br3.readLine()) != null){
	            	
	            	String[] yoyo = line3.split("	");
	            	int user_idx = train_uid.indexOf(yoyo[0]);
	
	            	if(user_idx != -1) {
	            		
	            		int movie_idx = train_mid.indexOf(yoyo[1]);
	
	            		if(movie_idx != -1){
	            			
	            			double user_val = ((Double) train_usim.get(user_idx)).doubleValue();
	            			double movie_val = ((Double) train_msim.get(movie_idx)).doubleValue(); 
	            			
	            			double rating_weight = (alpha_user * user_val) + (beta_movie * movie_val);
	            			//System.out.println(user_idx+" "+movie_idx+" "+user_val+" "+movie_val+" "+yoyo[2]+" "+rating_weight);
	            			
	            			rating_num += rating_weight * Double.parseDouble(yoyo[2]);
	            			rating_den += rating_weight;
	            			//System.out.println(rating_num/rating_den);
	            		}
	            	}
	            	
	            }
	            double rating = rating_num / rating_den;
	            if(rating == Double.NaN){
	            	rating = 1.0;
	            }
	            double rates = rating + 0.5;
	            long rate = (long) rates; 
	            
	            if(rating < rating_thresh) {
	            	System.out.println(uname + " will NOT see the movie " + umname + " (" + umyear + " ).");
	            }
	            else {
	            	System.out.println(uname + " will see the movie " + umname + "(" + umyear + " ).");
	            	System.out.println(uname + "'s rating would be: ");
	            	System.out.println(rating);
	            	System.out.println(rate);
	            	
	            }
	           
	           
            	writer.println(mstr[0] + "	" + mstr[5] + "	" + mstr[27] + "	" + rating );
            	
	            
	            br3.close();
	            
	        }
            modi.close();
            writer.close();
            System.out.println("end");
            
        	
        }
        
        catch(Exception e)
        {
        System.out.println(e);
        }
        
       
        
    
}
    
    
    public static double cosineSimilarity(double[] docVector1, double[] docVector2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < docVector1.length; i++) //docVector1 and docVector2 must be of same length
        {
            dotProduct += docVector1[i] * docVector2[i];  //a.b
            magnitude1 += Math.pow(docVector1[i], 2);  //(a^2)
            magnitude2 += Math.pow(docVector2[i], 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }

    
}
