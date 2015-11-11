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
		
		result = new Comment();
		
		return result;
	}
	
	// Save solo debe usarse para guardar el objeto por primera vez	
	public void save(Comment comment){
		Assert.isTrue(!this.exists(comment));

		System.out.println("El método save en CommentService no tiene en cuenta la concurrencia");
		commentRepository.save(comment);
	}
	
	public void delete(Comment comment){
		Assert.isTrue(this.exists(comment));

		commentRepository.delete(comment.getId());
	}
	
	//Other business methods -------------------------------------------------

	public Collection<Comment> findAllByItem(Item item){
		Assert.isTrue(itemService.exists(item));
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByItemId(item.getId());
		
		return result;
	}
	
}
