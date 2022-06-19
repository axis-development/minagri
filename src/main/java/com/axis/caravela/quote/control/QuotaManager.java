package com.axis.caravela.quote.control;

import com.axis.caravela.quote.entity.Quote;
import com.axis.caravela.quote.entity.QuoteDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class QuotaManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Quote saveQuote(QuoteDTO dto) throws Exception {
        return null; //entityManager.merge(dto);
    }

}
