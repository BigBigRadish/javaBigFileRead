package com.example.readfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ReadFileJavaApplicationFileInputStream {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		File f= new File("E://workplace/BigFileRead/src/data/itcont.txt");
		try {
			FileInputStream inputstream=new FileInputStream(f);
			Scanner sc =new Scanner(inputstream,"utf-8");
			//得到总行数
			Instant lineCountStart=Instant.now();//当前时间
			int lines=0;
			Instant nameStart=Instant.now();
			ArrayList<String> names=new ArrayList<String>();
			//得到第432个和第43243个名字
			ArrayList<Integer> indexs=new ArrayList<>();
			
			indexs.add(1);
			indexs.add(433);
			indexs.add(43244);
			//计算每个月的捐赠量
			Instant donationsStart=Instant.now();
			ArrayList<String> dates=new ArrayList<String>();
			//计算每一个名字的出现次数
			Instant commonNamesStart=Instant.now();
			ArrayList<String> firstNames=new ArrayList<String>();
			System.out.println("start read file using input stream!");
			while(sc.hasNext()){
			String line =sc.nextLine();
			lines++;
			//得到所有的名字
			String array1[]=line.split("\\s*\\|s*");
			String name=array1[7];
			names.add(name);
			if(indexs.contains(lines)){
				System.out.println("names:"+names.get(lines-1)+"at index: "+(lines-1));
				
			}
			if (name.contains(", ")){
				String array2[]=(name.split(","));
				String firstHalfOfName=array2[1].trim();
				if(firstHalfOfName!="undefined"||!firstHalfOfName.isEmpty()){
					if(firstHalfOfName.contains(" ")){
						String array3[]=(name.split(" "));
						String firstName=array3[0].trim();
						firstNames.add(firstName);
						
					}
					else{
						firstNames.add(firstHalfOfName);
					}
					
				}
			}
			String rawDate=array1[4];
			String year=rawDate.substring(0,4);
			String month=rawDate.substring(4,6);
			String formatDate=month+"-"+year;
			dates.add(formatDate);
			}
			
			Instant namesEnd=Instant.now();
			long timeElapseNames=Duration.between(nameStart, namesEnd).toMillis();
			System.out.println("names time :"+timeElapseNames+"ms");
			System.out.println("total lines :"+lines);
			
			Instant lineCountsEnd=Instant.now();
			long timeElapselineCounts=Duration.between(lineCountStart, lineCountsEnd).toMillis();
			System.out.println("lines time :"+timeElapseNames+"ms");
			
			HashMap<String, Integer> dateMap=new HashMap<String, Integer>();
			for(String date:dates){
				Integer count =dateMap.get(date);
				if(count==null){
					dateMap.put(date, 1);
				}
				else{
					dateMap.put(date, count+1);
				}
			}
			for (Map.Entry<String, Integer> entry :dateMap.entrySet()){
				String key=entry.getKey();
				Integer value=entry.getValue();
				System.out.println("Donations per month and year: "+key+" and donation count: "+value);
			}
			Instant donationEnd=Instant.now();
			long timeElapsedDonations=Duration.between(donationsStart, donationEnd).toMillis();
			System.out.println("Donation time: "+timeElapsedDonations+" ms");
				
			HashMap<String, Integer> map=new HashMap<String, Integer>();
			for(String name : firstNames){
				Integer count =dateMap.get(name);
				if(count==null){
					map.put(name, 1);
				}
				else{
					map.put(name, count+1);
				}
			}
			LinkedList<Entry<String, Integer>> list =new LinkedList<>(map.entrySet());
			
			Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					// TODO Auto-generated method stub
					return (o2.getValue()).compareTo(o1.getValue());
				}
			});
			
			System.out.println("The most Common first name is: "+list.get(0).getKey()+" and it occurs: "+list.get(0).getValue()+" time.");
			Instant commonNameEnd=Instant.now();
			long timeElapseCommonName =Duration.between(commonNamesStart, commonNameEnd).toMillis();
			System.out.println("most common name time: "+timeElapseCommonName+" ms");
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		

	}

}
