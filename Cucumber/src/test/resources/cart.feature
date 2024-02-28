Feature: Validacion funcionalidad carrito

  Background:
    Given me encuentro en la pagina de login
    When realizo el login con el user "standard_user" y el pass "secret_sauce"

  Scenario: Validar eliminar producto desde el carrito
    And anado al carrito "2" productos
    And hago click en el boton del carrito
    And elimino un producto de la lista
    Then valido que desaparece de la lista