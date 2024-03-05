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

  Scenario: Validar que un producto se anade al carrito
    When anado el producto "<product>" al carrito
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "1" elementos

    Examples:
      | product            |
      | MacBook            |
      | iPhone             |
      | Apple Cinema 30\\" |
      | Canon EOS 5D       |

  Scenario: Validar que varios productos aleatorios se anaden al carrito
    When anado "<num>" productos al carrito
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito hay "<num>" elementos

    Examples:
      | num |
      | 2   |

  Scenario: Validar que el mismo producto se anada varias veces al carrito
    When anado el producto "<product>" al carrito
    And anado el producto "<product>" al carrito
    And anado el producto "<product>" al carrito
    And hago click en el boton de carrito negro
    Then compruebo que en el carrito esta el elemento "<product>" "3" veces
    
    Examples:
      | product |
      | MacBook |
      | iPhone  |

#  Scenario: Validar que un producto se anade a la whishlist
#    When anado el producto "<product>" a la whislist
#    Then compruebo que en la whislist hay "1" elementos
#
#    Examples:
#      | product |
#      | MacBook |
#      | iPhone  |

#  Scenario: Validar que varios productos aleatorios se anaden a la whishlist
#    When anado "3" productos a la whislist
#    Then compruebo que en la whislist hay "3" elementos

#  Scenario: Validar que el mismo producto no se añade varias veces a la whislist
#    When anado el producto "<product>" a la whislist
#    And anado el producto "<product>" a la whislist
#    Then compruebo que en la whislist hay "1" elemento

#    Examples:
#      | product |
#      | MacBook |
#      | iPhone  |

#  Scenario: Validar que un producto se añade a la comparacion de productos
#    When anadno el producto "<product>" a la comparacion de productos
#    Then se muestra el mensaje "Success: You have added <product> to your product comparison!"
#    Examples:
#      | product |
#      | MacBook |
#      | iPhone  |