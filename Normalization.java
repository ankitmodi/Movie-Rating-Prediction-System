package movielensedatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import weka.core.matrix.Maths;

public class Normalization {

	public static void main(String args[])
	{
		BufferedReader br = null;
		double rating_mean[]=new double[1000];
		double count[]=new double[1000];
		double stand_dev[]= new double[1000];

		
		for(int i=0;i<1000;i++)
		{
			stand_dev[i]=0;
			rating_mean[i]=0;
			count[i]=0;
		}
		
				
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("u.data"));
 
			while ((sCurrentLine = br.readLine()) != null) 
			{
				String[] yoyo = sCurrentLine.split("	");
				
            	int uid = Integer.parseInt(yoyo[0]);
            	
            	count[uid]+=1;
            	
            	rating_mean[uid]+= Integer.parseInt(yoyo[2]);
            	
			}
			
			for(int i=1;i<1000;i++)
			{
				rating_mean[i]/=count[i];
			}
			
			BufferedReader br1 = new BufferedReader(new FileReader("u.data"));
			 
			while ((sCurrentLine = br1.readLine()) != null) 
			{
				String[] yoyo = sCurrentLine.split("	");
				
                                int uid = Integer.parseInt(yoyo[0]);
            	
                                stand_dev[uid] += ( Integer.parseInt(yoyo[2])-rating_mean[uid])*(Integer.parseInt(yoyo[2])-rating_mean[uid]);
            	
			}
			
			//for(int i=0;i<1000;i++)
            //	System.out.println(" "+stand_dev[i]);
			
			for(int i=0;i<1000;i++)
			{
				stand_dev[i]/=count[i];
				stand_dev[i]= Math.sqrt(stand_dev[i]);
				System.out.println(" "+stand_dev[i]);
			}	
                        
			File file = new File("normalized_u.data");
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file);
            	
			BufferedWriter bw= new BufferedWriter(fw);
			
            
            	
            BufferedReader br3 = new BufferedReader(new FileReader("u.data"));
            
		while ((sCurrentLine = br3.readLine()) != null) 
		{
                    //System.out.pri("hi");
                    String[] yoyo = sCurrentLine.split("\t");
				
                    int uid = Integer.parseInt(yoyo[0]);
                    
                    System.out.println("Orignal"+yoyo[2]);
                    double rating= Integer.parseInt(yoyo[2]);
            	
                    rating= (rating-rating_mean[uid])/stand_dev[uid];
                    double orig=rating*stand_dev[uid]+rating_mean[uid];
                    
                    System.out.println("Orignal"+yoyo[2]);
                    
                    
                    double denor=(rating+1)*2+1;
                 
                    if(denor<1)
                        denor=1;
                    else if(denor>5)
                         denor=5;
                    
                    
            	
                    String new_rate=yoyo[0]+"\t"+yoyo[1]+"\t"+denor+"\t"+yoyo[3]+"\t"+"\tend";
            	
                    bw.write(""+new_rate);
                    
                    bw.newLine();
                    
            	}
                
                bw.flush();
			
                bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
 		
		
	}
	
}
