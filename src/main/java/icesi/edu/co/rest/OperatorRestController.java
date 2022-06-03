package icesi.edu.co.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OperatorRestController {

//	private DocumentDAOService documentDAOService;
//	
//	private TransactionHistoryDAOService transactionDAOService;
//	
//	@Autowired
//	public OperatorRestController(TransactionHistoryServiceImp thServiceImpl, DocumentDAOService documentDAOService,
//			TransactionHistoryDAOService transactionDAOService, DocumentServiceImp documentServiceImpl) {
//		this.documentDAOService = documentDAOService;
//		this.transactionDAOService = transactionDAOService;
//	}
//	
//	
//	//Transaccion
//	
//	@RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Transactionhistory> getTransaction(@PathVariable(value = "id") int id) {
//		Transactionhistory p = transactionDAOService.findByID(id);
//		return new ResponseEntity<Transactionhistory>(p, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
//	public ResponseEntity<Transactionhistory> listTransaction() {
//		List<Transactionhistory> entities = ((List<Transactionhistory>) transactionDAOService.findAll());
//		return new ResponseEntity(entities, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
//	public ResponseEntity<Transactionhistory> createProduct(@Validated(Information.class) @RequestBody Transactionhistory pe) {
//		transactionDAOService.save(pe);
//		return new ResponseEntity<Transactionhistory>(pe, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/transactions/{id}")
//	public ResponseEntity<Transactionhistory> updatePerson(@Validated(Information.class) @RequestBody Transactionhistory pe) {
//
//		//productService.(pe);
//		return ResponseEntity.ok(pe);
//	}
//	
//	//DOCUMENT
//	
//	@RequestMapping(value = "/documents/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Document> getDocument(@PathVariable(value = "id") int id) {
//		Document p = documentDAOService.findByOwner(id);
//		return new ResponseEntity<Document>(p, HttpStatus.OK);
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@RequestMapping(value = "/doucuments", method = RequestMethod.GET)
//	public ResponseEntity<Document> listDocument() {
//		List<Document> entities = ((List<Document>) documentDAOService.findAll());
//		return new ResponseEntity(entities, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/documents", method = RequestMethod.POST)
//	public ResponseEntity<Document> createDocument(@Validated(Information.class) @RequestBody Document pe) {
//		documentDAOService.save(pe);
//		return new ResponseEntity<Document>(pe, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/documents/{id}")
//	public ResponseEntity<Document> updateDocument(@Validated(Information.class) @RequestBody Document pe) {
//
//		//productService.(pe);
//		return ResponseEntity.ok(pe);
//	}
//	
	
	
}
