package com.neu.findmyroomie.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryId;
	private String categoryName;
	private String categoryDescript;
	
	@OneToMany(mappedBy="category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Post> post;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescript() {
		return categoryDescript;
	}

	public void setCategoryDescript(String categoryDescript) {
		this.categoryDescript = categoryDescript;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}
	
	
}
