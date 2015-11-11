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
	
	public Folder save(Folder folder){
		Assert.isTrue(!this.exists(folder));
		
		Folder result;
		
		System.out.println("El método save en FolderService no comprueba la concurrencia");
		result = folderRepository.save(folder);
		
		return result;
	}
	
	public Folder update(Folder folder){
		Assert.isTrue(this.exists(folder));
		
		Folder result;
		
		System.out.println("El método update en FolderService no comprueba la concurrencia");
		result = folderRepository.save(folder);
		
		return result;
	}
	
	public void delete(Folder folder){
		Assert.isTrue(this.exists(folder));
		
		System.out.println("El método delete en FolderService NO ESTA implementado");
	}
	
	public boolean exists(Folder folder){
		Assert.isNull(folder);
		
		boolean result;
		
		result = folderRepository.exists(folder.getId());
		
		return result;
	}
	
	//Other business methods -------------------------------------------------
	
	public Collection<Folder> findAllByActor(Actor actor){
		Assert.isTrue(actorService.exists(actor));
		
		Collection<Folder> result;
		
		result = folderRepository.findAllByActorId();
		
		return result;
	}
}
