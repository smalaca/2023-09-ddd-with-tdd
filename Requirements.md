1. Dodawanie nowego produktu.
    Zmienia się: 
    - Produkt (id: Kod Produktu)        -> Encja
    - Asortyment (id: Kod Sprzedawcy)   -> Agregat
    Jak to zidentyfikować? 
    - Kod Produktu -> Produkt
    - Kod Sprzedawcy -> Asortyment
    Dane:
    - Nazwa Produktu
    - Cena -> musi być większa niż 0
    - Produkt musi być możliwy do sprzedaży -> ProductValidationService:REST:GET:validate




2. Wybór produktów z Koszyka
    Zmienia się: 
    - Zamówienie (id: Numer Zamówienia) -> Agregat
    Jak to zidentyfikować:
    - Numer Zamówienia -> Zamówienia
    - Id Kupującego -> Koszyk
    Dane:
    - Wybrane Produkty: Kod Produktu + Ilość
        - Ilość - musi być większa od zera
        - Wybrane Produkty były obecne w koszyku
    - Numer Zamówienia = String
    - Rezerwacja produktów (Kod Produktu + Ilość) -> ProductManagementService

3. Dodawanie produktów do Koszyka
    Zmienia się: Koszyk
    Jak to zidentyfikować:
    - Id Kupującego -> Koszyk
    Dane:
    - Kod Produktu
    - Ilość - musi być większa od zera

4. Usuwanie produktów z Koszyka
    Zmienia się: Koszyk
    Jak to zidentyfikować:
    - Id Kupującego -> Koszyk
    Dane:
    - Kod Produktu - Produkt musi istnieć w koszyku
    - Produkt Musi istnieje (Kod Produktu) -> ProductManagementService
