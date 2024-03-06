Feature: Validar el funcionamiento del carrito

  Background:
    Given me encuentro en la pagina home

  Scenario: Validar que un producto de la home se anade al carrito
    When anado el producto de feature "<product>" al carrito "1" veces
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "1" elementos

    Examples:
      | product            |
      | MacBook            |
      | iPhone             |

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
      
    Scenario: Validar eliminar un producto del carrito negro
      When anado el producto de feature "<product1>" al carrito "1" veces
      And anado el producto de feature "<product2>" al carrito "1" veces
      And hago click en el boton de carrito negro
      #And elimino uno de ellos
      Then compruebo que en el carrito hay "2" elementos
      
      Examples:
        | product1 | product2 |
        | MacBook  | iPhone   |
        #| iPhone   | MacBook  |