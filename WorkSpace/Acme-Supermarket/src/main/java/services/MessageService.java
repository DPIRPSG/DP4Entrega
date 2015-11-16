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
	
	/** 
	 * Devuelve Message preparado para ser modificado. Necesita usar save para que persista en la base de datos
	 */	
	//req: 24.2
	public Message create(){
		Message result;
		
		result = new Message();
		
		return result;	
	}
	
	/**
	 * Guarda un message creado o modificado
	 */
	//req: 24.2
	public void save(Message message){
		Assert.notNull(message);
		
		messageRepository.save(message);
	}

	//Other business methods -------------------------------------------------
	
	/**
	 * Devuelve todos los mensajes contenidos en una determinada carpeta
	 */
	//req: 24.1
	public Collection<Message> findAllByFolder(Folder folder){
		Assert.notNull(folder);
		
		Collection<Message> result;
		
		result = messageRepository.findAllByFolderId(folder.getId());
		
		return result;
	}
	
	/**
	 * Borra un mensaje de una carpeta
	 */
	//req: 24.2
	public void deleteMessageFromFolder(Message message, Folder folder){
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		
		Collection<Message> messagesInFolder;
		
		messagesInFolder = this.findAllByFolder(folder);
		messagesInFolder.remove(message);
		folder.setMessages(messagesInFolder);
		
		folderService.save(folder);
	}
}
