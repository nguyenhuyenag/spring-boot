package com.service.impl;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.XSSFService;
import com.util.PathUtils;

@Service
public class XSSFServiceImpl implements XSSFService {

	@Autowired
	private VocabRepository repository;

	private final Path FILE = Paths.get("D:/GDrive/ToCompany/english/vocabulary.xlsx");

	private String getCellValue(XSSFRow row, int i) {
		if (row == null) {
			return "";
		}
		try {
			XSSFCell cell = row.getCell(i);
			return cell != null ? cell.getStringCellValue().trim().toLowerCase() : "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private Vocabulary rowToVocab(XSSFRow row) {
		String word = getCellValue(row, 0);
		String pronounce = getCellValue(row, 1);
		String translate = getCellValue(row, 2);
		if (StringUtils.isNotEmpty(word) && StringUtils.isNotEmpty(translate)) {
			return new Vocabulary(word, pronounce, translate);
		}
		return null;
	}

	@Override
	public List<String> importExcel() {
		int size, count = 0;
		List<String> msg = new ArrayList<>();
		if (!PathUtils.exists(FILE)) {
			msg.add("File not found!");
			return msg;
		}
		try ( //
			FileInputStream excelFile = new FileInputStream(FILE.toFile()); //
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile); //
		) {
			Iterator<Sheet> itr = workbook.sheetIterator();
			while (itr.hasNext()) {
				String sheetName = itr.next().getSheetName();
				char c = sheetName.charAt(0);
				if (sheetName.length() == 1 && ('A' <= c || c <= 'Z')) {
					List<Vocabulary> listVocab = new ArrayList<>();
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					// XSSFSheet worksheet = workbook.getSheetAt(16);
					for (int i = 0; i <= worksheet.getLastRowNum(); i++) {
						XSSFRow row = worksheet.getRow(i);
						if (row != null) {
							Vocabulary entity = rowToVocab(row);							// get new word
							if(entity != null) {
								Vocabulary vcb = repository.findByWord(entity.getWord());	// get word from db
								if (vcb == null) { 				// chưa có
									listVocab.add(entity); 		// thêm mới
								} else { 						// đã có
									if (!vcb.equals(entity)) { 	// so sánh để update
										vcb.setPronounce(entity.getPronounce());
										vcb.setTranslate(entity.getTranslate());
										// BeanUtils.copyProperties(entity, vcb, "id, word, count");
										repository.save(vcb);
										msg.add("Update: " + vcb.getWord());
									}
								}
							}
						}
					}
					size = listVocab.size();
					if (size > 0) {
						repository.saveAll(listVocab);
						count += size;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count > 0) {
			msg.add("Add new " + count + " word");
		}
		if (msg.size() == 0) {
			msg.add("No change!");
		}
		return msg;
	}

}
