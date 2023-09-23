1. Dodawanie nowego produktu.
    Zmienia się: 
    - Produkt (id: Kod Produktu)        -> Encja
    - Asortyment (id: Kod Sprzedawcy)   -> Agregat
    Jak to zidentyfikować? 
    - Kod Produktu -> Produkt
    - Kod Sprzedawcy -> Asortyment
    Dane:
    - Nazwa Produktu




2. Wybór produktów z Koszyka
    Zmienia się: 
    - Zamówienie (id: Numer Zamówienia) -> Agregat
    Jak to zidentyfikować:
    - Numer Zamówienia -> Zamówienia
    - Id Kupującego -> Koszyk
    Dane:
    - Wybrane Produkty: Kod Produktu + Ilość
    - Numer Zamówienia = String

3. Dodawanie produktów do Koszyka
    Zmienia się: Koszyk
    Jak to zidentyfikować:
    - Id Kupującego -> Koszyk
    Dane:
    - Kod Produktu
    - Ilość

4. Usuwanie produktów z Koszyka
    Zmienia się: Koszyk
    Jak to zidentyfikować:
    - Id Kupującego -> Koszyk
    Dane:
    - Kod Produktu
