package com.tools.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVWriters {

	public static void writeCsv(Object object, String file) throws Exception {
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema schema = CsvSchema.builder()
				   .addColumn("Name") 
				   .addColumn("Item tag")
				   .addColumn("Description")
				   .addColumn("Category")
				   .build().withHeader();
		
		ObjectWriter myObjectWriter = mapper.writer(schema);
		File tempFile = new File(file);
		FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
		OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
		myObjectWriter.writeValue(writerOutputStream, object);
	}

	public static <T> void writeCsv(List<T> t, String file) throws Exception {
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
//		CsvSchema schema = mapper.schemaFor(t.get(0).getClass()).withHeader();
		CsvSchema schema = CsvSchema.builder()
				   .addColumn("Name") 
				   .addColumn("Item tag")
				   .addColumn("Description")
				   .addColumn("Category")
				   .build().withHeader();
		
		ObjectWriter myObjectWriter = mapper.writer(schema);
		File tempFile = new File(file);
		FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
		OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
		myObjectWriter.writeValue(writerOutputStream, t);
	}


}
