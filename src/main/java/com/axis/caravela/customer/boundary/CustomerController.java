package com.axis.caravela.customer.boundary;

import com.axis.caravela.customer.control.CustomerManager;
import com.axis.caravela.customer.entity.Customer;
import com.axis.caravela.customer.entity.CustomerDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private CustomerManager customerManager;

    @RequestMapping(method = RequestMethod.POST, value = "/customer/saveCustomer")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer customer = customerManager.saveCustomer(customerDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(customer.getId());
        }
        catch(Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/fetchCustomers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDTO> fetchCustomers() {
        return customerManager.findAllCustomers();
    }


}
