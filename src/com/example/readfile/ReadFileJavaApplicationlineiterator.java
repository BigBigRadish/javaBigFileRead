package com.example.readfile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
public class ReadFileJavaApplicationlineiterator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f= new File("E://workplace/BigFileRead/src/data/itcont.txt");
		try {
			LineIterator it=FileUtils.lineIterator(f,"UTF-8");
			
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
			while(it.hasNext()){
			String line =it.nextLine();
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
/**
start read file using input stream!
names:PEREZ, JOHN Aat index: 0
names:MILESKI, JOHN Aat index: 432
names:COX, JOHN MARTINat index: 43243
names time :122819ms
total lines :18245416
lines time :122819ms
Donations per month and year: 02-2017 and donation count: 36367
Donations per month and year: 05-2018 and donation count: 535807
Donations per month and year: 05-2017 and donation count: 197636
Donations per month and year: 08-2017 and donation count: 298740
Donations per month and year: 08-2018 and donation count: 744574
Donations per month and year: 10-2018 and donation count: 3632780
Donations per month and year: 10-2017 and donation count: 585708
Donations per month and year: 02-2018 and donation count: 765317
Donations per month and year: 12-2018 and donation count: 1183294
Donations per month and year: 12-2017 and donation count: 486447
Donations per month and year: 09-2017 and donation count: 377544
Donations per month and year: 01-2019 and donation count: 855
Donations per month and year: 06-2017 and donation count: 180699
Donations per month and year: 09-2018 and donation count: 1277727
Donations per month and year: 01-2017 and donation count: 21
Donations per month and year: 01-2018 and donation count: 1252689
Donations per month and year: 03-2018 and donation count: 272344
Donations per month and year: 03-2017 and donation count: 78664
Donations per month and year: 06-2018 and donation count: 497210
Donations per month and year: 11-2017 and donation count: 346019
Donations per month and year: 11-2018 and donation count: 309158
Donations per month and year: 04-2017 and donation count: 254810
Donations per month and year: 04-2018 and donation count: 3034497
Donations per month and year: 07-2018 and donation count: 1128458
Donations per month and year: 07-2017 and donation count: 768051
Donation time: 123879 ms
The most Common first name is:  and it occurs: 1 time.
most common name time: 125618 ms
**/