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
	
	@Autowired
	private FolderService folderService;

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
	
	public Message save(Message message){
		Assert.isTrue(!this.exists(message));
		
		Message result;
		
		System.out.println("El método save en MessageService no tiene en cuenta la concurrencia");
		result = messageRepository.save(message);
		
		return result;
	}
	
	public Message update(Message message){
		Assert.isTrue(this.exists(message));
		
		Message result;
		
		System.out.println("El método update en MessageService no tiene en cuenta la concurrencia");
		result = messageRepository.save(message);
		
		return result;	}
	
	public boolean exists(Message message){
		Assert.isNull(message);
		
		boolean result;
		
		result = messageRepository.exists(message.getId());
		
		return result;
	}

	//Other business methods -------------------------------------------------
	
/*
 * Sería interesante buscar quien lo envía y quienes lo reciben	
 */
	public Collection<Message> findAllByFolder(Folder folder){
		Assert.isTrue(folderService.exists(folder));
		
		Collection<Message> result;
		
		result = messageRepository.findAllByFolderId(folder.getId());
		
		return result;
	}
}
