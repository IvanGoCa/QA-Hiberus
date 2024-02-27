Feature: Validacion funcionalidad inventario

  Background:
    Given me encuentro en la pagina de login
    When realizo el login con el user "standard_user" y el pass "secret_sauce"
    Then me encuentro en la home de la app

  Scenario: Validar el numero de resultados
    Given me encuentro en la pagina de inventario
    Then valido que el numero de productos mostrados es igual a "6"

  Scenario: Validar la existencia de un producto
    Given me encuentro en la pagina de inventario
    Then valido que el producto "<product_name>" esta en el inventario

    Examples:
    |product_name             |
    |Sauce Labs Bolt T-Shirt  |
    |Sauce Labs Fleece Jacket |
    |Sauce Labs Onesie        |

  Scenario: Validar que un producto se anade al carrito
    Given me encuentro en la pagina de inventario
    When anado al carrito el producto "<product_name>"
    Then valido que hay "1" productos en el carrito

    Examples:
    |product_name             |
    |Sauce Labs Bolt T-Shirt  |
    |Sauce Labs Fleece Jacket |
    |Sauce Labs Onesie        |

  Scenario: Validar eliminar producto del carrito
    Given me encuentro en la pagina de inventario
    When anado al carrito el producto "<product_name>"
    And elimino el producto "<product_name>"
    Then valido que el producto no se encuentra en el carrito

    Examples:
    |product_name             |
    |Sauce Labs Bolt T-Shirt  |

  Scenario: Validar agregar varios productos al carrito
    Given me encuentro en la pagina de inventario
    When anado al carrito "3" productos
    Then valido que hay "3" productos en el carrito

  Scenario: Validar ordenar de Z-A
    Given me encuentro en la pagina de inventario
    When selecciono el filtro Z-A
    Then valido que los resultados van en orden inverso al alfabeto

  Scenario: Validar ordenar por precio de Menor a Mayor
    Given me encuentro en la pagina de inventario
    When selecciono el filtro Menor a Mayor
    Then valido que los resultados van de precio menor a mayor

  Scenario: Validar ordenar por precio de Mayor a Menor
    Given me encuentro en la pagina de inventario
    When selecciono el filtro Mayor a Menor
    Then valido que los resultados van de precio mayor a menor

  Scenario: Validar boton de carrito
    Given me encuentro en la pagina de inventario
    When hago click en el boton del carrito
#    Then me encuentro en la pagina del carrito