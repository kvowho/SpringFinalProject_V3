package net.voznjuk.fp.repos;

import net.voznjuk.fp.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepo extends CrudRepository<Invoice, Long> {
    List<Invoice> findByStatus(String status);
}
