package br.com.roy.rest.message.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.roy.rest.message.database.DataBase;
import br.com.roy.rest.message.model.Message;

public class MessageService {

	
	private Map<Long, Message> messages = DataBase.getMessages();
	
	public MessageService() {
	   messages.put(1L,  new Message(1L, "MESSAGE 1", new Date(), "Roy"));
	   messages.put(2L,  new Message(1L, "MESSAGE 2", new Date(), "Roy"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
        List<Message> listMessageForYear= new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for(Message message : messages.values()){
        	c.setTime(message.getCreated());
        	if(c.get(Calendar.YEAR)== year){
        		listMessageForYear.add(message);
        	}
        }
        return listMessageForYear;
	}
	
	public List<Message> getMessagePaginated(int start,int size){
		
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		return list.subList(start, start +size);
		
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	
	public Message updateMessage(Message message){
		if(message.getId()<=0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
		
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
