package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.UUID;

class AssortmentApplicationServiceTest {
    private final AssortmentRepository assortmentRepository = Mockito.mock(AssortmentRepository.class);
    private final AssortmentApplicationService service = new AssortmentApplicationService(assortmentRepository);

    @Test
    void shouldAddProductToAssortment() {
        UUID sellerId = UUID.randomUUID();
        String productCode = new Faker().lorem().word();
        String productName = new Faker().lorem().word();
        AddProductCommand command = new AddProductCommand(sellerId, productCode, productName);
        Assortment assortment = givenExistingAssortmentFor(sellerId);

        service.addProduct(command);

        BDDMockito.then(assortmentRepository).should().save(assortment);
        // sprawdź czy produkt dodany
    }

    private Assortment givenExistingAssortmentFor(UUID sellerId) {
        Assortment assortment = new Assortment();
        BDDMockito.given(assortmentRepository.find(sellerId)).willReturn(assortment);
        return assortment;
    }
}
