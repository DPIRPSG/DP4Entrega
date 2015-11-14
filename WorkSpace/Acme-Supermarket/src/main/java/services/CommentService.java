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

	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	public Comment create(){
		Comment result;
		
		result = new Comment();
		
		return result;
	}
	
	public void save(Comment comment){
		Assert.notNull(comment);

		commentRepository.save(comment);
	}
	
	public void delete(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		commentRepository.delete(comment.getId());
	}
	
	//Other business methods -------------------------------------------------

	public Collection<Comment> findAllByItem(Item item){
		Assert.notNull(item);
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByItemId(item.getId());
		
		return result;
	}
	
}
