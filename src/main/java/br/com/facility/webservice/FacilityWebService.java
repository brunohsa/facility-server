package br.com.facility.webservice;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.FinanceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facility")
public class FacilityWebService {

	@RequestMapping(value ="/payments/types", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getPaymentTypes() {
		List<PaymentType> payments = Arrays.asList(PaymentType.values());

		List<String> paymentNames = payments.stream()
				.map(paymentType -> paymentType.getPaymentName())
				.collect(Collectors.toList());

		return new ResponseEntity(paymentNames, HttpStatus.OK);
	}

	@RequestMapping(value ="/finances/status", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity getStatusFinances() {
		List<FinanceStatus> statusFinances = Arrays.asList(FinanceStatus.values());

		List<String> statusNames = statusFinances.stream()
				.map(statusName -> statusName.getStatusName())
				.collect(Collectors.toList());

		return new ResponseEntity(statusNames, HttpStatus.OK);
	}
}
