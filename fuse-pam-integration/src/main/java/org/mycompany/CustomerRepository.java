package org.mycompany;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mycompany.exception.CustomerExistsException;
import org.mycompany.exception.CustomerNotFoundException;

/**
 * Very simple impl of a customer repository.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class CustomerRepository {

    private Map<String, Map<String, Object>> customers = new ConcurrentHashMap<>();
    
    public Map getCustomer(String id) {
        return customers.get(id);
    }

    public void addCustomer(Map<String, Object> payload) {
        String id = (String) payload.get("id");
        if (customers.containsKey(id)) {
            throw new CustomerExistsException();
        }
        customers.put(id, payload);
    }
    
    public void updateCustomer(String id, Map<String, Object> payload) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException();
        }
        customers.put(id, payload);
    }
    

    public void removeCustomer(String id) {
        customers.remove(id);
    }
}
