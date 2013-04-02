package com.amc.foodecalc.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.foodecalc.domain.Meal;
import com.amc.foodecalc.repository.RecordTableService;

@Controller
@RequestMapping(value = "/data")
public class DataController {

	private static final Logger logger = LoggerFactory.getLogger(DataController.class);
	
	@Resource(name = "RecordTableService")
	private RecordTableService recordTableService;
	
	/**
	 * Simply selects the get home view to render by returning its name.
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String dataGet(Locale locale, Model model) {
		
		
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<Meal> allRecords = recordTableService.getAll();
		System.out.println("allRecords Var is: " + allRecords);
		
		model.addAttribute("allRecords", allRecords );
		
		return "dataGet";
	}
}
