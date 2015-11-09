package services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {
 	//Managed repository -----------------------------------------------------

	private CommentRepository commentRepository;
	
	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------
	
	public CommentService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------
	
	//Other business methods -------------------------------------------------
 
}
