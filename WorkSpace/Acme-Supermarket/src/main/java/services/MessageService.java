package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Folder;
import domain.Message;

import repositories.MessageRepository;

@Service
@Transactional 
public class MessageService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private MessageRepository messageRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public MessageService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Message create(){
		Message result;
		
		result = new Message();
		
		return result;	
	}
	
	public void save(Message message){
		Assert.notNull(message);
		
		messageRepository.save(message);
	}

	//Other business methods -------------------------------------------------
	
	public Collection<Message> findAllByFolder(Folder folder){
		Assert.notNull(folder);
		
		Collection<Message> result;
		
		result = messageRepository.findAllByFolderId(folder.getId());
		
		return result;
	}
}
