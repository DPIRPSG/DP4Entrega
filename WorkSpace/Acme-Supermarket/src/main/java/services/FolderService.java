package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;

import repositories.FolderRepository;

@Service
@Transactional 
public class FolderService {
 	//Managed repository -----------------------------------------------------
	
	@Autowired
	private FolderRepository folderRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	//Constructors -----------------------------------------------------------
	
	public FolderService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public Folder create(){
		Folder result;
		
		result = new Folder();
		
		return result;
	}
	
	public void save(Folder folder){
		Assert.notNull(folder);
		
		folderRepository.save(folder);
	}
	
	public void delete(Folder folder){
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		
		// Si es del sistema no debe poder borrarse
		Assert.isTrue(!folder.getIsSystem());
		
		folderRepository.delete(folder);
	}
	
	//Other business methods -------------------------------------------------
	
	public Collection<Folder> findAllByActor(Actor actor){
		Assert.notNull(actor);
		
		Collection<Folder> result;
		
		result = folderRepository.findAllByActorId(actor.getId());
		
		return result;
	}
}
