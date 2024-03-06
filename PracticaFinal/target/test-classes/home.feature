Feature: Verificar el funcionamiento de los productos destacados

  Background:
    Given me encuentro en la pagina home

  Scenario: Validar el número de resultados
    Then en el apartado featured hay "4" productos

  Scenario: Validar la existencia de un producto
    Then compruebo que el producto "<product>" existe

    Examples:
      | product            |
      | MacBook            |
      | iPhone             |
      | Apple Cinema 30\\" |
      | Canon EOS 5D       |