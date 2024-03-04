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

  Scenario: Validar que un producto se añade al carrito
    When anado el producto "<product>" al carrito
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "1" elementos

    Examples:
      | product            |
      | MacBook            |
      | iPhone             |
      | Apple Cinema 30\\" |
      | Canon EOS 5D       |

  Scenario: Validar que varios productos aleatorios se añaden al carrito
    When anado "<num>" productos al carrito
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "<num>" elementos

    Examples:
      | num |
      | 2   |

  Scenario: Validar que el mismo producto se añada varias veces al carrito
    When anado el producto "<product>" al carrito
    And anado el producto "<product>" al carrito
    And anado el producto "<product>" al carrito
    Then compruebo que en el carrito hay "3" elementos
    
    Examples:
      | product |
      | MacBook |
#      | iPhone  |

#  Scenario: Validar que un producto se añade a la whishlist
#  Scenario: Validar que varios productos diferentes se añaden a la whishlist
#  Scenario: Validar que el mismo producto se añade varias veces a la whislist
#  Scenario: Validar que un producto se añade a la comparación de productos
#  Scenario: Validar que todos los productos se añaden a la comparación de productos