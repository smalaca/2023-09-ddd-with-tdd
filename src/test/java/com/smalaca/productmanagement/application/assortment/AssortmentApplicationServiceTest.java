package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.UUID;

class AssortmentApplicationServiceTest {
    private static final Faker FAKER = new Faker();

    private final AssortmentRepository assortmentRepository = Mockito.mock(AssortmentRepository.class);
    private final AssortmentApplicationService service = new AssortmentApplicationService(assortmentRepository);

    @Test
    void shouldAddProductToAssortment() {
        UUID sellerId = randomSellerId();
        AddProductCommand command = new AddProductCommand(sellerId, randomProductCode(), randomProductName());
        givenExistingAssortmentFor(sellerId);

        service.addProduct(command);

        Assortment actual = thenAssortmentSaved();
        // sprawdź czy produkt dodany
    }

    private Assortment thenAssortmentSaved() {
        ArgumentCaptor<Assortment> captor = ArgumentCaptor.forClass(Assortment.class);
        BDDMockito.then(assortmentRepository).should().save(captor.capture());
        return captor.getValue();
    }

    private String randomProductName() {
        return FAKER.lorem().word();
    }

    private String randomProductCode() {
        return FAKER.lorem().word();
    }

    private UUID randomSellerId() {
        return UUID.randomUUID();
    }

    private void givenExistingAssortmentFor(UUID sellerId) {
        BDDMockito.given(assortmentRepository.find(sellerId)).willReturn(new Assortment());
    }
}
