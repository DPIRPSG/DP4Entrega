package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Item;

import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {
 	//Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public boolean exists(Comment comment){
		Assert.isNull(comment);
		
		boolean result;
		
		result = commentRepository.exists(comment.getId());
		
		return result;
	}
	
	public Comment create(){
		Comment result;
		
		result = null;
		System.out.println("El método create de CommentService no está finalizado");
		
		return result;
	}
	
	public void save(Comment comment){
		Assert.isTrue(this.exists(comment));
		
		commentRepository.save(comment);
	}
	
	//Other business methods -------------------------------------------------

	public Collection<Comment> findAllByItem(Item item){
		Assert.isNull(item);
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByItemId(item.getId());
		
		return result;
	}
	
}
