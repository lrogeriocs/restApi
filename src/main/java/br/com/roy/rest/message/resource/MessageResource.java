package br.com.roy.rest.message.resource;



import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.roy.rest.message.model.Message;
import br.com.roy.rest.message.resource.beans.MessageFilterBean;
import br.com.roy.rest.message.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_XML)
@Produces (MediaType.APPLICATION_XML)
public class MessageResource {
 
	private MessageService messageService;
	
	public MessageResource() {
	   this.messageService = new MessageService();
	}
	
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		
		if(filterBean.getYear()>0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getSize()>=0 && filterBean.getStart()>=0){
			return messageService.getMessagePaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
		
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessages(@PathParam("messageId")long messageId){
		return messageService.getMessage(messageId);
	}
	
	
	@POST 
	public Message addMessage(Message message){
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId, Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("{messageId}")
	public void removeMessage(@PathParam("messageId")long messageId){
		messageService.removeMessage(messageId);
	}
	
	
}
