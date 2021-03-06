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
	
	/**
	 * Crea un comentario. Necesita ser guardado. Usar desde createByItem.
	 */
	//ref: 23.2
	private Comment create(){
		Comment result;
		
		result = new Comment();
		
		return result;
	}
	
	/**
	 * Guarda un comment creado o modificado
	 */
	//req: 23.2
	public void save(Comment comment){
		Assert.notNull(comment);

		commentRepository.save(comment);
	}

	
	/**
	 * Elimina un comment
	 */
	//req: 25.1
	public void delete(Comment comment){
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);

		commentRepository.delete(comment.getId());
	}
	
	//Other business methods -------------------------------------------------

	/**
	 * Lista todos los comentarios de un Item
	 */
	//ref: 23.1
	public Collection<Comment> findAllByItem(Item item){
		Assert.notNull(item);
		Assert.isTrue(item .getId() != 0);
		
		Collection<Comment> result;
		
		result = commentRepository.findAllByItemId(item.getId());
		
		return result;
	}
	
	/**
	 * Crear un comentario en relaci�n con un item. Debe de ser guardado con save.
	 */
	//ref: 23.2
	public Comment createByItem(Item item){
		Comment result;
		
		result = this.create();
		result.setItem(item);
		
		return result;
	}
}
