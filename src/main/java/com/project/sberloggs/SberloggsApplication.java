package com.project.sberloggs;

import com.project.sberloggs.model.InputParam;
import com.project.sberloggs.model.Log;
import com.project.sberloggs.parser.NewLog;
import com.project.sberloggs.parser.ReadRowsFromTXT;
import com.project.sberloggs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SberloggsApplication implements CommandLineRunner {

	private static InputParam inputParam = new InputParam();

	public static void setInputParam(String pattern, String path) {
		inputParam.setPattern(pattern) ;
		inputParam.setPath(path);
	}

	public static void main(String[] args) {
		SpringApplication.run(SberloggsApplication.class, args);
	}

	@Autowired
	private LogRepository logRepository;

	@Override
	public void run(String... args) throws Exception {
		inputParam.setPath("");
		inputParam.setPattern("");
		boolean f = true;
		while (f){
			if(!inputParam.getPath().isEmpty() && !inputParam.getPattern().isEmpty()){
				System.out.println("Path: " + inputParam.getPath());
				System.out.println("Pattern: " + inputParam.getPattern());
//				f = false;
				List<NewLog> loggs = new ArrayList<>();
				List<String> listRows = ReadRowsFromTXT.GetRowsFromFile(inputParam.getPath());
				loggs = NewLog.create(listRows, inputParam.getPattern());
				for (NewLog logg : loggs) {
					Log log = new Log();
					log.setDate(logg.getData());
					log.setLevel(logg.getLevel());
					log.setThread(logg.getThread());
					log.setLogger(logg.getLogger());
					if(logg.getMessage().length() < 250)
						log.setMessage(logg.getMessage());
					else
						log.setMessage(logg.getMessage().substring(0, 250));
					this.logRepository.save(log);
				}
				inputParam.setPath("");
				inputParam.setPattern("");
			}
		}
	}
}
