package br.com.facility.webservice;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import br.com.facility.util.Messages;
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
@RequestMapping("/facility")
public class FacilityWebService {

	@RequestMapping(value ="/payments/types", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getPaymentTypes() {
		List<PaymentType> payments = Arrays.asList(PaymentType.values());

		List<String> paymentNames = new ArrayList<>();
		payments.forEach(paymentType -> paymentNames.add(Messages.getMessage(paymentType.getPaymentName())));

		return new ResponseEntity(paymentNames, HttpStatus.OK);
	}

	@RequestMapping(value ="/status/finances", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getStatusFinances() {
		List<StatusFinance> statusFinances = Arrays.asList(StatusFinance.values());

		List<String> statusNames = new ArrayList<>();
		statusFinances.forEach(statusName -> statusNames.add(Messages.getMessage(statusName.getStatusName())));

		return new ResponseEntity(statusNames, HttpStatus.OK);
	}
}
