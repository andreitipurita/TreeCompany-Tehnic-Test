package be.tree.JsonConverter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.tree.domain.ChatInfo;



public class JsonConverterApplication {

	@SuppressWarnings({ "null", "deprecation" })
	public static void main(String[] args) throws IOException{
		
		
		  Path start = Paths.get("/slack-dataset/data"); 
		  Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE);
		
		    List<String> collect = stream
		        .map(String::valueOf)
		        .sorted()
		        .filter(x-> x.contains(".json"))
		        .collect(Collectors.toList());
		    
		    //collect.forEach(System.out::println);
		    
		    
		    ObjectMapper objectMapper = new ObjectMapper(); 
		    List<ChatInfo> chatInfoList= null;
		    List<ChatInfo> chatInfoListAll = new ArrayList<ChatInfo>();
		    int i=0;
		    String channelName;
		    for (String path: collect)
		    {
		    	i++;
		     chatInfoList = objectMapper.readValue(new File(path), new TypeReference<List<ChatInfo>>() {} );
		     String[] channelTemporary =path.split("/");
		     channelName=channelTemporary[3];
		     
		    for(ChatInfo chatInfo: chatInfoList)
		    {	
		    	String ts1=chatInfo.getTs().toString().substring(0, 10);
			    Timestamp timestamp = new Timestamp(Long.parseLong(ts1));
			    Date date = new Date(timestamp.getTime());
			    chatInfo.setChannel(channelName);
			    chatInfo.setYear(date.getYear());
			    chatInfo.setMonth(date.getMonth());
			    chatInfo.setDay(date.getDay());
			    chatInfo.setHour(date.getHours());
			    chatInfo.setMinute(date.getMinutes());
			    chatInfo.setSecond(date.getSeconds());

		    }
		    chatInfoListAll.addAll(chatInfoList);
		    }
		    stream.close();
		    System.out.println("Number of files ="+i);
		    System.out.println("Number of objects="+chatInfoListAll.size());
		    sendToCSVFile(chatInfoListAll);
		    
		    
	}
	
	
	
	public static void sendToCSVFile(List<ChatInfo> chatInfoList) throws IOException
	{
		
		PrintWriter file =  new PrintWriter("/slack-dataset/chat1.csv");
		
		file.println("Channel,User,Ts,Year,Month,Day,Hour,Minute,Second");
		
	    for(ChatInfo response: chatInfoList)
	   {

	    	file.println(response.getChannel()+","+response.getUser()+","+response.getTs()+","+String.valueOf(response.getYear())+","+String.valueOf(response.getMonth())+","+String.valueOf(response.getDay())+","+String.valueOf(response.getHour())+","+String.valueOf(response.getMinute())+","+String.valueOf(response.getSecond()));
	    }
	    file.flush();
	    file.close();	
	}
	
	@SuppressWarnings("deprecation")
	public static ChatInfo Populate(ChatInfo chatInfo) 
	{

	    String ts1=chatInfo.getTs().toString().substring(0, 10);
	    Timestamp timestamp = new Timestamp(Long.parseLong(ts1));
	    Date date = new Date(timestamp.getTime());
	    chatInfo.setYear(date.getYear());
	    chatInfo.setMonth(date.getMonth());
	    chatInfo.setDay(date.getDay());
	    chatInfo.setHour(date.getHours());
	    chatInfo.setMinute(date.getMinutes());
	    chatInfo.setSecond(date.getSeconds());
	    return chatInfo;
		
	}


}
