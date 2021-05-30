package com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.entity.Vocabulary;

public interface VocabService {

	Vocabulary getRandomVocab(String flag);

	Vocabulary getRandomVocab2(String flag);

	void increaseCountById(Vocabulary vocab);
	
	List<String> incomplete();
	
	Vocabulary search(String word);

	List<Vocabulary> incompleteVocabulary();
	
	List<Vocabulary> findAll();
	
	Page<Vocabulary> pagination(int page);
	
	void deleteByWord(String word);
	
	Vocabulary update(Vocabulary v);

}
