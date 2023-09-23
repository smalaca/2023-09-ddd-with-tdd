package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.Assortment;
import com.smalaca.productmanagement.domain.assortment.AssortmentRepository;
import com.smalaca.productmanagement.domain.assortment.AssortmentTestFactory;
import com.smalaca.productmanagement.domain.assortment.ProductValidationService;
import net.datafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.UUID;

import static com.smalaca.productmanagement.domain.assortment.AssortmentAssertion.assertAssertion;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssortmentApplicationServiceTest {
    private static final Faker FAKER = new Faker();
    private static final boolean VALID = true;
    private static final boolean INVALID = false;

    private final AssortmentRepository assortmentRepository = Mockito.mock(AssortmentRepository.class);
    private final ProductValidationService productValidationService = Mockito.mock(ProductValidationService.class);
    private final AssortmentApplicationService service = new AssortmentApplicationService(
            assortmentRepository, productValidationService);

    @Test
    void shouldRecognizePriceIsNotGreaterThanZero() {
        UUID sellerId = randomSellerId();
        String productCode = givenValidProductCode();
        String productName = randomProductName();
        AddProductCommand command = new AddProductCommand(sellerId, productCode, productName, -13);
        givenExistingAssortmentFor(sellerId);

        Executable actual = () -> service.addProduct(command);

        RuntimeException actualException = assertThrows(RuntimeException.class, actual);
        Assertions.assertThat(actualException).hasMessage("Price -13 is invalid");
    }

    @Test
    void shouldRecognizeProductIsInvalid() {
        UUID sellerId = randomSellerId();
        String productCode = givenInvalidProductCode();
        String productName = randomProductName();
        AddProductCommand command = new AddProductCommand(sellerId, productCode, productName, 13);
        givenExistingAssortmentFor(sellerId);

        Executable actual = () -> service.addProduct(command);

        RuntimeException actualException = assertThrows(RuntimeException.class, actual);
        Assertions.assertThat(actualException).hasMessage("Product: " + productCode + " cannot be added.");
    }

    @Test
    void shouldAddProductToAssortment() {
        UUID sellerId = randomSellerId();
        String productCode = givenValidProductCode();
        String productName = randomProductName();
        AddProductCommand command = new AddProductCommand(sellerId, productCode, productName, 123);
        givenExistingAssortmentFor(sellerId);

        service.addProduct(command);

        assertAssertion(thenAssortmentSaved())
                .hasSellerId(sellerId)
                .containsProduct(productCode, productName, 123);
    }

    private String givenValidProductCode() {
        return givenProductCode(VALID);
    }

    private String givenInvalidProductCode() {
        return givenProductCode(INVALID);
    }

    private String givenProductCode(boolean isValid) {
        String productCode = randomProductCode();
        BDDMockito.given(productValidationService.isValid(productCode)).willReturn(isValid);
        return productCode;
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
        BDDMockito.given(assortmentRepository.find(sellerId)).willReturn(AssortmentTestFactory.create(sellerId));
    }
}
