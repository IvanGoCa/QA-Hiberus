Feature: Validar el funcionamiento del carrito

  Scenario: Validar que un producto de la home se anade al carrito
    When anado el producto de feature "<product>" al carrito "1" veces
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "1" elementos

    Examples:
      | product            |
      | MacBook            |
      | iPhone             |
      | Apple Cinema 30\\" |
      | Canon EOS 5D       |

    Scenario: Validar que varios productos aleatorios de la home se anaden al carrito
      When anado "<num>" productos al carrito
      And hago click en el boton de carrito negro
      Then compruebo que en el carrito hay "<num>" elementos

      Examples:
        | num |
        | 2   |

    Scenario: Validar que el mismo producto de la home se anada varias veces al carrito
      When anado el producto de feature "<product>" al carrito "3" veces
      And hago click en el boton de carrito negro
      Then compruebo que en el carrito esta el elemento "<product>" "3" veces

      Examples:
        | product |
        | MacBook |
        | iPhone  |
#  Scenario: Añadir un item aleatorio desde la pantalla home
#  Scenario: Añadir varios items al carrito desde la pantalla home
#  Scenario: Añadir un item aleatorio desde su página
#  Scenario: Añadir varios items al carrito desde su página