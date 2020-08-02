package net.confirmo.appexample.db;

import net.confirmo.appexample.model.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {

}
