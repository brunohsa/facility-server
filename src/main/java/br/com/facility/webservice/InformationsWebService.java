package br.com.facility.webservice;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/infos")
public class InformationsWebService {

	@RequestMapping(value = "/getpaymentnames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getPaymentTypes() {
		List<PaymentType> payments = Arrays.asList(PaymentType.values());

		List<String> paymentNames = new ArrayList<>();
		payments.forEach(paymentType -> paymentNames.add(Messages.getMessage(paymentType.getPaymentName())));

		return new ResponseEntity(paymentNames, HttpStatus.OK);
	}
}
