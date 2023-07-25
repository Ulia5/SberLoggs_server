package com.project.sberloggs.controllers;

import com.project.sberloggs.SberloggsApplication;
import com.project.sberloggs.model.Log;
import com.project.sberloggs.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class AppController {

    @Autowired
    private LogRepository logRepository;

    private String sortParam = "";
    private String dateFiltr = "";
    private String levelFiltr = "";

    @GetMapping("loggs")
    public List<Log> getLoggs(){
        if(Objects.equals(sortParam, "date")){
            sortParam = "";
            return this.logRepository.findAll(Sort.by("date"));
        } else if(Objects.equals(sortParam, "level")){
            sortParam = "";
            return this.logRepository.findAll(Sort.by("level"));
        } else if(Objects.equals(sortParam, "thread")){
            sortParam = "";
            return this.logRepository.findAll(Sort.by("thread"));
        } else if(Objects.equals(sortParam, "logger")){
            sortParam = "";
            return this.logRepository.findAll(Sort.by("logger"));
        } else if(Objects.equals(sortParam, "message")){
            sortParam = "";
            return this.logRepository.findAll(Sort.by("message"));
        } else if(!levelFiltr.isEmpty()){
            String level = levelFiltr;
            levelFiltr = "";
            return this.logRepository.findByLevel(level);
        } else if(!dateFiltr.isEmpty()){
            String date = dateFiltr;
            dateFiltr = "";
            return this.logRepository.findByDateIsContaining(date);
        }
        return this.logRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces =
            "application/json", value = "upload")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void getVistaConnection(@RequestBody String pattern) {
        String[] parts = pattern.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
        if(parts.length >= 4) {
            String pat = parts[1];
            String path = "C:" + parts[4];
            path = path.substring(0, path.length() - 1);
            SberloggsApplication.setInputParam(pat, path);
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces =
            "application/json", value = "sort")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void getSortParam(@RequestBody String pattern) {
        String[] parts = pattern.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
        if(parts.length >= 1) {
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            sortParam = parts[1];
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces =
            "application/json", value = "date")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void getFilterDate(@RequestBody String datef) {
        String[] parts = datef.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
        if(parts.length >= 1) {
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            dateFiltr = parts[1];
        }
    }
    @RequestMapping(method = RequestMethod.POST, produces =
            "application/json", value = "level")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void getFilterLevel(@RequestBody String levelf) {
        String[] parts = levelf.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
        if(parts.length >= 1) {
            parts[1] = parts[1].substring(0, parts[1].length() - 1);
            levelFiltr = parts[1];
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces =
            "application/json", value = "deleteData")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteData() {
        this.logRepository.deleteAll();
    }
}
