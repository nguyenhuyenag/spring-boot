package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vocab")
@NoArgsConstructor
public class Vocabulary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String word;
	private String mean;
	private String pronounce;
	private int count = 0;

	public Vocabulary(String word, String pronounce, String mean) {
		this.word = word;
		this.mean = mean;
		this.pronounce = pronounce;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	private boolean equal(String s1, String s2) {
		return s1.trim().equalsIgnoreCase(s2.trim());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vocabulary) {
			Vocabulary vcb = (Vocabulary) obj;
			if (equal(this.word, vcb.word) && equal(this.pronounce, vcb.pronounce) && equal(this.mean, vcb.mean)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.word.hashCode();
	}

}
