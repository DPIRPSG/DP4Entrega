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
