package com.axis.caravela.quote.boundary;

import com.axis.caravela.commons.control.Conversions;
import com.axis.caravela.quote.control.QuotaManager;
import com.axis.caravela.quote.entity.QuoteDTO;
import com.axis.caravela.customer.control.CustomerManager;
import com.axis.caravela.customer.entity.CustomerDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
public class QuoteController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private QuotaManager quotaManager;

    @Autowired
    private CustomerManager customerManager;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @RequestMapping(method = RequestMethod.POST, value = "/quote/saveQuote")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveQuote(@RequestBody QuoteDTO quoteDTO) {
        try {
            quotaManager.saveQuote(quoteDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(quoteDTO);
        }
        catch(Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quote/fetchCustomer")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity fetchCustomer() {
        HttpSession session = httpSessionFactory.getObject();
        Object customerId = session.getAttribute("customerId");
        CustomerDTO customer = null;
         if(customerId != null) {
             customer =  customerManager.findCustomerById(Conversions.toLong((String) customerId));
         }
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/quote/getAllCustomers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return customerManager.findAllCustomers();
    }
}
