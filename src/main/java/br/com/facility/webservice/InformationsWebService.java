package br.com.facility.webservice;

import br.com.facility.model.enuns.PaymentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/infos")
public class InformationsWebService {

	@RequestMapping(value = "/getpaymentnames", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getPaymentTypes() {
		List<PaymentType> payments = Arrays.asList(PaymentType.values());

		List<String> paymentNames = new ArrayList<>();
		payments.forEach(paymentType -> paymentNames.add(paymentType.getPaymentName()));

		return new ResponseEntity(paymentNames, HttpStatus.OK);
	}
}
