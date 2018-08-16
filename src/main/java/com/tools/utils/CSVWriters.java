package com.tools.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tools.entities.ItemCsv;

public class CSVWriters {

	public static void writeCsv(Object object, String file) throws Exception {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(object.getClass()).withHeader();
		ObjectWriter myObjectWriter = mapper.writer(schema);

		File tempFile = new File(file);
		FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
		OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
		myObjectWriter.writeValue(writerOutputStream, object);
	}

	public static <T> void writeCsv(List<T> t, String file) throws Exception {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(t.get(0).getClass()).withHeader();
		System.out.println("Class is " + t.get(0).getClass());
		
		ObjectWriter myObjectWriter = mapper.writer(schema);

		File tempFile = new File(file);
		FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
		OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
		
		myObjectWriter.writeValue(writerOutputStream, t);
	}


}
