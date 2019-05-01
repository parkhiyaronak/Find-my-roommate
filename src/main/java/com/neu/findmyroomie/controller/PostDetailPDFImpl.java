package com.neu.findmyroomie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.findmyroomie.dao.CategoryDAO;
import com.neu.findmyroomie.exception.PostException;
import com.neu.findmyroomie.pojo.Post;


public class PostDetailPDFImpl extends AbstractPdfView{
	
	private CategoryDAO categoryDao;
	private String selectedCategory;
	public PostDetailPDFImpl(CategoryDAO categoryDao,String selectedCategory) {
		this.selectedCategory = selectedCategory;
		this.categoryDao = categoryDao;
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Font fontTitle = new Font(Font.TIMES_ROMAN, 23, Font.BOLD);
		Paragraph title = new Paragraph("List of all the Post", fontTitle );
		Font postTitleFont = new Font(Font.TIMES_ROMAN, 15, Font.BOLD);
		document.add(title);
		String cat = selectedCategory;
		try {
			List<Post> postList = categoryDao.postList(cat);
			for(Post p: postList) {
				 document.add(new Phrase("Title	"+p.getTitle(),postTitleFont)) ;
				 document.add( Chunk.NEWLINE );
				 document.add(new Phrase("Description"+p.getDescription()));
				 document.add( Chunk.NEWLINE );
				 document.add(new Phrase("Number of people Interested:"+String.valueOf(p.getInterestedCount())));
				 document.add( Chunk.NEWLINE );
				 document.add( Chunk.NEWLINE );
			}
			
		} catch (PostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
}
